package com.myapp.dailynote.data.repository

import android.Manifest
import android.annotation.SuppressLint
import androidx.annotation.RequiresPermission
import com.myapp.dailynote.data.database.ReminderDao
import com.myapp.dailynote.data.entities.ReminderEntity
import com.myapp.dailynote.data.reminder_scheduler.ReminderScheduler
import javax.inject.Inject
import kotlin.math.max

class ReminderRepository @Inject constructor(
    private val dao: ReminderDao,
    private val scheduler: ReminderScheduler
) {
    @SuppressLint("ScheduleExactAlarm")
    @RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
    suspend fun saveScheduleReminder(
        title: String,
        message: String,
        triggerTimeMillis: Long
    ){
        dao.insetReminder(
            ReminderEntity(
                id = 0,
                title = title,
                message =  message,
                triggerTimeMillis = triggerTimeMillis
            )
        )
        scheduler.scheduleReminder(
            title = title,
            message = message,
            triggerTimeMillis = triggerTimeMillis,
        )
    }
    fun getAllReminders()= dao.getReminders()
}