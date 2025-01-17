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

package org.loboevolution.html.js.xml;

import lombok.Getter;
import org.htmlunit.cssparser.dom.DOMException;
import org.loboevolution.gui.LocalHtmlRendererConfig;
import org.loboevolution.html.dom.nodeimpl.DOMImplementationImpl;
import org.loboevolution.html.node.*;
import org.loboevolution.http.UserAgentContext;
import org.xml.sax.*;
import org.xml.sax.ext.LexicalHandler;

import java.util.Objects;

/**
 * <p>XMLContentHandler class.</p>
 */
public class XMLContentHandler implements ContentHandler, LexicalHandler, ErrorHandler {

    @Getter
    private Document document = null;

    private Node currentNode = null;

    private Locator lastLocator = null;

    /** {@inheritDoc} */
    @Override
    public void setDocumentLocator(final Locator locator) {
        this.lastLocator = locator;
    }

    /** {@inheritDoc} */
    @Override
    public void startDocument() {
        document = null;
    }

    /** {@inheritDoc} */
    @Override
    public void endDocument() {
        currentNode = null;
    }

    /** {@inheritDoc} */
    @Override
    public void startPrefixMapping(final String prefix, final String uri) {}

    /** {@inheritDoc} */
    @Override
    public void endPrefixMapping(final String prefix) {}

    /** {@inheritDoc} */
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
        if (document == null) {
            documentElement(uri, localName, qName, atts);
        } else {
            newElement(uri, localName, qName, atts);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void endElement(final String uri, final String localName, final String qName) {
        currentNode = currentNode.getParentNode();
    }

    /** {@inheritDoc} */
    @Override
    public void characters(final char[] ch, final int start, final int length) {}

    /** {@inheritDoc} */
    @Override
    public void ignorableWhitespace(final char[] ch, final int start, final int length) {}

    /** {@inheritDoc} */
    @Override
    public void processingInstruction(final String target, final String data) {}

    /** {@inheritDoc} */
    @Override
    public void skippedEntity(final String name) {}

    /** {@inheritDoc} */
    @Override
    public void warning(final SAXParseException exception) {}

    /** {@inheritDoc} */
    @Override
    public void error(final SAXParseException exception) {}

    /** {@inheritDoc} */
    @Override
    public void fatalError(final SAXParseException exception) {}

    /** {@inheritDoc} */
    @Override
    public void startDTD(final String name, final String publicId, final String systemId) {}

    /** {@inheritDoc} */
    @Override
    public void endDTD() {}

    /** {@inheritDoc} */
    @Override
    public void startEntity(final String name) {}

    /** {@inheritDoc} */
    @Override
    public void endEntity(final String name) {}

    /** {@inheritDoc} */
    @Override
    public void startCDATA() {}

    /** {@inheritDoc} */
    @Override
    public void endCDATA() {}

    /** {@inheritDoc} */
    @Override
    public void comment(final char[] ch, final int start, final int length) {

    }

    private void documentElement(final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
        document = createDocument(uri, qName);
        final Element element = document.getDocumentElement();
        currentNode = element;
        setAttributes(element, atts);

        if(qName != null) {
            newElement(uri, localName, qName, atts);
        }
    }

    private void newElement(final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
        final Element element = document.createElementNS(uri, qName);
        setAttributes(element, atts);
        appendChild(element);
        currentNode = element;
    }

    private void setAttributes(final Element element, final Attributes atts) {
        final int len = atts.getLength();
        for (int i = 0; i < len; i++) {
            final String namespaceURI = atts.getURI(i);
            final String value = atts.getValue(i);
            final String attrQName = atts.getQName(i);
            final Attr attr = document.createAttributeNS(namespaceURI, attrQName);
            attr.setValue(value);
            element.getAttributes().setNamedItem(attr);
            if ("ID".equals(atts.getType(i))
                    || ("id".equals(attrQName) && !Objects.equals(element.getNamespaceURI(), document.getNamespaceURI()))) {
                element.setIdAttributeNode(attr, true);
            }
        }
    }

    private Document createDocument(final String uri, final String qName) {
        final UserAgentContext context = new UserAgentContext(new LocalHtmlRendererConfig());
        context.setUserAgentEnabled(false);
        final DOMImplementationImpl domImpl = new DOMImplementationImpl(context);
        final DocumentType doctype = domImpl.createDocumentType("HTML", null, null);
        return domImpl.createDocument(uri, "HTML", doctype);
    }

    private void appendChild(final Node node) throws SAXException {
        try {
            document.appendChild(node);
        } catch (final DOMException e) {
            error("Error appending child " + node.getNodeName() + " to " + currentNode.getNodeName(), e);
        }
    }

    private void error(final String message, final Exception ex) throws SAXException {
        if (lastLocator == null) {
            throw new SAXException(message, ex);
        } else {
            throw new SAXParseException(message, lastLocator, ex);
        }
    }
}