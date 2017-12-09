package com.ppwasin.autoinjector.lifecycle

import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by cnr on 12/2/2017.
 */
class CallbackCreator @Inject constructor(){
    fun <Target, Data, Callback> create(target:Target, callbackI: ILifecycleCallback<Target, Data, Callback>): Observable<Data> {
        return Observable.create<Data>{ emitter ->
            val callback = callbackI.createCallback(emitter::onNext)
            callbackI.register(target, callback)
            emitter.setCancellable { callbackI.unRegister(target, callback) }
        }
    }
}