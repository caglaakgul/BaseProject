package com.base.clean.di.module

import com.base.clean.ui.common.WebViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun contributeMovieFragment(): WebViewFragment
}