package com.bitflippin.bedivere

import kotlinx.serialization.Serializable

@Serializable
data class ArgMap(
    var sources: List<Source> = ArrayList(),
    var claims: List<Claim> = ArrayList(),
    var ideologies: List<Ideology> = ArrayList()
)

@Serializable
data class Citation(
    var description: String = "",
    var enthememe: Boolean = false,
    var source: Int = 0
)

@Serializable
data class Claim(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var confidence: Confidence = Confidence.UNEXAMINED,
    var citations: List<Citation> = ArrayList(),
    var supports: List<Support> = ArrayList(),
    var counters: List<Support> = ArrayList(),
    var neighbors: List<Int> = ArrayList()
)

@Serializable
data class Ideology(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var claims: List<Int> = ArrayList()
)

@Serializable
data class Source(
    var id: Int = 0,
    var title: String = "",
    var url: String = "",
    var description: String = ""
)

@Serializable
data class Support(
    var description: String = "",
    var strength: Strength = Strength.UNEXAMINED,
    var evidence: List<Int> = ArrayList()
)

enum class Confidence { TRUE, LIKELY, NEUTRAL, UNLIKELY, FALSE, UNEXAMINED }
enum class Strength { SOUND, STRONG, MODERATE, WEAK, NONE, UNEXAMINED }