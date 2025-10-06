package com.jahez.websocket.domain

import com.jahez.websocket.WebSocketManager
import com.jahez.websocket.model.ChatWebMessage
import com.jahez.websocket.util.toTimeString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRemoteDataSource @Inject constructor(
    private val manager: WebSocketManager
) {

    private val _incomingMessage = MutableSharedFlow<ChatWebMessage>(replay = 0)
    val incomingMessage = _incomingMessage.asSharedFlow()

    private val _events = MutableSharedFlow<AppThrowable>(replay = 0)
    val events = _events.asSharedFlow()

    private val listener = object : WebSocketListener() {

        override fun onMessage(webSocket: WebSocket, text: String) {
            CoroutineScope(Dispatchers.IO).launch {
                _incomingMessage.emit(
                    ChatWebMessage(
                        message = text,
                        time = System.currentTimeMillis().toTimeString(),
                        isMine = false
                    )
                )
            }
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            CoroutineScope(Dispatchers.IO).launch { _events.emit(AppThrowable.SERVER_CONNECTION_ERROR) }
        }
    }

    fun connect() {
        manager.connect(listener)
    }

    fun send(message: String): Boolean {
        return manager.send(message)
    }

    fun close() {
        manager.close()
    }
}