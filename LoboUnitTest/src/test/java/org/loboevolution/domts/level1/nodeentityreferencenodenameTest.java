
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

package org.loboevolution.domts.level1;

import org.junit.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;
import org.loboevolution.html.node.Node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * The string returned by the "getNodeName()" method for an
 * EntityReference Node is the name of the entity referenced.
 * <p>
 * Retrieve the first Entity Reference node from the last
 * child of the second employee and check the string
 * returned by the "getNodeName()" method.   It should be
 * equal to "ent2".
 *
 * @author NIST
 * @author Mary Brady
 * @see <a href="http://www.w3.org/TR/1998/REC-DOM-Level-1-19981001/level-one-core#ID-F68D095">http://www.w3.org/TR/1998/REC-DOM-Level-1-19981001/level-one-core#ID-F68D095</a>
 */
public class nodeentityreferencenodenameTest extends LoboUnitTest {

    /**
     * Runs the test case.
     *
     */
    @Test
    public void runTest() {
        Document doc;
        HTMLCollection elementList;
        Element entRefAddr;
        Node entRefNode;
        String entRefName;
        int nodeType;
        doc = sampleXmlFile("staff.xml");
        elementList = doc.getElementsByTagName("address");
        entRefAddr = (Element) elementList.item(1);
        entRefNode = entRefAddr.getFirstChild();
        nodeType = entRefNode.getNodeType();

        if (nodeType != 5) {
            entRefNode = doc.createEntityReference("ent2");
            assertNotNull("createdEntRefNotNull", entRefNode);
        }
        entRefName = entRefNode.getNodeName();
        assertEquals("nodeEntityReferenceNodeNameAssert1", "ent2", entRefName);
    }
}
