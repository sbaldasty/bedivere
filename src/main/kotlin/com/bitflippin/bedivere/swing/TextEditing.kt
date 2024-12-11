package com.bitflippin.bedivere.swing

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.editor.broadcastChange
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import java.util.*
import javax.swing.DefaultComboBoxModel
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JTextField
import javax.swing.SwingUtilities
import kotlin.reflect.KMutableProperty1

typealias EditListener = (FocusEvent) -> Unit

fun JComponent.addEditListener(listener: EditListener) {
    addFocusListener(FocusLostAdapter(listener))
}

private class FocusLostAdapter(private val listener: EditListener) : FocusAdapter() {
    override fun focusLost(event: FocusEvent) {
        listener(event)
    }
}