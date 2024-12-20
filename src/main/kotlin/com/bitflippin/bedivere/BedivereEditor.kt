package com.bitflippin.bedivere

import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.ClaimId
import com.bitflippin.bedivere.model.ClaimSource
import com.bitflippin.bedivere.model.ClaimSourceId
import com.bitflippin.bedivere.model.Confidence
import com.bitflippin.bedivere.model.Entity
import com.bitflippin.bedivere.model.Ideology
import com.bitflippin.bedivere.model.IdeologyClaim
import com.bitflippin.bedivere.model.IdeologyClaimId
import com.bitflippin.bedivere.model.IdeologyId
import com.bitflippin.bedivere.model.RelatedClaim
import com.bitflippin.bedivere.model.RelatedClaimId
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.SourceId
import com.bitflippin.bedivere.model.Strength
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.model.SupportClaim
import com.bitflippin.bedivere.model.SupportClaimId
import com.bitflippin.bedivere.model.SupportId
import com.bitflippin.bedivere.model.SupportSource
import com.bitflippin.bedivere.model.SupportSourceId
import java.util.concurrent.CopyOnWriteArrayList

private fun <E : Entity<*>> Iterable<E>.nextId() =
    maxOfOrNull { it.id.value + 1} ?: 1

class BedivereEditor {

    private var database = BedivereDatabase()

    private val listeners = CopyOnWriteArrayList<BedivereListener>()

    private fun fire(event: (BedivereListener) -> Unit) = listeners.forEach { event(it) }

    fun registerListener(listener: BedivereListener) = listeners.add(listener)

    fun unregisterListener(listener: BedivereListener) = listeners.remove(listener)

    fun addClaim(
        title: String = "[untitled]",
        description: String = "",
        confidence: Confidence = Confidence.UNEXAMINED
    ) {
        val result = Claim()
        result.id = ClaimId(database.claims.nextId())
        result.title = title
        result.description = description
        result.confidence = confidence
        fire { it.onClaimAdded(result) }
    }

    fun updateClaim(
        id: ClaimId,
        title: String = database.lookupClaim(id).title,
        description: String = database.lookupClaim(id).description,
        confidence: Confidence = database.lookupClaim(id).confidence
    ) {
        val target = database.lookupClaim(id)
        val old = target.copy()
        target.title = title
        target.description = description
        target.confidence = confidence
        fire { it.onClaimUpdated(old, target) }
    }

    fun removeClaim(id: ClaimId) {
        database.lookupClaimSources(id).forEach { removeClaimSource(it.id) }
        database.lookupIdeologyClaims(id).forEach { removeIdeologyClaim(it.id) }
        database.lookupRelatedClaims(id).forEach { removeRelatedClaim(it.id) }
        database.lookupSupportClaims(id).forEach { removeSupportClaim(it.id) }
        val target = database.lookupClaim(id)
        database.claims.remove(target)
        fire { it.onClaimRemoved(target) }
    }

    fun addClaimSource(
        claimId: ClaimId = ClaimId(),
        sourceId: SourceId = SourceId(),
        description: String = "",
        enthymeme: Boolean = false
    ) {
        database.lookupClaim(claimId)
        database.lookupSource(sourceId)
        val result = ClaimSource()
        result.id = ClaimSourceId(database.claimSources.nextId())
        result.claimId = claimId
        result.sourceId = sourceId
        result.description = description
        result.enthymeme = enthymeme
        fire { it.onClaimSourceAdded(result) }
    }

    fun updateClaimSource(
        id: ClaimSourceId,
        claimId: ClaimId = database.lookupClaimSource(id).claimId,
        sourceId: SourceId = database.lookupClaimSource(id).sourceId,
        description: String = database.lookupClaimSource(id).description,
        enthymeme: Boolean = database.lookupClaimSource(id).enthymeme
    ) {
        database.lookupClaim(claimId)
        database.lookupSource(sourceId)
        val target = database.lookupClaimSource(id)
        val old = target.copy()
        target.claimId = claimId
        target.sourceId = sourceId
        target.description = description
        target.enthymeme = enthymeme
        fire { it.onClaimSourceUpdated(old, target) }
    }

    fun removeClaimSource(id: ClaimSourceId) {
        val target = database.lookupClaimSource(id)
        database.claimSources.remove(target)
        fire { it.onClaimSourceRemoved(target) }
    }

    fun addIdeology(
        title: String = "[untitled]",
        description: String = ""
    ) {
        val result = Ideology()
        result.id = IdeologyId(database.ideologies.nextId())
        result.title = title
        result.description = description
        fire { it.onIdeologyAdded(result) }
    }

    fun updateIdeology(
        id: IdeologyId,
        title: String = database.lookupIdeology(id).title,
        description: String = database.lookupIdeology(id).description
    ) {
        val target = database.lookupIdeology(id)
        val old = target.copy()
        target.title = title
        target.description = description
        fire { it.onIdeologyUpdated(old, target) }
    }

    fun removeIdeology(id: IdeologyId) {
        database.lookupIdeologyClaims(id).forEach { removeIdeologyClaim(it.id) }
        val target = database.lookupIdeology(id)
        database.ideologies.remove(target)
        fire { it.onIdeologyRemoved(target) }
    }

    fun addIdeologyClaim(
        ideologyId: IdeologyId = IdeologyId(),
        claimId: ClaimId = ClaimId()
    ) {
        val result = IdeologyClaim()
        result.id = IdeologyClaimId(database.ideologyClaims.nextId())
        result.ideologyId = ideologyId
        result.claimId = claimId
        fire { it.onIdeologyClaimAdded(result) }
    }

