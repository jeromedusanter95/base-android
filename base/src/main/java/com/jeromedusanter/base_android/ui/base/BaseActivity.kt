package com.jeromedusanter.base_android.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, A : IAction, VM : BaseViewModel<A>> :
    AppCompatActivity(), IView<A> {

    abstract val resId: Int

    abstract val viewModelVariableId: Int

    abstract val viewModel: VM

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, resId)
        binding.setVariable(viewModelVariableId, viewModel)
        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        viewModel.action.observe(this, { action -> onAction(action) })
    }
}