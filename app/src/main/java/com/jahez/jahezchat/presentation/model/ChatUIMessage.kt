package com.jahez.jahezchat.presentation.model

data class ChatUIMessage(
    val text: String,
    val time: String,
    val isMine: Boolean,
    val avatarUrl: String = "https://avatar.iran.liara.run/public/3"
)