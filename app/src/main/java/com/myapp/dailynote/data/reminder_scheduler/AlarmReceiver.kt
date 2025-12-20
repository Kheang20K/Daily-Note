package com.myapp.dailynote.data.reminder_scheduler

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra(EXTRA_TITLE) ?: "Reminder"
        val content = intent.getStringExtra(EXTRA_MESSAGE) ?: "You have a reminder"


        val channelId = "reminder_channel"

        val notification = NotificationCompat.Builder(context,channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(content)
            .setContentText(title)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(System.currentTimeMillis().toInt(),notification)



    }
}

const val EXTRA_TITLE = "extra_title"
const val EXTRA_MESSAGE = "extra_message"