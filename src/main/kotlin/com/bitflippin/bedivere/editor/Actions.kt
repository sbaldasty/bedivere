package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.ClaimSource
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.Support

fun addCitation(
    editorState: EditorState,
    claim: Claim,
) {
    val result = editorState.argmap.addClaimSource()
    result.claimId = claim.id
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

fun addSupport(
    editorState: EditorState,
    claim: Claim,
): Support {
    val result = editorState.argmap.addSupport()
    result.claimId = claim.id
    broadcastChange(editorState.hub.supportListeners, result, Change.ADD)
    return result
}

fun removeClaimCitation(
    editorState: EditorState,
    claim: Claim,
    citation: ClaimSource,
) {
    editorState.argmap.claimSources.remove(citation)
    broadcastChange(editorState.hub.citationListeners, citation, Change.REMOVE)
}

fun setCitationSource(
    editorState: EditorState,
    citation: ClaimSource,
) {
    citation.sourceId = editorState.selectedSourceId
    broadcastChange(editorState.hub.citationListeners, citation, Change.UPDATE)
}
