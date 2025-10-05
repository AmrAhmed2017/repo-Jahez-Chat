package com.jahez.storage.domain

import com.jahez.storage.model.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    suspend fun insertMessage(message: ChatMessageEntity)

    fun getMessages(): Flow<List<ChatMessageEntity>>
}