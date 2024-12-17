package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.ChangeListener

interface Binder<U, M, L> {
    val ui: U
    val model: M
    val listeners: MutableSet<ChangeListener<L>>

    fun release()
}
