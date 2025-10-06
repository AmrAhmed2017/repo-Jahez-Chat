package com.jahez.jahezchat.data

import com.jahez.jahezchat.mapper.toEntity
import com.jahez.storage.domain.MessageRepository
import com.jahez.storage.domain.MessagesLocalDataSource
import com.jahez.storage.model.ChatMessageEntity
import com.jahez.websocket.domain.AppThrowable
import com.jahez.websocket.domain.ChatRemoteDataSource
import com.jahez.websocket.domain.ChatRepository
import com.jahez.websocket.model.ChatWebMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val local: MessagesLocalDataSource,
    private val remote: ChatRemoteDataSource
) : ChatRepository, MessageRepository {

    override fun connect() {
        remote.connect()
    }

    override suspend fun sendMessage(text: ChatWebMessage): Boolean {
        val isSent = remote.send(text.text)
        if (isSent) {
            insertMessage(
                text.toEntity()
            )
        }
        return isSent
    }

    override fun observeIncomingMessages() =

        remote.incomingMessages.onEach { msgText ->

            insertMessage(msgText.toEntity())
        }

    override fun observeEvents(): Flow<AppThrowable> = remote.events

    override fun close() {
        remote.close()
    }

    override fun getMessages(): Flow<List<ChatMessageEntity>> = local.getMessages()

    override suspend fun insertMessage(message: ChatMessageEntity) {
        local.insertMessage(message)
    }
}
