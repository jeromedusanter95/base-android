package com.jeromedusanter.base_android.ui.base

interface IView<A : IAction> {
    fun onAction(action: A) = Unit
}