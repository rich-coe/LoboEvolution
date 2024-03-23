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
/*
 * Created on 11 Mar 2024
 */
package org.loboevolution.gui;

import org.loboevolution.common.EventDispatch2;
import org.loboevolution.common.WrapperLayout;
import org.loboevolution.component.IBrowserPanel;
import org.loboevolution.config.HtmlRendererConfig;
import org.loboevolution.html.dom.domimpl.HTMLDocumentImpl;
import org.loboevolution.html.dom.nodeimpl.NodeImpl;
import org.loboevolution.html.dom.nodeimpl.event.DocumentNotificationListener;
import org.loboevolution.html.io.WritableLineReader;
import org.loboevolution.html.node.Document;
import org.loboevolution.html.node.Element;
import org.loboevolution.html.node.Node;
import org.loboevolution.html.parser.DocumentBuilderImpl;
import org.loboevolution.html.parser.InputSourceImpl;
import org.loboevolution.html.renderer.BoundableRenderable;
import org.loboevolution.html.renderer.FrameContext;
import org.loboevolution.html.renderer.NodeRenderer;
import org.loboevolution.html.renderer.RenderableSpot;
import org.loboevolution.html.renderstate.RenderState;
import org.loboevolution.http.UserAgentContext;
import org.loboevolution.net.HttpNetwork;
import org.xml.sax.InputSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The HtmlPanel class is a Swing component that can render a HTML
 * DOM. It uses either {@link HtmlBlockPanel}
 * internally, depending on whether the document is determined to be a FRAMESET
 * or not.
 * <p>
 * Invoke method {@link #setDocument(Document, HtmlRendererContext)} in order to
 * schedule a document for rendering.
 */
public class WebView extends HtmlPanel
{
    /** The Constant logger. */
    private static final Logger logger = Logger.getLogger(WebView.class.getName());

    private static String default_ua = 
         "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36";

    private String ua = default_ua;

    private HTMLDocumentImpl doc;

    private HtmlRendererConfig renderCfg;

    private UserAgentContext uactxt;

    private HtmlRendererContext renderCtxt;

    int width = 400;
    int height = 800; 

    public WebView()
    {
        super();
        setPreferredSize(new Dimension(width, height));
    }

    private void loadInternal(WritableLineReader wis, String url) throws java.io.IOException, Exception
    {
        if (null == renderCfg) {
            renderCfg = new LocalHtmlRendererConfig();
        }

        if (null == uactxt) {
            uactxt = new UserAgentContext(renderCfg, true);
        }

        setPreferredSize(new Dimension(width, height));

        if (null == renderCtxt) {
            renderCtxt = new LocalHtmlRendererContext(this, uactxt);
        }

        doc = new HTMLDocumentImpl(uactxt, renderCtxt, renderCfg, wis, url);
        doc.load();
    }

    public void loadContent(String data) throws java.io.IOException, Exception
    {
        WritableLineReader wis = new WritableLineReader(new StringReader(data));
        loadInternal(wis, "");
    }

    public void load(String uri) throws java.io.IOException, Exception
    {
        URL url = new URL(uri);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", ua);
        conn.getHeaderField("Set-Cookie");
        try (InputStream in = HttpNetwork.openConnectionCheckRedirects(conn);
            Reader reader = new InputStreamReader(in, "utf-8")) {
            loadInternal(new WritableLineReader(reader), uri);
        } catch (SocketTimeoutException ste) {
            logger.log(Level.SEVERE, "Socket timeout exceeded:" + conn.getConnectTimeout(), ste);
        }
    }

    public HtmlRendererConfig getRendererConfig()
    {
        return renderCfg;
    }

    public void setRendererConfig(HtmlRendererConfig cfg)
    {
        renderCfg = cfg;
    }

    public UserAgentContext getUserAgentContext()
    {
        return uactxt;
    }

    public void setUserAgentContext(UserAgentContext ctxt)
    {
        uactxt = ctxt;
    }

    public void resetUserAgent()
    {
        ua = default_ua;
    }

    public String getUserAgent()
    {
        return ua;
    }

    public void setUserAgent(String ua_str)
    {
        ua = ua_str;
    }

    public void setPreferredHeight(int hx)
    {
        height = hx;
    }

    public void setPreferredWidth(int wx)
    {
        width = wx;
    }
}
