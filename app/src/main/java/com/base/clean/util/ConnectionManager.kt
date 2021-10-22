package com.base.clean.util

import android.content.Context
import android.net.ConnectivityManager

class ConnectionManager {
    companion object {
        fun isConnected(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val i = connectivityManager.activeNetworkInfo ?: return false
            return if (!i.isConnected) false else i.isAvailable
        }
    }
}