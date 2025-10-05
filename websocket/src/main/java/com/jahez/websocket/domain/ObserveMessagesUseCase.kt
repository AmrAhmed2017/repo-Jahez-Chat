package com.jahez.websocket.domain

import com.jahez.websocket.model.ChatWebMessage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMessagesUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke(): Flow<ChatWebMessage> = repository.observeIncomingMessages()
}
