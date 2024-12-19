package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class SupportClaimId(
    override val value: Int = 0
) : Id

@Serializable
data class SupportClaim(
    override var id: SupportClaimId = SupportClaimId(),
    var supportId: SupportId = SupportId(),
    var claimId: ClaimId = ClaimId()
) : Entity<SupportClaimId>
