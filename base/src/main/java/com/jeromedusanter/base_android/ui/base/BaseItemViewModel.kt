package com.jeromedusanter.base_android.ui.base

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean

abstract class BaseItemViewModel<O : IUiModel> : BaseObservable() {
    protected lateinit var item: O
    val isSelected = ObservableBoolean(false)
    abstract val onItemClick: ((O, Int) -> Unit)?

    fun setNewItem(item: O, isSelected: Boolean) {
        this.item = item
        this.isSelected.set(isSelected)
        onItemChanged(item)
    }

    abstract fun onItemChanged(item: O)
}