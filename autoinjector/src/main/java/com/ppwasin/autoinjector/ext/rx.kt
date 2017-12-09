package com.ppwasin.autoinjector.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by cnr on 12/5/2017.
 */
infix fun Disposable?.into(compositeDisposable: CompositeDisposable){
    if(this != null)
        compositeDisposable.add(this)
}