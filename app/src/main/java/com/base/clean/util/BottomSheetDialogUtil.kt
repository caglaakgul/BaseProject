package com.base.clean.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.base.clean.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback

class BottomSheetDialogUtil : AppCompatDialog {
    var mBehavior: BottomSheetBehavior<FrameLayout>? = null
    var minHeightCalculator: BottomSheetDialogMinHeightCalculator
    var mCancelable = true
    private val bottomSheet: FrameLayout? = null
    private var mCanceledOnTouchOutside = true
    private var mCanceledOnTouchOutsideSet = false
    private val mBottomSheetCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(
            bottomSheet: View,
            @BottomSheetBehavior.State newState: Int
        ) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                cancel()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    @JvmOverloads
    constructor(context: Context, @StyleRes theme: Int = 0) : super(
        context,
        getThemeResId(context, theme)
    ) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        minHeightCalculator = DefaultMinHeightCalculator(this)
    }

    protected constructor(
        context: Context, cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        minHeightCalculator = DefaultMinHeightCalculator(this)
        mCancelable = cancelable
    }

    override fun setContentView(@LayoutRes layoutResId: Int) {
        super.setContentView(wrapInBottomSheet(layoutResId, null, null))
    }

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        val window = window
        if (window != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            }
            window.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun setContentView(view: View) {
        super.setContentView(wrapInBottomSheet(0, view, null))
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams?) {
        super.setContentView(wrapInBottomSheet(0, view, params))
    }

    override fun setCancelable(cancelable: Boolean) {
        super.setCancelable(cancelable)
        if (mCancelable != cancelable) {
            mCancelable = cancelable
            if (mBehavior != null) {
                mBehavior!!.isHideable = cancelable
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (mBehavior != null) {
            mBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun setCanceledOnTouchOutside(cancel: Boolean) {
        super.setCanceledOnTouchOutside(cancel)
        if (cancel && !mCancelable) {
            mCancelable = true
        }
        mCanceledOnTouchOutside = cancel
        mCanceledOnTouchOutsideSet = true
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun wrapInBottomSheet(
        layoutResId: Int,
        view: View?,
        params: ViewGroup.LayoutParams?
    ): View {
        var view = view
        val container = View.inflate(
            context,
            R.layout.dialog_bottom_sheet, null
        ) as FrameLayout
        val coordinator: CoordinatorLayout = container.findViewById(R.id.coordinator)

        if (layoutResId != 0 && view == null) {
            view = layoutInflater.inflate(layoutResId, coordinator, false)
        }

        val bottomSheet = coordinator.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        bottomSheet.minimumHeight = minHeightCalculator.calculateMinHeight()
        mBehavior = BottomSheetBehavior.from(bottomSheet)
        mBehavior!!.setBottomSheetCallback(mBottomSheetCallback)
        mBehavior!!.isHideable = mCancelable

        if (params == null) {
            bottomSheet.addView(view)
        } else {
            bottomSheet.addView(view, params)
        }

        coordinator.findViewById<View>(R.id.touch_outside).setOnClickListener {
            if (mCancelable && isShowing && shouldWindowCloseOnTouchOutside()) {
                cancel()
            }
        }
        // Handle accessibility events
        ViewCompat.setAccessibilityDelegate(
            bottomSheet,
            object : AccessibilityDelegateCompat() {
                override fun onInitializeAccessibilityNodeInfo(
                    host: View,
                    info: AccessibilityNodeInfoCompat
                ) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    if (mCancelable) {
                        info.addAction(AccessibilityNodeInfoCompat.ACTION_DISMISS)
                        info.isDismissable = true
                    } else {
                        info.isDismissable = false
                    }
                }

                override fun performAccessibilityAction(
                    host: View,
                    action: Int,
                    args: Bundle
                ): Boolean {
                    if (action == AccessibilityNodeInfoCompat.ACTION_DISMISS && mCancelable) {
                        cancel()
                        return true
                    }
                    return super.performAccessibilityAction(host, action, args)
                }
            })
        bottomSheet.setOnTouchListener { view1: View?, event: MotionEvent? -> true }
        return container
    }

    fun shouldWindowCloseOnTouchOutside(): Boolean {
        if (!mCanceledOnTouchOutsideSet) {
            if (Build.VERSION.SDK_INT < 11) {
                mCanceledOnTouchOutside = true
            } else {
                val a = context.obtainStyledAttributes(intArrayOf(R.attr.windowCloseOnTouchOutside))
                mCanceledOnTouchOutside = a.getBoolean(0, true)
                a.recycle()
            }
            mCanceledOnTouchOutsideSet = true
        }
        return mCanceledOnTouchOutside
    }

    fun setMinHeight(minHeight: Int) {
        bottomSheet!!.minimumHeight = minHeight
    }

    companion object {
        private fun getThemeResId(context: Context, themeId: Int): Int {
            var themeId = themeId
            if (themeId == 0) {
                val outValue = TypedValue()
                themeId = if (context.theme.resolveAttribute(
                        R.attr.bottomSheetDialogTheme, outValue, true
                    )
                ) {
                    outValue.resourceId
                } else {
                    R.style.Theme_Design_Light_BottomSheetDialog
                }
            }
            return themeId
        }
    }

}

interface BottomSheetDialogMinHeightCalculator {
    var dialog: BottomSheetDialogUtil
    fun calculateMinHeight(): Int
}

