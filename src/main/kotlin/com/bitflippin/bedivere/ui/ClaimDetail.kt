package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.addCitation
import com.bitflippin.bedivere.editor.addSupport
import com.bitflippin.bedivere.editor.removeClaimCitation
import com.bitflippin.bedivere.editor.setCitationSource
import com.bitflippin.bedivere.form.ClaimForm
import com.bitflippin.bedivere.form.SupportForm
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.Confidence
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.model.citations
import com.bitflippin.bedivere.model.supports
import com.bitflippin.bedivere.swing.bind.Binder
import com.bitflippin.bedivere.swing.bind.comboBoxBinder
import com.bitflippin.bedivere.swing.bind.textFieldBinder
import com.bitflippin.bedivere.swing.ext.enableAutoResize
import java.awt.GridBagConstraints
import java.awt.Insets

class ClaimDetail(
    override val model: Claim,
    private val editorState: EditorState,
) : Binder<ClaimForm, Claim, Claim> {

    override val ui = ClaimForm()
    override val listeners = editorState.hub.claimListeners

    private val titleBinder = textFieldBinder(ui.titleTextField, Claim::title)
    private val descriptionBinder = textFieldBinder(ui.descriptionTextField, Claim::description)
    private val confidenceBinder = comboBoxBinder(Confidence.entries.toList(), ui.confidenceComboBox, Claim::confidence)
    private val citationsBinder = CitationTable(ui.citationTable, editorState.argmap.citations(model).toMutableList(), editorState)
    private val supportBinders = ArrayList<Pair<SupportDetail, SupportForm>>()
    private val supportGridConstraints = GridBagConstraints()

    init {
        supportGridConstraints.gridx = 0
        supportGridConstraints.weightx = 1.0
        supportGridConstraints.fill = GridBagConstraints.HORIZONTAL
        supportGridConstraints.insets = Insets(5, 5, 5, 5)

        ui.setSourceButton.addActionListener { setCitationSource(editorState, citationsBinder.selection()) }
        ui.addCitationButton.addActionListener { addCitation(editorState, model) }
        ui.removeCitationButton.addActionListener { removeClaimCitation(editorState, model, citationsBinder.selection()) }
        ui.addSupportButton.addActionListener { addSupport(editorState, model) }
        ui.citationTable.enableAutoResize()

        editorState.argmap.supports(model).forEach { addSupportPanel(it) }
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
        if (change == Change.ADD) {
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
