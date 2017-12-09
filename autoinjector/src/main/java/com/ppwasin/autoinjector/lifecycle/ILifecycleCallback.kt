package com.ppwasin.autoinjector.lifecycle

/**
 * Created by cnr on 12/2/2017.
 */
interface ILifecycleCallback<in Target, out Event, Callback> {
    fun createCallback(callbackFunc: (Event)->Unit):Callback
    fun register(target:Target, callback: Callback)
    fun unRegister(target:Target, callback: Callback)
}