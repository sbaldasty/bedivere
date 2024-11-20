package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Source
import javax.swing.JLabel
import javax.swing.JPanel

class SourcePanel(editorState: EditorState) : JPanel() {

    init {
        editorState.hub.addListener(this::onSourcesAdded)
    }

    fun onSourcesAdded(source: Source, change: Change) {
        when (change) {
            Change.ADD -> add(JLabel("added $source"))
            Change.REMOVE -> TODO()
            Change.UPDATE -> TODO()
        }
    }
}