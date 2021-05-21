package com.jeromedusanter.base_android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<B : ViewDataBinding, A : IAction, VM : BaseViewModel<A>> :
    DialogFragment(), IView<A> {

    abstract val resId: Int

    abstract val viewModel: VM

    abstract val viewModelVariableId: Int

    lateinit var binding: B

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        binding = DataBindingUtil.inflate(inflater, resId, container, false)
        binding.setVariable(viewModelVariableId, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.action.observe(viewLifecycleOwner, { action -> action?.let { onAction(action) } })
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }
}