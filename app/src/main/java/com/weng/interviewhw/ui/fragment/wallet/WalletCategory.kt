package com.weng.interviewhw.ui.fragment.wallet

import java.io.Serializable

/**
 * 分為Coin和Coupon類, 給 CoinCouponFragment 用來載示Coin或Coupon的list
 */
enum class WalletCategory: Serializable {
    Coin,
    Coupon
}