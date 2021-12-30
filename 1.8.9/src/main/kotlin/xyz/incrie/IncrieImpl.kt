package xyz.incrie

import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import xyz.deftu.eventbus.SimpleEventBus
import xyz.incrie.compat.KotlinAdapter
import xyz.incrie.core.Incrie
import xyz.incrie.core.IncrieInfo
import xyz.incrie.core.events.IncrieInitializationEvent
import xyz.incrie.core.notifications.Notifications

@Mod(
    name = IncrieInfo.NAME,
    modid = IncrieInfo.ID,
    version = IncrieInfo.VERSION
)
class IncrieImpl : Incrie {

    private lateinit var logger: Logger

    private lateinit var eventBus: SimpleEventBus
    private lateinit var notifications: Notifications

    override fun onInitialization(event: IncrieInitializationEvent) {
        logger = LogManager.getLogger(IncrieInfo.NAME)
        logger.info("Started Incrie.")

        eventBus = SimpleEventBus()
    }

    override fun logger() = logger

    override fun eventBus() = eventBus
    override fun notifications() = notifications

}