package com.bitflippin.bedivere.ui

import com.bitflippin.bedivere.editor.ChangeListener
import com.bitflippin.bedivere.editor.EditorState
import com.bitflippin.bedivere.form.MainForm
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.swing.bind.Binder
import com.bitflippin.bedivere.swing.bind.ListBinder
import com.bitflippin.bedivere.swing.ext.setCellRenderer

class Editor(
    override val ui: MainForm,
    override val model: EditorState
) : Binder<MainForm, EditorState> {

    private val claimsList = ListBinder(ui.claimsList, model.argmap.claims, model.hub.claimListeners)
    private val sourcesList = ListBinder(ui.sourcesList, model.argmap.sources, model.hub.sourceListeners)

    init {
        ui.claimsList.setCellRenderer { x -> x.title }
        ui.sourcesList.setCellRenderer { x -> x.title }
    }

    override fun release() {
        claimsList.release()
        sourcesList.release()
    }
}