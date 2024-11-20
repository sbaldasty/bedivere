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
    var citations: List<Citation> = ArrayList(),
    var claims: List<Claim> = ArrayList(),
    var ideologies: List<Ideology> = ArrayList(),
    var sources: List<Source> = ArrayList(),
    var supports: List<Support> = ArrayList()
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
    var citations: List<Citation> = ArrayList(),
    var supportIds: List<SupportId> = ArrayList(),
    var counterSupportIds: List<SupportId> = ArrayList(),
    var neighbors: List<ClaimId> = ArrayList()
)

@Serializable
data class Ideology(
    var id: IdeologyId = IdeologyId(0),
    var title: String = "",
    var description: String = "",
    var claimIds: List<ClaimId> = ArrayList()
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
    var evidence: List<Int> = ArrayList(),
    var citationIds: List<CitationId> = ArrayList()
)

enum class Confidence { TRUE, LIKELY, NEUTRAL, UNLIKELY, FALSE, UNEXAMINED }
enum class Strength { SOUND, STRONG, MODERATE, WEAK, NONE, UNEXAMINED }