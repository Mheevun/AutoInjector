package com.ppwasin.autoinjector.integrationtest.lifecycle

import android.app.Application
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ppwasin.autoinjector.integrationtest.EmptyActivity
import com.ppwasin.autoinjector.integrationtest.RelayLifecycleFragment
import com.ppwasin.autoinjector.integrationtest.helper.UiTestHelper
import com.ppwasin.autoinjector.lifecycle.ActivityCallback
import com.ppwasin.autoinjector.lifecycle.CallbackCreator
import com.ppwasin.autoinjector.lifecycle.SupportFragmentCallback
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * ensure observability of activity/fragment lifecycle
 */
@RunWith(AndroidJUnit4::class)
class CallbackCreatorTest {
    private lateinit var target: CallbackCreator
    @get:Rule
    var activityTestRule = ActivityTestRule(EmptyActivity::class.java, false, false)
    private val app = InstrumentationRegistry.getTargetContext().applicationContext as Application

    @Before
    fun setup() {
//        val app = InstrumentationRegistry.getInstrumentation().targetContext as Application
        target = CallbackCreator()
    }

    @Test
    fun should_get_activity_when_launch() {
        val activityCallback = ActivityCallback()
        val testObserver = target.create(app, activityCallback).test()

        activityTestRule.launchActivity(null)
        testObserver.assertValue { it == activityTestRule.activity }
    }

    @Test
    fun should_get_fragment_on_activity_created() {
        val fragmentCallback = SupportFragmentCallback()
        activityTestRule.launchActivity(null)
        val activity = activityTestRule.activity
        val testObserver = target.create(activity.supportFragmentManager, fragmentCallback).test()

        val fragment = RelayLifecycleFragment()
        UiTestHelper.addFragment(activity, fragment)

//        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        fragment.oberve().test().awaitTerminalEvent()
        testObserver.assertValue { it == fragment }
    }
}