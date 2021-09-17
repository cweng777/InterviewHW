package com.weng.interviewhw.ui.activity.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.weng.interviewhw.R
import com.weng.interviewhw.databinding.ActivityMainBinding
import com.weng.interviewhw.extension.takeColor
import com.weng.interviewhw.ui.fragment.chat.ChatFragment
import com.weng.interviewhw.ui.fragment.explore.ExploreFragment
import com.weng.interviewhw.ui.fragment.phone.PhoneFragment
import com.weng.interviewhw.ui.fragment.wallet.WalletFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomTab()
        setEventListener()
        setInitialSelectedTab(MainTab.Phone)
        //假資料: if we have 2 new Wallet news, count should be passed when we get the news count from api
        setFakeWalletNewsCount(2)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initBottomTab() {
        val mainTabs = MainTab.values()
        for (tab in mainTabs) {
            val singleTabLayout = LayoutInflater.from(this).inflate(R.layout.layout_bottom_tab, binding.bottomTabTabLayout, false)
            singleTabLayout.apply {
                findViewById<ImageView>(R.id.bottom_tab_icon_imageView).setImageResource(tab.getIcon())
                findViewById<TextView>(R.id.bottom_tab_description_imageView).text = tab.getTitle()
            }
            val mainTab = binding.bottomTabTabLayout.newTab().setCustomView(singleTabLayout)
            if (tab == MainTab.FabSpace) {
                mainTab.view.setOnTouchListener { _,_ -> true }
            }
            binding.bottomTabTabLayout.addTab(mainTab)
        }
    }

    private fun setEventListener() {
        binding.bottomTabTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            private var isFirst = true
            override fun onTabSelected(tab: TabLayout.Tab) {
                val color = takeColor(R.color.green20B2AA)
                tab.customView?.apply {
                    findViewById<ImageView>(R.id.bottom_tab_icon_imageView)?.setColorFilter(color)
                    findViewById<TextView>(R.id.bottom_tab_description_imageView)?.setTextColor(color)
                }
                handleTabSelect(tab.position, tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val color = takeColor(R.color.black)
                tab.customView?.apply {
                    findViewById<ImageView>(R.id.bottom_tab_icon_imageView)?.setColorFilter(color)
                    findViewById<TextView>(R.id.bottom_tab_description_imageView)?.setTextColor(color)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                if (isFirst) {
                    isFirst = false
                    handleTabSelect(tab.position, tab)
                }
            }

            private fun handleTabSelect(position: Int, tab: TabLayout.Tab) {
                when (MainTab.values()[position]) {
                    MainTab.Phone -> {
                        replaceFragment(PhoneFragment.newInstance())
                    }
                    MainTab.Chat -> {
                        replaceFragment(ChatFragment.newInstance())
                    }
                    MainTab.Explore -> {
                        replaceFragment(ExploreFragment.newInstance())
                    }
                    MainTab.Wallet -> {
                        //點wallet後, 表示已看過, 使紅點notification消失
                        tab.customView?.apply {
                            findViewById<TextView>(R.id.count_textView)?.isVisible = false
                        }
                        replaceFragment(WalletFragment.newInstance())
                    }
                    MainTab.FabSpace -> Unit //do nothing
                }
            }

        })
    }

    private fun replaceFragment(targetFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_frameLayout, targetFragment)
            .commit()
    }

    private fun setInitialSelectedTab(selectTab: MainTab) {
        //select Tab
        val selectTabPosition = selectTab.ordinal
        val tab = binding.bottomTabTabLayout.getTabAt(selectTabPosition)
        val color = takeColor(R.color.green20B2AA)
        tab?.customView?.apply {
            findViewById<ImageView>(R.id.bottom_tab_icon_imageView)?.setColorFilter(color)
            findViewById<TextView>(R.id.bottom_tab_description_imageView)?.setTextColor(color)
        }
        tab?.select()
    }

    private fun setFakeWalletNewsCount(count: Int) {
        val selectTabPosition = MainTab.Wallet.ordinal
        val tab = binding.bottomTabTabLayout.getTabAt(selectTabPosition)
        tab?.customView?.apply {
            val countTextView = findViewById<TextView>(R.id.count_textView)
            countTextView.isVisible = true
            countTextView.text = count.toString()
        }
    }
}