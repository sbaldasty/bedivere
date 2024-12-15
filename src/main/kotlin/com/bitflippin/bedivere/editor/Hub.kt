package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Citation
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.Ideology
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.Support

enum class Change { ADD, REMOVE, UPDATE }

typealias ChangeListener<T> = (T, Change) -> Unit

fun <T> broadcastChange(
    listeners: Set<ChangeListener<T>>,
    t: T,
    change: Change,
) = listeners.forEach { it(t, change) }

class Hub {
    val citationListeners = HashSet<ChangeListener<Citation>>()
    val claimListeners = HashSet<ChangeListener<Claim>>()
    val ideologyListeners = HashSet<ChangeListener<Ideology>>()
    val sourceListeners = HashSet<ChangeListener<Source>>()
    val supportListeners = HashSet<ChangeListener<Support>>()
}
