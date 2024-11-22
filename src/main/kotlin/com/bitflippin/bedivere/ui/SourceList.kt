package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Source
import javax.swing.DefaultListModel
import javax.swing.JList

class SourceList(editorState: EditorState) : JList<Source>() {

    private val list = DefaultListModel<Source>()

    init {
        model = list
        editorState.hub.addListener(this::onSourcesAdded)
        list.addAll(editorState.argmap.sources)
    }

    fun onSourcesAdded(source: Source, change: Change) {
        when (change) {
            Change.ADD -> list.addElement(source)
            Change.REMOVE -> list.removeElement(source)
            Change.UPDATE -> list.setElementAt(source, list.indexOf(source))
        }
    }
}