package com.shoxrux.cardbin.data.model

data class CardBindResponse(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val scheme: String,
    val type: String
)