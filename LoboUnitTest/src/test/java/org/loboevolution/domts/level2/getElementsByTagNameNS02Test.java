
/*
 * GNU GENERAL LICENSE
 * Copyright (C) 2014 - 2021 Lobo Evolution
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

package org.loboevolution.domts.level2;

import org.junit.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;

import static org.junit.Assert.assertEquals;


/**
 * The "getElementsByTagNameNS(namespaceURI,localName)" method for a
 * Document should return a new NodeList of all Elements with a given
 * localName and namespaceURI in the order they were encountered in a preorder
 * traversal of the document tree.
 * <p>
 * Invoke method getElementsByTagNameNS(namespaceURI,localName) on this document
 * with namespaceURI being " " and localName is "employee".
 * Method should return a new NodeList containing five Elements.
 * Retrieve the FOURTH element whose name should be "employee".
 *
 * @author NIST
 * @author Mary Brady
 * @see <a href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-getElBTNNS">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-getElBTNNS</a>
 */
public class getElementsByTagNameNS02Test extends LoboUnitTest {

    /**
     * Runs the test case.
     *
     */
    @Test
    public void runTest() {
        Document doc;
        HTMLCollection newList;
        Element newElement;
        String prefix;
        String lname;
        doc = sampleXmlFile("staffNS.xml");
        newList = doc.getElementsByTagNameNS("*", "employee");
        assertEquals( "employeeCount", 5, newList.getLength());
        newElement = (Element) newList.item(3);
        prefix = newElement.getPrefix();
        assertEquals("prefix", "emp", prefix);
        lname = newElement.getLocalName();
        assertEquals("lname", "employee", lname);
    }
}
