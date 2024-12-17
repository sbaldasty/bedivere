package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.editor.broadcastChange
import com.bitflippin.bedivere.swing.ext.addEditListener
import javax.swing.JTextField
import kotlin.reflect.KMutableProperty1

class TextFieldBinder<M>(
    override val ui: JTextField,
    override val model: M,
    listeners: MutableSet<ChangeListener<M>>,
    private val property: KMutableProperty1<M, String>
) : SimpleBinder<JTextField, M, M>(listeners) {

    init {
        onModelUpdate(model)
        ui.addEditListener {
            property.set(model, ui.text)
            broadcastChange(listeners, model, Change.UPDATE)
        }
    }

    override fun onModelUpdate(target: M) {
        if (model == target && ui.text != property(model)) {
            ui.text = property(model)
        }
    }
}
