package com.example.mycvapp.view.history

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.get
import androidx.lifecycle.Observer
import com.example.mycvapp.R
import com.example.mycvapp.databinding.FragmentWorkHistoryBinding
import com.example.mycvapp.view.base.BaseFragment
import com.example.mycvapp.view.error.ErrorConfiguration.BROKEN_DATA
import com.example.mycvapp.view.error.ErrorConfiguration.NETWORK
import com.example.mycvapp.view.error.ErrorState.DATA_ERROR
import com.example.mycvapp.view.error.ErrorState.NETWORK_ERROR
import kotlinx.android.synthetic.main.fragment_personal_data.errorView

class WorkHistoryFragment : BaseFragment<FragmentWorkHistoryBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_work_history

    private lateinit var menu: Menu

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.work_history_menu, menu)
        this.menu = menu.apply { get(0).isVisible = mainViewModel.isDataVisible.value ?: false }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_sort) {
            mainViewModel.sortOrderActionClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun applyBindings(binding: FragmentWorkHistoryBinding) {
        binding.viewModel = mainViewModel
    }

    private fun setupObservers() {
        mainViewModel.snackbarErrorEvent.observe(viewLifecycleOwner, Observer { errorState ->
            when(errorState) {
                NETWORK_ERROR -> errorView.showSnackbar(NETWORK)
                DATA_ERROR -> errorView.showSnackbar(BROKEN_DATA)
                else -> {
                    //nothing to do
                }
            }
        })

        mainViewModel.isDataVisible.observe(viewLifecycleOwner, Observer {
            if(this::menu.isInitialized) {
                menu[0].isVisible = it
            }
        })
    }
}