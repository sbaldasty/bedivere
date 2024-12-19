package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class IdeologyId(
    override val value: Int = 0
) : Id

@Serializable
data class Ideology(
    override var id: IdeologyId = IdeologyId(),
    var title: String = "[untitled]",
    var description: String = ""
) : Entity<IdeologyId>
