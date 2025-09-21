package com.KtorPractica.Routers

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.KtorPractica.Repository.UserRepository


fun Route.userRouting(){
    route("/user"){
/*
        get{
            val page = call.request.queryParameters["page"]?.toIntOrNull()?:
            return@get call.respond(
                HttpStatusCode.BadRequest,
                mapOf("error" to "El tipo de dato  de 'page' es incorrecto"))

            val pageSize= call.request.queryParameters["pageSize"]?.toIntOrNull()?:
                return@get call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf("error" to "El tipo de dato  de 'pageSize' es incorrecto"))

            try {
                val pageUser = UserRepository.getPageUser(page,pageSize)
                return@get call.respond(HttpStatusCode.OK,
                    pageUser)

            }catch (e:IllegalArgumentException){
                return@get call.respond(HttpStatusCode.BadRequest,
                    mapOf("error" to e.message))
            }
        }*/
    }
}