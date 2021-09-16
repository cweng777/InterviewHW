package com.weng.interviewhw.ui.fragment.wallet

import com.weng.interviewhw.R

enum class WalletTab {
    Promote {
        override fun getIcon() = R.drawable.promote
        override fun getTitle() = "Promote"
    },
    ShoppingCart {
        override fun getIcon() = R.drawable.shopping_cart
        override fun getTitle() = "Shopping Cart"
    },
    OtherStore {
        override fun getIcon() = R.drawable.store
        override fun getTitle() = "Other Store"
    };
    abstract fun getIcon(): Int
    abstract fun getTitle(): String
}