package xyz.incrie

import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager
import xyz.deftu.eventbus.SimpleEventBus
import xyz.incrie.core.Incrie
import xyz.incrie.core.IncrieInfo

@Mod(
    name = IncrieInfo.NAME,
    modid = IncrieInfo.ID,
    version = IncrieInfo.VERSION
)
class IncrieImpl : Incrie {

    override fun getLogger() =
        LogManager.getLogger(IncrieInfo.NAME)

    override fun getEventBus(): SimpleEventBus {
        TODO("Not yet implemented")
    }

}