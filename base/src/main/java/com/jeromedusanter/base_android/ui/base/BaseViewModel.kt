package com.jeromedusanter.base_android.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<A : IAction> : ViewModel() {

    val disposable = CompositeDisposable()

    val action: SingleLiveEvent<A>
        get() = _action
    private val _action: SingleLiveEvent<A> = SingleLiveEvent()

    protected fun dispatch(action: A) {
        _action.postValue(action)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}