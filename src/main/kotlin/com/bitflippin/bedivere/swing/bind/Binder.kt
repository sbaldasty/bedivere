package com.bitflippin.bedivere.swing.bind

interface Binder<U, M> {
    val ui: U
    val model: M
    fun release()
}