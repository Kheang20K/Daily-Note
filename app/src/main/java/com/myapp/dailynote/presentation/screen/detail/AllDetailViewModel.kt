package com.myapp.dailynote.presentation.screen.detail


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.dailynote.data.TimerState
import com.myapp.dailynote.data.local.FolderEntity
import com.myapp.dailynote.data.repository.FolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AllDetailViewModel @Inject constructor(
    private val repo : FolderRepository,
) : ViewModel(){
/*
    mutableStateOf change to mutableIntStateOf
 */
    var selectedMinute by mutableIntStateOf(5)
        private set
    var remainingSeconds by mutableIntStateOf(0)
        private set
    var timerState by mutableStateOf(TimerState.IDLE)
    private var timerJob: Job? = null

    private val _selectedTimerDate = MutableStateFlow<Long?>(null)
    val selectedTimerDate: StateFlow<Long?> = _selectedTimerDate
    private val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    fun setTimer(millis: Long?){
        _selectedTimerDate.value = millis
    }
    fun setDate(){
        _selectedTimerDate.value = null
    }
    fun formatTime(millis:Long?):String{
        if (millis == null) return "No time Selected"
        return timeFormatter.format(Date(millis))
    }
    fun formatDate(millis : Long?): String{
        if (millis == null) return "No date"
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return millis?.let {
            formatter.format(Date(it))
        } ?: "Select date"
    }

    fun setMinute(minute: Int){
        selectedMinute = minute
        remainingSeconds = minute * 60
        timerState = TimerState.IDLE
    }

    fun startTimer(){
        if (timerState == TimerState.RUNNING) return

        if (remainingSeconds == 0){
            remainingSeconds = selectedMinute * 60
        }
        timerState = TimerState.RUNNING

        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (remainingSeconds >0 && timerState == TimerState.RUNNING){
                delay(1000L)
                remainingSeconds--
            }
            if (remainingSeconds == 0){
                timerState = TimerState.FINISHED
            }
        }
    }

    fun pauseTimer(){
        timerState = TimerState.PAUSED
        timerJob?.cancel()
    }

    fun resumeTimer(){
        startTimer()
    }

    fun resetTimer(){
        timerJob?.cancel()
        timerState = TimerState.IDLE
        remainingSeconds = selectedMinute * 60
    }

    fun endTimer(){
        timerJob?.cancel()
        timerState = TimerState.IDLE
        remainingSeconds = 0
    }

    //    chip on list
    val folders = repo.getAllFolderUser()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    /* insert By Id
        */
    fun insertFolder(name: String, color: Int, icon: Int){
        viewModelScope.launch {
            repo.insertFolderUser(
                FolderEntity(
                    folderName = name,
                    colorPicker = color,
                    iconPicker = icon
                )
            )
        }
    }
    /* Delete By Id
    */
    fun deleteCate(id: Int){
        viewModelScope.launch {
            try {
                repo.deleteTodoById(id)
                repo.deleteFolderById(id)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}