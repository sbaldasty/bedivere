package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.model.Argmap
import java.awt.Dimension
import javax.swing.JFrame

class MainFrame(argmap: Argmap): JFrame() {
    init {
        title = "Bedivere Argument Mapper"
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = null
        size = Dimension(300, 200)
        extendedState = MAXIMIZED_BOTH
    }
}