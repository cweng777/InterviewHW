package com.weng.interviewhw.model.data.getuserinfo.ui

/**
 * coin 或 coupon 用到的資料結構
 */
data class CoinCoupon(
    val id: Int,
    val coin: String,
    val bag: String,
    val dayPassed: Int,
    val interactionRewards: String,
    val socialRewards: String,
    val revenue: String
)
