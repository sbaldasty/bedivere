package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class SupportSourceId(
    override val value: Int = 0
) : Id

@Serializable
data class SupportSource(
    override var id: SupportSourceId = SupportSourceId(),
    var supportId: SupportId = SupportId(),
    var sourceId: SourceId = SourceId(),
    var description: String = ""
) : Entity<SupportSourceId>
