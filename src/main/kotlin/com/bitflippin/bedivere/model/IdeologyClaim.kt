package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class IdeologyClaimId(
    override val value: Int = 0
) : Id

@Serializable
data class IdeologyClaim(
    override var id: IdeologyClaimId = IdeologyClaimId(),
    var ideologyId: IdeologyId = IdeologyId(),
    var claimId: ClaimId = ClaimId()
) : Entity<IdeologyClaimId>
