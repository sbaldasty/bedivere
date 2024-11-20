package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.*

enum class Change { ADD, REMOVE, UPDATE }

typealias ChangeListener<T> = (T, Change) -> Unit

fun interface CitationListener : ChangeListener<Citation>
fun interface ClaimListener : ChangeListener<Claim>
fun interface IdeologyListener : ChangeListener<Ideology>
fun interface SourceListener : ChangeListener<Source>
fun interface SupportListener : ChangeListener<Support>

class Hub {
    private val citationListeners = HashSet<CitationListener>()
    private val claimListeners = HashSet<ClaimListener>()
    private val ideologyListeners = HashSet<IdeologyListener>()
    private val sourceListeners = HashSet<SourceListener>()
    private val supportListeners = HashSet<SupportListener>()

    private fun <T> bc(listeners: Set<ChangeListener<T>>, t: T, change: Change) =
        listeners.forEach { it(t, change) }

    fun broadcast(model: Citation, change: Change) = bc(citationListeners, model, change)
    fun broadcast(model: Claim, change: Change) = bc(claimListeners, model, change)
    fun broadcast(model: Ideology, change: Change) = bc(ideologyListeners, model, change)
    fun broadcast(model: Source, change: Change) = bc(sourceListeners, model, change)
    fun broadcast(model: Support, change: Change) = bc(supportListeners, model, change)

    fun addListener(listener: CitationListener) = citationListeners.add(listener)
    fun addListener(listener: ClaimListener) = claimListeners.add(listener)
    fun addListener(listener: IdeologyListener) = ideologyListeners.add(listener)
    fun addListener(listener: SourceListener) = sourceListeners.add(listener)
    fun addListener(listener: SupportListener) = supportListeners.add(listener)

    fun removeListener(listener: ChangeListener<*>) {
        citationListeners.remove(listener)
        claimListeners.remove(listener)
        ideologyListeners.remove(listener)
        sourceListeners.remove(listener)
        supportListeners.remove(listener)
    }
}