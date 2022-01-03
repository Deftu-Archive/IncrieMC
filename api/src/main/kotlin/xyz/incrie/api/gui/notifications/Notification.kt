package xyz.incrie.api.gui.notifications

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIWrappedText
import gg.essential.elementa.constraints.FillConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.OutlineEffect
import gg.essential.universal.ChatColor
import xyz.incrie.api.gui.IncrieTheme
import java.util.function.Consumer

class Notification(
    title: String,
    description: String,
    private val alignment: NotificationAlignment,
    private val theme: IncrieTheme,
    private val clickOperation: Consumer<Notification>
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
        constrain {
            x = if (alignment.verticalStartPoint == null) (-10).pixels(alignment.oppositeStart) else alignment.getX()
            y = if (alignment.verticalStartPoint == null) alignment.getY() else (-10).pixels(alignment.verticalStartPoint == NotificationAlignment.VerticalStartPoint.BOTTOM)
            width = WIDTH.pixels()
            height = HEIGHT.pixels()
        }

        effect(OutlineEffect(
            color = theme.getPrimary(),
            width = 1f,
            drawAfterChildren = true
        ))

        onMouseEnter {
            highlight()
        }.onMouseLeave {
            unhighlight()
        }.onMouseClick {
            clickOperation.accept(this@Notification)
            close()
        }

        val titleText = UIWrappedText("${ChatColor.BOLD}$title").constrain {
            x = 2.pixels()
            y = 2.pixels()
        } childOf this
        val descriptionText = UIWrappedText(description).constrain {
            x = 2.pixels()
            y = SiblingConstraint(2f)
        } childOf this
    }

    override fun afterInitialization() {
        animateIn()
    }

    private fun highlight() {
        val colour = theme.getPrimary().brighter().brighter()
        (effects[0] as OutlineEffect)::color.animate(Animations.OUT_EXP, 1f, colour)
        progressBar.animate {
            setColorAnimation(Animations.OUT_EXP, 1f, colour.toConstraint())
        }
    }

    private fun unhighlight() {
        val colour = theme.getPrimary()
        (effects[0] as OutlineEffect)::color.animate(Animations.OUT_EXP, 1f, colour)
        progressBar.animate {
            setColorAnimation(Animations.OUT_EXP, 1f, colour.toConstraint())
        }
    }

    private fun animateIn() {
        animate {
            if (alignment.verticalStartPoint == null) {
                setXAnimation(
                    Animations.IN_OUT_QUAD,
                    MOVEMENT_DURATION,
                    alignment.getX()
                )
            } else {
                setYAnimation(
                    Animations.IN_OUT_QUAD,
                    MOVEMENT_DURATION,
                    alignment.getY()
                )
            }

            onComplete {
                animateProgress()
            }
        }
    }

    private fun animateProgress() {
        progressBar.animate {
            setWidthAnimation(
                Animations.LINEAR,
                DURATION,
                FillConstraint()
            ).onComplete {
                close()
            }
        }
    }

    private fun animateOut() {
        animate {
            if (alignment.verticalStartPoint == null) {
                setXAnimation(
                    Animations.IN_OUT_QUAD,
                    MOVEMENT_DURATION,
                    0.pixels(alignment.oppositeStart, true)
                )
            } else {
                setYAnimation(
                    Animations.IN_OUT_QUAD,
                    MOVEMENT_DURATION,
                    (-10).pixels(alignment.verticalStartPoint == NotificationAlignment.VerticalStartPoint.BOTTOM, true)
                )
            }

            onComplete {
                hide(true)
            }
        }
    }

    fun close() {
        animateOut()
    }

    companion object {
        @JvmStatic val WIDTH = 165
        @JvmStatic val HEIGHT = 60
        @JvmStatic val MOVEMENT_DURATION = 1f
        @JvmStatic val DURATION = 60f
    }
}