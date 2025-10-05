package com.KtorPractica.repositories

import com.KtorPractica.dto.game.GameOnlyName

interface UserGameRepository {
    suspend fun addGameToUser(userId: Int, gameId: Int): Int
    /*suspend fun removeGameFromUser(userId: Int, gameId: Int): Boolean
    suspend fun getGamesForUser(userId: Int): List<GameOnlyName>
    suspend fun removeAllGamesFromUser(userId: Int): Boolean*/
}