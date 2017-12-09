package com.ppwasin.autoinjector.integrationtest.injector

import android.support.v4.app.Fragment
import com.ppwasin.autoinjector.executor.Injectable
import javax.inject.Inject

class InjectedFragment :Fragment(), Injectable {
    @Inject
    lateinit var injectedData: InjectedData
}