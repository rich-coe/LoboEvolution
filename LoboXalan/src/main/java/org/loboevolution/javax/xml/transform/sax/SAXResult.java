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

package org.loboevolution.javax.xml.transform.sax;

import org.loboevolution.javax.xml.transform.Result;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

public class SAXResult
    implements Result
{

    public SAXResult()
    {
        throw new RuntimeException("Stub!");
    }

    public SAXResult(final ContentHandler handler)
    {
        throw new RuntimeException("Stub!");
    }

    public void setHandler(final ContentHandler handler)
    {
        throw new RuntimeException("Stub!");
    }

    public ContentHandler getHandler()
    {
        throw new RuntimeException("Stub!");
    }

    public void setLexicalHandler(final LexicalHandler handler)
    {
        throw new RuntimeException("Stub!");
    }

    public LexicalHandler getLexicalHandler()
    {
        throw new RuntimeException("Stub!");
    }

    public void setSystemId(final String systemId)
    {
        throw new RuntimeException("Stub!");
    }

    public String getSystemId()
    {
        throw new RuntimeException("Stub!");
    }

    public static final String FEATURE = "http://org.loboevolution.javax.xml.transform.sax.SAXResult/feature";
}
