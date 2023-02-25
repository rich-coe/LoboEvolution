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
import org.loboevolution.html.node.*;

import static org.junit.Assert.assertEquals;


/**
 * Invoke the adoptNode method on a new document with a new Element of this
 * Document as the source.  Verify if the node has been adopted correctly by checking the
 * nodeName of the adopted Element.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-adoptNode">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-adoptNode</a>
 */
public class documentadoptnode25Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        Element newElem;
        Document newDoc;
        DOMImplementation domImpl;
        Node adoptedNode;
        String adoptedName;
        String adoptedNS;
        Element docElem;
        String rootNS;
        String rootName;
        DocumentType nullDocType = null;

        doc = sampleXmlFile("hc_staff.xml");
        docElem = doc.getDocumentElement();
        rootNS = docElem.getNamespaceURI();
        rootName = docElem.getTagName();
        newElem = doc.createElementNS("http://www.w3.org/1999/xhtml", "th");
        domImpl = doc.getImplementation();
        newDoc = domImpl.createDocument(rootNS, rootName, nullDocType);
        adoptedNode = newDoc.adoptNode(newElem);

        if ((adoptedNode != null)) {
            adoptedName = adoptedNode.getNodeName();
            adoptedNS = adoptedNode.getNamespaceURI();
            assertEquals("documentadoptnode25_1", "th", adoptedName);
            assertEquals("documentadoptnode25_2", "http://www.w3.org/1999/xhtml", adoptedNS);
        }
    }
}

