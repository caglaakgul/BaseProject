package com.base.clean.util

import com.base.clean.util.constant.Constants
import java.text.SimpleDateFormat

class DatePattern {
    companion object {
        val MONTH_FULL_NAME = "MMMM"
        val MONTH_SHORT_NAME = "MMM"
        val YEAR_SHORT = "yy"
        val DAY_OF_MONTH = "dd"
        val DAY_OF_WEEK_SHORT = "EE"
        val DAY_MONTH_YEAR_FORMAT = "dd.MM.yyyy"
        val DAY_MONTH_YEAR_FORMAT_WITH_SLASH = "dd/MM/yyyy"
        val DAY_MONTH_YEAR_DAYNAME_WITH_SPACE_FORMAT = "dd MMM yyyy, EEE"
        val DAY_MONTH_DAY_FULL_NAME_WITH_SPACE_FORMAT = "dd MMM EEEE"
        val DAY_MONTH_YEAR_DAY_FULL_NAME_WITH_SPACE_FORMAT = "dd MMMM yyyy, EEEE"
        val TIME_DAY_MONTH_YEAR_DAY_FULL_NAME_WITH_SPACE_FORMAT = "HH:mm  dd MMMM yyyy, EEEE"
        val DAYTEXT_MONTH_YEAR = "dd MMM EEEE"
        val SHORT_DAYTEXT_MONTH_YEAR = "dd MMM EEE"
        val DAY_LONG_NAME = "EEEE"
        val DAY_MONTH_NAME = "dd MMMM"
        val DAYTEXT_MONTHNAME = "dd MMMM EEEE"
        val DAY_MONTHTEXT_YEAR = "dd MMMM yyyy"
        val DAY_MONTH_YEAR_FORMAT_DASH = "dd-MM-yyyy"
        val YEAR_MONTH_DAY_FORMAT_DASH = "yyyy-MM-dd"
        val YEAR_MONTH_DAY_WITH_TIME_FORMAT_DASH = "yyyy-MM-dd HH:mm:sssss"
        val DATE_TIME_FORMATTER = SimpleDateFormat("dd.MM.yyyy HH:mm", Constants.LOCALE_TR)
        val DATE_DAY_MONTHNAME_YEAR_SPACE = SimpleDateFormat("dd MMMM yyyy", Constants.LOCALE_TR)
        val SHORT_DAYTEXT_MONTH_YEAR_FORMATTER = SimpleDateFormat(SHORT_DAYTEXT_MONTH_YEAR, Constants.LOCALE_TR)
        val MONTH_YEAR_V1 = "$MONTH_FULL_NAME'$YEAR_SHORT"
        val DAY_MONTH_V1 = "$DAY_OF_MONTH $MONTH_SHORT_NAME"
    }
}