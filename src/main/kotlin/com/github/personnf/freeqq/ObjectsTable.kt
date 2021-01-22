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

import org.eclipse.jface.viewers.*
import org.eclipse.jface.window.ToolTip
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite

open class ObjectsTable(parent: Composite) :
    TableViewer(parent, SWT.MULTI or SWT.H_SCROLL or SWT.V_SCROLL or SWT.BORDER or SWT.FULL_SELECTION) {
    init {
        table.headerVisible = true
        table.linesVisible = true
        ColumnViewerToolTipSupport.enableFor(this, ToolTip.NO_RECREATE)

        contentProvider = ArrayContentProvider.getInstance()
    }

    fun appendColumn(header: String, width: Int, elementToText: (Any?) -> String): TableViewerColumn {
        val viewerColumn = TableViewerColumn(this, SWT.NONE)
        viewerColumn.column.text = header
        viewerColumn.column.width = width
        viewerColumn.column.resizable = true
        viewerColumn.column.moveable = true
        viewerColumn.setLabelProvider(object : ColumnLabelProvider() {
            override fun getText(element: Any?): String {
                return elementToText(element)
            }

            override fun getToolTipText(element: Any?): String {
                return elementToText(element)
            }

            override fun getToolTipDisplayDelayTime(`object`: Any?): Int {
                return 1000
            }

            override fun useNativeToolTip(`object`: Any?): Boolean {
                return true
            }
        })
        return viewerColumn
    }
}