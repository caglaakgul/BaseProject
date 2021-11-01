package com.base.clean.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.base.clean.R
import com.base.clean.util.BottomSheetDialogUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseBottomSheetDialog : BottomSheetDialogFragment() {
    var TAG = this.javaClass.name

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dismissListener: DialogInterface.OnDismissListener? = null

    fun <T : ViewModel> viewModelOf(c: Class<T>): T {
        return ViewModelProviders.of(this, viewModelFactory).get(c)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialogUtil(context!!, R.style.Theme_AppCompat_Light)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var tag = this.javaClass.canonicalName?:""
        if(tag.contains("com.mobilatolye.android.enuygun.")){
            tag= tag.replace("com.mobilatolye.android.enuygun.","")
        }
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        var  tag = this.javaClass.canonicalName?:""
        if(tag.contains("com.mobilatolye.android.enuygun.")){
            tag = tag.replace("com.mobilatolye.android.enuygun.","")
        }
        super.onResume()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.onDismiss(dialog)
    }

    override fun onStart() {
        super.onStart()
        if (dialog is BottomSheetDialogUtil) {
            val dialog = dialog as BottomSheetDialogUtil

            dialog.mBehavior?.skipCollapsed = true
            dialog.mBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}