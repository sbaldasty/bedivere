package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.addCitation
import com.bitflippin.bedivere.editor.setCitationSource
import com.bitflippin.bedivere.form.ClaimForm
import com.bitflippin.bedivere.model.Citation
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.Confidence
import com.bitflippin.bedivere.swing.*
import javax.swing.*
import javax.swing.table.DefaultTableCellRenderer
import kotlin.reflect.KMutableProperty1

class ClaimDetail(
    private val editorState: EditorState,
    private val model: Claim
) : TabbedPanel() {

    private val form = ClaimForm()
    private val titleBinder = textFieldBinder(form.titleTextField, Claim::title)
    private val descriptionBinder = textFieldBinder(form.descriptionTextField, Claim::description)
    private val confidenceBinder = comboBoxBinder(Confidence.entries.toList(), form.confidenceComboBox, Claim::confidence)
    private val citationsBinder = createCitationsBinder()

    init {
        setViewportView(form.contentPanel)
        form.confidenceComboBox.model = DefaultComboBoxModel(Confidence.entries.toTypedArray())
        form.setSourceButton.addActionListener { setCitationSource(editorState, citationsBinder.selection()) }
        form.addCitationButton.addActionListener { addCitation(editorState, model) }
    }

    override fun onClose() {
        titleBinder.onClose()
        descriptionBinder.onClose()
        confidenceBinder.onClose()
        citationsBinder.onClose()
    }

    private fun createCitationsBinder(): TableBinder<Citation> {
        val sourceColumn = ModularColumn(
            "Source",
            32,
            { false },
            Citation::sourceId,
            DefaultTableCellRenderer(),
            DefaultCellEditor(JTextField()))

        val descriptionColumn = ModularColumn(
            "Description",
            75,
            { true },
            Citation::description,
            DefaultTableCellRenderer(),
            DefaultCellEditor(JTextField()))

        val enthymemeColumn = ModularColumn(
            "Enthymeme",
            32,
            { true },
            Citation::enthymeme,
            CheckBoxRenderer(),
            DefaultCellEditor(JCheckBox()))

        val columns = listOf(sourceColumn, descriptionColumn, enthymemeColumn)
        return TableBinder(columns, model.citations, form.citationTable, editorState.hub.citationListeners)
    }

    private fun <U> comboBoxBinder(items: List<U>, comboBox: JComboBox<U>, property: KMutableProperty1<Claim, U>) =
        ComboBoxBinder(items, comboBox, model, property, editorState.hub.claimListeners)

    private fun textFieldBinder(textField: JTextField, property: KMutableProperty1<Claim, String>) =
        TextFieldBinder(textField, model, property, editorState.hub.claimListeners)
}