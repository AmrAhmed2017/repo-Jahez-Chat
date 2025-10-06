package com.jahez.jahezchat.di

import android.content.Context
import androidx.room.Room
import com.jahez.storage.ChatDao
import com.jahez.storage.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object CacheModule {

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): ChatDatabase =
            Room.databaseBuilder(
                context,
                ChatDatabase::class.java,
                "chat_db"
            ).build()

        @Provides
        fun provideChatDao(db: ChatDatabase): ChatDao = db.chatDao()
    }

}