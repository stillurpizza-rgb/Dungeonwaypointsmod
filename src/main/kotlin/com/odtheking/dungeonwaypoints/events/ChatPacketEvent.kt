package com.odtheking.dungeonwaypoints.events

import com.odtheking.dungeonwaypoints.events.core.Event
import net.minecraft.network.chat.Component

class ChatPacketEvent(val component: Component) : Event()