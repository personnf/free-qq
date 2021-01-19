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

import com.github.personnf.freeqq.SwtExtension.center
import com.github.personnf.freeqq.SwtExtension.dialog
import com.github.personnf.freeqq.SwtExtension.jface
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.utils.BotConfiguration
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*

class LoginWindow(display: Display) : Shell(display) {
    init {
        text = "QQ Login"
        layout = GridLayout(1, false)

        val fieldsComposite = Composite(this, SWT.NONE)
        fieldsComposite.layoutData = GridData(SWT.FILL, SWT.FILL, true, true, 1, 1)
        fieldsComposite.layout = GridLayout(2, false)

        val qqLabel = Label(fieldsComposite, SWT.RIGHT)
        qqLabel.text = "QQ:"
        qqLabel.layoutData = GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1)

        val qqText = Text(fieldsComposite, SWT.SINGLE or SWT.BORDER)
        qqText.layoutData = GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1)

        Label(fieldsComposite, SWT.RIGHT).text = "Password:"

        val passwordText = Text(fieldsComposite, SWT.PASSWORD or SWT.BORDER)
        passwordText.layoutData = GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1)

        val button = Button(this, SWT.PUSH)
        button.text = "Login"
        button.layoutData = button.jface(GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1))
        button.addSelectionListener(SelectionListener.widgetSelectedAdapter {
            val qqStr = qqText.text
            val password = passwordText.text
            if (qqStr.isNotBlank() && password.isNotEmpty()) {
                return@widgetSelectedAdapter
            }
            val qq = qqStr.toLong()

            qqText.enabled = false
            passwordText.enabled = false
            button.enabled = false
            button.text = "Login..."
            text = "QQ Login..."

            Thread {
                runBlocking {
                    try {
                        val config = BotConfiguration()
                        config.fileBasedDeviceInfo()
                        config.inheritCoroutineContext()
                        val bot = BotFactory.newBot(qq, password, config)
                        bot.login()
                        Main.bot = bot

                        display.asyncExec {
                            Main.shell = MainWindow(display)
                            close()
                            Main.shell.open()
                        }
                    } catch (ex: Exception) {
                        ex.dialog()
                        display.asyncExec {
                            text = "QQ Login"
                            button.text = "Login"
                            button.enabled = true
                            passwordText.enabled = true
                            qqText.enabled = true
                        }
                    }
                }
            }.start()
        })

        pack()
        val windowSize = size
        if (windowSize.x < 300) {
            windowSize.x = 300
            size = windowSize
        }

        center()
    }

    override fun checkSubclass() {}
}