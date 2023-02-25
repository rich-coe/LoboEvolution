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


import com.gargoylesoftware.css.dom.DOMException;
import org.junit.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.node.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Using removeChild on a namespace Attribute node attempt to remove its Text child node and
 * and verify the name of the returned node that was removed.  Now attempt the reverse
 * and verify if a NOT_FOUND_ERR is thrown.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-1734834066">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-1734834066</a>
 */
public class noderemovechild29Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        HTMLCollection parentList;
        NamedNodeMap attrsMap;
        Attr parent;
        Text child;
        Element elem;
        Text removed;
        String removedName;
        Node removedNode;
        doc = sampleXmlFile("hc_staff.xml");
        parentList = doc.getElementsByTagName("p");
        elem = (Element) parentList.item(0);
        attrsMap = elem.getAttributes();
        parent = (Attr) attrsMap.getNamedItem("xmlns:dmstc");
        child = (Text) parent.getFirstChild();
        removed = (Text) parent.removeChild(child);
        removedName = removed.getNodeValue();
        assertEquals("noderemovechild29", "http://www.usa.com", removedName);

        {
            boolean success = false;
            try {
                removedNode = child.removeChild(parent);
            } catch (DOMException ex) {
                success = (ex.getCode() == DOMException.NOT_FOUND_ERR);
            }
            assertTrue("throw_NOT_FOUND_ERR", success);
        }
    }
}

