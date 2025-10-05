package com.jahez.storage.domain

import com.jahez.storage.model.ChatMessageEntity
import javax.inject.Inject

class InsertMessageUseCase @Inject constructor(
    private val repository: MessageRepository
) {
    suspend operator fun invoke(message: ChatMessageEntity) =
        repository.insertMessage(message)
}
