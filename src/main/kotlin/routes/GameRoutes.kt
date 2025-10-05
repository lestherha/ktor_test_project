package com.KtorPractica.routes

import com.KtorPractica.repositories.GameRepository
import com.KtorPractica.Utils.getPaginationParams
import com.KtorPractica.Utils.getRangeParams
import com.KtorPractica.dto.game.GameRequest
import com.KtorPractica.dto.game.GameUpdate
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.server.http.httpDateFormat
import io.ktor.server.request.receive
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SortOrder
import java.lang.Exception

fun Route.gameRoutes(repository: GameRepository){
    route("/game"){

        get("/all"){
            val (page,pageSize) = call.getPaginationParams()?:return@get
            val games = repository.allGame(page,pageSize)
            call.respond(games)
        }

        get("/byId/{id}"){
            val id = call.parameters["id"]?.toIntOrNull()?: return@get call.respond(
                HttpStatusCode.BadRequest,
                mapOf(
                "error" to "El parametro path 'id' es incorrecto"
                )
            )

            val game = repository.gameById(id)?: return@get call.respond(
                HttpStatusCode.NotFound,
                mapOf(
                    "error" to "El objeto no se encuentra en la BD"
                )
            )

            call.respond(game)
        }

        get("/byName/{name}"){
            val (page,pageSize) = call.getPaginationParams()?: return@get
            val name = call.parameters["name"]?: return@get call.respond(
                HttpStatusCode.BadRequest,
                mapOf(
                    "error" to "EL parametro path 'name' no puede ser null"
                )
            )

            call.respond(repository.gameByName(page,pageSize, name))
        }

        get("/notDescription"){
            val (page,pageSize) = call.getPaginationParams()?: return@get

            call.respond(repository.allGameNotDescription(page,pageSize))
        }

        get("/byPrece"){
            val (page,pageSize) = call.getPaginationParams()?: return@get
            val (minPrece, maxPrece, order) = call.getRangeParams()?: return@get

            call.respond(repository.gameByPrece(page,pageSize,minPrece,maxPrece,order))
        }

        post{
            try {
                val game = call.receive<GameRequest>()
                repository.addGame(game)
                call.respond(HttpStatusCode.NoContent)
            }catch(ex: IllegalStateException){
                call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf(
                        "error" to "Expresion ilegal",
                        "ex" to "${ex.message}"
                    )
                )
            }catch (ex: JsonConvertException){
                call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf(
                        "error" to "Conversion a Json",
                        "ex" to "${ex.message}"
                    )
                )
            }
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()?: return@put call.respond(
                HttpStatusCode.BadRequest,
                mapOf(
                    "error" to "El valor de parametro 'id' es incorrecto"
                )
            )
            try {
                val game = call.receive<GameUpdate>()
                if(repository.updateGame(id,game))
                    call.respond(HttpStatusCode.NoContent)
                else
                    call.respond(
                        HttpStatusCode.NotFound,
                        mapOf(
                            "error" to "El objeto no se encuentra en la BD"
                        )
                    )
            }catch (ex: IllegalStateException){
                call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf(
                        "error" to "Expresion ilegal",
                        "ex" to "${ex.message}"
                    )
                )
            }catch (ex: JsonConvertException){
                call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf(
                        "error" to "Conversion a Json",
                        "ex" to "${ex.message}"
                    )
                )
            }
        }

        delete("/{id}") {
            val id  = call.parameters["id"]?.toIntOrNull()?: return@delete call.respond(
                HttpStatusCode.BadRequest,
                mapOf(
                    "error" to "El valor de parametro 'id' es incorrecto"
                )
            )

            if(repository.removeGame(id))
                call.respond(HttpStatusCode.NoContent)
            else
                call.respond(
                    HttpStatusCode.NotFound,
                    mapOf(
                        "error" to "El objeto no se encuentra en la BD"
                    )
                )
        }
    }
}