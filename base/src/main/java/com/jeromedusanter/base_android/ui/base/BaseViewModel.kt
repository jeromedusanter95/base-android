package com.jeromedusanter.base_android.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<A : IAction> : ViewModel() {

    val disposable = CompositeDisposable()

    val action: LiveData<A>
        get() = _action
    private val _action: MutableLiveData<A> = MutableLiveData()

    private val publisher = PublishSubject.create<A>()
        .apply {
            this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _action.value = it
                    _action.value = null
                }
                .addTo(disposable)
        }

    protected fun dispatch(action: A) {
        publisher.onNext(action)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}