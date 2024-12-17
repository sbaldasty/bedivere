package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.ChangeListener
import java.awt.BorderLayout
import java.util.concurrent.CopyOnWriteArraySet
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTabbedPane

class TabbedPaneBinder(
    override val ui: JTabbedPane,
) : Binder<JTabbedPane, List<Binder<*, *, *>>, Binder<*, *, *>> {

    override val model = ArrayList<Binder<*, *, *>>()
    override val listeners: MutableSet<ChangeListener<Binder<*, *, *>>> = CopyOnWriteArraySet()

    override fun release() {
        model.forEach { it.release() }
    }

    fun open(tabText: String, component: JComponent, binder: Binder<*, *, *>) {
        val content = JPanel()
        content.layout = BorderLayout()
        val scrollPane = JScrollPane(component)
        content.add(scrollPane, BorderLayout.CENTER)
        ui.add(tabText, content)
    }
}
