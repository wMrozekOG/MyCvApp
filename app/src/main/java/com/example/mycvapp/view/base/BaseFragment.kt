package com.example.mycvapp.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.mycvapp.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    protected val mainViewModel: MainViewModel by sharedViewModel(from = { requireActivity() })

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: T = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        applyBindings(binding)
        setHasOptionsMenu(true)
        return binding.root
    }

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    protected open fun applyBindings(binding: T) {}
}