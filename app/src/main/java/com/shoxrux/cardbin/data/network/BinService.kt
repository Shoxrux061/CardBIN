package com.shoxrux.cardbin.data.network

import com.shoxrux.cardbin.data.model.CardBinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinService {

    @GET("{card}")
    suspend fun getCardData(
        @Path("card") card: String
    ): Response<CardBinResponse?>
}