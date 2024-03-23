/*
 * MIT License
 *
 * Copyright (c) 2014 - 2024 LoboEvolution
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Contact info: ivan.difrancesco@yahoo.it
 */
package com.jtattoo.plaf.bernstein;

import java.awt.Graphics;

import com.jtattoo.plaf.BaseSplitPaneDivider;

/**
 * <p>BernsteinSplitPaneDivider class.</p>
 *
 * Author Michael Hagen
 *
 */
public class BernsteinSplitPaneDivider extends BaseSplitPaneDivider {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>Constructor for BernsteinSplitPaneDivider.</p>
	 *
	 * @param ui a {@link com.jtattoo.plaf.bernstein.BernsteinSplitPaneUI} object.
	 */
	public BernsteinSplitPaneDivider(final BernsteinSplitPaneUI ui) {
		super(ui);
	}

	/** {@inheritDoc} */
	@Override
	public void paint(final Graphics g) {
		BernsteinUtils.fillComponent(g, this);
		paintComponents(g);
	}

} // end of cass BernsteinSplitPaneDivider