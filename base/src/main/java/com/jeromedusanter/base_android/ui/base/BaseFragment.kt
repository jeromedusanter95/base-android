package com.jeromedusanter.base_android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<B : ViewDataBinding, A : IAction, VM : BaseViewModel<A>> : Fragment(),
    IView<A> {

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
        binding = DataBindingUtil.inflate(inflater, resId, container, false)
        binding.setVariable(viewModelVariableId, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.action.observe(viewLifecycleOwner, { action -> action?.let { onAction(action) } })
        return binding.root
    }

    @CallSuper
    override fun onAction(action: A) {
        super.onAction(action)
        when (action) {
            is ErrorAction -> showSnackBar(action.resId)
        }
    }

    fun navigate(navDirections: NavDirections) {
        try {
            findNavController().navigate(navDirections)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    fun navigate(resId: Int, bundle: Bundle? = null) {
        try {
            findNavController().navigate(resId, bundle)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    fun popBackStack() {
        try {
            findNavController().popBackStack()
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    fun popBackStack(destinationId: Int, inclusive: Boolean) {
        try {
            findNavController().popBackStack(destinationId, inclusive)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    private fun showSnackBar(resId: Int) {
        Snackbar.make(requireView(), resId, Snackbar.LENGTH_SHORT).show()
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}