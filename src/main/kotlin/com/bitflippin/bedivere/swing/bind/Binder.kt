package com.bitflippin.bedivere.swing.bind

import com.bitflippin.bedivere.editor.ChangeListener
import javax.swing.JComboBox
import javax.swing.JTextField
import kotlin.reflect.KMutableProperty1

interface Binder<U, M, L> {
    val ui: U
    val model: M
    val listeners: MutableSet<ChangeListener<L>>

    fun release()
}

fun <U, M> Binder<U, M, M>.textFieldBinder(
    textField: JTextField, property:
    KMutableProperty1<M, String>
) = TextFieldBinder(textField, model, listeners, property)

fun <U, M, P> Binder<U, M, M>.comboBoxBinder(
    items: List<P>,
    comboBox: JComboBox<P>,
    property: KMutableProperty1<M, P>
) = ComboBoxBinder(comboBox, model, listeners, property, items)
