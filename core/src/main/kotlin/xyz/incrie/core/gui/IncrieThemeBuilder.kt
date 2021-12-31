package xyz.incrie.core.gui

import java.awt.Color

class IncrieThemeBuilder {
    var primary: Color? = null
        private set
    var secondary: Color? = null
        private set
    var tertiary: Color? = null
        private set
    fun setPrimary(primary: Color) =
        this.also { this.primary = primary }
    fun setSecondary(secondary: Color) =
        this.also { this.secondary = secondary }
    fun setTertiary(tertiary: Color) =
        this.also { this.tertiary = tertiary }
    fun build() = object : IncrieTheme {
        override fun getPrimary(): Color = primary ?: IncrieTheme.DEFAULT.getPrimary()
        override fun getSecondary(): Color = secondary ?: IncrieTheme.DEFAULT.getSecondary()
        override fun getTertiary(): Color = tertiary ?: IncrieTheme.DEFAULT.getTertiary()
    }
}

class IncrieThemeBlock {
    var primary: Color? = null
    var secondary: Color? = null
    var tertiary: Color? = null
    fun build() = object : IncrieTheme {
        override fun getPrimary(): Color = primary ?: IncrieTheme.DEFAULT.getPrimary()
        override fun getSecondary(): Color = secondary ?: IncrieTheme.DEFAULT.getSecondary()
        override fun getTertiary(): Color = tertiary ?: IncrieTheme.DEFAULT.getTertiary()
    }
}

fun theme(block: IncrieThemeBlock.() -> Unit): IncrieTheme {
    return IncrieThemeBlock()
        .apply(block)
        .build()
}