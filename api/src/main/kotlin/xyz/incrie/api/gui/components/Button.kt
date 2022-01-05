package xyz.incrie.api.gui.components

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.OutlineEffect
import xyz.incrie.api.gui.IncrieTheme
import java.awt.Color
import java.util.function.Consumer

class Button @JvmOverloads constructor(
    text: String = "",
    val theme: IncrieTheme = IncrieTheme.DEFAULT,
    val clickOperation: Consumer<Button> = Consumer {  },
    private var toggled: Boolean = true
) : UIComponent() {
    private val textColor: Color
        get() = theme.getTertiary()
    private val contentColor: Color
        get() = theme.getSecondary()
    private val disabledTextColor: Color
        get() = theme.getTertiary().darker()

    var border = true
    var animations = true

    private val displayedBorder = UIBlock().constrain {
        width = RelativeConstraint()
        height = RelativeConstraint()
    } childOf this effect OutlineEffect(Color(0, 0, 0, 0), 1f)
    private val displayedContent = UIBlock(contentColor).constrain {
        width = RelativeConstraint()
        height = RelativeConstraint()
    } childOf this
    private val displayedText = UIText(text).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
    }.setColor(if (toggled) textColor else disabledTextColor) childOf this

    init {
        constrain {
            width = DEFAULT_WIDTH.pixels()
            height = DEFAULT_HEIGHT.pixels()
        }

        onMouseClick {
            clickOperation.accept(this@Button)
        }.onMouseEnter {
            highlight(animations)
        }.onMouseLeave {
            unhighlight(animations)
        }
    }

    @JvmOverloads fun highlight(animate: Boolean = true) {
        if (border) {
            val borderColor = if (toggled) theme.getPrimary().brighter() else Color.RED
            if (animate) (displayedBorder.effects[0] as OutlineEffect)::color.animate(Animations.OUT_EXP, 1f, borderColor)
            else (displayedBorder.effects[0] as OutlineEffect)::color.set(borderColor)
        }
    }

    @JvmOverloads fun unhighlight(animate: Boolean = true) {
        if (border && toggled) {
            if (animate) (displayedBorder.effects[0] as OutlineEffect)::color.animate(Animations.OUT_EXP, 1f, Color(0, 0, 0, 0))
            else (displayedBorder.effects[0] as OutlineEffect)::color.set(Color(0, 0, 0, 0))
        }
    }

    fun getToggle() = toggled

    @JvmOverloads fun setToggle(value: Boolean, style: Boolean = true, animate: Boolean = true) {
        this.toggled = value
        if (style) {
            highlight(animate)

            val textColor = if (value) textColor else disabledTextColor
            if (animate) {
                displayedText.animate {
                    setColorAnimation(Animations.OUT_EXP, 1f, textColor.toConstraint())
                }
            } else {
                displayedText.setColor(textColor)
            }
        }
    }

    companion object {
        const val DEFAULT_WIDTH = 200
        const val DEFAULT_HEIGHT = 20
    }
}