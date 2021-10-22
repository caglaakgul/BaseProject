package com.base.clean.base

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.base.clean.R
import com.base.clean.ui.common.ProgressFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity(){
    val TAG = this.javaClass.name

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    fun <T : ViewModel> viewModelOf(c: Class<T>) : T {
        return ViewModelProvider(this, viewModelFactory).get(c)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(this.TAG, true)
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
        if(message.isNotEmpty()){
            MaterialDialog.Builder(this@BaseActivity)
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

    fun showProgressDialog(messageResource: Int = 0, tag: String) {
        val progressIcon = R.drawable.ic_launcher_foreground

        showProgressDialog(messageResource, tag, progressIcon)
    }

    fun showProgressDialog(messageResource: Int = 0, tag: String, iconResource: Int) {

        var progressDialog = supportFragmentManager.findFragmentByTag(tag)
        if (progressDialog == null) {
            progressDialog = ProgressFragment.newInstance(0, iconResource)
        }

        (progressDialog as ProgressFragment).setIcon(iconResource)

        if (messageResource != 0) {
            val handler = Handler()
            handler.postDelayed({
                (progressDialog as ProgressFragment).setText(messageResource)
            }, 400)
        }

        if (!progressDialog.isVisible && supportFragmentManager != null && !supportFragmentManager.isDestroyed) {
            supportFragmentManager.executePendingTransactions()
            (progressDialog as DialogFragment).show(supportFragmentManager, tag)
        }
    }

    fun hideProgressDialog(tag: String) {
        val progressDialog = supportFragmentManager.findFragmentByTag(tag)
        if (progressDialog != null) {
            val dialogFragment = progressDialog as DialogFragment
            if (dialogFragment.isVisible) {
                dialogFragment.dismissAllowingStateLoss()
            }
        }
    }
}