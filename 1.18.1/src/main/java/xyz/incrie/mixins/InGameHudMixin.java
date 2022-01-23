/*
 * Incrie - The ultimate Minecraft modding utility.
 * Copyright (C) 2022 Incrie
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.incrie.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.incrie.api.Incrie;
import xyz.incrie.text.ChatManagerImpl;

import java.util.UUID;

@Mixin({InGameHud.class})
public class InGameHudMixin {

    @Inject(method = "addChatMessage", at = @At(value = "TAIL"))
    private void incrie$onChatMessageAdded(MessageType type, Text message, UUID sender, CallbackInfo ci) {
        World world = MinecraftClient.getInstance().world;
        if (world == null) return;
        world.getPlayers().stream().filter(player -> player.getUuid().equals(sender)).findFirst().ifPresent(player -> {
            ((ChatManagerImpl) Incrie.getChatManager()).getSenderHistory().add((AbstractClientPlayerEntity) player);
        });
    }

}