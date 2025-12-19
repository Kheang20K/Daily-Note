package com.myapp.dailynote.di

import android.content.Context
import androidx.room.Room
import com.myapp.dailynote.data.database.NoteDao
import com.myapp.dailynote.data.database.NoteDatabase
import com.myapp.dailynote.data.database.ReminderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase{
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "daily_note_db"
        ).build()
    }

    @Provides
    fun provideDao(db: NoteDatabase): NoteDao{
        return db.userDao()
    }
    @Provides
    fun provideReminderDao(db: NoteDatabase): ReminderDao {
        return db.reminderDao()
    }


}