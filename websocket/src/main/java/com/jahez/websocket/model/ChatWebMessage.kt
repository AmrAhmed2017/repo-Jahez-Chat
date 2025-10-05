package com.jahez.websocket.model

data class ChatWebMessage(
    val text: String,
    val time: String,
    val isMine: Boolean,
    val avatarUrl: String
)
