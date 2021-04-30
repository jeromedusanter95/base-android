package com.jeromedusanter.base_android.domain.base

interface IErrorHandler {
    fun mapThrowableToErrorEntity(throwable: Throwable): IError
}