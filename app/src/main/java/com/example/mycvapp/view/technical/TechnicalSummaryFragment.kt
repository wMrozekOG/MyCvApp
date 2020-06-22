package com.example.mycvapp.view.technical

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mycvapp.R
import com.example.mycvapp.extensions.orNotAvailable
import kotlinx.android.synthetic.main.fragment_technical_summary.technicalSummary

class TechnicalSummaryFragment: Fragment(R.layout.fragment_technical_summary) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.run {
            technicalSummary?.text =
                TechnicalSummaryFragmentArgs.fromBundle(this).technicalSummary.orNotAvailable(requireContext())
        }
    }
}