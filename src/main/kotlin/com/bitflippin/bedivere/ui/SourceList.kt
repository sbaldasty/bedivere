package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Source
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.DefaultListModel
import javax.swing.JList

class SourceList(editorState: EditorState) : JList<Source>() {

    private val list = DefaultListModel<Source>()

    init {
        model = list
        addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                if (e.clickCount == 2) {
                    val index = locationToIndex(e.point);
                    if (index >= 0) {
                        val item = model.getElementAt(index);
                        editorState.tabManager.open(SourceDetail(editorState, item))
                    }
                }
            }
        })
        editorState.hub.sourceListeners.add(this::onSourceChange)
        list.addAll(editorState.argmap.sources)
    }

    fun onSourceChange(source: Source, change: Change) {
        when (change) {
            Change.ADD -> list.addElement(source)
            Change.REMOVE -> list.removeElement(source)
            Change.UPDATE -> list.setElementAt(source, list.indexOf(source))
        }
    }
}