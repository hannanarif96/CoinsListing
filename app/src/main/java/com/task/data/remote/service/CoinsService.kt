package com.task.data.remote.service

import com.task.data.dto.coin.Coin
import retrofit2.Response
import retrofit2.http.GET

interface CoinsService {
    @GET("v1/coins")
    suspend fun fetchCoins(): Response<List<Coin>>
}