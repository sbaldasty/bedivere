package com.bitflippin.bedivere.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun loadModel(file: File)
        = Json.decodeFromString<Argmap>(file.readText())

fun saveModel(file: File, argMap: Argmap)
        = file.writeText(Json.encodeToString(argMap))

fun Argmap.addCitation()
        = Citation(CitationId(this.citations.maxOf { x -> x.id.value }))

fun Argmap.addClaim()
        = Claim(ClaimId(this.claims.maxOf { x -> x.id.value }))

fun Argmap.addIdeology()
        = Ideology(IdeologyId(this.ideologies.maxOf { x -> x.id.value }))

fun Argmap.addSource()
        = Source(SourceId(this.sources.maxOf { x -> x.id.value }))

fun Argmap.addSupport()
        = Support(SupportId(this.supports.maxOf { x -> x.id.value }))