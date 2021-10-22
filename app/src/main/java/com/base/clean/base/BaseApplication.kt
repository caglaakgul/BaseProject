package com.base.clean.base

import com.base.clean.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
    }
    companion object {
        lateinit var INSTANCE: BaseApplication
    }
}
