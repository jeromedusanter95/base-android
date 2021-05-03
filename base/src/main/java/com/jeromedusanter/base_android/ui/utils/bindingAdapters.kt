package com.jeromedusanter.base_android.ui.utils

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
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

@BindingAdapter("app:textRes")
fun setTextRes(view: TextView, @StringRes resId: Int) {
    if (resId != -1) view.setText(resId)
}

@BindingAdapter("app:srcRes")
fun setSrcRes(view: ImageView, @DrawableRes resId: Int) {
    if (resId != -1) view.setImageResource(resId)
}

@BindingAdapter(
    "app:url",
    "app:progressColor",
    "app:radius",
    "app:errorDrawable",
    requireAll = false
)
fun setUrl(
    view: ImageView,
    url: String,
    @ColorRes colorResId: Int? = null,
    @DimenRes dimenResId: Int? = null,
    @DrawableRes drawableResId: Int? = null
) {
    Glide.with(view.context)
        .load(url)
        .apply(
            if (dimenResId != null)
                RequestOptions().transform(
                    RoundedCorners(view.context.resources.getDimension(dimenResId).toInt())
                )
            else
                RequestOptions().dontTransform()
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply {
            if (colorResId != null) placeholder(createCircularProgressDrawable(view, colorResId))
        }
        .apply { if (drawableResId != null) error(drawableResId) }
        .into(view)
}

fun createCircularProgressDrawable(
    view: ImageView,
    @ColorRes resId: Int
): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.setColorFilter(
        view.context.resources.getColor(resId),
        PorterDuff.Mode.SRC_IN
    )
    circularProgressDrawable.start()
    return circularProgressDrawable
}