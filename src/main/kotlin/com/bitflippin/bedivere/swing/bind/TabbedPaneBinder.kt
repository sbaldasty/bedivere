package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.ChangeListener
import java.awt.BorderLayout
import java.util.concurrent.CopyOnWriteArraySet
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTabbedPane

interface TabBinder<U, M, L> : Binder<U, M, L> {
    val title: String
    val component: JComponent
}

class TabbedPaneBinder(
    override val ui: JTabbedPane,
) : Binder<JTabbedPane, List<TabBinder<*, *, *>>, TabBinder<*, *, *>> {

    override val model = ArrayList<TabBinder<*, *, *>>()
    override val listeners: MutableSet<ChangeListener<TabBinder<*, *, *>>> = CopyOnWriteArraySet()

    override fun release() {
        model.forEach { it.release() }
    }

    // TODO This should happen via listeners
    fun open(binder: TabBinder<*, *, *>) {
        val existingBinder = model.firstOrNull { it.model == binder.model }
        if (existingBinder == null) {
            model.add(binder)
            val content = JPanel()
            content.layout = BorderLayout()
            val scrollPane = JScrollPane(binder.component)
            content.add(scrollPane, BorderLayout.CENTER)
            ui.add(binder.title, content)
        } else {
            binder.release()
            ui.selectedIndex = model.indexOf(existingBinder)
        }
    }
}