package com.bitflippin.bedivere

import com.bitflippin.bedivere.model.loadModel
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
        val state = EditorState(argmap, devfile)
        val frame = MainFrame(state)
        frame.isVisible = true
    }
}