package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.form.SupportForm
import com.bitflippin.bedivere.model.Strength
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.swing.ComboBoxBinder
import com.bitflippin.bedivere.swing.TextFieldBinder
import javax.swing.JComboBox
import javax.swing.JTextField
import kotlin.reflect.KMutableProperty1

class SupportBinder(
    component: SupportForm,
    private val editorState: EditorState,
    private val model: Support
) {

    private val descriptionBinder = textFieldBinder(component.descriptionTextField, Support::description)
    private val strengthBinder = comboBoxBinder(Strength.entries.toList(), component.strengthComboBox, Support::strength)

    fun onClose() {
        descriptionBinder.onClose()
        strengthBinder.onClose()
    }

    private fun <U> comboBoxBinder(items: List<U>, comboBox: JComboBox<U>, property: KMutableProperty1<Support, U>) =
        ComboBoxBinder(items, comboBox, model, property, editorState.hub.supportListeners)

    private fun textFieldBinder(textField: JTextField, property: KMutableProperty1<Support, String>) =
        TextFieldBinder(textField, model, property, editorState.hub.supportListeners)
}