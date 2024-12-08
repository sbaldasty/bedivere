package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.form.ClaimForm
import com.bitflippin.bedivere.model.Citation
import com.bitflippin.bedivere.model.Claim
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

    // Stuff not ready yet
    private val addCitationButton = AddCitationButton(editorState, model)
    private val citationsBinder = createCitationsBinder()
//    private val citationTableWrapper = AutoResizeTable(citationTable, editorState.hub.citationListeners)
    //private val setCitationSourceButton = SetCitationSourceButton(editorState) { citationTable.selection() }

    init {
        add(form.contentPanel)
    }

    override fun onClose() {
        titleBinder.onClose()
        descriptionBinder.onClose()
        citationsBinder.onClose()
        //citationTableWrapper.onClose()
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

    private fun textFieldBinder(textField: JTextField, property: KMutableProperty1<Claim, String>) =
        TextFieldBinder(textField, model, property, editorState.hub.claimListeners)
}