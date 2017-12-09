package com.ppwasin.autoinjector

import android.app.Application
import android.support.v4.app.FragmentActivity
import com.ppwasin.autoinjector.executor.IExecutor
import com.ppwasin.autoinjector.ext.into
import com.ppwasin.autoinjector.lifecycle.ActivityCallback
import com.ppwasin.autoinjector.lifecycle.CallbackCreator
import com.ppwasin.autoinjector.lifecycle.SupportFragmentCallback
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by cnr on 12/1/2017.
 */
class Executor @Inject constructor(
        private val activityCallback: ActivityCallback,
        private val supportFragmentCallback: SupportFragmentCallback,
        private val callbackCreator: CallbackCreator) {
    private val disposables = CompositeDisposable()
    fun init(app: Application, executors:List<IExecutor>) {
            callbackCreator.create(app, activityCallback)
                    .doOnNext { execute(it, executors) }
                    .flatMap {
                        if(it is FragmentActivity) callbackCreator.create(it.supportFragmentManager, supportFragmentCallback)
                        else Observable.empty()
                    }
                    .doOnNext { execute(it, executors) }
                    .subscribe() into disposables
    }

    private fun execute(event:Any, executors:List<IExecutor>){
        executors.forEach {
            it.execute(event)
        }
    }

    fun clear() {
        disposables.clear()
    }
}