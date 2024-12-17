package com.bitflippin.bedivere.swing.bind

import javax.swing.JComboBox
import javax.swing.JTextField
import kotlin.reflect.KMutableProperty1

fun <U, M> Binder<U, M, M>.textFieldBinder(
    textField: JTextField, property:
    KMutableProperty1<M, String>
) = TextFieldBinder(textField, model, listeners, property)

fun <U, M, P> Binder<U, M, M>.comboBoxBinder(
    items: List<P>,
    comboBox: JComboBox<P>,
    property: KMutableProperty1<M, P>
) = ComboBoxBinder(comboBox, model, listeners, property, items)
