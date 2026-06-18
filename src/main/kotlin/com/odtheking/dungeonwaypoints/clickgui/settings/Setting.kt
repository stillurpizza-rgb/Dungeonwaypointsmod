package com.odtheking.dungeonwaypoints.clickgui.settings

open class Setting<T>(val name: String, val defaultValue: T) {
    var value: T = defaultValue
}

class BooleanSetting(name: String, defaultValue: Boolean) : Setting<Boolean>(name, defaultValue)
class NumberSetting(name: String, defaultValue: Float, min: Float, max: Float, step: Float, desc: String = "") : Setting<Float>(name, defaultValue)
class ColorSetting(name: String, defaultValue: Any) : Setting<Any>(name, defaultValue)
class StringSetting(name: String, defaultValue: String, desc: String = "", length: Int = 64) : Setting<String>(name, defaultValue)
class ActionSetting(name: String, desc: String = "", val action: () -> Unit = {}) : Setting<Unit>(name, Unit)
class KeybindSetting(name: String, defaultValue: Any) : Setting<Any>(name, defaultValue)
class DropdownSetting(name: String, defaultValue: String, options: List<String>) : Setting<String>(name, defaultValue)
class ListSetting(name: String, defaultValue: List<String>) : Setting<List<String>>(name, defaultValue)
class SelectorSetting(name: String, defaultValue: List<String>, options: List<String>) : Setting<List<String>>(name, defaultValue)

object Setting {
    fun <T> Setting<T>.withDependency(dep: () -> Boolean): Setting<T> = this
}

object HUD