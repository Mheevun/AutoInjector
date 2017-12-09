package com.ppwasin.autoinjector.integrationtest

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner


/**
 * Custom Test Runner for specify custom application
 */
class TestRunner:AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
//        return Instrumentation.newApplication(TestApp::class.java, context)
    }

//    override fun onStart() {
//
//        runOnMainSync {
//            val app = targetContext.applicationContext
//            val name = TestRunner::class.java.simpleName
//            unlockScreen(app, name);
//            keepSceenAwake(app, name)
//        }
//
//        super.onStart()
//    }
//
//    private fun keepSceenAwake(app: Context, name: String) {
//        val power = app.getSystemService(Context.POWER_SERVICE) as PowerManager
//        power.newWakeLock(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.ON_AFTER_RELEASE, name)
//                .acquire()
//    }
//
//    private fun unlockScreen(app: Context, name: String) {
//        val keyguard = app.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
//        keyguard.newKeyguardLock(name).disableKeyguard()
//    }


}