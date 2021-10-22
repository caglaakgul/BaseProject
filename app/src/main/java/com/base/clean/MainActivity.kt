package com.base.clean

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ProgressBar
import com.base.clean.base.BaseActivity

import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.preference.PreferenceManager


class MainActivity : BaseActivity() {
    // lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // prefs = PreferenceManager.getDefaultSharedPreferences(this)
    }
}