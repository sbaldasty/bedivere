package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.form.SupportForm
import com.bitflippin.bedivere.model.Strength
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.swing.bind.Binder
import com.bitflippin.bedivere.swing.bind.comboBoxBinder
import com.bitflippin.bedivere.swing.bind.textFieldBinder

class SupportBinder(
    override val model: Support,
    editorState: EditorState,
) : Binder<SupportForm, Support, Support> {

    override val ui = SupportForm()
    override val listeners = editorState.hub.supportListeners

    private val descriptionBinder = textFieldBinder(ui.descriptionTextField, Support::description)
    private val strengthBinder = comboBoxBinder(Strength.entries.toList(), ui.strengthComboBox, Support::strength)

    override fun release() {
        descriptionBinder.release()
        strengthBinder.release()
    }
}
