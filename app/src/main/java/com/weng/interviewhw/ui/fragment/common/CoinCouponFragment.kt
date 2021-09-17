package com.weng.interviewhw.ui.fragment.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.FragmentCoinCouponBinding
import com.weng.interviewhw.model.data.getuserinfo.ui.GetUserInfoResultUI
import com.weng.interviewhw.ui.fragment.wallet.WalletCategory
import com.weng.interviewhw.ui.fragment.wallet.WalletViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinCouponFragment : Fragment(R.layout.fragment_coin_coupon) {

    private var _binding: FragmentCoinCouponBinding? = null
    private val binding get() = _binding!!

    private lateinit var coinCouponListAdapter: CoinCouponListAdapter

    private lateinit var walletCategory: WalletCategory

    private val parentWalletViewModel by lazy {
        requireParentFragment().viewModel<WalletViewModel>().value
    }

    companion object {
        /**
         * bundle key 分類 (Coin or Coupon)
         */
        private const val BUNDLE_WALLET_CATEGORY = "bundle_wallet_category"

        fun newInstance(walletCategory: WalletCategory): CoinCouponFragment {
            val fragment = CoinCouponFragment()
            val bundle = Bundle()
            bundle.apply {
                putSerializable(BUNDLE_WALLET_CATEGORY, walletCategory)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.apply {
            walletCategory = this?.getSerializable(BUNDLE_WALLET_CATEGORY) as? WalletCategory
                ?: WalletCategory.Coin
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCoinCouponBinding.bind(view)
        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        parentWalletViewModel.userInfoResult.observe(viewLifecycleOwner) { userInfoResult ->
            when (walletCategory) {
                WalletCategory.Coin -> {
                    val coinList = (userInfoResult as? GetUserInfoResultUI.Success)?.coinCouponInfo?.coinList
                    coinCouponListAdapter.submitList(coinList)
                }
                WalletCategory.Coupon -> {
                    val couponList = (userInfoResult as? GetUserInfoResultUI.Success)?.coinCouponInfo?.couponList
                    coinCouponListAdapter.submitList(couponList)
                }
            }
        }
    }

    private fun initRecyclerView() {
        coinCouponListAdapter = CoinCouponListAdapter(object : WalletItemCallback {
            override fun onItemCallBack(message: String) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }, walletCategory)
        binding.coinCouponRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinCouponListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}