package com.shoxrux.cardbin.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomArticles::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        private var db: AppDataBase? = null
        fun init(context: Context) {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "SavedNews"
                ).allowMainThreadQueries().build()
            }
        }

        fun getInstance() = db?.getDao()
    }

    abstract fun getDao(): RoomDao
}