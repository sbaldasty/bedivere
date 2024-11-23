package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.swing.BoundTextField
import com.bitflippin.bedivere.swing.Property
import com.bitflippin.bedivere.swing.TabbedPanel
import javax.swing.JLabel

class SourceDetail(
    private val editorState: EditorState,
    private val model: Source
) : TabbedPanel() {

    private val titleTextField = boundTextField(Source::title)
    private val urlTextField = boundTextField(Source::url)
    private val descriptionTextField = boundTextField(Source::description)

    init {
        add(JLabel("Title:"))
        add(titleTextField)
        add(JLabel("URL:"))
        add(urlTextField)
        add(JLabel("Description:"))
        add(descriptionTextField)
    }

    override fun onClose() {
        titleTextField.onClose()
        urlTextField.onClose()
        descriptionTextField.onClose()
    }

    private fun boundTextField(property: Property<Source>) =
        BoundTextField(model, property, editorState.hub.sourceListeners)
}