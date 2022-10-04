
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

package org.loboevolution.domts.level2;

import com.gargoylesoftware.css.dom.DOMException;
import org.junit.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;

import static org.junit.Assert.assertTrue;


/**
 * The method setPrefix raises a NAMESPACE_ERR if the specified prefix is malformed.
 * Create a new namespace aware element node and call the setPrefix method on it with several malformed
 * prefix values.  Check if a NAMESPACE_ERR is thrown.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/DOM-Level-2-Core/core#ID-NodeNSPrefix">http://www.w3.org/TR/DOM-Level-2-Core/core#ID-NodeNSPrefix</a>
 */
public class nodesetprefix05Test extends LoboUnitTest {

    /**
     * Runs the test case.
     *
     */
    @Test
    public void runTest() {
        Document doc;
        Element element;
        String prefixValue;
        java.util.List prefixValues = new java.util.ArrayList();
        prefixValues.add("_:");
        prefixValues.add(":0");
        prefixValues.add(":");
        prefixValues.add("_::");
        prefixValues.add("a:0:c");

        doc = sampleXmlFile("staffNS.xml");
        element = doc.createElementNS("http://www.w3.org/DOM/Test/L2", "dom:elem");
        for (int indexN10050 = 0; indexN10050 < prefixValues.size(); indexN10050++) {
            prefixValue = (String) prefixValues.get(indexN10050);

            {
                boolean success = false;
                try {
                    element.setPrefix(prefixValue);
                } catch (DOMException ex) {
                    success = (ex.getCode() == DOMException.NAMESPACE_ERR);
                }
                assertTrue("throw_NAMESPACE_ERR", success);
            }
        }
    }

    /**
     * Gets URI that identifies the test.
     *
     * @return uri identifier of test
     */
    public String getTargetURI() {
        return "http://www.w3.org/2001/DOM-Test-Suite/level2/core/nodesetprefix05";
    }
}
