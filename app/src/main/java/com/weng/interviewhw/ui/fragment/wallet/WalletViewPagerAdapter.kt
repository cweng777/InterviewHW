package com.weng.interviewhw.ui.fragment.wallet

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.weng.interviewhw.ui.fragment.common.CoinCouponFragment

class WalletViewPagerAdapter(
    fragment: Fragment,
    private val allTabs: Array<WalletMiddleTab>
): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return allTabs.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(allTabs[position]) {
            WalletMiddleTab.COIN -> {
                CoinCouponFragment.newInstance(
                   WalletCategory.Coin
                )
            }
            WalletMiddleTab.COUPON -> {
                CoinCouponFragment.newInstance(
                    WalletCategory.Coupon
                )
            }
        }
    }

}