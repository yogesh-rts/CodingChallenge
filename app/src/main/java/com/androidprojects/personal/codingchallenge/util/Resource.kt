package com.androidprojects.personal.codingchallenge.util
/*
* Resource class - A Generic class that contains the data and its corresponding status about the data
* retrieved from the server (via network call)
*
* Modelled and used based on the documentation which was referenced from the developers.android.com
* */
sealed class Resource<T>(
  val data: T? = null,
  val error: Throwable? = null
) {
    class Success<T>(data: T): Resource<T>(data)
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null): Resource<T>(data, throwable)
}