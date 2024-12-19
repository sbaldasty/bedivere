package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class SourceId(
    override val value: Int = 0
) : Id

@Serializable
data class Source(
    override var id: SourceId = SourceId(),
    var title: String = "[untitled]",
    var url: String = "",
    var description: String = "",
) : Entity<SourceId>
