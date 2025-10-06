package com.jahez.jahezchat.mapper

import com.jahez.jahezchat.presentation.model.ChatUIMessage
import com.jahez.storage.model.ChatMessageEntity
import com.jahez.websocket.model.ChatWebMessage

fun ChatWebMessage.toEntity(): ChatMessageEntity =
    ChatMessageEntity(
        text = this.message,
        time = this.time,
        isMine = this.isMine,
        avatarUrl = this.avatarUrl
    )

fun ChatUIMessage.toWebMessage(): ChatWebMessage =
    ChatWebMessage(
        message = this.message,
        time = this.time,
        isMine = this.isMine,
        avatarUrl = this.avatarUrl
    )

fun ChatWebMessage.toUIMessage(): ChatUIMessage =
    ChatUIMessage(
        message = this.message,
        time = this.time,
        isMine = this.isMine,
        avatarUrl = this.avatarUrl
    )