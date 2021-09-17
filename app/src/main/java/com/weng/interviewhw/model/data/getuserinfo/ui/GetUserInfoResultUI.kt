package com.weng.interviewhw.model.data.getuserinfo.ui

/**
 * 呼api拿到的使用者資訊, 顯示在UI用的Data,內容有:
 * UserInfo: 使用者資訊 (email, firstName, lastName)
 * coinCouponInfo: 使用者coin和coupon的資訊
 */
sealed class GetUserInfoResultUI {
    object Loading: GetUserInfoResultUI()
    data class Success(val userInfoUI: UserInfoUI, val coinCouponInfo: CoinCouponInfo): GetUserInfoResultUI()
    data class Failure(val message: String): GetUserInfoResultUI()
}
