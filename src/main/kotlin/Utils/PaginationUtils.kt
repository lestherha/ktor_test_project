package com.KtorPractica.Utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.SortOrder

suspend fun ApplicationCall.getPaginationParams(): Pair<Int, Int>? {
    val page = request.queryParameters["page"]?.toIntOrNull()
    val pageSize = request.queryParameters["pageSize"]?.toIntOrNull()

    if (page == null || page <= 0 || pageSize == null || pageSize <= 0) {
        respond(
            HttpStatusCode.BadRequest,
            mapOf("error" to "Los parámetros query 'page' y 'pageSize' son obligatorios y deben ser mayores que 0")
        )
        return null
    }

    return page to pageSize
}

suspend fun ApplicationCall.getRangeParams(): Triple<Double, Double, SortOrder>?{
    val minPrece = request.queryParameters["minPrece"]?.toDoubleOrNull()?: 0.0
    val maxPrece = request.queryParameters["maxPrece"]?.toDoubleOrNull()?: Double.MAX_VALUE

    val orderParam = request.queryParameters["order"]?.uppercase()
    val order = when(orderParam){
        "ASC",null -> SortOrder.ASC
        "DESC" -> SortOrder.DESC
        else -> {
            respond(
                HttpStatusCode.BadRequest,
                mapOf(
                    "error" to "El valor de parametro 'order' es incorrcto"
                )
            )
            return null
        }

    }
    return Triple(minPrece, maxPrece, order)
}

