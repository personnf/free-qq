/*
 * Copyright (c) 2021, personnf <https://github.com/personnf>. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.personnf.freeqq.window.main.groups

import com.github.personnf.freeqq.Main
import com.github.personnf.freeqq.ObjectsTable
import net.mamoe.mirai.contact.Group
import org.eclipse.swt.widgets.Composite

class GroupsTable(parent: Composite) : ObjectsTable(parent) {
    init {
        appendColumn("Name", 140) { (it as Group).name }
        appendColumn("QQ", 100) { (it as Group).id.toString() }

        input = Main.bot.groups.toTypedArray()
    }
}