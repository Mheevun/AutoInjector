package com.ppwasin.autoinjector.integrationtest

import android.os.Bundle
import android.support.v4.app.Fragment
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by cnr on 12/2/2017.
 */
class RelayLifecycleFragment :Fragment() {
    private val lifecycleRelay = BehaviorSubject.create<Unit>()
    fun oberve(): Observable<Unit> {
        return lifecycleRelay.hide()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleRelay.onComplete()
    }
}