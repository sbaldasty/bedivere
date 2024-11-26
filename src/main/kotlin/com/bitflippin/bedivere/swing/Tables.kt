package com.bitflippin.bedivere.swing

import javax.swing.JTable
import javax.swing.table.AbstractTableModel
import javax.swing.table.TableCellEditor
import javax.swing.table.TableCellRenderer
import kotlin.reflect.KMutableProperty1

class ModularColumn<T>(
    val name: String,
    val width: Int,
    val editable: (T) -> Boolean,
    val property: KMutableProperty1<T, *>,
    val cellRenderer: TableCellRenderer,
    val cellEditor: TableCellEditor
)

class ColumnDrivenTableModel<T>(
    private val columns: List<ModularColumn<T>>,
    private val rows: List<T>
) : AbstractTableModel() {

    override fun getColumnName(column: Int) = columns[column].name
    override fun getRowCount() = rows.size
    override fun getColumnCount() = columns.size
    override fun getValueAt(row: Int, column: Int) = columns[column].property(rows[row])
    override fun setValueAt(value: Any, row: Int, column: Int) = castProperty(columns[column]).set(rows[row], value)
    override fun isCellEditable(row: Int, column: Int) = columns[column].editable(rows[row])

    @Suppress("UNCHECKED_CAST")
    private fun castProperty(column: ModularColumn<T>) = column.property as KMutableProperty1<T, Any>
}

class ColumnDrivenTable<T>(columns: List<ModularColumn<T>>, rows: List<T>) : JTable() {

    private val columnDrivenTableModel = ColumnDrivenTableModel(columns, rows)

    init {
        model = columnDrivenTableModel

        columns.zip(columnModel.columns.toList()) { m, c ->
            c.width = m.width
            c.cellRenderer = m.cellRenderer
            c.cellEditor = m.cellEditor
        }
        // TODO Listeners
    }

    fun onClose() {

    }
}