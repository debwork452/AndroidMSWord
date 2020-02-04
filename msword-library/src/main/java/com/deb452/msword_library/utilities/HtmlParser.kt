package com.deb452.msword_library.utilities

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import com.deb452.msword_library.models.HtmlTag

class HtmlParser(private val context: Context) {
    var parentView: LinearLayout = LinearLayout(context)

    companion object {
        fun matchesTag(test: String): Boolean {
            for (tag in HtmlTag.values()) {
                if (tag.name == test) {
                    return true
                }
            }
            return false
        }
    }

    init {
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        parentView.layoutParams = params
    }
}