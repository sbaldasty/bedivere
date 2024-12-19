package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.swing.bind.TableBinder
import com.bitflippin.bedivere.swing.ext.ModularColumn
import javax.swing.DefaultCellEditor
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.table.DefaultTableCellRenderer

private fun titleColumn() =
    ModularColumn(
        "Title",
        75,
        { false },
        Claim::title,
        DefaultTableCellRenderer(),
        DefaultCellEditor(JTextField()),
    )

class SupportClaimTable(
    ui: JTable,
    model: MutableList<Claim>,
    private val editorState: EditorState
) : TableBinder<Claim>(ui, model, editorState.hub.claimListeners, listOf(titleColumn()))
