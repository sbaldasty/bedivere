package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Citation
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.model.addCitation
import com.bitflippin.bedivere.model.addClaim
import com.bitflippin.bedivere.model.addSource
import com.bitflippin.bedivere.model.addSupport

fun addCitation(
    editorState: EditorState,
    claim: Claim,
) {
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

fun addSupport(
    editorState: EditorState,
    claim: Claim,
): Support {
    val result = editorState.argmap.addSupport()
    claim.supportIds.add(result.id)
    broadcastChange(editorState.hub.supportListeners, result, Change.ADD)
    return result
}

fun addSupportClaim(
    editorState: EditorState,
    support: Support,
    claim: Claim
): Claim {
    support.claimIds.add(claim.id)
    return Claim()
}

fun removeClaimCitation(
    editorState: EditorState,
    claim: Claim,
    citation: Citation,
) {
    claim.citationIds.remove(citation.id)
    broadcastChange(editorState.hub.citationListeners, citation, Change.REMOVE)
}

fun setCitationSource(
    editorState: EditorState,
    citation: Citation,
) {
    citation.sourceId = editorState.selectedSourceId
    broadcastChange(editorState.hub.citationListeners, citation, Change.UPDATE)
}
