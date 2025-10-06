package com.jahez.websocket.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// It should be implemented in separated core module,
// but we have only one file
fun getCurrentDay(): String {
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val formattedDate = sdf.format(Date())

    return formattedDate
}

fun Long.toTimeString(): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val formattedTime = sdf.format(this)

    return formattedTime
}