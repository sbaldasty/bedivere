package com.bitflippin.bedivere

import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.ClaimId
import com.bitflippin.bedivere.model.ClaimSource
import com.bitflippin.bedivere.model.ClaimSourceId
import com.bitflippin.bedivere.model.Entity
import com.bitflippin.bedivere.model.Id
import com.bitflippin.bedivere.model.Ideology
import com.bitflippin.bedivere.model.IdeologyClaim
import com.bitflippin.bedivere.model.IdeologyClaimId
import com.bitflippin.bedivere.model.IdeologyId
import com.bitflippin.bedivere.model.RelatedClaim
import com.bitflippin.bedivere.model.RelatedClaimId
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.SourceId
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.model.SupportClaim
import com.bitflippin.bedivere.model.SupportClaimId
import com.bitflippin.bedivere.model.SupportId
import com.bitflippin.bedivere.model.SupportSource
import com.bitflippin.bedivere.model.SupportSourceId
import kotlinx.serialization.Serializable

@Serializable
data class BedivereDatabase(
    var claims: MutableList<Claim> = ArrayList(),
    var claimSources: MutableList<ClaimSource> = ArrayList(),
    var ideologies: MutableList<Ideology> = ArrayList(),
    var ideologyClaims: MutableList<IdeologyClaim> = ArrayList(),
    var relatedClaims: MutableList<RelatedClaim> = ArrayList(),
    var sources: MutableList<Source> = ArrayList(),
    var supportClaims: MutableList<SupportClaim> = ArrayList(),
    var supportSources: MutableList<SupportSource> = ArrayList(),
    var supports: MutableList<Support> = ArrayList()
)

fun <I : Id, E : Entity<I>> Iterable<E>.lookup(id: I) =
    first { it.id == id }

fun BedivereDatabase.lookupClaim(id: ClaimId) =
    claims.lookup(id)

fun BedivereDatabase.lookupClaimSource(id: ClaimSourceId) =
    claimSources.lookup(id)

fun BedivereDatabase.lookupClaimSources(id: ClaimId) =
    claimSources.filter { it.claimId == id }

fun BedivereDatabase.lookupClaimSources(id: SourceId) =
    claimSources.filter { it.sourceId == id }

fun BedivereDatabase.lookupIdeology(id: IdeologyId) =
    ideologies.lookup(id)

fun BedivereDatabase.lookupIdeologyClaim(id: IdeologyClaimId) =
    ideologyClaims.lookup(id)

fun BedivereDatabase.lookupIdeologyClaims(id: ClaimId) =
    ideologyClaims.filter { it.claimId == id }

fun BedivereDatabase.lookupIdeologyClaims(id: IdeologyId) =
    ideologyClaims.filter { it.ideologyId == id }

fun BedivereDatabase.lookupRelatedClaim(id: RelatedClaimId) =
    relatedClaims.lookup(id)

fun BedivereDatabase.lookupRelatedClaims(id: ClaimId) =
    relatedClaims.filter { it.claim1 == id || it.claim2 == id }

fun BedivereDatabase.lookupSource(id: SourceId) =
    sources.lookup(id)

fun BedivereDatabase.lookupSupport(id: SupportId) =
    supports.lookup(id)

fun BedivereDatabase.lookupSupportClaim(id: SupportClaimId) =
    supportClaims.lookup(id)

fun BedivereDatabase.lookupSupportClaims(id: ClaimId) =
    supportClaims.filter { it.claimId == id }

fun BedivereDatabase.lookupSupportClaims(id: SupportId) =
    supportClaims.filter { it.supportId == id }

fun BedivereDatabase.lookupSupportSource(id: SupportSourceId) =
    supportSources.lookup(id)

fun BedivereDatabase.lookupSupportSources(id: SourceId) =
    supportSources.filter { it.sourceId == id }

fun BedivereDatabase.lookupSupportSources(id: SupportId) =
    supportSources.filter { it.supportId == id }