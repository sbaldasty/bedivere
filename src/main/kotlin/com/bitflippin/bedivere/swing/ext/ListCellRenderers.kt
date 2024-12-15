package com.bitflippin.bedivere.swing.ext

import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JLabel
import javax.swing.JList

fun <T> JList<T>.setCellRenderer(textProvider: (T) -> String) {
    cellRenderer = CustomRenderer(textProvider)
}

private class CustomRenderer<T>(
    private val textProvider: (T) -> String,
) : DefaultListCellRenderer() {
    override fun getListCellRendererComponent(
        list: JList<*>,
        value: Any,
        index: Int,
        isSelected: Boolean,
        cellHasFocus: Boolean,
    ): Component {
        val label = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus) as JLabel
        @Suppress("UNCHECKED_CAST")
        label.text = textProvider(value as T)
        return label
    }
}
