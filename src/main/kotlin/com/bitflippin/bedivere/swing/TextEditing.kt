package com.bitflippin.bedivere.swing

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.editor.broadcastChange
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import javax.swing.JTextField
import kotlin.reflect.KMutableProperty1

typealias EditListener = (FocusEvent) -> Unit
typealias Property<T> = KMutableProperty1<T, String>

fun JTextField.addEditListener(listener: EditListener) {
    addFocusListener(FocusLostAdapter(listener))
}

private class FocusLostAdapter(private val listener: EditListener) : FocusAdapter() {
    override fun focusLost(event: FocusEvent) {
        listener(event)
    }
}

class BoundTextField<T>(
    private val t: T,
    private val property: Property<T>,
    private val listeners: MutableSet<ChangeListener<T>>
) : JTextField(20) {

    init {
        onChange(t, Change.UPDATE)
        addEditListener {
            property.set(t, text)
            broadcastChange(listeners, t, Change.UPDATE)
        }
        listeners.add(this::onChange)
    }

    fun onChange(model: T, change: Change) {
        if (t == model && change == Change.UPDATE && text != property(model)) {
            text = property(model)
        }
    }

    fun onClose() {
        listeners.remove(this::onChange)
    }
}
