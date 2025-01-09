package com.shoxrux.cardbin.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bins_table")
data class RoomArticles(

    @PrimaryKey
    @ColumnInfo("bin")
    val bin: String,
    @ColumnInfo("scheme") val scheme: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("brand") val brand: String,
    @ColumnInfo("prepaid") val prepaid: Boolean?,
    @ColumnInfo("country") val country: String,
    @ColumnInfo("currency") val currency: String,
    @ColumnInfo("bank") val bank: String,
    @ColumnInfo("url_bank") val urlBank: String,
    @ColumnInfo("phone_bank") val phoneBank: String,
    @ColumnInfo("lat") val lat: Int,
    @ColumnInfo("lon") val lon: Int

)
