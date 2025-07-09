package com.menna.caffeine.ui.navigation

object AppDestination {
    const val FIRST = "firstScreen"
    const val SECOND = "SecondScreen"
    const val THIRD = "ThirdScreen"
    const val FOURTH_BASE = "fourthScreen"
    const val FOURTH = "$FOURTH_BASE/{volume}/{scale}"
    const val FIFTH = "fifthScreen"
    const val SIX = "SixScreen"
    const val SEVEN_BASE = "SevenScreen"
    const val SEVEN = "$SEVEN_BASE/{id}"
    fun sevenWithArgs(id: Int) = "$SEVEN_BASE/$id"

    fun fourthWithArgs(volume: Int, scale: String): String {
        return "$FOURTH_BASE/$volume/${scale}"
    }
}