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

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.TabFolder
import org.eclipse.swt.widgets.TabItem

class FriendsTab(parent: TabFolder) : TabItem(parent, SWT.NONE) {
    init {
        text = "Friends"
        control = FriendsList(parent).control
    }
}