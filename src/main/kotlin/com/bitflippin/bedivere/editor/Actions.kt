package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.addSource

fun addSource(editorState: EditorState): Source {
    val result = editorState.argmap.addSource()
    editorState.hub.broadcast(result, Change.ADD)
    return result
}