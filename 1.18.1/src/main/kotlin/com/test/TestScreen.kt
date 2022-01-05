package com.test

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.*
import xyz.incrie.api.Incrie
import xyz.incrie.api.gui.components.Button

class TestScreen : WindowScreen(
    ElementaVersion.V1
) {
    init {
        val btn = Button(
            text = "Test",
            clickOperation = {
                notifyUser()
            }
        ).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf window
    }

    fun notifyUser() {
        Incrie.getNotifications().post("Button", "You clicked the test button! Good job.") {
            notifyUser()
        }
    }
}