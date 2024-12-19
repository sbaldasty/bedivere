package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.ClaimSource
import com.bitflippin.bedivere.model.SourceId
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
        ClaimSource::sourceId,
        { x, y -> x.sourceId = y as SourceId },
        PropertyTableCellRenderer { x: SourceId -> if (x.value == 0) "(none)" else editorState.argmap.lookup(x).title },
        DefaultCellEditor(JTextField()),
    )

private fun descriptionColumn() =
    ModularColumn(
        "Description",
        75,
        { true },
        ClaimSource::description,
        { x, y -> x.description = y as String },
        DefaultTableCellRenderer(),
        DefaultCellEditor(JTextField()),
    )

private fun enthymemeColumn() =
    ModularColumn(
        "Enthymeme",
        32,
        { true },
        ClaimSource::enthymeme,
        { x, y -> x.enthymeme = y as Boolean },
        CheckBoxRenderer(),
        DefaultCellEditor(JCheckBox()),
    )

class ClaimSourceTable(
    ui: JTable,
    claim: Claim,
    private val editorState: EditorState
) : TableBinder<ClaimSource>(ui,
    editorState.argmap.lookupClaimSources(claim),
    editorState.hub.claimSourceListeners,
    listOf(sourceColumn(editorState), descriptionColumn(), enthymemeColumn()),
    { editorState.argmap.lookupClaimSources(claim).contains(it) })