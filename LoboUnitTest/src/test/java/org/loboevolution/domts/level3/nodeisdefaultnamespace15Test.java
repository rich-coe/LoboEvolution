/*
 * MIT License
 *
 * Copyright (c) 2014 - 2023 LoboEvolution
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Contact info: ivan.difrancesco@yahoo.it
 */

package org.loboevolution.domts.level3;


import org.junit.jupiter.api.Test;
import org.loboevolution.driver.LoboUnitTest;
import org.loboevolution.html.dom.HTMLCollection;
import org.loboevolution.html.node.Comment;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;
import org.loboevolution.html.node.Node;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Using isDefaultNamespace on a Element's new cloned Comment node, which has a namespace attribute
 * declaration without a namespace prefix in its parent Element node and  verify if the
 * value returned is true.
 *
 * @author IBM
 * @author Neil Delima
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Node3-isDefaultNamespace">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#Node3-isDefaultNamespace</a>
 */
public class nodeisdefaultnamespace15Test extends LoboUnitTest {
    @Test
    public void runTest() {
        final Document doc;
        final Element bodyElem;
        final Element elem;
        final Comment comment;
        final Comment clonedComment;
        final boolean isDefault;
        Node appendedChild;
        final HTMLCollection bodyList;
        doc = sampleXmlFile("hc_staff.xml");
        bodyList = doc.getElementsByTagName("body");
        bodyElem = (Element) bodyList.item(0);
        elem = doc.createElementNS("http://www.w3.org/1999/xhtml", "p");
        comment = doc.createComment("Text");
        clonedComment = (Comment) comment.cloneNode(true);
        appendedChild = elem.appendChild(clonedComment);
        appendedChild = bodyElem.appendChild(elem);
        isDefault = clonedComment.isDefaultNamespace("http://www.w3.org/1999/xhtml");
        assertTrue(isDefault);
    }
}

