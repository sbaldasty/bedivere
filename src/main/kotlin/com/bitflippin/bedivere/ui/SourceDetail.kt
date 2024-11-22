package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Source
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import javax.swing.JPanel
import javax.swing.JTextField

class SourceDetail(
    private val editorState: EditorState,
    private val model: Source) : JPanel() {

    private val titleTextField = JTextField(20)

    private fun updateTextField(textField: JTextField, value: String) {
        if (textField.text != value) {
            textField.text = value
        }
    }

    init {
        titleTextField.addActionListener { onEditComplete() }
        titleTextField.addFocusListener(object : FocusListener {
            override fun focusGained(p0: FocusEvent?) {
            }

            override fun focusLost(p0: FocusEvent?) {
                onEditComplete()
            }
        })
        add(titleTextField)
        editorState.hub.addListener(this::onSourceChange)
        onSourceChange(model, Change.UPDATE)
    }

    fun onEditComplete() {
        model.title = titleTextField.text
        editorState.hub.broadcast(model, Change.UPDATE)
    }

    fun onSourceChange(source: Source, change: Change) {
        if (source != model) {
            return
        } else if (change == Change.UPDATE) {
            updateTextField(titleTextField, model.title)
        }
    }
}