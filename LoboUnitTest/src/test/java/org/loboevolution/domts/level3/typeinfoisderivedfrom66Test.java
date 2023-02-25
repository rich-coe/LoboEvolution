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
import org.loboevolution.html.node.TypeInfo;

import static org.junit.Assert.assertTrue;


/**
 * The isDerivedFrom method checks if this TypeInfo derives from the specified ancestor type.
 * <p>
 * Get schemaTypeInfo on an element of type Union that belongs to a document with an XML schema.
 * Invoke method isDerivedFrom with derivation method union and verify that the value returned is true.
 * Verify that emType is derived from emp0004_5Type by union.
 *
 * @author IBM
 * @author Jenny Hsu
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#TypeInfo-isDerivedFrom">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#TypeInfo-isDerivedFrom</a>
 */
public class typeinfoisderivedfrom66Test extends LoboUnitTest {
    @Test
    public void runTest() {
        Document doc;
        Element unionElem;
        TypeInfo elemTypeInfo;
        String typeName;
        HTMLCollection elemList;
        boolean retValue;
        doc = sampleXmlFile("hc_staff.xml");
        elemList = doc.getElementsByTagName("em");
        unionElem = (Element) elemList.item(0);
        elemTypeInfo = unionElem.getSchemaTypeInfo();
        retValue = elemTypeInfo.isDerivedFrom("http://www.w3.org/1999/xhtml", "emp0004_5Type", 0);
        assertTrue("typeinfoisderivedfrom66", retValue);
    }
}

