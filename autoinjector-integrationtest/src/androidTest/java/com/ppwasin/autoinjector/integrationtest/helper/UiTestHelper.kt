package com.ppwasin.autoinjector.integrationtest.helper

import android.content.res.Resources
import android.support.test.InstrumentationRegistry
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * Created by cnr on 12/5/2017.
 */
object UiTestHelper {
    fun addFragment(activity:FragmentActivity, fragment: Fragment){
        activity.supportFragmentManager.beginTransaction()
                .add(fragment, "TEST")
                .commit()
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    /*Sample usage: getResource().getString(R.string.test)*/
    fun getResource(): Resources {
        return InstrumentationRegistry.getTargetContext().resources
    }
//
//    fun isTablet(): Boolean {
//        return getResource().getBoolean(R.bool.isTablet)
//    }
//    fun isPhone():Boolean{
//        return !getResource().getBoolean(R.bool.isTablet)
//    }
}