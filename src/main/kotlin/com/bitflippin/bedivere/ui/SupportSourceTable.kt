package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.SourceId
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.model.SupportSource
import com.bitflippin.bedivere.swing.bind.TableBinder
import com.bitflippin.bedivere.swing.ext.ModularColumn
import com.bitflippin.bedivere.swing.ext.PropertyTableCellRenderer
import javax.swing.DefaultCellEditor
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.table.DefaultTableCellRenderer

private fun sourceColumn(editorState: EditorState) =
    ModularColumn(
        "Source",
        32,
        { false },
        SupportSource::sourceId,
        { x, y -> x.sourceId = y as SourceId },
        PropertyTableCellRenderer { x: SourceId -> if (x.value == 0) "(none)" else editorState.argmap.lookup(x).title },
        DefaultCellEditor(JTextField()),
    )

private fun descriptionColumn() =
    ModularColumn(
        "Description",
        75,
        { true },
        SupportSource::description,
        { x, y -> x.description = y as String },
        DefaultTableCellRenderer(),
        DefaultCellEditor(JTextField()),
    )

class SupportSourceTable(
    ui: JTable,
    support: Support,
    private val editorState: EditorState
) : TableBinder<SupportSource>(ui,
    editorState.argmap.lookupSupportSources(support),
    editorState.hub.supportSourceListeners,
    listOf(sourceColumn(editorState), descriptionColumn()),
    { editorState.argmap.lookupSupportSources(support).contains(it) })