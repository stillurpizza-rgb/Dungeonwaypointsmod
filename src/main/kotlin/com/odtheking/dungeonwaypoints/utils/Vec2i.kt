package com.odtheking.dungeonwaypoints.utils

data class Vec2i(val x: Int, val z: Int) {
    operator fun plus(other: Vec2i): Vec2i = Vec2i(x + other.x, z + other.z)
    operator fun minus(other: Vec2i): Vec2i = Vec2i(x - other.x, z - other.z)
    operator fun times(scalar: Double): Vec2i = Vec2i((x * scalar).toInt(), (z * scalar).toInt())
    operator fun div(scalar: Double): Vec2i = Vec2i((x / scalar).toInt(), (z / scalar).toInt())
}