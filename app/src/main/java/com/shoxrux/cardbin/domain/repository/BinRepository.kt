package com.shoxrux.cardbin.domain.repository

import com.shoxrux.cardbin.core.ResultWrapper
import com.shoxrux.cardbin.core.parseResponse
import com.shoxrux.cardbin.data.model.CardBindResponse
import com.shoxrux.cardbin.data.network.BinService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class BinRepository @Inject constructor(private val service: BinService) {

    suspend fun getCardBin(
        bin: Int
    ): ResultWrapper<CardBindResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getCardData(
                bin
            )
        }
    }
}