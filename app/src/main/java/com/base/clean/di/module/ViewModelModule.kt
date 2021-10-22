package com.base.clean.di.module

import androidx.lifecycle.ViewModel
import com.base.clean.di.ViewModelKey
import com.base.clean.viewmodel.WebviewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WebviewViewModel::class)
    internal abstract fun bindWebviewViewModel(viewModel: WebviewViewModel): ViewModel

}