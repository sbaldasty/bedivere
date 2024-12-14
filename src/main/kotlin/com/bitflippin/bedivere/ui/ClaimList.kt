package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.swing.ext.addDoubleClickListener
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.DefaultListModel
import javax.swing.JLabel
import javax.swing.JList

class ClaimList(editorState: EditorState) : JList<Claim>() {

    private val list = DefaultListModel<Claim>()

    init {
        model = list
        cellRenderer = CustomRenderer()
        addDoubleClickListener { editorState.tabManager.open(ClaimDetail(editorState, it)) }
        editorState.hub.claimListeners.add(this::onClaimChange)
        list.addAll(editorState.argmap.claims)
    }

    fun onClaimChange(claim: Claim, change: Change) {
        when (change) {
            Change.ADD -> list.addElement(claim)
            Change.REMOVE -> list.removeElement(claim)
            Change.UPDATE -> list.setElementAt(claim, list.indexOf(claim))
        }
    }
}

class CustomRenderer : DefaultListCellRenderer() {
    override fun getListCellRendererComponent(
        list: JList<*>?,
        value: Any?,
        index: Int,
        isSelected: Boolean,
        cellHasFocus: Boolean
    ): Component {
        val label = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus) as JLabel
        val claim = value as Claim
        label.text = claim.title
        return label
    }
}