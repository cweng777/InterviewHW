package com.weng.interviewhw.util

import kotlinx.coroutines.flow.*

inline fun <RequestType, ResultType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>, //call dataStore to get the data
    crossinline fetch: suspend () -> RequestType, //call api to get the data
    crossinline saveFetchResult: suspend (RequestType) -> Unit, //save api data into db
    crossinline shouldFetch: (ResultType) -> Boolean = {true} //determine if the app should call api to fetch data, default true
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}