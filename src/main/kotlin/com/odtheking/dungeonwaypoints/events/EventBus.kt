package com.odtheking.dungeonwaypoints.events.core

import com.odtheking.dungeonwaypoints.events.ChatPacketEvent
import com.odtheking.dungeonwaypoints.events.PacketEvent
import net.minecraft.network.protocol.Packet
import net.minecraft.util.profiling.Profiler
import net.minecraft.util.profiling.ProfilerFiller

object EventBus {
    @JvmField
    internal val listenerArrays = mutableMapOf<Class<out Event>, Array<ListenerEntry<out Event>>>()

    @JvmField
    internal val activeSubscribers = mutableSetOf<Any>()

    @JvmField
    internal val subscriberClasses = mutableMapOf<Any, Class<*>>()

    @JvmField
    internal val invokers = HashMap<Class<out Event>, Invoker>()

    private val profilerNameCache = HashMap<Class<out Event>, String>()

    fun subscribe(subscriber: Any) {
        if (activeSubscribers.add(subscriber)) {
            subscriberClasses[subscriber] = subscriber.javaClass
            rebuildAffectedCaches(subscriber.javaClass)
        }
    }

    fun unsubscribe(subscriber: Any) {
        if (activeSubscribers.remove(subscriber)) {
            subscriberClasses.remove(subscriber)
            rebuildAffectedCaches(subscriber.javaClass)
        }
    }

    fun post(event: Event): Event = post(event, null)

    fun post(event: Event, profiler: ProfilerFiller?): Event {
        val eventClass = event.javaClass
        val listeners = listenerArrays[eventClass]

        if (listeners != null) {
            for (entry in listeners) {
                @Suppress("UNCHECKED_CAST")
                val listener = entry as ListenerEntry<Event>
                if (!listener.ignoreCancelled || !event.cancelled) {
                    listener.invoke(event)
                }
            }
        }

        return event
    }

    fun <T : Event> registerListener(
        priority: Int = 0,
        ignoreCancelled: Boolean = false,
        subscriberName: String = "",
        handler: (T) -> Unit
    ) {
        @Suppress("UNCHECKED_CAST")
        val eventClass = (handler as (Any) -> Unit).javaClass
    }

    fun postAndCatch(event: Event): Boolean {
        try {
            post(event)
        } catch (e: Exception) {
        }
        return event.cancelled
    }

    private fun rebuildAffectedCaches(changedClass: Class<*>) {
    }

    class ListenerEntry<T : Event>(
        val subscriber: Class<*>,
        val listener: EventListener<T>
    )

    class EventListener<T : Event>(
        val priority: Int,
        val ignoreCancelled: Boolean,
        val subscriberName: String,
        val handler: (T) -> Unit
    ) {
        operator fun invoke(event: T) {
            handler(event)
        }
    }

    fun interface Invoker {
        fun invoke(event: Event)
    }
}