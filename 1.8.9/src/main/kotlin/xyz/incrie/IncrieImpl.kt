package xyz.incrie

import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import xyz.deftu.eventbus.SimpleEventBus
import xyz.incrie.core.Incrie
import xyz.incrie.core.IncrieInfo
import xyz.incrie.core.events.IncrieInitializationEvent

@Mod(
    name = IncrieInfo.NAME,
    modid = IncrieInfo.ID,
    version = IncrieInfo.VERSION
)
class IncrieImpl : Incrie {

    private val logger = LogManager.getLogger(IncrieInfo.NAME)
    private val eventBus = SimpleEventBus()

    override fun initialize(event: IncrieInitializationEvent) {
        logger.info("Started Incrie.")
    }

    override fun logger() = logger
    override fun eventBus() = eventBus

}