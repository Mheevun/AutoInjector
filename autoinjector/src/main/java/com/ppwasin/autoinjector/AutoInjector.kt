package com.ppwasin.autoinjector

import android.app.Application
import com.ppwasin.autoinjector.executor.DaggerInjector
import com.ppwasin.autoinjector.executor.IExecutor
import com.ppwasin.autoinjector.lifecycle.ActivityCallback
import com.ppwasin.autoinjector.lifecycle.CallbackCreator
import com.ppwasin.autoinjector.lifecycle.SupportFragmentCallback

/**
 * Created by cnr on 12/7/2017.
 */
object AutoInjector {
    private val lazyExecutor: Executor by lazy{
        Executor(ActivityCallback(), SupportFragmentCallback(), CallbackCreator())
    }
    fun init(app: Application, vararg  followupExecutor:IExecutor){
        val executors:List<IExecutor> = listOf(DaggerInjector()) + followupExecutor
        lazyExecutor.init(app, executors)
    }
    fun clear(){
        lazyExecutor.clear()
    }
}