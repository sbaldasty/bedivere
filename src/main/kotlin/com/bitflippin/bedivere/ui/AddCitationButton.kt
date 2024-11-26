package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.addCitation
import com.bitflippin.bedivere.model.Claim
import javax.swing.JButton

class AddCitationButton(editorState: EditorState, claim: Claim) : JButton() {

    init {
        text = "Add Citation"
        addActionListener { addCitation(editorState, claim) }
    }
}