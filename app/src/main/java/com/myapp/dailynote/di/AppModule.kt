package com.myapp.dailynote.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.myapp.dailynote.data.local.AppDataBase
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
    fun providerDatabase(@ApplicationContext context: Context) : AppDataBase{
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFolderDao(db: AppDataBase) = db.folderUserDao()

    @Provides
    @Singleton
    fun provideTodo(todo: AppDataBase) = todo.todoUserDao()


}