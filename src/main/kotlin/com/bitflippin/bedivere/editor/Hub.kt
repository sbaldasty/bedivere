package com.bitflippin.bedivere.editor

import com.bitflippin.bedivere.model.Claim
import com.bitflippin.bedivere.model.ClaimSource
import com.bitflippin.bedivere.model.Ideology
import com.bitflippin.bedivere.model.Source
import com.bitflippin.bedivere.model.Support
import com.bitflippin.bedivere.model.SupportClaim
import com.bitflippin.bedivere.model.SupportSource
import java.util.concurrent.CopyOnWriteArraySet

enum class Change { ADD, REMOVE, UPDATE }

typealias ChangeListener<T> = (T, Change) -> Unit

fun <T> broadcastChange(listeners: Set<ChangeListener<T>>, t: T, change: Change) =
    listeners.forEach { it(t, change) }

class Hub {
    val claimSourceListeners = CopyOnWriteArraySet<ChangeListener<ClaimSource>>()
    val claimListeners = CopyOnWriteArraySet<ChangeListener<Claim>>()
    val ideologyListeners = CopyOnWriteArraySet<ChangeListener<Ideology>>()
    val sourceListeners = CopyOnWriteArraySet<ChangeListener<Source>>()
    val supportListeners = CopyOnWriteArraySet<ChangeListener<Support>>()
    val supportClaimListeners = CopyOnWriteArraySet<ChangeListener<SupportClaim>>()
    val supportSourceListeners = CopyOnWriteArraySet<ChangeListener<SupportSource>>()
}
