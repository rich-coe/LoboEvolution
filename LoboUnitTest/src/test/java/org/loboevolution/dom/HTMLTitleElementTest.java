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

package org.loboevolution.dom;

import org.junit.jupiter.api.Test;
import org.loboevolution.driver.LoboUnitTest;

/**
 * Tests for {@link org.loboevolution.html.dom.HTMLTitleElement}.
 */
public class HTMLTitleElementTest extends LoboUnitTest {
	
    /**
     * <p>text.</p>
     */
    @Test		 
    public void text() {
        final String html =
                "<html>\n"
                        + "  <head>\n"
                        + "    <title>Page Title</title>\n"
                        + "    <script>\n"
                        + "      function test() {\n"
                        + "        var title = document.getElementsByTagName('title')[0];\n"
                        + "        alert(title.text);\n"
                        + "        title.text = 'New Title';\n"
                        + "        alert(title.text);\n"
                        + "      }\n"
                        + "    </script>\n"
                        + "  </head>\n"
                        + "  <body onload='test()'>\n"
                        + "  </body>\n"
                        + "</html>";
        final String[] messages = {"Page Title", "New Title"};
        checkHtmlAlert(html, messages);
    }
	
    /**
     * <p>textCreateElement.</p>
     */
    @Test
    public void textCreateElement() {
        final String html =
                "<html>\n"
                        + "  <head>\n"
                        + "    <script>\n"
                        + "      function test() {\n"
                        + "        var title = document.createElement('title');\n"
                        + "        alert(title.text);\n"
                        + "        title.text = 'New Title';\n"
                        + "        alert(title.text);\n"
                        + "      }\n"
                        + "    </script>\n"
                        + "  </head>\n"
                        + "  <body onload='test()'>\n"
                        + "  </body>\n"
                        + "</html>";
        final String[] messages = {"", "New Title"};
        checkHtmlAlert(html, messages);
    }
}
