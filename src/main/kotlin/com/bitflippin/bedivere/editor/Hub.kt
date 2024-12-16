package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Citation
import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.Ideology
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.Support
import java.util.concurrent.CopyOnWriteArraySet

enum class Change { ADD, REMOVE, UPDATE }

typealias ChangeListener<T> = (T, Change) -> Unit

fun <T> broadcastChange(
    listeners: Set<ChangeListener<T>>,
    t: T,
    change: Change,
) = listeners.forEach { it(t, change) }

class Hub {
    val citationListeners = CopyOnWriteArraySet<ChangeListener<Citation>>()
    val claimListeners = CopyOnWriteArraySet<ChangeListener<Claim>>()
    val ideologyListeners = CopyOnWriteArraySet<ChangeListener<Ideology>>()
    val sourceListeners = CopyOnWriteArraySet<ChangeListener<Source>>()
    val supportListeners = CopyOnWriteArraySet<ChangeListener<Support>>()
}
