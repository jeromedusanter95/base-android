package com.jeromedusanter.base_android.domain.base

sealed class Result<T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Error<T>(val error: IError) : Result<T>()
}