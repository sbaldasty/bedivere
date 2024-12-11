package com.bitflippin.bedivere.bind

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.editor.broadcastChange
import com.bitflippin.bedivere.swing.addEditListener
import java.util.*
import javax.swing.DefaultComboBoxModel
import javax.swing.JComboBox
import javax.swing.SwingUtilities
import kotlin.reflect.KMutableProperty1

class ComboBoxBinder<T, U>(
    items: List<U>,
    private val comboBox: JComboBox<U>,
    private val t: T,
    private val property: KMutableProperty1<T, U>,
    private val listeners: MutableSet<ChangeListener<T>>
) {
    init {
        comboBox.model = DefaultComboBoxModel(Vector(items))
        SwingUtilities.invokeLater { onChange(t, Change.UPDATE) }
        comboBox.addEditListener {
            property.set(t, comboBox.model.getElementAt(comboBox.selectedIndex))
            broadcastChange(listeners, t, Change.UPDATE)
        }
        listeners.add(this::onChange)
    }

    fun onChange(model: T, change: Change) {
        val value = property(model)
        if (t == model && change == Change.UPDATE && comboBox.model.selectedItem != value) {
            comboBox.model.selectedItem = value
        }
    }

    fun onClose() {
        listeners.remove(this::onChange)
    }
}