package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class RelatedClaimId(
    override val value: Int = 0
) : Id

@Serializable
data class RelatedClaim(
    override var id: RelatedClaimId = RelatedClaimId(),
    var claim1: ClaimId = ClaimId(),
    var claim2: ClaimId = ClaimId()
) : Entity<RelatedClaimId>
