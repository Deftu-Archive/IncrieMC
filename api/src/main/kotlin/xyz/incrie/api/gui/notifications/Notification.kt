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
    val alignment: NotificationAlignment,
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
            x = if (alignment.verticalStartPoint == null) 0.pixels(alignment.oppositeStart, true) else alignment.getX()
            y = if (alignment.verticalStartPoint == null) alignment.getY() else 0.pixels(alignment.verticalStartPoint == NotificationAlignment.VerticalStartPoint.BOTTOM, true)
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
            animateOut()
        }

        val titleText = UIWrappedText("${ChatColor.BOLD}$title").constrain {
            x = 2.pixels()
            y = 2.pixels()
            width = FillConstraint() - 2.pixels()
        } childOf this
        val descriptionText = UIWrappedText(description).constrain {
            x = 2.pixels()
            y = SiblingConstraint(2f)
            width = FillConstraint() - 2.pixels()
        } childOf this
    }

    override fun afterInitialization() {
        animateIn()
    }

    private fun highlight() {
        val colour = theme.getPrimary().brighter()
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
                    MOVEMENT_ANIMATION,
                    MOVEMENT_DURATION,
                    alignment.getX()
                )
            } else {
                setYAnimation(
                    MOVEMENT_ANIMATION,
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
                PROGRESS_ANIMATION,
                DURATION,
                FillConstraint()
            )

            onComplete {
                animateOut()
            }
        }
    }

    private fun animateOut() {
        animate {
            if (alignment.verticalStartPoint == null) {
                setXAnimation(
                    MOVEMENT_ANIMATION,
                    MOVEMENT_DURATION,
                    0.pixels(alignment.oppositeStart, true)
                )
            } else {
                setYAnimation(
                    MOVEMENT_ANIMATION,
                    MOVEMENT_DURATION,
                    0.pixels(alignment.verticalStartPoint == NotificationAlignment.VerticalStartPoint.BOTTOM, true)
                )
            }

            onComplete {
                hide(true)
            }
        }
    }

    companion object {
        @JvmStatic val WIDTH = 150
        @JvmStatic val HEIGHT = 55
        @JvmStatic val MOVEMENT_ANIMATION = Animations.IN_OUT_QUAD
        @JvmStatic val MOVEMENT_DURATION = 0.75f
        @JvmStatic val PROGRESS_ANIMATION = Animations.LINEAR
        @JvmStatic val DURATION = 7.5f
    }
}