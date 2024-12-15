package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.ChangeListener
import javax.swing.DefaultListModel
import javax.swing.JList

class ListBinder<E>(
    ui: JList<E>,
    model: List<E>,
    listeners: MutableSet<ChangeListener<E>>,
) : AbstractSingleBinder<JList<E>, List<E>, E>(ui, model, listeners) {
    private val list = DefaultListModel<E>()

    init {
        ui.model = list
        list.addAll(model)
    }

    override fun onModelAdd(target: E) {
        list.addElement(target)
    }

    override fun onModelRemove(target: E) {
        list.removeElement(target)
    }

    override fun onModelUpdate(target: E) {
        list.setElementAt(target, list.indexOf(target))
    }
}
