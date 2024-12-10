package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.*

fun addCitation(editorState: EditorState, claim: Claim) {
    val result = editorState.argmap.addCitation()
    claim.citationIds.add(result.id)
    broadcastChange(editorState.hub.citationListeners, result, Change.ADD)
}

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

fun addSupport(editorState: EditorState, claim: Claim): Support {
    val result = editorState.argmap.addSupport()
    claim.supportIds.add(result.id)
    broadcastChange(editorState.hub.supportListeners, result, Change.ADD)
    return result
}

fun setCitationSource(editorState: EditorState, citation: Citation) {
    citation.sourceId = editorState.selectedSourceId
    broadcastChange(editorState.hub.citationListeners, citation, Change.UPDATE)
}