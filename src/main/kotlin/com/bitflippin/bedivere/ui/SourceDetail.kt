package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.swing.BoundTextField
import javax.swing.JPanel
import kotlin.reflect.KMutableProperty1

class SourceDetail(
    private val editorState: EditorState,
    private val model: Source) : JPanel() {

    private val titleTextField = boundTextField(Source::title)

    init {
        add(titleTextField)
    }

    private fun boundTextField(property: KMutableProperty1<Source, String>) =
        BoundTextField(model, property, editorState.hub.sourceListeners)
}