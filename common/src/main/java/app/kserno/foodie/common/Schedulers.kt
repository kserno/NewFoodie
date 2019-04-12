package app.kserno.foodie.common

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  Created by filipsollar on 2019-03-28
 */

fun <T> Single<T>.applySchedulers()
    = this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


fun <T> Observable<T>.applySchedulers()
        = this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


fun Completable.applySchedulers()
        = this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())