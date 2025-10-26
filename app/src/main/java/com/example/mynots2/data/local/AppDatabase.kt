package com.example.mynots2.data.local

import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynots2.data.model.NotesModel

@Database(entities = [NotesModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun dao(): NotesDao
}