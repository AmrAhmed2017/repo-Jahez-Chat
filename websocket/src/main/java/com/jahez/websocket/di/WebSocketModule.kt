package com.jahez.websocket.di

import com.jahez.websocket.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideOkHttpRequest(): Request =
        Request.Builder().url(BuildConfig.CHAT_SERVER_URL).build()
}