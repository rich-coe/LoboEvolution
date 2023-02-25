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
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;

import static org.junit.Assert.assertTrue;


/**
 * Using replaceChild on an Element node attempt to replace a new Element node with
 * another new Element node and verify if a NOT_FOUND_ERR gets thrown.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-785887307">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-785887307</a>
 */
public class nodereplacechild29Test extends LoboUnitTest {

    @Test
    public void runTest() {
        Document doc;
        HTMLCollection childList;
        Element elem;
        Element oldChild;
        Element newChild;
        doc = sampleXmlFile("hc_staff.xml");
        childList = doc.getElementsByTagName("p");
        elem = (Element) childList.item(0);
        oldChild = doc.createElementNS("http://www.w3.org/1999/xhtml", "dom3:br");
        newChild = doc.createElementNS("http://www.w3.org/1999/xhtml", "dom3:span");

        boolean success = false;
        try {
            elem.replaceChild(newChild, oldChild);
        } catch (DOMException ex) {
            success = (ex.getCode() == DOMException.NOT_FOUND_ERR);
        }
        assertTrue("throw_NOT_FOUND_ERR", success);

    }
}

