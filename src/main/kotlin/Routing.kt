package com.KtorPractica

import com.KtorPractica.Routers.userRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hlo mu122232ndo")
        }
        get("/test1") {
            val re = 4
            val res = "Hola q tal tengo ${re+4}"
            call.respondText(res)
        }
        userRouting()
    }
}
