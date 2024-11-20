package com.bitflippin.bedivere.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun loadModel(file: File)
        = Json.decodeFromString<Argmap>(file.readText())

fun saveModel(file: File, argmap: Argmap)
        = file.writeText(Json.encodeToString(argmap))

fun Argmap.addCitation()
        = Citation(CitationId(this.citations.maxOfOrNull { x -> x.id.value } ?: 1))

fun Argmap.addClaim()
        = Claim(ClaimId(this.claims.maxOfOrNull { x -> x.id.value } ?: 1))

fun Argmap.addIdeology()
        = Ideology(IdeologyId(this.ideologies.maxOfOrNull { x -> x.id.value } ?: 1))

fun Argmap.addSource()
        = Source(SourceId(this.sources.maxOfOrNull { x -> x.id.value } ?: 1))

fun Argmap.addSupport()
        = Support(SupportId(this.supports.maxOfOrNull { x -> x.id.value } ?: 1))