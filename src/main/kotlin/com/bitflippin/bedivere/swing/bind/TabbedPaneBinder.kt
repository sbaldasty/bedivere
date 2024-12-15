package com.bitflippin.bedivere.swing.bind

import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTabbedPane

class TabbedPaneBinder(
    override val ui: JTabbedPane
) : Binder<JTabbedPane, List<Binder<*, *>>> {

    override val model = ArrayList<Binder<*, *>>()
    override fun component() = ui

    override fun release() {
        model.forEach { it.release() }
    }

    fun open(detail: Binder<*, *>) {
        val content = JPanel()
        content.layout = BorderLayout()
        val scrollPane = JScrollPane(detail.component())
        content.add(scrollPane, BorderLayout.CENTER)
        ui.add("asdf", content)
    }

}