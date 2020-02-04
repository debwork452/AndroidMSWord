package com.deb452.msword_library.utilities

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.widget.Toast
import java.util.regex.Pattern

object Utilities {
    fun getScreenDimension(context: Context): IntArray {
        val display = (context as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y
        return intArrayOf(width, height)
    }

    fun toastItOut(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun containsString(text: String?): Boolean {
        val HTML_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>"
        val pattern = Pattern.compile(HTML_PATTERN)
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }

    fun dpToPx(context: Context, dp: Float): Int {
        val metrics = context.resources.displayMetrics
        val px = dp * (metrics.densityDpi / 160f)
        return px.toInt()
    }

    fun pxToDp(context: Context, px: Float): Int {
        return (px / context.resources.displayMetrics.density).toInt()
    }
}