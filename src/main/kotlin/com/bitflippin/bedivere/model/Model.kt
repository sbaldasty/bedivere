package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class CitationId(val value: Int)

@JvmInline
@Serializable
value class ClaimId(val value: Int)

@JvmInline
@Serializable
value class IdeologyId(val value: Int)

@JvmInline
@Serializable
value class SourceId(val value: Int)

@JvmInline
@Serializable
value class SupportId(val value: Int)

@Serializable
data class Argmap(
    var citations: MutableList<Citation> = ArrayList(),
    var claims: MutableList<Claim> = ArrayList(),
    var ideologies: MutableList<Ideology> = ArrayList(),
    var sources: MutableList<Source> = ArrayList(),
    var supports: MutableList<Support> = ArrayList()
)

@Serializable
data class Citation(
    var id: CitationId = CitationId(0),
    var description: String = "",
    var enthymeme: Boolean = false,
    var sourceId: SourceId = SourceId(0)
)

@Serializable
data class Claim(
    var id: ClaimId = ClaimId(0),
    var title: String = "",
    var description: String = "",
    var confidence: Confidence = Confidence.UNEXAMINED,
    var citationIds: MutableList<CitationId> = ArrayList(),
    var supportIds: MutableList<SupportId> = ArrayList(),
    var counterSupportIds: MutableList<SupportId> = ArrayList(),
    var neighbors: MutableList<ClaimId> = ArrayList()
)

@Serializable
data class Ideology(
    var id: IdeologyId = IdeologyId(0),
    var title: String = "",
    var description: String = "",
    var claimIds: MutableList<ClaimId> = ArrayList()
)

@Serializable
data class Source(
    var id: SourceId = SourceId(0),
    var title: String = "",
    var url: String = "",
    var description: String = ""
)

@Serializable
data class Support(
    var id: SupportId = SupportId(0),
    var description: String = "",
    var strength: Strength = Strength.UNEXAMINED,
    var citationIds: MutableList<CitationId> = ArrayList()
)

enum class Confidence { TRUE, LIKELY, NEUTRAL, UNLIKELY, FALSE, UNEXAMINED }
enum class Strength { SOUND, STRONG, MODERATE, WEAK, NONE, UNEXAMINED }