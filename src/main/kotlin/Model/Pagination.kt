package com.KtorPractica.Model

import kotlinx.serialization.Serializable

@Serializable
data class Pagination<T>(
    val data: List<T>,
    val page: Int,
    val pageSize: Int,
    val total: Int
)