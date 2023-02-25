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
import org.loboevolution.html.node.Attr;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;
import org.loboevolution.html.node.NamedNodeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * First use setAttribute to create two new attributes on the second strong element and sup element.
 * Invoke setIdAttribute on the new attributes. Verify by calling isID on the new attributes and getElementById
 * with two different values on document.
 *
 * @author IBM
 * @author Jenny Hsu
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-ElSetIdAttr">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-ElSetIdAttr</a>
 */
public class elementsetidattribute09Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        HTMLCollection elemList1;
        HTMLCollection elemList2;
        Element nameElem;
        Element salaryElem;
        NamedNodeMap attributesMap;
        Attr attr;
        boolean id = false;
        Element elem;
        String elemName;
        doc = sampleXmlFile("hc_staff.xml");
        elemList1 = doc.getElementsByTagName("strong");
        elemList2 = doc.getElementsByTagName("sup");
        nameElem = (Element) elemList1.item(2);
        salaryElem = (Element) elemList2.item(2);
        nameElem.setAttribute("hasMiddleName", "Antoine");
        salaryElem.setAttribute("annual", "2002");
        nameElem.setIdAttribute("hasMiddleName", true);
        salaryElem.setIdAttribute("annual", true);
        attributesMap = nameElem.getAttributes();
        attr = (Attr) attributesMap.getNamedItem("hasMiddleName");
        id = attr.isId();
        assertTrue("elementsetidattributeIsId1True09", id);
        attributesMap = salaryElem.getAttributes();
        attr = (Attr) attributesMap.getNamedItem("annual");
        id = attr.isId();
        assertTrue("elementsetidattributeIsId2True09", id);
        elem = doc.getElementById("Antoine");
        elemName = elem.getTagName();
        assertEquals("elementsetidattribute1GetElementById09", "strong", elemName);
        elem = doc.getElementById("2002");
        elemName = elem.getTagName();
        assertEquals("elementsetidattribute2GetElementById09", "sup", elemName);
    }
}

