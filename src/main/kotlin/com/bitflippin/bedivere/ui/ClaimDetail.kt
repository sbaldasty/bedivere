package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.swing.bind.ComboBoxBinder
import com.bitflippin.bedivere.swing.bind.TextFieldBinder
import com.bitflippin.bedivere.editor.*
import com.bitflippin.bedivere.form.ClaimForm
import com.bitflippin.bedivere.form.SupportForm
import com.bitflippin.bedivere.model.*
import com.bitflippin.bedivere.swing.bind.Binder
import com.bitflippin.bedivere.swing.ext.CheckBoxRenderer
import com.bitflippin.bedivere.swing.ext.ModularColumn
import com.bitflippin.bedivere.swing.ext.PropertyTableCellRenderer
import com.bitflippin.bedivere.swing.ext.TableBinder
import javax.swing.*
import javax.swing.table.DefaultTableCellRenderer
import kotlin.reflect.KMutableProperty1

class ClaimDetail(
    override val ui: ClaimForm,
    override val model: Claim,
    private val editorState: EditorState,
) : Binder<ClaimForm, Claim> {

    private val titleBinder = textFieldBinder(ui.titleTextField, Claim::title)
    private val descriptionBinder = textFieldBinder(ui.descriptionTextField, Claim::description)
    private val confidenceBinder = comboBoxBinder(Confidence.entries.toList(), ui.confidenceComboBox, Claim::confidence)
    private val citationsBinder = createCitationsBinder()
    private val supportBinders = ArrayList<Pair<SupportBinder, SupportForm>>()

    init {
        ui.setSourceButton.addActionListener { setCitationSource(editorState, citationsBinder.selection()) }
        ui.addCitationButton.addActionListener { addCitation(editorState, model) }
        ui.addSupportButton.addActionListener { addSupport(editorState, model) }

        editorState.argmap.supports(model).forEach { addSupportPanel(it) }

        editorState.hub.supportListeners.add(this::onSupportChange)
    }

    private fun addSupportPanel(support: Support) {
        val supportForm = SupportForm()
        ui.supportsPanel.add(supportForm.contentPanel)
        val binder = SupportBinder(supportForm, editorState, support)
        supportBinders.add(Pair(binder, supportForm))
    }

    private fun onSupportChange(support: Support, change: Change) {
        if (change == Change.ADD) {
            addSupportPanel(support)
        }
        // TODO Handle other kinds of Support change
    }

    override fun release() {
        titleBinder.release()
        descriptionBinder.release()
        confidenceBinder.release()
        citationsBinder.onClose()
        supportBinders.forEach { it.first.onClose() }
    }

    private fun createCitationsBinder(): TableBinder<Citation> {
        val sourceColumn = ModularColumn(
            "Source",
            32,
            { false },
            Citation::sourceId,
            PropertyTableCellRenderer {x: SourceId -> editorState.argmap.lookup(x).title },
            DefaultCellEditor(JTextField()))

        val descriptionColumn = ModularColumn(
            "Description",
            75,
            { true },
            Citation::description,
            DefaultTableCellRenderer(),
            DefaultCellEditor(JTextField()))

        val enthymemeColumn = ModularColumn(
            "Enthymeme",
            32,
            { true },
            Citation::enthymeme,
            CheckBoxRenderer(),
            DefaultCellEditor(JCheckBox()))

        val columns = listOf(sourceColumn, descriptionColumn, enthymemeColumn)
        return TableBinder(columns, editorState.argmap.citations(model), ui.citationTable, editorState.hub.citationListeners)
    }

    override fun component(): JPanel = ui.contentPanel

    private fun <U> comboBoxBinder(items: List<U>, comboBox: JComboBox<U>, property: KMutableProperty1<Claim, U>) =
        ComboBoxBinder(comboBox, model, editorState.hub.claimListeners, property, items)

    private fun textFieldBinder(textField: JTextField, property: KMutableProperty1<Claim, String>) =
        TextFieldBinder(textField, model, editorState.hub.claimListeners, property)
}