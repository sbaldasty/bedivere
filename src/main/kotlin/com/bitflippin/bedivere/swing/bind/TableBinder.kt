package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.swing.ext.ColumnDrivenTableModel
import com.bitflippin.bedivere.swing.ext.ModularColumn
import javax.swing.JTable

abstract class TableBinder<E>(
    final override val ui: JTable,
    final override val model: MutableList<E>,
    listeners: MutableSet<ChangeListener<E>>,
    columns: List<ModularColumn<E>>
) : SimpleBinder<JTable, MutableList<E>, E>(listeners) {

    private val columnDrivenTableModel = ColumnDrivenTableModel(columns, model)

    init {
        ui.model = columnDrivenTableModel
        columns.zip(ui.columnModel.columns.toList()) { m, c ->
            c.width = m.width
            c.cellRenderer = m.cellRenderer
            c.cellEditor = m.cellEditor
        }
        ui.repaint()
    }

    fun selection() = model[ui.selectedRow]

    override fun onModelAdd(target: E) {
        val index = model.size
        model.add(target)
        columnDrivenTableModel.fireTableRowsInserted(index, index)
    }

    override fun onModelRemove(target: E) {
        val index = model.indexOf(target)
        model.remove(target)
        columnDrivenTableModel.fireTableRowsDeleted(index, index)
    }

    override fun onModelUpdate(target: E) {
        val index = model.indexOf(target)
        columnDrivenTableModel.fireTableRowsUpdated(index, index)
    }
}
