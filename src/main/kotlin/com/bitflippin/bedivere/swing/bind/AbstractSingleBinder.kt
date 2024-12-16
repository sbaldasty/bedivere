package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import javax.swing.JComponent

abstract class AbstractSingleBinder<U, M, E>(
    override val ui: U,
    override val model: M,
    override val component: JComponent,
    private val listeners: MutableSet<ChangeListener<E>>,
) : Binder<U, M> {
    private val listener = { target: E, change: Change ->
        when (change) {
            Change.ADD -> onModelAdd(target)
            Change.REMOVE -> onModelRemove(target)
            Change.UPDATE -> onModelUpdate(target)
        }
    }

    init {
        listeners.add(listener)
    }

    open fun onModelAdd(target: E) { /* Empty */ }

    open fun onModelRemove(target: E) { /* Empty */ }

    open fun onModelUpdate(target: E) { /* Empty */ }

    override fun release() {
        listeners.remove(listener)
    }
}
