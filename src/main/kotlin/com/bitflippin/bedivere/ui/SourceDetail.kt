package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.form.SourceForm
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.swing.bind.Binder
import com.bitflippin.bedivere.swing.bind.textFieldBinder

class SourceDetail(
    override val model: Source,
    editorState: EditorState
) : Binder<SourceForm, Source, Source> {

    override val ui = SourceForm()
    override val listeners = editorState.hub.sourceListeners

    private val titleBinder = textFieldBinder(ui.titleTextField, Source::title)
    private val urlBinder = textFieldBinder(ui.urlTextField, Source::url)
    private val descriptionBinder = textFieldBinder(ui.descriptionTextField, Source::description)

    override fun release() {
        titleBinder.release()
        urlBinder.release()
        descriptionBinder.release()
    }
}
