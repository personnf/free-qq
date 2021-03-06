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
package com.github.personnf.freeqq

import com.github.personnf.freeqq.window.LoginWindow
import net.mamoe.mirai.Bot
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell

object Main {

    @Volatile
    lateinit var bot: Bot
    lateinit var shell: Shell

    @JvmStatic
    fun main(args: Array<String>) {
        val display = Display.getDefault()
        shell = LoginWindow(display)
        shell.open()
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep()
            }
        }
        display.dispose()
        if (Main::bot.isInitialized) {
            bot.close()
        }
    }
}
