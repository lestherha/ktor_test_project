package com.KtorPractica.routes

import com.KtorPractica.dto.user.UserRequest
import com.KtorPractica.dto.user.UserUpdate
import com.KtorPractica.repositories.UserRepository
import com.KtorPractica.Utils.getPaginationParams
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(repository: UserRepository){
    route("/user"){
        get("/all"){
           val (page,pageSize) = call.getPaginationParams()?: return@get

           val alluser = repository.allUser(page,pageSize)
           call.respond(alluser)
        }

        get("/byId/{id}"){
            val id = call.parameters["id"]?.toIntOrNull()?: return@get call.respond(
                HttpStatusCode.BadRequest,
                mapOf(
                "error" to "El parametro path 'id' no puede ser null"
                )
            )
            val user = repository.userById(id)?: return@get call.respond(
                HttpStatusCode.NotFound,
                mapOf(
                    "error" to "Ojeto no encontrado en la BD"
                )
            )
            call.respond(user)
        }

        get("/byName/{name}"){
            val (page, pageSize) = call.getPaginationParams()?: return@get

            val name = call.parameters["name"]?: return@get call.respond(
                HttpStatusCode.BadRequest,
                mapOf(
                    "error" to "El parametro path 'name' no puede ser null"
                )
            )

            val userName = repository.userByName(page,pageSize,name)
            call.respond(userName)
        }

        post{
            try{
                val user = call.receive<UserRequest>()
                repository.addUser(user)
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

        put("/{id}"){
            val id = call.parameters["id"]?.toIntOrNull()?: return@put call.respond(
                HttpStatusCode.BadRequest,
                mapOf(
                    "error" to "El parametro path 'id' es incorrecto",
                )
            )

            try{

                val userUpdate = call.receive<UserUpdate>()
                val res = repository.updateUser(id, userUpdate)
                if(res==3)
                    call.respond(HttpStatusCode.NoContent)
                else if(res==2)
                    call.respond(
                        HttpStatusCode.NotFound,
                        mapOf(
                            "error" to "El email ya se encuentra en utilizado"
                        )
                    )
                else{
                    call.respond(
                        HttpStatusCode.NotFound,
                        mapOf(
                            "error" to "El usuario no se encuentra en la BD"
                        )
                    )
                }


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

        delete("/{id}"){
            val id = call.parameters["id"]?.toIntOrNull()?: return@delete call.respond(
                HttpStatusCode.BadRequest,
                mapOf(
                    "error" to "El valor de parametro 'id' es incorrecto"
                )
            )
            if(repository.removeUser(id))
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