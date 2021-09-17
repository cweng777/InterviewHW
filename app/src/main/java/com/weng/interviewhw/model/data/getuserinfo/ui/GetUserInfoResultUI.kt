package com.weng.interviewhw.model.data.getuserinfo.ui


sealed class GetUserInfoResultUI {
    object Loading: GetUserInfoResultUI()
    data class Success(val userInfoUI: UserInfoUI, val coinCouponInfo: CoinCouponInfo): GetUserInfoResultUI()
    data class Failure(val message: String): GetUserInfoResultUI()
}
