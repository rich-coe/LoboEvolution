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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Append a Comment node and normalize with "comments" set to false.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-normalizeDocument">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Document3-normalizeDocument</a>
 * @see <a href="http://www.w3.org/Bugs/Public/show_bug.cgi?id=416">http://www.w3.org/Bugs/Public/show_bug.cgi?id=416</a>
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#parameter-comments">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#parameter-comments</a>
 */
public class documentnormalizedocument04Test extends LoboUnitTest {


    @Test
    public void runTest() {
        Document doc;
        Element elem;
        Comment newComment;
        Node lastChild;
        String nodeName;
        DOMConfiguration domConfig;
        DOMErrorMonitor errorMonitor = new DOMErrorMonitor();

        HTMLCollection pList;
        doc = sampleXmlFile("barfoo.xml");
        pList = doc.getElementsByTagName("p");
        elem = (Element) pList.item(0);
        newComment = doc.createComment("COMMENT_NODE");
        elem.appendChild(newComment);
        domConfig = doc.getDomConfig();
        domConfig.setParameter("comments", Boolean.TRUE);
        /*DOMErrorMonitor */
        domConfig.setParameter("error-handler", errorMonitor);
        doc.normalizeDocument();
        assertTrue("normalizationError", errorMonitor.assertLowerSeverity(2));
        pList = doc.getElementsByTagName("p");
        elem = (Element) pList.item(0);
        lastChild = elem.getLastChild();
        nodeName = lastChild.getNodeName();
        assertEquals("documentnormalizedocument04_true", "#comment", nodeName);
        domConfig.setParameter("comments", Boolean.FALSE);
        doc.normalizeDocument();
        assertTrue("normalization2Error", errorMonitor.assertLowerSeverity(2));
        pList = doc.getElementsByTagName("p");
        elem = (Element) pList.item(0);
        lastChild = elem.getLastChild();
        nodeName = lastChild.getNodeName();
        assertEquals("hasChildText", "#text", nodeName);
    }
}
