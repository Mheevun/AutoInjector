package com.ppwasin.autoinjector.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import javax.inject.Inject

/**
 * interest only onCreate event
 */
class ActivityCallback @Inject constructor() : ILifecycleCallback<Application, Activity, Application.ActivityLifecycleCallbacks> {
    override fun register(target: Application, callback: Application.ActivityLifecycleCallbacks) {
        target.registerActivityLifecycleCallbacks(callback)
    }

    override fun unRegister(target: Application, callback: Application.ActivityLifecycleCallbacks) {
        target.unregisterActivityLifecycleCallbacks(callback)
    }

    override fun createCallback(callbackFunc: (Activity) -> Unit): Application.ActivityLifecycleCallbacks {
        return object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) = Unit
            override fun onActivityResumed(activity: Activity) = Unit
            override fun onActivityStarted(activity: Activity) = Unit
            override fun onActivityDestroyed(activity: Activity) = Unit
            override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle?) = Unit
            override fun onActivityStopped(activity: Activity) = Unit
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                callbackFunc(activity)
            }
        }
    }
}