package com.base.clean.util.extension

import com.base.clean.util.constant.Constants
import com.base.clean.util.DatePattern
import java.text.SimpleDateFormat
import java.util.*

fun Date.formatToViewDayMonthYearWithDotAndDefaultTime(): String{
    val sdf = DatePattern.DATE_TIME_FORMATTER
    return sdf.format(this)
}

fun Date.formatToViewDayMonthYearWithSlash(): String{
    val sdf = SimpleDateFormat(DatePattern.DAY_MONTH_YEAR_FORMAT_WITH_SLASH, Constants.LOCALE_TR)
    return sdf.format(this)
}

fun Date.formatToViewDayMonthYearWithDot(): String{
    val sdf = SimpleDateFormat(DatePattern.DAY_MONTH_YEAR_FORMAT, Constants.LOCALE_TR)
    return sdf.format(this)
}