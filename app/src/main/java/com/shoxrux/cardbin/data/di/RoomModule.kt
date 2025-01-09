package com.shoxrux.cardbin.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shoxrux.cardbin.data.room.AppDataBase
import com.shoxrux.cardbin.data.room.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @[Provides Singleton]
    fun provideRoom(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "app_database"
        ).build()
    }

    @[Provides Singleton]
    fun provideDao(dataBase: AppDataBase): RoomDao {
        return dataBase.getDao()
    }

}