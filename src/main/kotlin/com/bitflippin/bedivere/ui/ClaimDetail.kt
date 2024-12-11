package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.bind.ComboBoxBinder
import com.bitflippin.bedivere.bind.TextFieldBinder
import com.bitflippin.bedivere.editor.*
import com.bitflippin.bedivere.form.ClaimForm
import com.bitflippin.bedivere.form.SupportForm
import com.bitflippin.bedivere.model.*
import com.bitflippin.bedivere.swing.*
import javax.swing.*
import javax.swing.table.DefaultTableCellRenderer
import kotlin.reflect.KMutableProperty1

class ClaimDetail(
    private val editorState: EditorState,
    private val model: Claim
) : TabbedPanel() {

    private val form = ClaimForm()
    private val titleBinder = textFieldBinder(form.titleTextField, Claim::title)
    private val descriptionBinder = textFieldBinder(form.descriptionTextField, Claim::description)
    private val confidenceBinder = comboBoxBinder(Confidence.entries.toList(), form.confidenceComboBox, Claim::confidence)
    private val citationsBinder = createCitationsBinder()
    private val supportBinders = ArrayList<Pair<SupportBinder, SupportForm>>()

    init {
        setViewportView(form.contentPanel)

        form.setSourceButton.addActionListener { setCitationSource(editorState, citationsBinder.selection()) }
        form.addCitationButton.addActionListener { addCitation(editorState, model) }
        form.addSupportButton.addActionListener { addSupport(editorState, model) }

        editorState.argmap.supports(model).forEach { addSupportPanel(it) }

        editorState.hub.supportListeners.add(this::onSupportChange)
    }

    private fun addSupportPanel(support: Support) {
        val supportForm = SupportForm()
        form.supportsPanel.add(supportForm.contentPanel)
        val binder = SupportBinder(supportForm, editorState, support)
        supportBinders.add(Pair(binder, supportForm))
    }

    private fun onSupportChange(support: Support, change: Change) {
        if (change == Change.ADD) {
            addSupportPanel(support)
        }
        // TODO Handle other kinds of Support change
    }

    override fun onClose() {
        titleBinder.onClose()
        descriptionBinder.onClose()
        confidenceBinder.onClose()
        citationsBinder.onClose()
        supportBinders.forEach { it.first.onClose() }
    }

    private fun createCitationsBinder(): TableBinder<Citation> {
        val sourceColumn = ModularColumn(
            "Source",
            32,
            { false },
            Citation::sourceId,
            DefaultTableCellRenderer(),
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
        return TableBinder(columns, editorState.argmap.citations(model), form.citationTable, editorState.hub.citationListeners)
    }

    private fun <U> comboBoxBinder(items: List<U>, comboBox: JComboBox<U>, property: KMutableProperty1<Claim, U>) =
        ComboBoxBinder(items, comboBox, model, property, editorState.hub.claimListeners)

    private fun textFieldBinder(textField: JTextField, property: KMutableProperty1<Claim, String>) =
        TextFieldBinder(textField, model, property, editorState.hub.claimListeners)
}