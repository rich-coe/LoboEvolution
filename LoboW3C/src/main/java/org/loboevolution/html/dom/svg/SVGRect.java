
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
package org.loboevolution.html.dom.svg;



/**
 * <p>SVGRect interface.</p>
 *
 *
 *
 */
public interface SVGRect {
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
	 * @throws com.gargoylesoftware.css.dom.DOMException if any.
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
	 * @throws com.gargoylesoftware.css.dom.DOMException if any.
	 */
	void setY(final float y);

	/**
	 * <p>getWidth.</p>
	 *
	 * @return a float.
	 */
	float getWidth();

	/**
	 * <p>setWidth.</p>
	 *
	 * @param width a float.
	 * @throws com.gargoylesoftware.css.dom.DOMException if any.
	 */
	void setWidth(float width);

	/**
	 * <p>getHeight.</p>
	 *
	 * @return a float.
	 */
	float getHeight();

	/**
	 * <p>setHeight.</p>
	 *
	 * @param height a float.
	 * @throws com.gargoylesoftware.css.dom.DOMException if any.
	 */
	void setHeight(float height);
}
