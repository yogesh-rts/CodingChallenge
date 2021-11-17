package com.androidprojects.personal.codingchallenge.util

import kotlinx.coroutines.flow.*
/*
* ResultType: For the Resource data
* ResponseType: For API response
*
* A function that provides a resource backed by both sqlite DB and network
*
* Modelled and used based on the documentation which was referenced from the developers.android.com
*
* @crossinline used to void the return type when using inline function.
* In other words, making the return type as Unit/Void
* */
inline fun <ResultType, RequestType> networkBoundResource(
   crossinline query: () -> Flow<ResultType>,
   crossinline fetch: suspend () -> RequestType,
   crossinline saveFetchResult: suspend(RequestType) -> Unit,
   crossinline shouldFetch: (ResultType) -> Boolean ={ true }
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