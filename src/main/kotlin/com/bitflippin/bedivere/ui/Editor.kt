package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.editor.addClaim
import com.bitflippin.bedivere.editor.addSource
import com.bitflippin.bedivere.form.MainForm
import com.bitflippin.bedivere.model.SourceId
import com.bitflippin.bedivere.model.saveModel
import com.bitflippin.bedivere.swing.bind.Binder
import com.bitflippin.bedivere.swing.bind.ListBinder
import com.bitflippin.bedivere.swing.ext.addDoubleClickListener
import com.bitflippin.bedivere.swing.ext.setCellRenderer
import java.util.concurrent.CopyOnWriteArraySet

class Editor(
    override val ui: MainForm,
    override val model: EditorState
) : Binder<MainForm, EditorState, EditorState> {

    override val listeners = CopyOnWriteArraySet<ChangeListener<EditorState>>()

    private val claimsList = ListBinder(ui.claimsList, model.argmap.claims, model.hub.claimListeners)
    private val sourcesList = ListBinder(ui.sourcesList, model.argmap.sources, model.hub.sourceListeners)

    init {
        ui.saveButton.addActionListener { saveModel(model.file, model.argmap) }
        ui.addClaimButton.addActionListener { addClaim(model) }
        ui.addSourceButton.addActionListener { addSource(model) }
        ui.claimsList.addDoubleClickListener {
            val binder = ClaimDetail(it, model)
            model.detailTabs.open("Claim ${it.id.value}", binder.ui.contentPanel, binder)
        }
        ui.claimsList.setCellRenderer { x -> x.title }
        ui.sourcesList.addDoubleClickListener {
            val binder = SourceDetail(it, model)
            model.detailTabs.open("Source ${it.id.value}", binder.ui.contentPanel, binder)
        }
        ui.sourcesList.setCellRenderer { x -> x.title }
        ui.sourcesList.addListSelectionListener {
            if (!it.valueIsAdjusting) {
                model.selectedSourceId = ui.sourcesList.selectedValue?.id ?: SourceId(0)
            }
        }
    }

    override fun release() {
        claimsList.release()
        sourcesList.release()
    }
}
