package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener

abstract class SimpleBinder<U, M, L>(
    final override val listeners: MutableSet<ChangeListener<L>>
) : Binder<U, M, L> {

    private val listener = { target: L, change: Change ->
        when (change) {
            Change.ADD -> onModelAdd(target)
            Change.REMOVE -> onModelRemove(target)
            Change.UPDATE -> onModelUpdate(target)
        }
    }

    init {
        listeners.add(listener)
    }

    final override fun release() {
        listeners.remove(listener)
    }

    open fun onModelAdd(target: L) { /* Empty */ }
    open fun onModelRemove(target: L) { /* Empty */ }
    open fun onModelUpdate(target: L) { /* Empty */ }
}
