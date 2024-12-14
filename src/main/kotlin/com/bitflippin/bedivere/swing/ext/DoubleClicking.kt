package com.bitflippin.bedivere.swing.ext

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JList

typealias DoubleClickListener<T> = (T) -> Unit

private class DoubleClickAdapter<T>(
    private val component: JList<T>,
    private val listener: DoubleClickListener<T>
) : MouseAdapter() {
    override fun mouseClicked(event: MouseEvent) {
        if (event.clickCount != 2) {
            return
        }
        val index = component.locationToIndex(event.point)
        if (index < 0) {
            return
        }
        val element = component.model.getElementAt(index)
        listener(element)
    }
}

fun <T> JList<T>.addDoubleClickListener(listener: DoubleClickListener<T>) {
    addMouseListener(DoubleClickAdapter(this, listener))
}