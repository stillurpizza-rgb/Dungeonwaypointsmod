package com.odtheking.dungeonwaypoints.events.core

abstract class Event {
    var cancelled: Boolean = false
    fun cancel() { cancelled = true }
    fun isCancelled(): Boolean = cancelled
}

interface CancellableEvent {
    var cancelled: Boolean
}