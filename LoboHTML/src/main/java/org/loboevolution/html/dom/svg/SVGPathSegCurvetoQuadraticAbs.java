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
package org.loboevolution.html.dom.svg;


import org.htmlunit.cssparser.dom.DOMException;

/**
 * <p>SVGPathSegCurvetoQuadraticAbs interface.</p>
 *
 *
 *
 */
public interface SVGPathSegCurvetoQuadraticAbs extends SVGPathSeg {

	/**
	 * <p>getX.</p>
	 *
	 * @return a float.
	 */
	float getX();

	/**
	 * <p>setX.</p>
	 *
	 * @param x a float.
	 * @throws DOMException if any.
	 */
	void setX(float x);

	/**
	 * <p>getY.</p>
	 *
	 * @return a float.
	 */
	float getY();

	/**
	 * <p>setY.</p>
	 *
	 * @param y a float.
	 * @throws DOMException if any.
	 */
	void setY(final float y);

	/**
	 * <p>getX1.</p>
	 *
	 * @return a float.
	 */
	float getX1();

	/**
	 * <p>setX1.</p>
	 *
	 * @param x1 a float.
	 * @throws DOMException if any.
	 */
	void setX1(float x1);

	/**
	 * <p>getY1.</p>
	 *
	 * @return a float.
	 */
	float getY1();

	/**
	 * <p>setY1.</p>
	 *
	 * @param y1 a float.
	 * @throws DOMException if any.
	 */
	void setY1(float y1);
}