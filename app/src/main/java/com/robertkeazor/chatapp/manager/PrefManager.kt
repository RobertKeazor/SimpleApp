package com.robertkeazor.chatapp.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.robertkeazor.chatapp.BuildConfig
import javax.inject.Inject

class PrefManager @Inject constructor(val context: Context) {
   val userKey = "${BuildConfig.APPLICATION_ID}_user"
   val userIdKey = "${BuildConfig.APPLICATION_ID}_user_id"
   val pref: SharedPreferences = context.getSharedPreferences("${context.packageName}_pref", Context.MODE_PRIVATE)

    fun setStringData(data: Pair<String, String>) = pref.edit {
            putString(data.first, data.second)
    }

    fun getStringData(key: String) = pref.getString(key, "")

    fun saveUserId(id: String) = setStringData(userIdKey to id)

    fun saveUserName(name: String) = setStringData(userKey to name)

    fun getUserId() = getStringData(userIdKey)

    fun getUserName() = getStringData(userKey)

    fun clearUserInfo() = pref.all.clear()
}
