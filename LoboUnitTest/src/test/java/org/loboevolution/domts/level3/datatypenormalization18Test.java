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
import org.loboevolution.html.dom.nodeimpl.DOMErrorMonitor;
import org.loboevolution.html.node.DOMConfiguration;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;
import org.loboevolution.html.node.Node;

import static org.junit.Assert.*;


/**
 * Normalize document using Node.normalize which is not affected by DOMConfiguration unlike
 * Document.normalizeDocument.  Strings should not have been normalized.
 *
 * @author Curt Arnold
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-normalize">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-normalize</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#parameter-datatype-normalization">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#parameter-datatype-normalization</a>
 */
public class datatypenormalization18Test extends LoboUnitTest {


    @Test
    public void runTest() {
        Document doc;
        HTMLCollection elemList;
        Element element;
        DOMConfiguration domConfig;
        String str;
        boolean canSetValidate;
        boolean canSetXMLSchema;
        boolean canSetDataNorm;
        String xsdNS = "http://www.w3.org/2001/XMLSchema";
        DOMErrorMonitor errorMonitor = new DOMErrorMonitor();

        Node childNode;
        String childValue;
        doc = sampleXmlFile("datatype_normalization2.xml");
        domConfig = doc.getDomConfig();
        canSetValidate = domConfig.canSetParameter("validate", Boolean.TRUE);
        /*DOMString */
        canSetXMLSchema = domConfig.canSetParameter("schema-type", xsdNS);
        canSetDataNorm = domConfig.canSetParameter("datatype-normalization", Boolean.TRUE);

        if (
                canSetValidate & canSetXMLSchema & canSetDataNorm) {
            domConfig.setParameter("datatype-normalization", Boolean.TRUE);
            domConfig.setParameter("validate", Boolean.TRUE);
            /*DOMString */
            domConfig.setParameter("schema-type", xsdNS);
            /*DOMErrorMonitor */
            domConfig.setParameter("error-handler", errorMonitor);
            doc.normalize();
            assertTrue("normalizeError", errorMonitor.assertLowerSeverity(2));
            elemList = doc.getElementsByTagNameNS("http://www.w3.org/1999/xhtml", "code");
            element = (Element) elemList.item(1);
            childNode = element.getFirstChild();
            childValue = childNode.getNodeValue();
            assertEquals("content2", "EMP  0001", childValue);
            element = (Element) elemList.item(2);
            childNode = element.getFirstChild();
            childValue = childNode.getNodeValue();
            assertEquals("content3", "EMP 0001", childValue);
            element = (Element) elemList.item(0);
            childNode = element.getFirstChild();
            childValue = childNode.getNodeValue();
            assertNotEquals("content1", "EMP 0001", childValue);
        }
    }
}