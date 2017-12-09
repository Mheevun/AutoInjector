package com.ppwasin.autoinjector.integrationtest.injector

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ppwasin.autoinjector.executor.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * For testing whether injected instance is valid
 */
class InjectedActivity:AppCompatActivity(), HasSupportFragmentInjector,Injectable {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var injectedData: InjectedData
}