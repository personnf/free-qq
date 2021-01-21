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

import org.eclipse.jface.dialogs.Dialog
import org.eclipse.jface.dialogs.IDialogConstants
import org.eclipse.jface.dialogs.MessageDialog
import org.eclipse.jface.resource.JFaceResources
import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.FontMetrics
import org.eclipse.swt.graphics.GC
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.*

object SwtExtension {

    private val dialogFontMetrics: FontMetrics

    init {
        val display = Display.getDefault()
        val gc = GC(display)
        gc.font = JFaceResources.getDialogFont()
        dialogFontMetrics = gc.fontMetrics
        display.disposeExec { gc.dispose() }
    }

    fun Button.jface(gridData: GridData): GridData {
        this.font = JFaceResources.getDialogFont()
        val widthHint = Dialog.convertHorizontalDLUsToPixels(
            dialogFontMetrics,
            IDialogConstants.BUTTON_WIDTH
        )
        val minSize = this.computeSize(SWT.DEFAULT, SWT.DEFAULT, true)
        gridData.widthHint = Math.max(widthHint, minSize.x)
        return gridData
    }

    fun TabFolder.append(title: String, content: Control): TabItem {
        val tabItem = TabItem(this, SWT.NONE)
        tabItem.text = title
        tabItem.control = content
        return tabItem
    }

    fun String.infoDialog() {
        Display.getDefault().asyncExec {
            MessageDialog.openError(
                Display.getCurrent()
                    .activeShell, "Information", this
            )
        }
    }

    fun String.errorDialog() {
        Display.getDefault().asyncExec {
            MessageDialog.openError(
                Display.getCurrent()
                    .activeShell, "Error", this
            )
        }
    }

    fun String.errorDialogSync() {
        Display.getDefault().syncExec {
            MessageDialog.openError(
                Display.getCurrent()
                    .activeShell, "Error", this
            )
        }
    }

    fun Exception.dialog() {
        this.localizedMessage.errorDialog()
    }

    fun Shell.center() {
        val displayBounds = this.display.primaryMonitor
            .bounds
        val shellBounds = this.bounds
        val x = displayBounds.x + (displayBounds.width - shellBounds.width) shr 1
        val y = displayBounds.y + (displayBounds.height - shellBounds.height) shr 1
        this.setLocation(x, y)
    }
}