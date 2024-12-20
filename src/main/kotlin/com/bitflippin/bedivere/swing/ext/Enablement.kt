package com.bitflippin.bedivere.swing.ext

import javax.swing.JComponent
import javax.swing.JTable

fun JComponent.enableOnSelection(component: JTable) {
    isEnabled = component.selectedRow != -1
    component.selectionModel.addListSelectionListener {
        if (!it.valueIsAdjusting) {
            isEnabled = component.selectedRow != -1
        }
    }
}
