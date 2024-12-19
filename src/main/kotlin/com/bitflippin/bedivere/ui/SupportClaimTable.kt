package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Database
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.model.SupportClaim
import com.bitflippin.bedivere.swing.bind.TableBinder
import com.bitflippin.bedivere.swing.ext.ModularColumn
import javax.swing.DefaultCellEditor
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.table.DefaultTableCellRenderer

private fun titleColumn(database: Database) =
    ModularColumn<SupportClaim>(
        "Title",
        75,
        { false },
        { database.lookup(it.claimId).title },
        { _, _ -> },
        DefaultTableCellRenderer(),
        DefaultCellEditor(JTextField()),
    )

class SupportClaimTable(
    ui: JTable,
    support: Support,
    private val editorState: EditorState
) : TableBinder<SupportClaim>(ui,
    editorState.argmap.lookupSupportClaims(support),
    editorState.hub.supportClaimListeners,
    listOf(titleColumn(editorState.argmap)),
    { editorState.argmap.lookupSupportClaims(support).contains(it) })
