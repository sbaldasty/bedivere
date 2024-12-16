package com.bitflippin.bedivere.swing.bind

import javax.swing.JComponent

interface Binder<U, M> {
    val ui: U
    val model: M
    val component: JComponent

    fun release()
}
