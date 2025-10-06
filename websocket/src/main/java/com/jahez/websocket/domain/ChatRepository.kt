package com.jahez.websocket.domain

import com.jahez.websocket.model.ChatWebMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun connect()
    suspend fun sendMessage(text: ChatWebMessage): Boolean
    fun observeIncomingMessages(): Flow<ChatWebMessage>
    fun observeEvents(): Flow<AppThrowable>
    fun close()
}