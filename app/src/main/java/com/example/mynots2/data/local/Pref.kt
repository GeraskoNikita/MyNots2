package com.example.mynots2.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.mynots2.core.AppKey

class Pref(context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences(AppKey.PREF_KEY, Context.MODE_PRIVATE)

    fun saveOnBoard( value: Boolean){
        pref.edit{
            putBoolean(AppKey.ON_BOARD_KEY, value)
        }
    }

    fun isUserSeen(): Boolean{
        return pref.getBoolean(AppKey.ON_BOARD_KEY, false)
    }

    fun saveUserAuth(bool: Boolean) {
        pref.edit{
            putBoolean(AppKey.ON_AUTH, bool)
        }
    }

    fun isUserAuth(): Boolean {
        return pref.getBoolean(AppKey.ON_AUTH, false)
    }


}