package com.bitflippin.bedivere.bind

import com.bitflippin.bedivere.editor.ChangeListener

abstract class Binder<C, M>(
    val component: C,
    val model: M,
    val listeners: MutableSet<ChangeListener<M>>
) {

    abstract fun release()

}