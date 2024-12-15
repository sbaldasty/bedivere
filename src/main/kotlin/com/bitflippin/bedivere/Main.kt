package com.bitflippin.bedivere

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.Hub
import com.bitflippin.bedivere.form.MainForm
import com.bitflippin.bedivere.model.loadModel
import com.bitflippin.bedivere.swing.ext.TabManager
import com.bitflippin.bedivere.ui.Editor
import com.bitflippin.bedivere.ui.MainFrame
import java.awt.Dimension
import java.io.File
import javax.swing.JFrame
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        val devfile = File("dev.argmap")
        if (!devfile.exists()) {
            devfile.writeText("{}")
        }
        val argmap = loadModel(devfile)
        val state = EditorState(argmap, devfile, Hub(), TabManager())
        //val frame = MainFrame(state)
        val frame = Fr()
        val form = MainForm()
        val editor = Editor(form, state)
        frame.add(form.contentPanel)
        frame.isVisible = true
    }
}

class Fr : JFrame() {

    init {
        title = "Bedivere Argument Mapper"
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(300, 200)
        extendedState = MAXIMIZED_BOTH
    }
}