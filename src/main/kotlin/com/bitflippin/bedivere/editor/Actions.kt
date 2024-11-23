package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.addClaim
import com.bitflippin.bedivere.model.addSource

fun addClaim(editorState: EditorState): Claim {
    val result = editorState.argmap.addClaim()
    broadcastChange(editorState.hub.claimListeners, result, Change.ADD)
    return result
}

fun addSource(editorState: EditorState): Source {
    val result = editorState.argmap.addSource()
    broadcastChange(editorState.hub.sourceListeners, result, Change.ADD)
    return result
}