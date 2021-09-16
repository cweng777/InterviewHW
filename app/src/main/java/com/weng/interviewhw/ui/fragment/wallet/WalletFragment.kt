package com.weng.interviewhw.ui.fragment.wallet

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.FragmentWalletBinding
import com.weng.interviewhw.model.data.getuserinfo.ui.GetUserInfoResultUI
import com.weng.interviewhw.ui.activity.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class WalletFragment : Fragment(R.layout.fragment_wallet) {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<WalletViewModel>()

    companion object {
        fun newInstance() = WalletFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWalletBinding.bind(view)
        initTopTab()
        setEventListener()
        observeViewModel()
        viewModel.getUserInfo()
    }

    private fun setEventListener() {
        binding.settingImageView.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setMessage("Do you want to logout?")
                .setPositiveButton("ok") { dialog, _ ->
                    viewModel.logout()
                    dialog.dismiss()
                }
                .setNegativeButton("cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun observeViewModel() {
        viewModel.userInfoResult.observe(viewLifecycleOwner) {
            when (it) {
                GetUserInfoResultUI.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is GetUserInfoResultUI.Success -> {
                    binding.progressBar.isVisible = false
                    setUserName(it.userInfoUI.firstName, it.userInfoUI.lastName)

                    //todo: visible viewPager and TabLayout set true
                    //todo: init viewPager and TabLayout
                }
                is GetUserInfoResultUI.Failure -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    Log.d("getUserInfo", "error: ${it.message}")
                }
            }
        }
        viewModel.logoutState.observe(viewLifecycleOwner) { isLogout ->
            if (isLogout == true) {
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                activity?.finish()
            }
        }
    }

    private fun setUserName(firstName: String, lastName: String) {
        val userName = "$firstName $lastName"
        binding.userName.text = userName
    }

    private fun initTopTab() {
        val walletTabs = WalletTab.values()
        for (tab in walletTabs) {
            val singleTabLayout = LayoutInflater.from(requireContext())
                .inflate(R.layout.layout_wallet_top_tab, binding.topTabTabLayout, false)
            singleTabLayout.apply {
                findViewById<ImageView>(R.id.top_tab_icon_imageView).setImageResource(tab.getIcon())
                findViewById<TextView>(R.id.top_tab_description_imageView).text = tab.getTitle()
                if (tab == WalletTab.ShoppingCart) {
                    findViewById<View>(R.id.left_outline_view).isVisible = true
                    findViewById<View>(R.id.right_outline_view).isVisible = true
                }
            }
            val walletTab = binding.topTabTabLayout.newTab().setCustomView(singleTabLayout)
            binding.topTabTabLayout.addTab(walletTab)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}