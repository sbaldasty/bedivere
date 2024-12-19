package com.bitflippin.bedivere.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

interface Id {
    val value: Int
}

interface Entity<I : Id> {
    val id: I
}

fun loadDatabase(file: File) =
    Json.decodeFromString<Database>(file.readText())

fun <I : Id, E : Entity<I>> Iterable<E>.lookup(id: I) =
    first { it.id == id }

private fun <E : Entity<*>> Iterable<E>.nextId() =
    (maxOfOrNull { it.id.value } ?: 0) + 1

@Serializable
data class Database(
    var claims: MutableList<Claim> = ArrayList(),
    var claimSources: MutableList<ClaimSource> = ArrayList(),
    var ideologies: MutableList<Ideology> = ArrayList(),
    var ideologyClaims: MutableList<IdeologyClaim> = ArrayList(),
    var relatedClaims: MutableList<RelatedClaim> = ArrayList(),
    var sources: MutableList<Source> = ArrayList(),
    var supportClaims: MutableList<SupportClaim> = ArrayList(),
    var supportSources: MutableList<SupportSource> = ArrayList(),
    var supports: MutableList<Support> = ArrayList()
) {

    fun save(file: File) {
        file.writeText(Json.encodeToString(this))
    }

    fun addClaim(): Claim {
        val claim = Claim(ClaimId(claims.nextId()))
        claims.add(claim)
        return claim
    }

    fun addClaimSource(): ClaimSource {
        val claimSource = ClaimSource(ClaimSourceId(claimSources.nextId()))
        claimSources.add(claimSource)
        return claimSource
    }

    fun addSupportClaim(): SupportClaim {
        val supportClaim = SupportClaim(SupportClaimId(supportClaims.nextId()))
        supportClaims.add(supportClaim)
        return supportClaim
    }

    fun addSupportSource(): SupportSource {
        val supportSource = SupportSource(SupportSourceId(supportSources.nextId()))
        supportSources.add(supportSource)
        return supportSource
    }

    fun addSource(): Source {
        val source = Source(SourceId(sources.nextId()))
        sources.add(source)
        return source
    }

    fun addSupport(): Support {
        val support = Support(SupportId(supports.nextId()))
        supports.add(support)
        return support
    }

    fun lookup(id: ClaimId) = claims.lookup(id)
    fun lookup(id: SourceId) = sources.lookup(id)

    fun lookupClaimSources(claim: Claim) = claimSources
        .filter { it.claimId == claim.id }
        .toMutableList()

    fun lookupClaimSources(support: Support) = claimSources
        .filter { it.claimId == support.claimId }
        .toMutableList()

    fun lookupClaims(support: Support) = claims
        .filter { it.id == support.claimId }
        .toMutableList()

    fun lookupSupportClaims(support: Support) = supportClaims
        .filter { it.supportId == support.id }
        .toMutableList()

    fun lookupSupportSources(support: Support) = supportSources
        .filter { it.supportId == support.id }
        .toMutableList()

    fun lookupSupports(claim: Claim) = supports
        .filter { it.claimId == claim.id }
        .toMutableList()
}