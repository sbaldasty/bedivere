package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Citation
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.swing.*
import java.awt.BorderLayout
import javax.swing.*
import javax.swing.table.DefaultTableCellRenderer
import kotlin.reflect.KMutableProperty1
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout

class ClaimDetail(
    private val editorState: EditorState,
    private val model: Claim
) : TabbedPanel() {

    private val constraints = GridBagConstraints()
    private val titleTextField = boundTextField(Claim::title)
    private val descriptionTextField = boundTextField(Claim::description)
    private val addCitationButton = AddCitationButton(editorState, model)
    private val citationTable = createCitationTable()
    private val setCitationSourceButton = SetCitationSourceButton(editorState) { citationTable.selection() }

    init {
        layout = GridBagLayout()
        constraints.gridy = 0

        addComponentPair("Title:", titleTextField)
        addComponentPair("Description:", descriptionTextField)
        val jsp = JScrollPane(citationTable)
        jsp.preferredSize = Dimension(800, 40)
        jsp.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_NEVER
        jsp.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        constraints.gridx = 0
        constraints.gridwidth = 2
        add(jsp, constraints)
        constraints.gridy += 1
        addComponentPair("", addCitationButton)
        addComponentPair("", setCitationSourceButton)
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

    private fun addComponentPair(caption: String, component: JComponent) {
        constraints.gridx = 0
        add(JLabel(caption), constraints)
        constraints.gridx = 1
        add(component, constraints)
        constraints.gridy += 1
    }

    private fun boundTextField(property: KMutableProperty1<Claim, String>) =
        BoundTextField(model, property, editorState.hub.claimListeners)
}