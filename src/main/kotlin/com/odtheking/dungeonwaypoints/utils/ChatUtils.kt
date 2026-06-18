package com.odtheking.dungeonwaypoints.utils

import com.odtheking.dungeonwaypoints.OdinMod.mc
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style

fun sendChatMessage(message: Any) {
    mc.execute { mc.player?.connection?.sendChat(message.toString()) }
}

fun sendCommand(command: String) {
    mc.execute { mc.player?.connection?.sendCommand(command) }
}

fun modMessage(message: Any?, prefix: String = "§3Odin §8»§r ", chatStyle: Style? = null) {
    val text = Component.literal("$prefix$message")
    chatStyle?.let { text.setStyle(chatStyle) }
    mc.execute { mc.gui.chat.addMessage(text) }
}

fun modMessage(message: Component, prefix: String = "§3Odin §8»§r ", chatStyle: Style? = null) {
    val text = Component.literal(prefix).append(message)
    chatStyle?.let { text.setStyle(chatStyle) }
    mc.execute { mc.gui.chat.addMessage(text) }
}

fun devMessage(message: Any?) {
    modMessage(message, "§3Odin§bDev §8»§r ")
}