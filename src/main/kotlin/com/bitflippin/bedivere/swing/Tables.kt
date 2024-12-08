package com.bitflippin.bedivere.swing

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import java.awt.Component
import java.awt.Dimension
import java.awt.event.ComponentListener
import javax.swing.JCheckBox
import javax.swing.JScrollPane
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

class ColumnDrivenTable<T>(
    columns: List<ModularColumn<T>>,
    rows: List<T>,
    private val listeners: MutableSet<ChangeListener<T>>
) : JTable() {

    private val copiedRows = rows.toMutableList()
    private val columnDrivenTableModel = ColumnDrivenTableModel(columns, copiedRows)

    init {
        model = columnDrivenTableModel
        columns.zip(columnModel.columns.toList()) { m, c ->
            c.width = m.width
            c.cellRenderer = m.cellRenderer
            c.cellEditor = m.cellEditor
        }
        listeners.add(this::onChange)
    }

    fun selection() = copiedRows[selectedRow]

    fun onChange(t: T, change: Change) {
        when (change) {
            Change.ADD -> {
                val index = copiedRows.size
                copiedRows.add(t)
                columnDrivenTableModel.fireTableRowsInserted(index, index)
            }
            Change.REMOVE -> {
                val index = copiedRows.indexOf(t)
                copiedRows.remove(t)
                columnDrivenTableModel.fireTableRowsDeleted(index, index)
            }
            Change.UPDATE -> {
                val index = copiedRows.indexOf(t)
                columnDrivenTableModel.fireTableRowsUpdated(index, index)
            }
        }
    }

    fun onClose() {
        listeners.remove(this::onChange)
    }
}

internal class CheckBoxRenderer : JCheckBox(), TableCellRenderer {
    override fun getTableCellRendererComponent(
        table: JTable, value: Any, isSelected: Boolean,
        hasFocus: Boolean, row: Int, column: Int
    ): Component {
        setSelected(value as Boolean)
        background = if (isSelected) {
            table.selectionBackground
        } else {
            table.background
        }
        return this
    }
}

class AutoResizeTable<T>(
    private val table: ColumnDrivenTable<T>,
    private val listeners: MutableSet<ChangeListener<T>>
) : JScrollPane(table) {
    init {
        verticalScrollBarPolicy = VERTICAL_SCROLLBAR_NEVER
        horizontalScrollBarPolicy = HORIZONTAL_SCROLLBAR_AS_NEEDED
        listeners.add(this::onChange)
    }

    private fun fitToTable() {
        preferredSize = Dimension(preferredSize.width, table.rowHeight * table.rowCount)
    }

    fun onChange(t: T, change: Change) {
        if (change == Change.ADD || change == Change.REMOVE) {
            fitToTable()
        }
    }

    fun onClose() {
        listeners.remove(this::onChange)
    }
}