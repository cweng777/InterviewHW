package com.weng.interviewhw.ui.fragment.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weng.interviewhw.databinding.LayoutWalletItemBinding
import com.weng.interviewhw.model.data.getuserinfo.ui.CoinCoupon
import com.weng.interviewhw.ui.fragment.wallet.WalletCategory

class CoinCouponListAdapter(
    private val callback: WalletItemCallback,
    private val walletCategory: WalletCategory
) : ListAdapter<CoinCoupon, CoinCouponListAdapter.CoinCouponViewHolder>(object :
    DiffUtil.ItemCallback<CoinCoupon>() {
    override fun areItemsTheSame(oldItem: CoinCoupon, newItem: CoinCoupon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CoinCoupon, newItem: CoinCoupon): Boolean {
        return oldItem == newItem
    }

}) {

    inner class CoinCouponViewHolder(private val binding: LayoutWalletItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: CoinCoupon) {
            binding.coinValueTextView.text = data.coin
            binding.xncBagValueTextView.text = data.bag
            val progress = data.dayPassed  * 100 / 365
            binding.walletItemProgressbar.progress = progress
            val dayRemaining = 365 - data.dayPassed
            val dayRemainingText = "Expire in\n$dayRemaining days"
            binding.daysRemainingTextView.text = dayRemainingText
            binding.interactionRewardValueTextView.text = data.interactionRewards
            binding.socialRewardValueTextView.text = data.socialRewards
            binding.revenueValueTextView.text = data.revenue
            binding.moreTextView.setOnClickListener {
                val message = "More : Process:86 / 329天"
                callback.onItemCallBack(message)
            }
            binding.walletItemCountTextView.isVisible = walletCategory == WalletCategory.Coupon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinCouponViewHolder {
        val binding =
            LayoutWalletItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinCouponViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinCouponViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.setData(this)
        }
    }
}