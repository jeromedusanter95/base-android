package com.jeromedusanter.base_android.ui.base

import com.jeromedusanter.base_android.domain.base.IModel

interface IMapper<E : IModel, U : IUiModel> {
    fun mapModelToUiModel(model: E): U
    fun mapUiModelToModel(model: U): E
}