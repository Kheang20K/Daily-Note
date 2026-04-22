package com.myapp.dailynote.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.protobuf.Value
import com.myapp.dailynote.data.model.TimeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(name = "CounterTimer")
class DataStoreInstance(private val context: Context) {

    companion object{
        val START_TIMER = longPreferencesKey("start_key")
        val DURATION = intPreferencesKey("duration")
        val IS_RUNNING = booleanPreferencesKey("is_running")
    }

    suspend fun saveTimer(startTime: Long, duration: Int) {
        context.dataStore.edit {
            it[START_TIMER] = startTime
            it[DURATION] = duration
            it[IS_RUNNING] = true
        }
    }

    suspend fun clearTimer(){
        context.dataStore.edit {
            it.clear()
        }
    }

    val timerFlow: Flow<TimeData> = context.dataStore.data.map { prefs->
        TimeData(
            startTime = prefs[START_TIMER] ?: 0L,
            duration = prefs[DURATION] ?: 0,
            isRunning = prefs[IS_RUNNING] ?: false
        )
    }
}