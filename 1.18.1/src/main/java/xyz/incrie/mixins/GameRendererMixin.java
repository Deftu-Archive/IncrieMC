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

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.events.RenderTickEvent;

@Mixin({GameRenderer.class})
public class GameRendererMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void incrie$onRender(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
        Incrie.getEventBus().post(new RenderTickEvent());
    }

}