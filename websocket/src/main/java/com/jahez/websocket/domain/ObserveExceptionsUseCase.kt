package com.jahez.websocket.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveExceptionsUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke(): Flow<AppThrowable> = repository.observeEvents()
}
