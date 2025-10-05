package com.jahez.storage.domain

import com.jahez.storage.ChatDao
import com.jahez.storage.model.ChatMessageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessagesLocalDataSource @Inject constructor(
    private val chatDao: ChatDao
) {
    fun getMessages(): Flow<List<ChatMessageEntity>> = chatDao.getMessages()

    suspend fun insertMessage(message: ChatMessageEntity) {
        chatDao.insert(message)
    }
}