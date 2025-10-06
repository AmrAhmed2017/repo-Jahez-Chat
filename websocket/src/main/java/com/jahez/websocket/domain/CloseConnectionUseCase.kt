package com.jahez.websocket.domain

import javax.inject.Inject

class CloseConnectionUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke() = repository.close()
}
