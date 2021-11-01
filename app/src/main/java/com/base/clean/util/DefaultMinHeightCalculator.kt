package com.base.clean.util

/**
 * Created on 5/21/2018.
 */
open class DefaultMinHeightCalculator(override var dialog: BottomSheetDialogUtil) : BottomSheetDialogMinHeightCalculator {
    override fun calculateMinHeight(): Int {
        return dialog.context.resources.displayMetrics.heightPixels / 4
    }
}