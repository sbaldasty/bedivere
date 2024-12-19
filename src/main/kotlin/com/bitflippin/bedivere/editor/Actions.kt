package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.ClaimSource
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.model.SupportClaim
import com.bitflippin.bedivere.model.SupportSource

fun addClaimSource(
    editorState: EditorState,
    claim: Claim,
) {
    val result = editorState.argmap.addClaimSource()
    result.claimId = claim.id
    broadcastChange(editorState.hub.claimSourceListeners, result, Change.ADD)
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

fun addSupportClaim(editorState: EditorState, support: Support, claim: Claim): SupportClaim {
    val result = editorState.argmap.addSupportClaim()
    result.supportId = support.id
    result.claimId = claim.id
    broadcastChange(editorState.hub.supportClaimListeners, result, Change.ADD)
    return result
}

fun addSupportSource(editorState: EditorState, support: Support, source: Source): SupportSource {
    val result = editorState.argmap.addSupportSource()
    result.supportId = support.id
    result.sourceId = source.id
    broadcastChange(editorState.hub.supportSourceListeners, result, Change.ADD)
    return result
}

fun removeClaimSource(
    editorState: EditorState,
    claim: Claim,
    citation: ClaimSource,
) {
    editorState.argmap.claimSources.remove(citation)
    broadcastChange(editorState.hub.claimSourceListeners, citation, Change.REMOVE)
}

fun setCitationSource(
    editorState: EditorState,
    citation: ClaimSource,
) {
    citation.sourceId = editorState.selectedSourceId
    broadcastChange(editorState.hub.claimSourceListeners, citation, Change.UPDATE)
}
