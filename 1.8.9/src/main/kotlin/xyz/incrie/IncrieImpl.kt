package xyz.incrie

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import xyz.deftu.eventbus.SimpleEventBus
import xyz.incrie.core.Incrie
import xyz.incrie.core.IncrieInfo
import xyz.incrie.core.events.IncrieInitializationEvent
import xyz.incrie.core.notifications.Notifications
import xyz.incrie.gui.IncrieHud
import xyz.incrie.notifications.NotificationsImpl

@Mod(
    name = IncrieInfo.NAME,
    modid = IncrieInfo.ID,
    version = IncrieInfo.VERSION,
    clientSideOnly = true,
    acceptedMinecraftVersions = "[1.8.9]"
)
class IncrieImpl : Incrie {

    private val logger = LogManager.getLogger(IncrieInfo.NAME)
    private val eventBus = SimpleEventBus()
    lateinit var internalHud: IncrieHud
    lateinit var notifications: Notifications

    override fun onInitialization(event: IncrieInitializationEvent) {
        logger.info("Starting Incrie...")

        println(this::class.java.classLoader)

        internalHud = IncrieHud(this).also { it.initialize() }
        notifications = NotificationsImpl(this)
        MinecraftForge.EVENT_BUS.register(IncrieInternalForgeListener())

        logger.info("Started Incrie.")
    }

    override fun logger() = logger

    override fun eventBus() = eventBus
    override fun notifications() = notifications

}