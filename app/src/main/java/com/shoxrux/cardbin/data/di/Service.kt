package com.shoxrux.cardbin.data.di

import com.shoxrux.cardbin.data.network.BinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Service {

    @[Provides Singleton]
    fun provideAuthService(retrofit: Retrofit): BinService {
        return retrofit.create(BinService::class.java)
    }
}