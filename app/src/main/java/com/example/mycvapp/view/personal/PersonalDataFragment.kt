package com.example.mycvapp.view.personal

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.Observer
import com.example.mycvapp.R
import com.example.mycvapp.databinding.FragmentPersonalDataBinding
import com.example.mycvapp.view.base.BaseFragment
import com.example.mycvapp.view.error.ErrorConfiguration.BROKEN_DATA
import com.example.mycvapp.view.error.ErrorConfiguration.NETWORK
import com.example.mycvapp.view.error.ErrorState.DATA_ERROR
import com.example.mycvapp.view.error.ErrorState.NETWORK_ERROR
import kotlinx.android.synthetic.main.fragment_personal_data.errorView

class PersonalDataFragment : BaseFragment<FragmentPersonalDataBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_personal_data

    private val onClickListener = OnClickListener {
        when(it.id) {
            R.id.technicalSummaryButton ->
                navigate(PersonalDataFragmentDirections.actionPersonalToTechnical(mainViewModel.technicalSummary))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    override fun applyBindings(binding: FragmentPersonalDataBinding) {
        binding.viewModel = mainViewModel
        binding.onClickListener = onClickListener
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
    }
}