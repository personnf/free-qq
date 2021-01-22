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
package com.github.personnf.freeqq.window.main.friends

import com.github.personnf.freeqq.Main
import com.github.personnf.freeqq.ObjectsTable
import net.mamoe.mirai.contact.Friend
import org.eclipse.swt.widgets.Composite

class FriendsTable(parent: Composite) : ObjectsTable(parent) {
    init {
        appendColumn("Remark", 80) { (it as Friend).remark }
        appendColumn("Nick", 80) { (it as Friend).nick }
        appendColumn("QQ", 80) { (it as Friend).id.toString() }

        input = Main.bot.friends.toTypedArray()
    }
}