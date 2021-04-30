package com.jeromedusanter.base_android.data.base

import com.jeromedusanter.base_android.domain.base.IModel

interface IMapper<M : IModel, AM : IApiModel, LM : ILocalModel> {
    fun mapModelToApiModel(model: M): AM
    fun mapModelToLocalModel(model: M): LM
    fun mapApiModelToModel(model: AM): M
    fun mapLocalModelToModel(model: LM): M
}