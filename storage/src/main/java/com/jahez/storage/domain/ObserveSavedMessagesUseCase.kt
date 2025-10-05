package com.jahez.storage.domain

import javax.inject.Inject

class ObserveSavedMessagesUseCase @Inject constructor(
    private val repository: MessageRepository
) {
    operator fun invoke() =
        repository.getMessages()
}
