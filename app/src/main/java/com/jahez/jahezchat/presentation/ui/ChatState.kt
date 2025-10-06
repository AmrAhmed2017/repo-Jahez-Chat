package com.example.theculinarycompanion.presentation.ui

import com.jahez.jahezchat.presentation.model.ChatUIMessage

data class ChatState(
    val data: List<ChatUIMessage> = emptyList()
)