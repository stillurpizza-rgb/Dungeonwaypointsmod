package com.odtheking.dungeonwaypoints.events.core

interface Event {

    fun postAndCatch(): Boolean {
        runCatching {
            EventBus.post(this)
        }.onFailure {
            logError(it, this)
        }
        return false
    }
}