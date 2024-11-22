package com.bitflippin.bedivere.swing

import javax.swing.JPanel
import javax.swing.JTabbedPane

class TabManager : JTabbedPane() {
    fun open(tabbedPanel: TabbedPanel) {
        add("asdf", tabbedPanel)
    }
}

abstract class TabbedPanel : JPanel() {
    abstract fun onClose()
}