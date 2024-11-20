package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.saveModel
import javax.swing.JButton

class SaveArgmapButton(editorState: EditorState) : JButton() {

    init {
        text = "Save"
        addActionListener { saveModel(editorState.file, editorState.argmap) }
    }

}