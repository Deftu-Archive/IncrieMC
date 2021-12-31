package xyz.incrie

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import xyz.deftu.eventbus.SimpleEventBus
import xyz.incrie.compat.KotlinAdapter
import xyz.incrie.core.Incrie
import xyz.incrie.core.IncrieInfo
import xyz.incrie.core.events.IncrieInitializationEvent
import xyz.incrie.core.notifications.Notifications
import xyz.incrie.gui.IncrieHud
import xyz.incrie.notifications.NotificationsImpl

@Mod(
    name = IncrieInfo.NAME,
    modid = IncrieInfo.ID,
    version = IncrieInfo.VERSION
)
class IncrieImpl : Incrie {

    private val logger = LogManager.getLogger(IncrieInfo.NAME)
    private val eventBus = SimpleEventBus()

    lateinit var internalHud: IncrieHud
    lateinit var notifications: Notifications

    override fun onInitialization(event: IncrieInitializationEvent) {
        logger.info("Started Incrie.")

        internalHud = IncrieHud(this).also { it.initialize() }
        notifications = NotificationsImpl(this)
        MinecraftForge.EVENT_BUS.register(IncrieForgeEvents())
    }

    override fun logger() = logger

    override fun eventBus() = eventBus
    override fun notifications() = notifications

}