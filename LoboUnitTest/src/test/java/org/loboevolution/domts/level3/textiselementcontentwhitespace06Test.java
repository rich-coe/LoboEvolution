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
import org.loboevolution.html.node.*;

import static org.junit.Assert.assertTrue;

/**
 * Insert whitespace before the "p" element in barfoo and normalize with validation.
 * isElementContentWhitespace should be true since the node is whitespace and in element content.
 *
 * @author Curt Arnold
 * @author Curt Arnold
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Text3-isElementContentWhitespace">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Text3-isElementContentWhitespace</a>
 */
public class textiselementcontentwhitespace06Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        HTMLCollection bodyList;
        Element bodyElem;
        Node refChild;
        Text textNode;
        Text blankNode;
        Node returnedNode;
        boolean isElemContentWhitespace;
        DOMConfiguration domConfig;
        boolean canSetValidation;
        Node replacedNode;
        DOMErrorMonitor errorMonitor = new DOMErrorMonitor();

        doc = sampleXmlFile("barfoo.xml");
        domConfig = doc.getDomConfig();
        canSetValidation = domConfig.canSetParameter("validate", Boolean.TRUE);

        if (canSetValidation) {
            domConfig.setParameter("validate", Boolean.TRUE);
            /*DOMErrorMonitor */
            domConfig.setParameter("error-handler", errorMonitor);
            bodyList = doc.getElementsByTagName("body");
            bodyElem = (Element) bodyList.item(0);
            refChild = bodyElem.getFirstChild();
            blankNode = doc.createTextNode("     ");
            replacedNode = bodyElem.insertBefore(blankNode, refChild);
            doc.normalizeDocument();
            assertTrue("noErrors", errorMonitor.assertLowerSeverity(2));
            bodyList = doc.getElementsByTagName("body");
            bodyElem = (Element) bodyList.item(0);
            textNode = (Text) bodyElem.getFirstChild();
            isElemContentWhitespace = textNode.isElementContentWhitespace();
            assertTrue("isElemContentWhitespace", isElemContentWhitespace);
        }
    }
}

