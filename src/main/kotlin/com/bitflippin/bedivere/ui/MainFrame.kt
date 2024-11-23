package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JSplitPane

class MainFrame(editorState: EditorState): JFrame() {

    private val saveButton = SaveArgmapButton(editorState)
    private val addSourceButton = AddSourceButton(editorState)
    private val sourceList = SourceList(editorState)

    // FIXME Temporary
    private val sourceDetail = SourceDetail(editorState, editorState.argmap.sources.first())

    init {
        title = "Bedivere Argument Mapper"
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(300, 200)
        extendedState = MAXIMIZED_BOTH

        val buttonBar = JPanel(FlowLayout())
        buttonBar.add(saveButton)
        buttonBar.add(addSourceButton)
        add(buttonBar, BorderLayout.NORTH)

        val splitPane = JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sourceList, editorState.tabManager)
        splitPane.dividerLocation = 200
        add(splitPane, BorderLayout.CENTER)
    }
}