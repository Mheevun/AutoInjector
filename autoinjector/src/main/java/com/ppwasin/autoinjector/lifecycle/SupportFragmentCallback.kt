package com.ppwasin.autoinjector.lifecycle

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import javax.inject.Inject

/**
 * Created by cnr on 12/2/2017.
 */
class SupportFragmentCallback @Inject constructor() : ILifecycleCallback<FragmentManager, Fragment, FragmentManager.FragmentLifecycleCallbacks> {
    override fun register(target: FragmentManager, callback: FragmentManager.FragmentLifecycleCallbacks) {
        target.registerFragmentLifecycleCallbacks(callback, true)
    }

    override fun unRegister(target: FragmentManager, callback: FragmentManager.FragmentLifecycleCallbacks) {
        target.unregisterFragmentLifecycleCallbacks(callback)
    }

    override fun createCallback(callbackFunc: (Fragment) -> Unit): FragmentManager.FragmentLifecycleCallbacks {
        return object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentAttached(fm: FragmentManager?, fragment: Fragment, context: Context?) {
                callbackFunc(fragment)
            }
//            override fun onFragmentCreated(fm: FragmentManager?, fragment: Fragment, savedInstanceState: Bundle?) {
//                callbackFunc(fragment)
//            }
        }
    }

}