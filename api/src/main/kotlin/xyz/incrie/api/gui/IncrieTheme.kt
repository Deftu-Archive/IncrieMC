package xyz.incrie.api.gui

import java.awt.Color

interface IncrieTheme {
    fun getPrimary(): Color
    fun getSecondary(): Color
    fun getTertiary(): Color
    companion object {
        @JvmStatic val DEFAULT = theme {
            primary = Color(153, 0, 0)
            secondary = Color(31, 31, 31)
            tertiary = Color(26, 26, 26)
        }
    }
}