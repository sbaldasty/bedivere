package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.form.SourceForm
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.swing.bind.TabBinder
import com.bitflippin.bedivere.swing.bind.textFieldBinder
import javax.swing.JComponent

class SourceDetail(
    override val model: Source,
    editorState: EditorState
) : TabBinder<SourceForm, Source, Source> {

    override val ui = SourceForm()
    override val listeners = editorState.hub.sourceListeners
    override val title = "Claim ${model.id}"
    override val component: JComponent = ui.contentPanel

    private val titleBinder = textFieldBinder(ui.titleTextField, Source::title)
    private val urlBinder = textFieldBinder(ui.urlTextField, Source::url)
    private val descriptionBinder = textFieldBinder(ui.descriptionTextField, Source::description)

    override fun release() {
        titleBinder.release()
        urlBinder.release()
        descriptionBinder.release()
    }
}
