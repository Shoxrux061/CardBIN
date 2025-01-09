package com.shoxrux.cardbin.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBin(data: RoomArticles)

    @Query("SELECT * FROM bins_table")
    suspend fun getBins(): List<RoomArticles>
}