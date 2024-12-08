package com.bitflippin.bedivere.swing

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.editor.broadcastChange
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import javax.swing.JTextField
import kotlin.reflect.KMutableProperty1

typealias EditListener = (FocusEvent) -> Unit

fun JTextField.addEditListener(listener: EditListener) {
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
