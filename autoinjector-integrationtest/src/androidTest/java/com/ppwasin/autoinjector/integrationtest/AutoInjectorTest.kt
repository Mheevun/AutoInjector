package com.ppwasin.autoinjector.integrationtest

import android.app.Application
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ppwasin.autoinjector.AutoInjector
import com.ppwasin.autoinjector.integrationtest.helper.UiTestHelper
import com.ppwasin.autoinjector.integrationtest.injector.InjectedActivity
import com.ppwasin.autoinjector.integrationtest.injector.InjectedFragment
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by cnr on 12/5/2017.
 */
@RunWith(AndroidJUnit4::class)
class AutoInjectorTest {
    @get:Rule
    var activityTestRule = ActivityTestRule(InjectedActivity::class.java, false, false)
    private val app = InstrumentationRegistry.getTargetContext().applicationContext as Application

//    private lateinit var target: Executor

    @Before
    fun setup(){
//        target = Executor(ActivityCallback(), SupportFragmentCallback(), FragmentCallback(), CallbackCreator())
//        target.init(app)
        AutoInjector.init(app)
        activityTestRule.launchActivity(null)
    }

    @After
    fun clear(){
//        target.clear()
        AutoInjector.clear()
    }

    @Test
    fun could_injected_data_on_actvity() {
        val injectedData = activityTestRule.activity.injectedData
        assertThat(injectedData).isNotNull()
    }

    @Test
    fun could_injected_data_on_fragment(){
        val activity = activityTestRule.activity
        val fragment = InjectedFragment()
        UiTestHelper.addFragment(activity, fragment)

        val injectedData = fragment.injectedData
        assertThat(injectedData).isNotNull()
    }
}