package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class SupportId(
    override val value: Int = 0
) : Id

@Serializable
data class Support(
    override var id: SupportId = SupportId(),
    var claimId: ClaimId = ClaimId(),
    var description: String = "",
    var strength: Strength = Strength.UNEXAMINED
) : Entity<SupportId>
