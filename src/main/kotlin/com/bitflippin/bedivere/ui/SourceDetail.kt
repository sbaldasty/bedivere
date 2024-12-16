package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.form.SourceForm
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.swing.bind.AbstractSingleBinder
import com.bitflippin.bedivere.swing.bind.TextFieldBinder
import javax.swing.JTextField
import kotlin.reflect.KMutableProperty1

class SourceDetail(
    ui: SourceForm,
    model: Source,
    private val editorState: EditorState,
) : AbstractSingleBinder<SourceForm, Source, Source>(ui, model, ui.contentPanel, editorState.hub.sourceListeners) {
    private val titleBinder = textFieldBinder(ui.titleTextField, Source::title)
    private val urlBinder = textFieldBinder(ui.urlTextField, Source::url)
    private val descriptionBinder = textFieldBinder(ui.descriptionTextField, Source::description)

    override fun release() {
        titleBinder.release()
        urlBinder.release()
        descriptionBinder.release()
    }

    private fun textFieldBinder(
        textField: JTextField,
        property: KMutableProperty1<Source, String>,
    ) = TextFieldBinder(textField, model, editorState.hub.sourceListeners, property)
}
