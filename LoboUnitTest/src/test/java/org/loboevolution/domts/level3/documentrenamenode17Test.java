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
 * Invoke the renameNode method to rename a new element node of a new document so that
 * its namespaceURI is http://www.w3.org/2000/xmlns/ and qualifiedName is xmlns:xmlns.
 * Check if this element has been renamed successfully by verifying the
 * nodeName, attributes of the renamed node.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-renameNode">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-renameNode</a>
 */
public class documentrenamenode17Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        Document newDoc;
        DOMImplementation domImpl;
        Element element;
        Node renamedNode;
        String nodeName;
        int nodeType;
        String namespaceURI;
        Element docElem;
        String rootNS;
        String rootTagname;
        DocumentType nullDocType = null;

        doc = sampleXmlFile("barfoo.xml");
        docElem = doc.getDocumentElement();
        rootNS = docElem.getNamespaceURI();
        rootTagname = docElem.getTagName();
        domImpl = doc.getImplementation();
        newDoc = domImpl.createDocument(rootNS, rootTagname, nullDocType);
        element = newDoc.createElementNS("http://www.w3.org/1999/xhtml", "body");
        renamedNode = newDoc.renameNode(element, "http://www.w3.org/1999/xhtml", "xhtml:head");
        nodeName = renamedNode.getNodeName();
        namespaceURI = renamedNode.getNamespaceURI();
        nodeType = renamedNode.getNodeType();
        assertEquals("documentrenamenode16_nodeName", "xhtml:head", nodeName);
        assertEquals("documentrenamenode16_nodeType", 1, nodeType);
        assertEquals("documentrenamenode16_nodeValue", "http://www.w3.org/1999/xhtml", namespaceURI);
    }
}

