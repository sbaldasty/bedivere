package com.bitflippin.bedivere.bind

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.editor.broadcastChange
import com.bitflippin.bedivere.swing.addEditListener
import javax.swing.JTextField
import kotlin.reflect.KMutableProperty1

class TextFieldBinder<M>(
    ui: JTextField,
    model: M,
    listeners: MutableSet<ChangeListener<M>>,
    private val property: KMutableProperty1<M, String>
) : Binder<JTextField, M>(ui, model, listeners) {

    init {
        onChange(model, Change.UPDATE)
        ui.addEditListener {
            property.set(model, ui.text)
            broadcastChange(listeners, model, Change.UPDATE)
        }
        listeners.add(this::onChange)
    }

    fun onChange(target: M, change: Change) {
        if (model == target && change == Change.UPDATE && ui.text != property(model)) {
            ui.text = property(model)
        }
    }

    override fun release() {
        listeners.remove(this::onChange)
    }
}