package com.jahez.websocket.domain

import com.jahez.websocket.model.ChatWebMessage
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(message: ChatWebMessage): Boolean = repository.sendMessage(message)
}
