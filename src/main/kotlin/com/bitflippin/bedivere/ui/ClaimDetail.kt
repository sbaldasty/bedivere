package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Citation
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.swing.BoundTextField
import com.bitflippin.bedivere.swing.ColumnDrivenTable
import com.bitflippin.bedivere.swing.ModularColumn
import com.bitflippin.bedivere.swing.TabbedPanel
import javax.swing.DefaultCellEditor
import javax.swing.JLabel
import javax.swing.JScrollPane
import javax.swing.JTextField
import javax.swing.table.DefaultTableCellRenderer
import kotlin.reflect.KMutableProperty1

class ClaimDetail(
    private val editorState: EditorState,
    private val model: Claim
) : TabbedPanel() {

    private val tc = ModularColumn("Description", 75, { false }, Citation::description, DefaultTableCellRenderer(), DefaultCellEditor(JTextField()))

    private val titleTextField = boundTextField(Claim::title)
    private val descriptionTextField = boundTextField(Claim::description)
    private val citationTable = ColumnDrivenTable(listOf(tc), model.citations)

    init {
        add(JLabel("Title:"))
        add(titleTextField)
        add(JLabel("Description:"))
        add(descriptionTextField)
        add(JScrollPane(citationTable))
    }

    override fun onClose() {
        titleTextField.onClose()
        descriptionTextField.onClose()
        citationTable.onClose()
    }

    private fun boundTextField(property: KMutableProperty1<Claim, String>) =
        BoundTextField(model, property, editorState.hub.claimListeners)
}