package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
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

    private val titleTextField = boundTextField(Claim::title)
    private val descriptionTextField = boundTextField(Claim::description)
    private val addCitationButton = AddCitationButton(editorState, model)
    private val citationTable = createCitationTable()
    private val setCitationSourceButton = SetCitationSourceButton(editorState, { citationTable.selection() })

    init {
        add(JLabel("Title:"))
        add(titleTextField)
        add(JLabel("Description:"))
        add(descriptionTextField)
        add(addCitationButton)
        add(setCitationSourceButton)
        add(JScrollPane(citationTable))
    }

    override fun onClose() {
        titleTextField.onClose()
        descriptionTextField.onClose()
        citationTable.onClose()
    }

    private fun createCitationTable(): ColumnDrivenTable<Citation> {
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
        return ColumnDrivenTable(columns, model.citations, editorState.hub.citationListeners)
    }

    private fun boundTextField(property: KMutableProperty1<Claim, String>) =
        BoundTextField(model, property, editorState.hub.claimListeners)
}