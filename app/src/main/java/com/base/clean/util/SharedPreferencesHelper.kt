package com.base.clean.util

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class SharedPreferencesHelper @Inject constructor(val mSharedPreferences: SharedPreferences) {
    fun put(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }
    fun get(key: String, defaultValue: String): String {
        return mSharedPreferences.getString(key, defaultValue)!!
    }
}