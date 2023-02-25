/*
 * GNU GENERAL LICENSE
 * Copyright (C) 2014 - 2023 Lobo Evolution
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either
 * verion 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General License for more details.
 *
 * You should have received a copy of the GNU General Public
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact info: ivan.difrancesco@yahoo.it
 */

package org.loboevolution.menu.tools.developer;

import org.loboevolution.component.BrowserFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * <p>DeveloperToolsAction class.</p>
 */
public class DeveloperToolsAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    private final BrowserFrame frame;

    /**
     * <p>Constructor for DeveloperToolsAction.</p>
     *
     * @param frame a {@link org.loboevolution.component.BrowserFrame} object.
     */
    public DeveloperToolsAction(BrowserFrame frame) {
        this.frame = frame;
    }

    /** {@inheritDoc} */
    @Override
    public void actionPerformed(final ActionEvent e) {
        final DeveloperToolsWindow dev = new DeveloperToolsWindow(this.frame);
        dev.setSize(new Dimension(600, 500));
        dev.setLocationByPlatform(true);
        dev.setVisible(true);
    }
}
