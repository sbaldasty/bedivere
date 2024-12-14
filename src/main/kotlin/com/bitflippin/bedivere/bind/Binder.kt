package com.bitflippin.bedivere.bind

interface Binder<U, M> {
    val ui: U
    val model: M
    fun release()
}