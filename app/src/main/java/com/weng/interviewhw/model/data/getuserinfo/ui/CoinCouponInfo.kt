package com.weng.interviewhw.model.data.getuserinfo.ui

/**
 * 使用者資料, 內容:
 * coin的list
 * coupon的list
 */
data class CoinCouponInfo(
    val coinList: List<CoinCoupon>,
    val couponList: List<CoinCoupon>
)
