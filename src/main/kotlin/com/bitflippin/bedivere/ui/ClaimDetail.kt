package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.swing.BoundTextField
import com.bitflippin.bedivere.swing.Property
import com.bitflippin.bedivere.swing.TabbedPanel
import javax.swing.JLabel

class ClaimDetail(
    private val editorState: EditorState,
    private val model: Claim
) : TabbedPanel() {

    private val titleTextField = boundTextField(Claim::title)
    private val descriptionTextField = boundTextField(Claim::description)

    init {
        add(JLabel("Title:"))
        add(titleTextField)
        add(JLabel("Description:"))
        add(descriptionTextField)
    }

    override fun onClose() {
        titleTextField.onClose()
        descriptionTextField.onClose()
    }

    private fun boundTextField(property: Property<Claim>) =
        BoundTextField(model, property, editorState.hub.claimListeners)
}