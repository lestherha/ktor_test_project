package com.KtorPractica

import com.KtorPractica.repositories.GameRepositoryImpl
import com.KtorPractica.repositories.UserRepositoryImpl
import com.KtorPractica.repositories.UserGameRepositoryImpl
import com.KtorPractica.routes.gameRoutes
import com.KtorPractica.routes.userRoutes
import com.KtorPractica.routes.userGameRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hola mol2")
        }
        userRoutes(UserRepositoryImpl())
        gameRoutes(GameRepositoryImpl())
        userGameRoutes(UserGameRepositoryImpl())
    }
}
