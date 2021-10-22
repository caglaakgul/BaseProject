package com.base.clean.base

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {
    val TAG = this.javaClass.name

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    fun <T : ViewModel> viewModelOf(c: Class<T>): T {
        return ViewModelProvider(this, viewModelFactory).get(c)
    }

    abstract fun getTitle(): String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showMessageDialog(
        title: String,
        message: String,
        onNegativeCallback: MaterialDialog.SingleButtonCallback,
        cancellable: Boolean = true
    ) {
        if (message.isNotEmpty()) {
            MaterialDialog.Builder(requireActivity())
                .title(title)
                .content(message)
                .onNegative(onNegativeCallback)
                .cancelable(cancellable)
                .show()
        }
    }

    fun showMessageDialog(
        title: String,
        message: String,
        negativeTitle: String,
        positiveTitle: String,
        onPositiveCallback: MaterialDialog.SingleButtonCallback,
        onNegativeCallback: MaterialDialog.SingleButtonCallback,
        cancellable: Boolean = true
    ) {
        if (message.isNotEmpty()) {
            MaterialDialog.Builder(requireActivity())
                .title(title)
                .content(message)
                .positiveText(positiveTitle)
                .negativeText(negativeTitle)
                .onPositive(onPositiveCallback)
                .onNegative(onNegativeCallback)
                .cancelable(cancellable)
                .show()
        }
    }

    fun showProgressDialog(messageResourceId: Int = 0, tag: String) {
        if (isAdded) {
            (activity as BaseActivity).showProgressDialog(messageResourceId, tag)
        }
    }

    fun hideProgressDialog(tag: String) {
        if (isAdded) {
            (activity as BaseActivity).hideProgressDialog(tag)
        }
    }
}