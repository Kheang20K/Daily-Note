package com.myapp.dailynote.ui.component

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun formatTime(hour: Int, minute: Int): String{
    val calender = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY,hour)
        set(Calendar.MINUTE,minute)

    }
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return formatter.format(calender.time)
}

fun formatDate(millis : Long): String{
    val formatter = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


fun formatReminderDateTime(
    dateMillis: Long,
    hour: Int,
    minute: Int
): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = dateMillis
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val formatter = SimpleDateFormat(
        "dd MMM yyyy • hh:mm a",
        Locale.getDefault()
    )
    return formatter.format(calendar.time)
}

