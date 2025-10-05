package com.jahez.websocket.domain

import javax.inject.Inject

class ConnectUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke() = repository.connect()
}
