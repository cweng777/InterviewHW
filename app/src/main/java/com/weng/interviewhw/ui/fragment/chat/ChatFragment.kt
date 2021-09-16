package com.weng.interviewhw.ui.fragment.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.FragmentChatBinding


class ChatFragment : Fragment(R.layout.fragment_chat) {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ChatFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChatBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}