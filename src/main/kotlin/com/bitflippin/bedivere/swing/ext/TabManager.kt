package com.bitflippin.bedivere.swing.ext

import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTabbedPane

class TabManager : JTabbedPane() {

    fun open(tabbedPanel: TabbedPanel) {
        val content = JPanel()
        content.layout = BorderLayout()
        content.add(tabbedPanel, BorderLayout.CENTER)
        add("asdf", content)
    }
}

abstract class TabbedPanel : JScrollPane() {
    abstract fun onClose()
}