package com.myapp.dailynote.data

enum class TimerState {
    IDLE, // Not restart
    RUNNING, //counting
    PAUSED, //Stopped temporarily
    FINISHED   // done

}