package com.base.clean.common

import android.content.SharedPreferences
import com.base.clean.util.SharedPreferencesHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectSharedPreferences @Inject constructor(preferences: SharedPreferences) : SharedPreferencesHelper(preferences) {

    companion object {
        const val PREF_KEY_ACCESS_TOKEN = "access-token"
        const val PREF_KEY_REFRESH_TOKEN = "refresh-token"
    }

    open fun setAccessToken(token: String) {
        put(PREF_KEY_ACCESS_TOKEN, token)
    }

    open fun getAccessToken(): String {
        return get(PREF_KEY_ACCESS_TOKEN, "")
    }
}