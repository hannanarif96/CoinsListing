package com.task.data.remote

import com.task.data.Resource
import com.task.data.dto.coin.Coins


internal interface RemoteDataSource {
    suspend fun requestCoins(): Resource<Coins>
}
