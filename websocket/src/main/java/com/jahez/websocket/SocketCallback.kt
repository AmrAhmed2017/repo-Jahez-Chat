package com.jahez.websocket

interface SocketCallback {
    fun onMessageReceived(message: String)
    fun onConnected()
    fun onDisconnected(reason: String)
}