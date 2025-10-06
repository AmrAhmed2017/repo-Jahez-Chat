package com.jahez.jahezchat.presentation.ui

import com.jahez.jahezchat.presentation.model.ChatUIMessage
import com.jahez.websocket.domain.AppThrowable

data class ChatUIState(
    val data: List<ChatUIMessage> = emptyList(),
    val error: AppThrowable? = null
)
