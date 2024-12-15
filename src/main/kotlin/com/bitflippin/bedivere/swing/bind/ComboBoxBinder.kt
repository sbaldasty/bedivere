package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.editor.broadcastChange
import com.bitflippin.bedivere.swing.ext.addEditListener
import java.util.Vector
import javax.swing.DefaultComboBoxModel
import javax.swing.JComboBox
import javax.swing.SwingUtilities
import kotlin.reflect.KMutableProperty1

class ComboBoxBinder<M, P>(
    ui: JComboBox<P>,
    model: M,
    listeners: MutableSet<ChangeListener<M>>,
    private val property: KMutableProperty1<M, P>,
    items: List<P>,
) : AbstractSingleBinder<JComboBox<P>, M, M>(ui, model, listeners) {
    init {
        ui.model = DefaultComboBoxModel(Vector(items))
        SwingUtilities.invokeLater { onModelUpdate(model) }
        ui.addEditListener {
            property.set(model, ui.model.getElementAt(ui.selectedIndex))
            broadcastChange(listeners, model, Change.UPDATE)
        }
    }

    override fun onModelUpdate(target: M) {
        val value = property(model)
        if (model == target && ui.model.selectedItem != value) {
            ui.model.selectedItem = value
        }
    }
}
