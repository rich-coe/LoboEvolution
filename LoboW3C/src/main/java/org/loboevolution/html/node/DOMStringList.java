/*
 *
 *     GNU GENERAL LICENSE
 *     Copyright (C) 2014 - 2021 Lobo Evolution
 *
 *     This program is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU General Public
 *     License as published by the Free Software Foundation; either
 *     verion 3 of the License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     General License for more details.
 *
 *     You should have received a copy of the GNU General Public
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 *     Contact info: ivan.difrancesco@yahoo.it
 *
 */

package org.loboevolution.html.node;


/**
 * A type returned by some APIs which contains a list of DOMString (strings).
 */
public interface DOMStringList {
   
    /**
     * Returns the number of strings in strings.
     */
    int getLength();

    /**
     * Returns true if strings contains string, and false otherwise.
     */
    boolean contains(String string);

    /**
     * Returns the string with index index from strings.
     */
    String item(int index);

}