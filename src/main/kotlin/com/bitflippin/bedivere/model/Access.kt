package com.bitflippin.bedivere.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

private fun <T> nextId(items: Collection<T>, f: (T) -> Int) = (items.maxOfOrNull(f) ?: 0) + 1

fun loadModel(file: File)
        = Json.decodeFromString<Argmap>(file.readText())

fun saveModel(file: File, argmap: Argmap)
        = file.writeText(Json.encodeToString(argmap))

fun Argmap.addCitation(): Citation {
    val citation = Citation(CitationId(nextId(citations, { it.id.value })))
    citations.add(citation)
    return citation
}

fun Argmap.addClaim(): Claim {
    val claim = Claim(ClaimId(nextId(claims,{ it.id.value })))
    claims.add(claim)
    return claim
}

fun Argmap.addIdeology(): Ideology {
    val ideology = Ideology(IdeologyId(nextId(ideologies, { it.id.value })))
    ideologies.add(ideology)
    return ideology
}

fun Argmap.addSource(): Source {
    val source = Source(SourceId(nextId(sources, { it.id.value })))
    sources.add(source)
    return source
}

fun Argmap.addSupport(): Support {
    val support = Support(SupportId(nextId(supports, { it.id.value })))
    supports.add(support)
    return support
}