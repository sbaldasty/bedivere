package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class ClaimSourceId(
    override val value: Int = 0
) : Id

@Serializable
data class ClaimSource(
    override var id: ClaimSourceId = ClaimSourceId(),
    var claimId: ClaimId = ClaimId(),
    var sourceId: SourceId = SourceId(),
    var description: String = "",
    var enthymeme: Boolean = false
) : Entity<ClaimSourceId>
