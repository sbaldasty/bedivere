package com.bitflippin.bedivere.swing.ext

import java.awt.Component
import java.awt.Dimension
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JTable
import javax.swing.table.AbstractTableModel
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.table.TableCellEditor
import javax.swing.table.TableCellRenderer

class ModularColumn<T>(
    val name: String,
    val width: Int,
    val editable: (T) -> Boolean,
    val reader: (T) -> Any,
    val writer: (T, Any) -> Unit,
    val cellRenderer: TableCellRenderer,
    val cellEditor: TableCellEditor,
)

class ColumnDrivenTableModel<T>(
    private val columns: List<ModularColumn<T>>,
    private val rows: List<T>,
) : AbstractTableModel() {

    override fun getColumnName(column: Int) =
        columns[column].name

    override fun getRowCount() =
        rows.size

    override fun getColumnCount() =
        columns.size

    override fun getValueAt(row: Int, column: Int) =
        columns[column].reader(rows[row])

    override fun setValueAt(value: Any, row: Int, column: Int) =
        columns[column].writer(rows[row], value)

    override fun isCellEditable(row: Int, column: Int) =
        columns[column].editable(rows[row])
}

internal class CheckBoxRenderer :
    JCheckBox(),
    TableCellRenderer {
    override fun getTableCellRendererComponent(
        table: JTable,
        value: Any,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int,
    ): Component {
        setSelected(value as Boolean)
        background =
            if (isSelected) {
                table.selectionBackground
            } else {
                table.background
            }
        return this
    }
}

class PropertyTableCellRenderer<T>(
    private val textProvider: (T) -> String,
) : DefaultTableCellRenderer() {
    override fun getTableCellRendererComponent(
        table: JTable,
        value: Any,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int,
    ): Component {
        val label = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column) as JLabel
        @Suppress("UNCHECKED_CAST")
        label.text = textProvider(value as T)
        return label
    }
}

fun JTable.enableAutoResize() {
    fun bestFit() = Dimension(size.width, rowHeight * rowCount)
    preferredScrollableViewportSize = bestFit()
    model.addTableModelListener {
        preferredScrollableViewportSize = bestFit()
        val scrollPane = parent.parent
        scrollPane.revalidate()
        val panel = scrollPane.parent
        panel.revalidate()
        panel.repaint()
    }
}
