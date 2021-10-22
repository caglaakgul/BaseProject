package com.base.clean.util

import com.base.clean.base.BaseApplication

internal class ResourcesUtil {

    companion object{
        fun string(resourceId: Int): String {
            return BaseApplication.INSTANCE.getString(resourceId)
        }
    }
}