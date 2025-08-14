package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.addSupportClaim
import com.bitflippin.bedivere.editor.addSupportSource
import com.bitflippin.bedivere.editor.removeSupportClaim
import com.bitflippin.bedivere.form.SupportForm
import com.bitflippin.bedivere.model.Strength
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.swing.bind.Binder
import com.bitflippin.bedivere.swing.bind.comboBoxBinder
import com.bitflippin.bedivere.swing.bind.textFieldBinder
import com.bitflippin.bedivere.swing.ext.enableAutoResize

class SupportDetail(
    override val model: Support,
    editorState: EditorState,
) : Binder<SupportForm, Support, Support> {

    override val ui = SupportForm()
    override val listeners = editorState.hub.supportListeners

    private val descriptionBinder = textFieldBinder(ui.descriptionTextField, Support::description)
    private val strengthBinder = comboBoxBinder(Strength.entries.toList(), ui.strengthComboBox, Support::strength)
    private val supportSourcesBinder = SupportSourceTable(ui.citationTable, model, editorState)
    private val supportClaimsBinder = SupportClaimTable(ui.claimTable, model, editorState)

    init {
        ui.addClaimButton.addActionListener { addSupportClaim(editorState, model, editorState.argmap.lookup(editorState.selectedClaimId)) }
        ui.addCitationButton.addActionListener { addSupportSource(editorState, model, editorState.argmap.lookup(editorState.selectedSourceId)) }
        ui.removeClaimButton.addActionListener { removeSupportClaim(editorState, supportClaimsBinder.selection()) }
        ui.citationTable.enableAutoResize()
        ui.claimTable.enableAutoResize()
        ui.claimTable.selectionModel.addListSelectionListener {
            if (!it.valueIsAdjusting) {
                updateRemoveClaimButtonEnabled()
            }
        }
        ui.citationTable.selectionModel.addListSelectionListener {
            if (!it.valueIsAdjusting) {
                updateRemoveCitationButtonEnabled()
            }
        }
        updateRemoveCitationButtonEnabled()
        updateRemoveClaimButtonEnabled()
    }

    private fun updateRemoveCitationButtonEnabled() {
        ui.removeCitationButton.isEnabled = ui.citationTable.selectedRow != -1
    }

    private fun updateRemoveClaimButtonEnabled() {
        ui.removeClaimButton.isEnabled = ui.claimTable.selectedRow != -1
    }

    override fun release() {
        descriptionBinder.release()
        strengthBinder.release()
        supportSourcesBinder.release()
        supportClaimsBinder.release()
    }
}
