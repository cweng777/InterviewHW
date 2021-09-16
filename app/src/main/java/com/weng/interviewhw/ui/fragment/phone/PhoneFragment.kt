package com.weng.interviewhw.ui.fragment.phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.FragmentPhoneBinding


class PhoneFragment : Fragment(R.layout.fragment_phone) {

    private var _binding: FragmentPhoneBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = PhoneFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPhoneBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}