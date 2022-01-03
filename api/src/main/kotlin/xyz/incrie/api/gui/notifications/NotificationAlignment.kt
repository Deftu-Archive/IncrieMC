package xyz.incrie.api.gui.notifications

import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.XConstraint
import gg.essential.elementa.constraints.YConstraint
import gg.essential.elementa.dsl.*

enum class NotificationAlignment(
    private val xConstraint: AlignedConstraint,
    private val yConstraint: AlignedConstraint,
    val oppositeStart: Boolean = true,
    val verticalStartPoint: VerticalStartPoint? = null
) {
    TOP_LEFT(AlignedConstraint(pixels = 7.5f), AlignedConstraint(pixels = 7.5f), false),
    LEFT(AlignedConstraint(pixels = 7.5f), AlignedConstraint(centered = true), false),
    BOTTOM_LEFT(AlignedConstraint(pixels = 7.5f), AlignedConstraint(pixels = 7.5f, opposite = true), false),
    BOTTOM(AlignedConstraint(centered = true), AlignedConstraint(pixels = 7.5f, opposite = true), verticalStartPoint = VerticalStartPoint.BOTTOM),
    BOTTOM_RIGHT(AlignedConstraint(pixels = 7.5f, opposite = true), AlignedConstraint(pixels = 7.5f, opposite = true), true),
    RIGHT(AlignedConstraint(pixels = 7.5f, opposite = true), AlignedConstraint(centered = true), true),
    TOP_RIGHT(AlignedConstraint(pixels = 7.5f, opposite = true), AlignedConstraint(pixels = 7.5f), true),
    TOP(AlignedConstraint(centered = true), AlignedConstraint(pixels = 7.5f), verticalStartPoint = VerticalStartPoint.TOP);

    fun getX(): XConstraint {
        return if (xConstraint.centered) CenterConstraint()
        else xConstraint.pixels.pixels(xConstraint.opposite)
    }

    fun getY(): YConstraint {
        return if (yConstraint.centered) CenterConstraint()
        else yConstraint.pixels.pixels(yConstraint.opposite || (verticalStartPoint != null && verticalStartPoint == VerticalStartPoint.BOTTOM))
    }

    internal data class AlignedConstraint(val pixels: Number = 0, val centered: Boolean = false, val opposite: Boolean = false)
    internal enum class VerticalStartPoint {
        TOP,
        BOTTOM
    }
}