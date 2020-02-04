package com.deb452.msword_library

import android.view.View
import com.deb452.msword_library.EditorCore
import com.deb452.msword_library.components_classes.ComponentsWrapper
import com.deb452.msword_library.models.EditorContent
import com.deb452.msword_library.models.EditorType
import com.deb452.msword_library.models.Node
import org.jsoup.nodes.Element
import java.util.ArrayList

abstract class EditorComponent(private val editorCore: EditorCore) {
    @JvmField
    protected var componentsWrapper: ComponentsWrapper? = null
    abstract fun getContent(view: View?): Node?
    abstract fun getContentAsHTML(
        node: Node?,
        content: EditorContent?
    ): String?

    abstract fun renderEditorFromState(
        node: Node?,
        content: EditorContent?
    )

    abstract fun buildNodeFromHTML(element: Element?): Node?
    abstract fun init(componentsWrapper: ComponentsWrapper?)
    protected fun getNodeInstance(view: View?): Node {
        val node = Node()
        val type = editorCore.getControlType(view)
        node.type = type
        node.content = ArrayList()
        return node
    }

    protected fun getNodeInstance(type: EditorType?): Node {
        val node = Node()
        node.type = type
        node.content = ArrayList()
        return node
    }

}