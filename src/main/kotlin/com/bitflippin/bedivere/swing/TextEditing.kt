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

class TextFieldBinder<T>(
    private val textField: JTextField,
    private val t: T,
    private val property: KMutableProperty1<T, String>,
    private val listeners: MutableSet<ChangeListener<T>>
) {

    init {
        onChange(t, Change.UPDATE)
        textField.addEditListener {
            property.set(t, textField.text)
            broadcastChange(listeners, t, Change.UPDATE)
        }
        listeners.add(this::onChange)
    }

    fun onChange(model: T, change: Change) {
        if (t == model && change == Change.UPDATE && textField.text != property(model)) {
            textField.text = property(model)
        }
    }

    fun onClose() {
        listeners.remove(this::onChange)
    }
}

class ComboBoxBinder<T, U>(
    private val items: List<U>,
    private val comboBox: JComboBox<U>,
    private val t: T,
    private val property: KMutableProperty1<T, U>,
    private val listeners: MutableSet<ChangeListener<T>>
) {
    init {
        comboBox.model = DefaultComboBoxModel(Vector(items))
//        SwingUtilities.invokeLater { onChange(t, Change.UPDATE) }
        comboBox.addEditListener {
            property.set(t, comboBox.model.getElementAt(comboBox.selectedIndex))
            broadcastChange(listeners, t, Change.UPDATE)
        }
        listeners.add(this::onChange)
    }

    fun onChange(model: T, change: Change) {
        val value = property(model)
        if (t == model && change == Change.UPDATE && comboBox.model.selectedItem != value) {
            comboBox.model.selectedItem = value
        }
    }

    fun onClose() {
        listeners.remove(this::onChange)
    }
}
