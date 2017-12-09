package com.ppwasin.autoinjector.integrationtest

import com.ppwasin.autoinjector.integrationtest.di.DaggerTestComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TestApp: DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerTestComponent.builder().build()
    }
}