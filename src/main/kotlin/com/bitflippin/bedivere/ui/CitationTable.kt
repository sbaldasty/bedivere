package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Citation
import com.bitflippin.bedivere.model.SourceId
import com.bitflippin.bedivere.model.lookup
import com.bitflippin.bedivere.swing.bind.TableBinder
import com.bitflippin.bedivere.swing.ext.CheckBoxRenderer
import com.bitflippin.bedivere.swing.ext.ModularColumn
import com.bitflippin.bedivere.swing.ext.PropertyTableCellRenderer
import javax.swing.DefaultCellEditor
import javax.swing.JCheckBox
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.table.DefaultTableCellRenderer

private fun sourceColumn(editorState: EditorState) =
    ModularColumn(
        "Source",
        32,
        { false },
        Citation::sourceId,
        PropertyTableCellRenderer { x: SourceId -> if (x.value == 0) "(none)" else editorState.argmap.lookup(x).title },
        DefaultCellEditor(JTextField()),
    )

private fun descriptionColumn() =
    ModularColumn(
        "Description",
        75,
        { true },
        Citation::description,
        DefaultTableCellRenderer(),
        DefaultCellEditor(JTextField()),
    )

private fun enthymemeColumn() =
    ModularColumn(
        "Enthymeme",
        32,
        { true },
        Citation::enthymeme,
        CheckBoxRenderer(),
        DefaultCellEditor(JCheckBox()),
    )

class CitationTable(
    ui: JTable,
    model: MutableList<Citation>,
    private val editorState: EditorState
) : TableBinder<Citation>(ui, model, editorState.hub.citationListeners, listOf(sourceColumn(editorState), descriptionColumn(), enthymemeColumn()))