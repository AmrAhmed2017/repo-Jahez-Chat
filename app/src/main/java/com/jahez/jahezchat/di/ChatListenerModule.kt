package com.jahez.jahezchat.di

import com.jahez.jahezchat.data.ChatRepositoryImpl
import com.jahez.storage.domain.MessageRepository
import com.jahez.websocket.domain.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ChatListenerModule {

    @Binds
    abstract fun bindChatRepository(impl: ChatRepositoryImpl): MessageRepository

    @Binds
    abstract fun bindRoomRepository(impl: ChatRepositoryImpl): ChatRepository
}
