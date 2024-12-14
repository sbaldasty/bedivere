package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import javax.swing.JComponent

abstract class AbstractSingleBinder<U : JComponent, M>(
    override val ui: U,
    override val model: M,
    private val listeners: MutableSet<ChangeListener<M>>
) : Binder<U, M> {

    private val listener = { target: M, change: Change -> when (change) {
        Change.ADD -> onModelAdd(target)
        Change.REMOVE -> onModelRemove(target)
        Change.UPDATE -> onModelUpdate(target)
    }}

    init {
        listeners.add(listener)
    }

    open fun onModelAdd(target: M) { /* Empty */ }
    open fun onModelRemove(target: M) { /* Empty */ }
    open fun onModelUpdate(target: M) { /* Empty */ }

    override fun release() {
        listeners.remove(listener)
    }
}