/*
 * MIT License
 *
 * Copyright (c) 2014 - 2024 LoboEvolution
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
import org.loboevolution.html.dom.nodeimpl.UserDataHandlerImpl;
import org.loboevolution.html.dom.nodeimpl.UserDataNotification;
import org.loboevolution.html.node.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Call setUserData on a node providing a UserDataHandler and adopt the node.
 *
 * @author Curt Arnold
 * @see <a href="http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-handleUserDataEvent">http://www.w3.org/TR/2003/CR-DOM-Level-3-Core-20031107/core#ID-handleUserDataEvent</a>
 */
public class Userdatahandler04Test extends LoboUnitTest {
    @Test
    public void runTest() {
        final Document doc;
        final Node node;
        final HTMLCollection pList;
        final UserDataHandlerImpl userDataHandlerImpl = new UserDataHandlerImpl();

        Object oldUserData;
        final String elementNS;
        final Node newNode;
        final List<UserDataNotification> notifications;

        UserDataNotification notification;
        short operation;
        String key;
        String data;
        Node src;
        Node dst;
        int greetingCount = 0;
        int salutationCount = 0;
        final String hello = "Hello";
        final String mister = "Mr.";
        final Document newDoc;
        final String rootName;
        final String rootNS;
        final DOMImplementation domImpl;
        final DocumentType docType = null;

        final Element docElem;
        doc = sampleXmlFile("barfoo.xml");
        domImpl = doc.getImplementation();
        docElem = doc.getDocumentElement();
        rootNS = docElem.getNamespaceURI();
        rootName = docElem.getTagName();
        newDoc = domImpl.createDocument(rootNS, rootName, docType);
        pList = doc.getElementsByTagName("p");
        node = pList.item(0);
        oldUserData = node.setUserData("greeting", ((Object) /*DOMString */hello), userDataHandlerImpl);
        oldUserData = node.setUserData("salutation", ((Object) /*DOMString */mister), userDataHandlerImpl);
        elementNS = node.getNamespaceURI();
        newNode = doc.adoptNode(node);
        notifications = userDataHandlerImpl.getAllNotifications();
        assertEquals(2, notifications.size(), "Userdatahandler04Assert1");
        for (int indexN100CD = 0; indexN100CD < notifications.size(); indexN100CD++) {
            notification = (UserDataNotification) notifications.get(indexN100CD);
            operation = notification.getOperation();
            assertEquals(5, operation, "Userdatahandler04Assert2");
            key = notification.getKey();
            data = (String) notification.getData();

            if ("greeting".equals(key)) {
                assertEquals(hello, data, "Userdatahandler04Assert3");
                greetingCount += 1;
            } else {
                assertEquals("salutation", key, "Userdatahandler04Assert4");
                assertEquals(mister, data, "Userdatahandler04Assert5");
                salutationCount += 1;
            }

            src = notification.getSrc();
            assertSame(node, src, "Userdatahandler04Assert6");
            dst = notification.getDst();
            assertNull(dst, "Userdatahandler04Assert7");
        }
        assertEquals(1, greetingCount, "Userdatahandler04Assert8");
        assertEquals(1, salutationCount, "Userdatahandler04Assert9");
    }
}

