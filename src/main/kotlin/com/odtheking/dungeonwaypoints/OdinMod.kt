package com.odtheking.dungeonwaypoints

import net.minecraft.client.Minecraft
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.coroutines.EmptyCoroutineContext

object OdinMod {
    @JvmStatic
    val mc: Minecraft = Minecraft.getInstance()

    val logger: Logger = LogManager.getLogger("DungeonWaypoints")

    val scope = kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.SupervisorJob() + EmptyCoroutineContext)
}