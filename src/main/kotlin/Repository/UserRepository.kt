package com.KtorPractica.Repository

import com.KtorPractica.DTOs.User.UserResponse

import com.KtorPractica.Model.Pagination
import com.KtorPractica.Model.User
import kotlin.math.ceil

object UserRepository {
/*
    fun getPageUser(page:Int, pageSize: Int): Pagination<UserResponse>{
        require(page>0){"La pagina debe ser mayor que 0"}
        require(pageSize>0){"El tamaño de la pagina debe ser mayor que 0"}

        //Retorna lista vacia
        if(userList.isEmpty())return Pagination(listOf(),1 ,pageSize,0)

        //Ordena y total de lista
        val sortedUser = userList.sortedBy { it.id }
        val total = sortedUser.size

        //Pagina a tener en cuanta
        val pageTotal = ceil((total.toDouble()/pageSize)).toInt()
        val newPageTotal = minOf(page, pageTotal)

        //Confomacion de los datos a devolver
        val fromIndex = (newPageTotal - 1) * pageSize
        val toIndex = minOf(fromIndex+pageSize, total)
        val data = sortedUser.subList(fromIndex, toIndex).map {
            UserResponse(it.name, it.gmail, it.age, it.money, it.gameList)
        }

        return Pagination(data,newPageTotal,pageSize, total)
    }
    */


}