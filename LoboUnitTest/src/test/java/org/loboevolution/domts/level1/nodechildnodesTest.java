
/*
 * GNU GENERAL LICENSE
 * Copyright (C) 2014 - 2021 Lobo Evolution
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

package org.loboevolution.domts.level1;

import org.junit.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Node;
import org.loboevolution.html.node.NodeList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Collect the element names from Node.childNodes and check against expectations.
 *
 * @author NIST
 * @author Mary Brady
 * @see <a href="http://www.w3.org/TR/1998/REC-DOM-Level-1-19981001/level-one-core#ID-1451460987">http://www.w3.org/TR/1998/REC-DOM-Level-1-19981001/level-one-core#ID-1451460987</a>
 */
public class nodechildnodesTest extends LoboUnitTest {

    /**
     * Runs the test case.
     */
    @Test
    public void runTest() {
        Document doc;
        HTMLCollection elementList;
        Node employeeNode;
        NodeList childNodes;
        Node childNode;
        int childType;
        String childName;
        List<String> elementNames = new ArrayList<>();

        List<String> expectedElementNames = new ArrayList<>();
        expectedElementNames.add("EMPLOYEEID");
        expectedElementNames.add("NAME");
        expectedElementNames.add("POSITION");
        expectedElementNames.add("SALARY");
        expectedElementNames.add("GENDER");
        expectedElementNames.add("ADDRESS");

        doc = sampleXmlFile("staff.xml");
        elementList = doc.getElementsByTagName("employee");
        employeeNode = elementList.item(1);
        childNodes = employeeNode.getChildNodes();
        for (int indexN1006C = 0; indexN1006C < childNodes.getLength(); indexN1006C++) {
            childNode = childNodes.item(indexN1006C);
            childType = childNode.getNodeType();

            if (childType == 1) {
                childName = childNode.getNodeName();
                elementNames.add(childName);
            }
        }
        assertEquals("elementNames", expectedElementNames, elementNames);
    }
}
