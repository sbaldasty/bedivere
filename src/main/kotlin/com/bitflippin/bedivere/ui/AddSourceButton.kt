package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.addSource
import javax.swing.JButton

class AddSourceButton(editorState: EditorState) : JButton() {

    init {
        text = "Add Source"
        addActionListener { addSource(editorState) }
    }
}