package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JFrame

class MainFrame(editorState: EditorState): JFrame() {

    private val saveButton = SaveArgmapButton(editorState)
    private val addSourceButton = AddSourceButton(editorState)
    private val sourceList = SourceList(editorState)

    // FIXME Temporary
    private val sourceDetail = SourceDetail(editorState, editorState.argmap.sources.first())

    init {
        title = "Bedivere Argument Mapper"
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = null
        size = Dimension(300, 200)
        extendedState = MAXIMIZED_BOTH
        layout = FlowLayout()
        add(saveButton)
        add(addSourceButton)
        add(sourceList)
        add(sourceDetail)
    }
}