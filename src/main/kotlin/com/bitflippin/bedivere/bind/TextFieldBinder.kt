package com.bitflippin.bedivere.bind

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.editor.broadcastChange
import com.bitflippin.bedivere.swing.addEditListener
import javax.swing.JTextField
import kotlin.reflect.KMutableProperty1

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