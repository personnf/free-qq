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
import net.mamoe.mirai.contact.Friend
import org.eclipse.jface.viewers.ArrayContentProvider
import org.eclipse.jface.viewers.ColumnLabelProvider
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.jface.viewers.TableViewerColumn
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite

class FriendsList(parent: Composite) :
    TableViewer(parent, SWT.H_SCROLL or SWT.V_SCROLL or SWT.BORDER or SWT.FULL_SELECTION) {
    init {
        table.headerVisible = true
        table.linesVisible = true

        val remarkColumn = TableViewerColumn(this, SWT.NONE)
        remarkColumn.column.text = "Remark"
        remarkColumn.column.resizable = true
        remarkColumn.setLabelProvider(object : ColumnLabelProvider() {
            override fun getText(element: Any?): String {
                return (element as Friend).remark
            }
        })

        val nickColumn = TableViewerColumn(this, SWT.NONE)
        nickColumn.column.text = "Nick"
        nickColumn.column.resizable = true
        nickColumn.setLabelProvider(object : ColumnLabelProvider() {
            override fun getText(element: Any?): String {
                return (element as Friend).nick
            }
        })

        val qqColumn = TableViewerColumn(this, SWT.NONE)
        qqColumn.column.text = "QQ"
        qqColumn.column.resizable = true
        qqColumn.setLabelProvider(object : ColumnLabelProvider() {
            override fun getText(element: Any?): String {
                return (element as Friend).id.toString()
            }
        })

        contentProvider = ArrayContentProvider.getInstance()
        input = Main.bot.friends.toTypedArray()
        refresh()
    }
}