package com.bitflippin.bedivere.model

import kotlinx.serialization.json.Json
import java.io.InputStream

fun loadModel(s: String) {
    Json.decodeFromString<ArgMap>(s)
}

fun ArgMap.addCitation()
        = Citation(CitationId(this.citations.maxOf { x -> x.id.value }))

fun ArgMap.addClaim()
        = Claim(ClaimId(this.claims.maxOf { x -> x.id.value }))

fun ArgMap.addIdeology()
        = Ideology(IdeologyId(this.ideologies.maxOf { x -> x.id.value }))

fun ArgMap.addSource()
        = Source(SourceId(this.sources.maxOf { x -> x.id.value }))

fun ArgMap.addSupport()
        = Support(SupportId(this.supports.maxOf { x -> x.id.value }))