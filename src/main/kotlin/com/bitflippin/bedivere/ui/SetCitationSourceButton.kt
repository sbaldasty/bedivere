package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.setCitationSource
import com.bitflippin.bedivere.model.Citation
import javax.swing.JButton

class SetCitationSourceButton(editorState: EditorState, citation: () -> Citation) : JButton() {

    init {
        text = "Set Source"
        addActionListener {
            setCitationSource(editorState, citation())
        }
    }
}