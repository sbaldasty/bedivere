package com.bitflippin.bedivere

data class ArgMap(
    val claims: List<Claim>,
    val sources: List<Source>,
    val ideologies: List<Ideology>
)

data class Citation(
    var descr: String,
    var enthememe: Boolean,
    val source: Source
)

data class Claim(
    var title: String,
    var descr: String,
    var confidence: Confidence,
    val id: Int,
    val citations: List<Citation>,
    val supports: List<Support>,
    val counters: List<Support>,
    val neighbors: List<Claim>
)

data class Ideology(
    var name: String,
    var descr: String,
    val claims: List<Claim>,
    val id: Int
)

data class Source(
    var title: String,
    var url: String,
    var descr: String,
    val id: Int
)

data class Support(
    var descr: String,
    var strength: Strength,
    val evidence: List<Claim>
)

enum class Confidence { TRUE, LIKELY, NEUTRAL, UNLIKELY, FALSE }
enum class Strength { SOUND, STRONG, MODERATE, WEAK, NONE }