package com.KtorPractica.routes

import com.KtorPractica.dto.userGame.userGameRequest
import com.KtorPractica.repositories.UserGameRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userGameRoutes(repository: UserGameRepository) {
    route("/userGame") {

        post {
            val usergame = call.receive<userGameRequest>()

            val result = repository.addGameToUser(usergame.id_user, usergame.id_game)
            when (result) {
                1 -> call.respond(HttpStatusCode.NotFound, mapOf("error" to "User not found"))
                2 -> call.respond(HttpStatusCode.NotFound, mapOf("error" to "Game not found"))
                3 -> call.respond(HttpStatusCode.Conflict, mapOf("error" to "Relationship already exists"))
                4 -> call.respond(HttpStatusCode.NoContent)
            }
        }

    }
}