package com.odtheking.dungeonwaypoints.events

import net.minecraft.network.protocol.Packet

sealed class PacketEvent(val packet: Packet<*>) : Event() {
    class Send(packet: Packet<*>) : PacketEvent(packet)
    class Receive(packet: Packet<*>) : PacketEvent(packet)
}