package com.jahez.websocket.util

import java.text.SimpleDateFormat
import java.util.Locale

// It should be implemented in separated core module,
// but we have only one file
fun Long.toTimeString(): String {

    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val formattedTime = sdf.format(this)

    return formattedTime
}