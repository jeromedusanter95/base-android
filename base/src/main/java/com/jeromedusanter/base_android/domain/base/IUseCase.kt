package com.jeromedusanter.base_android.domain.base

interface IUseCase<I, O> {
    fun execute(param: I?): O
}