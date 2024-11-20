package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.EditorState
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JFrame

class MainFrame(editorState: EditorState): JFrame() {

    private val saveButton = SaveArgmapButton(editorState)

    init {
        title = "Bedivere Argument Mapper"
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = null
        size = Dimension(300, 200)
        extendedState = MAXIMIZED_BOTH
        layout = FlowLayout()
        add(saveButton)
    }
}