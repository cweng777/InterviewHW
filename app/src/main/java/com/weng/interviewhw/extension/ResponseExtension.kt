package com.weng.interviewhw.extension

import com.weng.interviewhw.model.remotedatasource.exception.EmptyBodyException
import retrofit2.HttpException
import retrofit2.Response

@Throws(EmptyBodyException::class)
fun <T: Response<R>, R> T.requireBody(): R {
    return body() ?: throw EmptyBodyException()
}

/**
 * 處理http status code，200-299的狀態，並回傳[R]，如果沒有拋出[EmptyBodyException]。
 * 其他拋出[HttpException]。
 */
@Throws(
    HttpException::class,
    EmptyBodyException::class
)
fun <T: Response<R>, R> T.getResponseBody(): R {
    return when {
        isSuccessful -> {
            requireBody()
        }
        else -> {
            throw HttpException(this)
        }
    }
}  