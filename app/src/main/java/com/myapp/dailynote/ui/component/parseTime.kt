package com.myapp.dailynote.ui.component

import java.text.SimpleDateFormat
import java.util.*
fun parseDateToMillis(dateStr: String?): Long? {
    if (dateStr.isNullOrEmpty()) return null
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        sdf.parse(dateStr)?.time
    } catch (e: Exception) {
        null
    }
}

// Convert time string to hour and minute
fun parseTimeToHourMinute(timeStr: String?): Pair<Int?, Int?> {
    if (timeStr.isNullOrEmpty()) return Pair(null, null)
    return try {
        val parts = timeStr.split(":")
        val hour = parts.getOrNull(0)?.toIntOrNull()
        val minute = parts.getOrNull(1)?.toIntOrNull()
        Pair(hour, minute)
    } catch (e: Exception) {
        Pair(null, null)
    }
}