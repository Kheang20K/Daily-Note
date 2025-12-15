//package com.myapp.dailynote.data.database
//
//import android.content.Context
//import androidx.room.Room
//
//object NoteDataBaseProvider {
//    @Volatile
//    private var INSTANCE: NoteDatabase? = null
//
//    fun getDataBase(context: Context): NoteDatabase {
//        return INSTANCE ?: synchronized(this) {
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                NoteDatabase::class.java,   // ✅ MUST be Database class
//                "note_db"
//            ).build()
//            INSTANCE = instance
//            instance
//        }
//    }
//}