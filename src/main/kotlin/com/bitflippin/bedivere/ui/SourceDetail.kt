package com.bitflippin.bedivere.ui

//import com.bitflippin.bedivere.swing.bind.TextFieldBinder
//import com.bitflippin.bedivere.editor.EditorState
//import com.bitflippin.bedivere.form.SourceForm
//import com.bitflippin.bedivere.model.Source
//import com.bitflippin.bedivere.swing.ext.TabbedPanel
//import javax.swing.JTextField
//import kotlin.reflect.KMutableProperty1
//
//class SourceDetail(
//    private val editorState: EditorState,
//    private val model: Source
//) : TabbedPanel() {
//
//    private val form = SourceForm()
//    private val titleBinder = textFieldBinder(form.titleTextField, Source::title)
//    private val urlBinder = textFieldBinder(form.urlTextField, Source::url)
//    private val descriptionBinder = textFieldBinder(form.descriptionTextField, Source::description)
//
//    init {
//        setViewportView(form.contentPanel)
//    }
//
//    override fun onClose() {
//        titleBinder.release()
//        urlBinder.release()
//        descriptionBinder.release()
//    }
//
//    private fun textFieldBinder(textField: JTextField, property: KMutableProperty1<Source, String>) =
//        TextFieldBinder(textField, model, editorState.hub.sourceListeners, property)
//}