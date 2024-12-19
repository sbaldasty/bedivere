package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class ClaimId(
    override val value: Int = 0
) : Id

@Serializable
data class Claim(
    override var id: ClaimId = ClaimId(),
    var title: String = "[untitled]",
    var description: String = "",
    var confidence: Confidence = Confidence.UNEXAMINED
) : Entity<ClaimId>