    fun updateIdeologyClaim(
        id: IdeologyClaimId,
        ideologyId: IdeologyId = database.lookupIdeologyClaim(id).ideologyId,
        claimId: ClaimId = database.lookupIdeologyClaim(id).claimId
    ) {
        val target = database.lookupIdeologyClaim(id)
        val old = target.copy()
        target.ideologyId = ideologyId
        target.claimId = claimId
        fire { it.onIdeologyClaimUpdated(old, target) }
    }

    fun removeIdeologyClaim(id: IdeologyClaimId) {
        val target = database.lookupIdeologyClaim(id)
        database.ideologyClaims.remove(target)
        fire { it.onIdeologyClaimRemoved(target) }
    }

    fun addRelatedClaim(
        claimId1: ClaimId = ClaimId(),
        claimId2: ClaimId = ClaimId()
    ) {
        val result = RelatedClaim()
        result.id = RelatedClaimId(database.relatedClaims.nextId())
        result.claim1 = claimId1
        result.claim2 = claimId2
        fire { it.onRelatedClaimAdded(result) }
    }

    fun updateRelatedClaim(
        id: RelatedClaimId,
        claimId1: ClaimId = database.lookupRelatedClaim(id).claim1,
        claimId2: ClaimId = database.lookupRelatedClaim(id).claim2
    ) {
        val target = database.lookupRelatedClaim(id)
        val old = target.copy()
        target.claim1 = claimId1
        target.claim2 = claimId2
        fire { it.onRelatedClaimUpdated(old, target) }
    }

    fun removeRelatedClaim(id: RelatedClaimId) {
        val target = database.lookupRelatedClaim(id)
        database.relatedClaims.remove(target)
        fire { it.onRelatedClaimRemoved(target) }
    }

    fun addSource(
        title: String = "[untitled]",
        description: String = "",
        url: String = ""
    ) {
        val result = Source()
        result.id = SourceId(database.sources.nextId())
        result.title = title
        result.description = description
        result.url = url
        fire { it.onSourceAdded(result) }
    }

    fun updateSource(
        id: SourceId,
        title: String = database.lookupSource(id).title,
        description: String = database.lookupSource(id).description,
        url: String = database.lookupSource(id).url
    ) {
        val target = database.lookupSource(id)
        val old = target.copy()
        target.title = title
        target.description = description
        target.url = url
        fire { it.onSourceUpdated(old, target) }
    }

    fun removeSource(id: SourceId) {
        database.lookupClaimSources(id).forEach { removeClaimSource(it.id) }
        database.lookupSupportSources(id).forEach { removeSupportSource(it.id) }
        val target = database.lookupSource(id)
        database.sources.remove(target)
        fire { it.onSourceRemoved(target) }
    }

    fun addSupport(
        claimId: ClaimId = ClaimId(),
        description: String = "",
        strength: Strength = Strength.UNEXAMINED
    ) {
        val result = Support()
        result.id = SupportId(database.supports.nextId())
        result.claimId = claimId
        result.description = description
        result.strength = strength
        fire { it.onSupportAdded(result) }
    }

    fun updateSupport(
        id: SupportId,
        claimId: ClaimId = database.supports.lookup(id).claimId,
        description: String = "",
        strength: Strength = Strength.UNEXAMINED
    ) {
        val target = database.lookupSupport(id)
        val old = target.copy()
        target.claimId = claimId
        target.description = description
        target.strength = strength
        fire { it.onSupportUpdated(old, target) }
    }

    fun removeSupport(id: SupportId) {
        database.lookupSupportClaims(id).forEach { removeSupportClaim(it.id) }
        database.lookupSupportSources(id).forEach { removeSupportSource(it.id) }
        val target = database.lookupSupport(id)
        database.supports.remove(target)
        fire { it.onSupportRemoved(target) }
    }

    fun addSupportClaim(
        supportId: SupportId = SupportId(),
        claimId: ClaimId = ClaimId()
    ) {
        val result = SupportClaim()
        result.id = SupportClaimId(database.supportClaims.nextId())
        result.supportId = supportId
        result.claimId = claimId
        fire { it.onSupportClaimAdded(result) }
    }

    fun updateSupportClaim(
        id: SupportClaimId,
        supportId: SupportId = database.lookupSupportClaim(id).supportId,
        claimId: ClaimId = database.lookupSupportClaim(id).claimId
    ) {
        val target = database.lookupSupportClaim(id)
        val old = target.copy()
        target.supportId = supportId
        target.claimId = claimId
        fire { it.onSupportClaimUpdated(old, target) }
    }

    fun removeSupportClaim(id: SupportClaimId) {
        val target = database.lookupSupportClaim(id)
        database.supportClaims.remove(target)
        fire { it.onSupportClaimRemoved(target) }
    }

    fun addSupportSource(
        supportId: SupportId = SupportId(),
        sourceId: SourceId = SourceId()
    ) {
        val result = SupportSource()
        result.id = SupportSourceId(database.supportSources.nextId())
        result.supportId = supportId
        result.sourceId = sourceId
        fire { it.onSupportSourceAdded(result) }
    }

    fun updateSupportSource(
        id: SupportSourceId,
        supportId: SupportId = database.lookupSupportSource(id).supportId,
        sourceId: SourceId = database.lookupSupportSource(id).sourceId
    ) {
        val target = database.lookupSupportSource(id)
        val old = target.copy()
        target.supportId = supportId
        target.sourceId = sourceId
        fire { it.onSupportSourceUpdated(old, target) }
    }

    fun removeSupportSource(id: SupportSourceId) {
        val target = database.lookupSupportSource(id)
        database.supportSources.remove(target)
        fire { it.onSupportSourceRemoved(target) }
    }

}