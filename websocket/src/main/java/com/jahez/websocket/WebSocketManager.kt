package com.jahez.websocket

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketManager @Inject constructor(
    private val client: OkHttpClient,
    private val request: Request
) {
    private var webSocket: WebSocket? = null

    fun connect(listener: WebSocketListener) {
        webSocket = client.newWebSocket(request, listener)
    }

    fun send(message: String): Boolean {
        return webSocket?.send(message) == true
    }

    fun close(code: Int = 1000, reason: String = "Client closed") {
        webSocket?.close(code, reason)
    }
}