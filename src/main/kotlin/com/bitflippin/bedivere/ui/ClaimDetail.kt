package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.addClaimSource
import com.bitflippin.bedivere.editor.addSupport
import com.bitflippin.bedivere.editor.removeClaimSource
import com.bitflippin.bedivere.editor.setCitationSource
import com.bitflippin.bedivere.form.ClaimForm
import com.bitflippin.bedivere.form.SupportForm
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.Confidence
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.swing.bind.TabBinder
import com.bitflippin.bedivere.swing.bind.comboBoxBinder
import com.bitflippin.bedivere.swing.bind.textFieldBinder
import com.bitflippin.bedivere.swing.ext.enableAutoResize
import java.awt.GridBagConstraints
import java.awt.Insets
import javax.swing.JComponent

class ClaimDetail(
    override val model: Claim,
    private val editorState: EditorState,
) : TabBinder<ClaimForm, Claim, Claim> {

    override val ui = ClaimForm()
    override val listeners = editorState.hub.claimListeners
    override val title = "Claim ${model.id.value}"
    override val component: JComponent = ui.contentPanel

    private val titleBinder = textFieldBinder(ui.titleTextField, Claim::title)
    private val descriptionBinder = textFieldBinder(ui.descriptionTextField, Claim::description)
    private val confidenceBinder = comboBoxBinder(Confidence.entries.toList(), ui.confidenceComboBox, Claim::confidence)
    private val citationsBinder = ClaimSourceTable(ui.citationTable, model, editorState)
    private val supportBinders = ArrayList<Pair<SupportDetail, SupportForm>>()
    private val supportGridConstraints = GridBagConstraints()

    init {
        supportGridConstraints.gridx = 0
        supportGridConstraints.weightx = 1.0
        supportGridConstraints.fill = GridBagConstraints.HORIZONTAL
        supportGridConstraints.insets = Insets(5, 5, 5, 5)

        ui.setSourceButton.addActionListener { setCitationSource(editorState, citationsBinder.selection()) }
        ui.addCitationButton.addActionListener { addClaimSource(editorState, model) }
        ui.removeCitationButton.addActionListener { removeClaimSource(editorState, model, citationsBinder.selection()) }
        ui.addSupportButton.addActionListener { addSupport(editorState, model) }
        ui.citationTable.enableAutoResize()

        editorState.argmap.lookupSupports(model).forEach { addSupportPanel(it) }
        editorState.hub.supportListeners.add(this::onSupportChange)
    }

    private fun addSupportPanel(support: Support) {
        supportGridConstraints.gridy += 1
        val binder = SupportDetail(support, editorState)
        ui.supportsPanel.add(binder.ui.contentPanel, supportGridConstraints)
        ui.supportsPanel.revalidate()
        ui.contentPanel.revalidate()
        ui.contentPanel.repaint()
        supportBinders.add(Pair(binder, binder.ui))
    }

    private fun onSupportChange(support: Support, change: Change) {
        if (support.claimId == model.id && change == Change.ADD) {
            addSupportPanel(support)
        }
    }

    override fun release() {
        titleBinder.release()
        descriptionBinder.release()
        confidenceBinder.release()
        citationsBinder.release()
        supportBinders.forEach { it.first.release() }
    }
}
