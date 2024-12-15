package com.bitflippin.bedivere.swing.ext

import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import javax.swing.JComponent

typealias EditListener = (FocusEvent) -> Unit

fun JComponent.addEditListener(listener: EditListener) {
    addFocusListener(FocusLostAdapter(listener))
}

private class FocusLostAdapter(
    private val listener: EditListener,
) : FocusAdapter() {
    override fun focusLost(event: FocusEvent) {
        listener(event)
    }
}
