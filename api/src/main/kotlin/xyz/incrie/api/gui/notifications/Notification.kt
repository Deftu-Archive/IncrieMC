package xyz.incrie.api.gui.notifications

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.constraints.FillConstraint
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.OutlineEffect
import xyz.incrie.api.gui.IncrieTheme

class Notification(
    val title: String,
    val description: String,
    val alignment: NotificationAlignment,
    theme: IncrieTheme
) : UIBlock(
    theme.getSecondary().toConstraint()
) {
    private val progressBar = UIBlock(theme.getPrimary().toConstraint()).constrain {
        x = 0.pixels()
        y = 0.pixels(true)
        width = 0.pixels()
        height = 5.pixels()
    } childOf this

    init {
        effect(OutlineEffect(
            color = theme.getPrimary(),
            width = 1f,
            drawAfterChildren = true
        ))
    }

    fun animateIn() {
        animate {
            setXAnimation(
                Animations.OUT_EXP,
                0.5f,
                2.pixels(true)
            )

            onComplete {
                animateProgress()
            }
        }
    }

    fun animateOut() {
        animate {
            setXAnimation(
                Animations.OUT_EXP,
                0.5f,
                (-5).pixels(true)
            )
        }
    }

    fun animateProgress() {
        progressBar.animate {
            setWidthAnimation(
                Animations.LINEAR,
                DURATION,
                FillConstraint()
            )

            onComplete {
                hide()
            }
        }
    }

    fun hide() {
        animateOut()
        hide(true)
    }

    companion object {
        @JvmStatic val DURATION = 5f
    }
}