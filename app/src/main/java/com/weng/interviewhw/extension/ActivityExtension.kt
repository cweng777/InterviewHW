package com.weng.interviewhw.extension

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 * 關閉鍵盤
 */
fun Activity.hideKeyboard() {
    this.currentFocus?.let {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}