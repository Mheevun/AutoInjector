package com.ppwasin.autoinjector.executor

import android.app.Activity
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

/**
 * Created by cnr on 12/9/2017.
 */
class DaggerInjector : IExecutor {
    override fun execute(event: Any) {
        if (event is Injectable)
            when (event) {
                is Activity -> AndroidInjection.inject(event)
                is Fragment -> AndroidSupportInjection.inject(event)
                is android.app.Fragment -> AndroidInjection.inject(event)
            }
    }
}