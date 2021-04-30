package com.jeromedusanter.base_android.ui.utils

import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jeromedusanter.base_android.ui.base.BaseAdapter
import com.jeromedusanter.base_android.ui.base.BaseItemViewModel
import com.jeromedusanter.base_android.ui.base.IUiModel
import cz.kinst.jakub.view.StatefulLayout

@BindingAdapter("app:list")
internal fun setList(view: RecyclerView, list: List<IUiModel>?) {
    val adapter =
        view.adapter as? BaseAdapter<ViewDataBinding, IUiModel, BaseItemViewModel<IUiModel>>
    adapter?.addItems(list ?: emptyList(), clear = true)
}

@BindingAdapter("app:uiState")
internal fun setUiState(view: StatefulLayout, state: String) {
    view.state = state
}

@BindingAdapter("app:visibleOrGone")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:underline")
fun underline(view: TextView, underline: Boolean) {
    if (underline) view.paintFlags = view.paintFlags or Paint.UNDERLINE_TEXT_FLAG
}