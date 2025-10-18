package com.example.mynots2.data.local_data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.mynots2.core.AppKey

class Pref(context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences(AppKey.PREF_KEY, Context.MODE_PRIVATE)

    fun saveOnBoard(key: String, value: Boolean){
        pref.edit{
            putBoolean(key, value)
        }
    }

    fun isUserSeen(): Boolean{
        return pref.getBoolean(AppKey.ON_BOARD_KEY, false)
    }
}