package com.bitflippin.bedivere

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.Hub
import com.bitflippin.bedivere.model.loadModel
import com.bitflippin.bedivere.swing.TabManager
import com.bitflippin.bedivere.ui.MainFrame
import java.io.File
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        val devfile = File("dev.argmap")
        if (!devfile.exists()) {
            devfile.writeText("{}")
        }
        val argmap = loadModel(devfile)
        val state = EditorState(argmap, devfile, Hub(), TabManager())
        val frame = MainFrame(state)
        frame.isVisible = true
    }
}