package com.bitflippin.bedivere.bind

import com.bitflippin.bedivere.editor.ChangeListener

abstract class Binder<U, M>(
    val ui: U,
    val model: M,
    val listeners: MutableSet<ChangeListener<M>>
) {

    abstract fun release()

}