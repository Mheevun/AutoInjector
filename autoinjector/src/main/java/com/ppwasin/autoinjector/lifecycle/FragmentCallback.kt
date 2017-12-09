package com.ppwasin.autoinjector.lifecycle

import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import javax.inject.Inject

/**
 * Created by cnr on 12/7/2017.
 */
class FragmentCallback @Inject constructor() : ILifecycleCallback<FragmentManager, Fragment, FragmentManager.FragmentLifecycleCallbacks> {
    override fun createCallback(callbackFunc: (Fragment) -> Unit): FragmentManager.FragmentLifecycleCallbacks {
        return object :FragmentManager.FragmentLifecycleCallbacks(){
            override fun onFragmentAttached(fm: FragmentManager?, f: Fragment, context: Context?) {
                callbackFunc(f)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun register(target: FragmentManager, callback: FragmentManager.FragmentLifecycleCallbacks) {
        target.registerFragmentLifecycleCallbacks(callback,true)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun unRegister(target: FragmentManager, callback: FragmentManager.FragmentLifecycleCallbacks) {
        target.unregisterFragmentLifecycleCallbacks(callback)
    }


}