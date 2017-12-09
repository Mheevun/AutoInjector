package com.ppwasin.autoinjector.integrationtest

import android.app.Application
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ppwasin.autoinjector.AutoExecutor
import com.ppwasin.autoinjector.executor.IExecutor
import com.ppwasin.autoinjector.integrationtest.injector.InjectedActivity
import com.ppwasin.autoinjector.integrationtest.injector.InjectedData
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AutoExecutorExtTest {
    @get:Rule
    var activityTestRule = ActivityTestRule(InjectedActivity::class.java, false, false)
    private val app = InstrumentationRegistry.getTargetContext().applicationContext as Application

    @Test
    fun could_execute_follow_with_custome_executor(){
        val customExecutor = object :IExecutor{
            var isExecute:Boolean = false
            var injectedData:InjectedData? = null
            override fun execute(event: Any) {
                isExecute = true
                if (event is InjectedActivity)
                    injectedData = event.injectedData
            }
        }
        AutoExecutor.init(app, customExecutor)
        activityTestRule.launchActivity(null)

        val injectedData = activityTestRule.activity.injectedData
        assertThat(injectedData).isNotNull()

        val customExecutorData = customExecutor.injectedData
        assertThat(customExecutorData).isNotNull()

        AutoExecutor.clear()
    }
}