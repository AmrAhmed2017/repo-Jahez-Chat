package com.jahez.websocket.model

data class ChatWebMessage(
    val message: String,
    val time: String,
    val isMine: Boolean,
    val avatarUrl: String = "https://avatar.iran.liara.run/public/4"
)
