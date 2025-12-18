package com.myapp.dailynote.data.viewmodel

import android.annotation.SuppressLint
import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.dailynote.data.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DateTimeViewModel @Inject constructor  (
    private val repository: ReminderRepository

): ViewModel(){
    @SuppressLint("ScheduleExactAlarm")
    fun saveReminder(
        dateMillis: Long,
        hour: Int,
        minute: Int,
        title: String = "Reminder",
        message: String = "This is a reminder"
    ){
        val calendar = Calendar.getInstance().apply {
            timeInMillis = dateMillis
            set(Calendar.HOUR_OF_DAY,hour)
            set(Calendar.MINUTE,minute)
            set(Calendar.SECOND,0)
            set(Calendar.MILLISECOND,0)
        }
        val triggerTimeMillis = calendar.timeInMillis
        viewModelScope.launch {
            repository.saveScheduleReminder(
                title = title,
                message = message,
                triggerTimeMillis = triggerTimeMillis
            )
        }
    }

}