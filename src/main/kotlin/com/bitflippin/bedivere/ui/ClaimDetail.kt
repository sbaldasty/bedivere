package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.Change
import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.addCitation
import com.bitflippin.bedivere.editor.addSupport
import com.bitflippin.bedivere.editor.removeClaimCitation
import com.bitflippin.bedivere.editor.setCitationSource
import com.bitflippin.bedivere.form.ClaimForm
import com.bitflippin.bedivere.form.SupportForm
import com.bitflippin.bedivere.model.Citation
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.Confidence
import com.bitflippin.bedivere.model.SourceId
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.model.citations
import com.bitflippin.bedivere.model.lookup
import com.bitflippin.bedivere.model.supports
import com.bitflippin.bedivere.swing.bind.Binder
import com.bitflippin.bedivere.swing.bind.comboBoxBinder
import com.bitflippin.bedivere.swing.bind.textFieldBinder
import com.bitflippin.bedivere.swing.ext.CheckBoxRenderer
import com.bitflippin.bedivere.swing.ext.ModularColumn
import com.bitflippin.bedivere.swing.ext.PropertyTableCellRenderer
import com.bitflippin.bedivere.swing.ext.TableBinder
import com.bitflippin.bedivere.swing.ext.enableAutoResize
import java.awt.GridBagConstraints
import java.awt.Insets
import javax.swing.DefaultCellEditor
import javax.swing.JCheckBox
import javax.swing.JTextField
import javax.swing.table.DefaultTableCellRenderer

class ClaimDetail(
    override val model: Claim,
    private val editorState: EditorState,
) : Binder<ClaimForm, Claim, Claim> {

    override val ui = ClaimForm()
    override val listeners = editorState.hub.claimListeners

    private val titleBinder = textFieldBinder(ui.titleTextField, Claim::title)
    private val descriptionBinder = textFieldBinder(ui.descriptionTextField, Claim::description)
    private val confidenceBinder = comboBoxBinder(Confidence.entries.toList(), ui.confidenceComboBox, Claim::confidence)
    private val citationsBinder = createCitationsBinder()
    private val supportBinders = ArrayList<Pair<SupportBinder, SupportForm>>()
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
        val binder = SupportBinder(support, editorState)
        val supportBinder = SupportForm()
        ui.supportsPanel.add(supportBinder.contentPanel, supportGridConstraints)
        supportBinders.add(Pair(binder, supportBinder))
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

    private fun createCitationsBinder(): TableBinder<Citation> {
        val sourceColumn =
            ModularColumn(
                "Source",
                32,
                { false },
                Citation::sourceId,
                PropertyTableCellRenderer { x: SourceId -> if (x.value == 0) "(none)" else editorState.argmap.lookup(x).title },
                DefaultCellEditor(JTextField()),
            )

        val descriptionColumn =
            ModularColumn(
                "Description",
                75,
                { true },
                Citation::description,
                DefaultTableCellRenderer(),
                DefaultCellEditor(JTextField()),
            )

        val enthymemeColumn =
            ModularColumn(
                "Enthymeme",
                32,
                { true },
                Citation::enthymeme,
                CheckBoxRenderer(),
                DefaultCellEditor(JCheckBox()),
            )

        return TableBinder(
            ui.citationTable,
            editorState.argmap.citations(model).toMutableList(),
            editorState.hub.citationListeners,
            listOf(sourceColumn, descriptionColumn, enthymemeColumn),
        )
    }
}
