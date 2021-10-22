package com.base.clean.util.extension

import android.content.SharedPreferences

fun SharedPreferences.put(name: String, any: Any) {
    when (any) {
        is String -> edit().putString(name, any).apply()
        is Int -> edit().putInt(name, any).apply()
        is Boolean -> edit().putBoolean(name, any).apply()
        is Float -> edit().putFloat(name, any).apply()
        is Long -> edit().putLong(name, any).apply()
    }
}

fun SharedPreferences.remove(name:String){
    edit().remove(name).apply()
}