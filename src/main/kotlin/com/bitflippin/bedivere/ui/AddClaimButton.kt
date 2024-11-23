package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.addClaim
import javax.swing.JButton

class AddClaimButton(editorState: EditorState) : JButton() {

    init {
        text = "Add Claim"
        addActionListener { addClaim(editorState) }
    }
}