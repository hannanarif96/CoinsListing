package com.task.data

import com.task.data.dto.coin.Coins
import kotlinx.coroutines.flow.Flow


interface DataRepositorySource {
    suspend fun requestCoins(): Flow<Resource<Coins>>
    suspend fun addToFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun removeFromFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun isFavourite(id: String): Flow<Resource<Boolean>>
}
