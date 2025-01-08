package com.shoxrux.cardbin.data.network

import com.shoxrux.cardbin.data.model.CardBindResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinService {

    @GET("{card}")
    suspend fun getCardData(
        @Path("card") card: Int
    ): Response<CardBindResponse?>
}