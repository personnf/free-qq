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
package com.github.personnf.freeqq.window.main

import com.github.personnf.freeqq.Main
import com.github.personnf.freeqq.window.main.friends.FriendsTab
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.TabFolder

class MainWindow(display: Display) : Shell(display) {
    lateinit var onlineLabel: Label

    init {
        text = "QQ"
        layout = GridLayout(1, false)

        val bot = Main.bot

        val nickLabel = Label(this, SWT.NONE)
        nickLabel.text = bot.nick

        val qqLabel = Label(this, SWT.NONE)
        qqLabel.text = bot.id.toString()

        onlineLabel = Label(this, SWT.NONE)
        onlineLabel.text = "online"

        val tabFolder = TabFolder(this, SWT.TOP)
        tabFolder.layoutData = GridData(SWT.FILL, SWT.FILL, true, true, 1, 1)

        FriendsTab(tabFolder)
    }

    override fun checkSubclass() {}
}
