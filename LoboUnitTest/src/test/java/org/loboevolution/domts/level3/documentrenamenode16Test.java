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

package org.loboevolution.domts.level3;


import org.junit.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;
import org.loboevolution.html.node.Node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * Invoke the renameNode method to rename the fourth
 * acronym element with a new namespaceURI that is
 * null and qualifiedName that is renamedNode.
 * Check if this element has been renamed successfully by verifying the
 * nodeName, attributes of the renamed node.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-renameNode">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-renameNode</a>
 */
public class documentrenamenode16Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        Element element;
        HTMLCollection childList;
        Node renamedclass;
        String nodeName;
        int nodeType;
        String namespaceURI;
        String nullNSURI = null;

        doc = sampleXmlFile("hc_staff.xml");
        childList = doc.getElementsByTagName("acronym");
        element = (Element) childList.item(3);
        renamedclass = doc.renameNode(element, nullNSURI, "renamedNode");
        nodeName = renamedclass.getNodeName();
        namespaceURI = renamedclass.getNamespaceURI();
        nodeType = renamedclass.getNodeType();
        assertEquals("documentrenamenode16_nodeName", "renamedNode", nodeName);
        assertEquals("documentrenamenode16_nodeType", 1, nodeType);
        assertNull("documentrenamenode16_nodeValue", namespaceURI);
    }
}

