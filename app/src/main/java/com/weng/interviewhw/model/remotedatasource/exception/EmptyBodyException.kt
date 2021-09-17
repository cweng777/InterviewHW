package com.weng.interviewhw.model.remotedatasource.exception

import java.lang.Exception

/**
 * Retrofit response body回傳null時拋出之例外
 */
class EmptyBodyException: Exception("No response body from server")