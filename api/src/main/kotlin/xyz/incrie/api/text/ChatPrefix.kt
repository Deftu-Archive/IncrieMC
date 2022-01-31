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

package xyz.incrie.api.text

import gg.essential.universal.ChatColor

enum class ChatPrefix(
    private val template: String
) {
    STYLE_ONE("${ChatColor.DARK_GRAY}[<colour><name>${ChatColor.DARK_GRAY}] ${ChatColor.RESET}"),
    STYLE_TWO("<colour><name> \u00BB ${ChatColor.RESET}");
    fun generate(name: String, color: ChatColor) = template.replace("<name>", name).replace("<colour>", color.toString())
}