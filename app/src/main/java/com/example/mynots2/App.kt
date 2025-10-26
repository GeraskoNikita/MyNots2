package com.example.mynots2

import android.app.Application
import androidx.room.Room
import com.example.mynots2.data.local.AppDatabase


class App : Application() {
    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java, "notes_data").allowMainThreadQueries().build()
    }
}