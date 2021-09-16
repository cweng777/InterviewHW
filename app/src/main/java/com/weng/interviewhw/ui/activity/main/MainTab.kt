package com.weng.interviewhw.ui.activity.main

import com.weng.interviewhw.R

enum class MainTab {
    Phone {
        override fun getIcon() = R.drawable.phone
        override fun getTitle() = "PHONE"
    },
    Chat {
        override fun getIcon() = R.drawable.chat
        override fun getTitle() = "CHAT"
    },
    FabSpace {
        override fun getIcon() = 0
        override fun getTitle() = ""
    },
    Explore {
        override fun getIcon() = R.drawable.explore
        override fun getTitle() = "EXPLORE"
    },
    Wallet {
        override fun getIcon() = R.drawable.wallet
        override fun getTitle() = "WALLET"
    };
    abstract fun getIcon(): Int
    abstract fun getTitle(): String
}