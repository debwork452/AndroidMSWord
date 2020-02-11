package com.deb452.msword_library

import android.graphics.Bitmap
import android.net.Uri
import android.text.Editable
import android.view.View
import android.widget.EditText

interface EditorListener {
    fun onTextChanged(editText: EditText?, text: Editable?)
    fun onUpload(image: Bitmap?, uri: Uri, uuid: String?)
    fun onRenderMacro(
        name: String?,
        props: Map<String?, Any?>?,
        index: Int
    ): View?
}