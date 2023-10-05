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

package org.loboevolution.menu.bookmarks;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import org.loboevolution.common.Strings;
import org.loboevolution.component.BrowserFrame;
import org.loboevolution.component.BrowserPanel;
import org.loboevolution.component.ITabbedPane;
import org.loboevolution.component.NavigatorFrame;
import org.loboevolution.html.dom.domimpl.HTMLDocumentImpl;
import org.loboevolution.gui.HtmlPanel;
import org.loboevolution.http.NavigationManager;
import org.loboevolution.info.BookmarkInfo;
import org.loboevolution.store.BookmarksStore;
import org.loboevolution.config.DesktopConfig;
import org.loboevolution.store.TabStore;

import com.jtattoo.plaf.lobo.LoboButton;
import com.jtattoo.plaf.lobo.LoboLabel;
import com.jtattoo.plaf.lobo.LoboLookAndFeel;
import com.jtattoo.plaf.lobo.LoboPanel;
import com.jtattoo.plaf.lobo.LoboSeparator;
import com.jtattoo.plaf.lobo.LoboTextField;

/**
 * <p>ShowBookmarksWindow class.</p>
 */
public class ShowBookmarksWindow extends JFrame implements LoboLookAndFeel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The desc. */
	private LoboTextField desc;
	
	/** The title Edit Txt Fld. */
	private LoboTextField titleEditTxtFld;
	
	/** The url Edit Txt Fld. */
	private LoboTextField urlEditTxtFld;
	
	/** The tmp Url. */
	private String tmpUrl;

	/**
	 * <p>Constructor for ShowBookmarksWindow.</p>
	 *
	 * @param frame a {@link org.loboevolution.component.BrowserFrame} object.
	 * @param num a {@link java.lang.Integer} object.
	 */
	public ShowBookmarksWindow(final BrowserFrame frame, final Integer num) {
		createAndShowGUI(frame, num);
	}
	
	private void createAndShowGUI(final BrowserFrame frame, final Integer num) {
		final Container contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(background());
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 410);
		final ImageIcon ico = new ImageIcon(DesktopConfig.getResourceFile("bookmark.png",DesktopConfig.PATH_IMAGE));
		setIconImage(ico.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		
		final LoboLabel label_6 = new LoboLabel("");
		label_6.setOpaque(true);
		label_6.setBounds(0, 11, 792, 8);
		contentPane.add(label_6);

		final LoboLabel label_1 = new LoboLabel("");
		label_1.setOpaque(true);
		label_1.setBounds(650, 11, 403, 9);
		contentPane.add(label_1);
		
		listBookmarks(frame, contentPane);
		saveBookmarks(frame, contentPane);

	}

	private void saveBookmarks(final BrowserFrame frame, final Container contentPane) {
		final LoboPanel panel2 = new LoboPanel();
		panel2.setLayout(null);
		panel2.setBounds(550, 40, 403, 436);
		contentPane.add(panel2);

		final LoboSeparator separator_2 = new LoboSeparator();
		separator_2.setBounds(0, 58, 792, 12);
		panel2.add(separator_2);
		
		final LoboLabel editBookmark = new LoboLabel("Bookmark");
		editBookmark.setHorizontalAlignment(SwingConstants.CENTER);
		editBookmark.setFont(new Font("Tahoma", Font.BOLD, 17));
		editBookmark.setBounds(70, 18, 167, 22);
		panel2.add(editBookmark);

		final LoboLabel lblDescription = new LoboLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescription.setBounds(12, 70, 282, 16);
		panel2.add(lblDescription);

		desc = new LoboTextField();
		desc.setFont(new Font("Tahoma", Font.BOLD, 12));
		desc.setColumns(10);
		desc.setBorder(null);
		desc.setBounds(12, 98, 350, 16);
		panel2.add(desc);

		final LoboSeparator separator_9 = new LoboSeparator();
		separator_9.setBounds(12, 115, 380, 12);
		panel2.add(separator_9);

		final LoboLabel titleLbl = new LoboLabel("Title");
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		titleLbl.setBounds(12, 139, 282, 16);
		panel2.add(titleLbl);

		titleEditTxtFld = new LoboTextField();
		titleEditTxtFld.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleEditTxtFld.setColumns(10);
		titleEditTxtFld.setBorder(null);
		titleEditTxtFld.setBounds(12, 167, 350, 16);
		panel2.add(titleEditTxtFld);

		final LoboSeparator separator_10 = new LoboSeparator();
		separator_10.setBounds(12, 184, 380, 12);
		panel2.add(separator_10);
		
		final LoboLabel url_1 = new LoboLabel("Url");
		url_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		url_1.setBounds(12, 200, 282, 16);
		panel2.add(url_1);

		urlEditTxtFld = new LoboTextField();
		urlEditTxtFld.setFont(new Font("Tahoma", Font.BOLD, 12));
		urlEditTxtFld.setColumns(10);
		urlEditTxtFld.setBorder(null);
		urlEditTxtFld.setBounds(12, 230, 275, 16);
		panel2.add(urlEditTxtFld);
		
		final LoboSeparator separator_11 = new LoboSeparator();
		separator_11.setBounds(12, 250, 380, 12);
		panel2.add(separator_11);

		final LoboButton okButton = new LoboButton();
		okButton.setText("Save");
		okButton.setActionCommand("okButton");
		okButton.setBounds(12, 280, 200, 40);
		okButton.addActionListener(e -> {
			final BookmarksStore book = new BookmarksStore();
			book.deleteBookmark(tmpUrl);
			book.insertBookmark(getinfo());
			JOptionPane.showMessageDialog(contentPane, "Edit Ok!");
			setVisible(false);
			dispose();
			new ShowBookmarksWindow(frame, 100).setVisible(true);
		});
		panel2.add(okButton);

		final LoboButton closeButton = new LoboButton();
		closeButton.setText("Close");
		closeButton.setActionCommand("closeButton");
		closeButton.addActionListener(e -> {
			setVisible(false);
			dispose();
		});
		closeButton.setBounds(224, 280, 138, 40);
		panel2.add(closeButton);
		
	}

	private void listBookmarks(final BrowserFrame frame, final Container contentPane) {
		final LoboSeparator separator_7 = new LoboSeparator();
		separator_7.setBounds(0, 98, 550, 12);
		contentPane.add(separator_7);
		
		final LoboPanel panel = new LoboPanel();
		panel.setLayout(null);
		panel.setBounds(0, 40, 650, 50);
		contentPane.add(panel);
		
		final LoboLabel description = new LoboLabel("Description");
		description.setFont(new Font("Tahoma", Font.BOLD, 14));
		description.setBounds(30, 18, 211, 16);
		panel.add(description);

		final LoboLabel title = new LoboLabel("Title");
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		title.setBounds(200, 18, 150, 17);
		panel.add(title);

		final LoboLabel action = new LoboLabel("Action");
		action.setFont(new Font("Tahoma", Font.BOLD, 14));
		action.setBounds(400, 18, 160, 17);
		panel.add(action);
		
		final LoboPanel panelGeneralViewAllItems = new LoboPanel();
		panelGeneralViewAllItems.setBounds(10, 100, 530, 313);
		final JScrollPane spViewallItems = new JScrollPane();
		spViewallItems.setBorder(null);
		spViewallItems.setViewportView(createItemPanel(frame));
		panelGeneralViewAllItems.setLayout(new BorderLayout());
		panelGeneralViewAllItems.add(spViewallItems, BorderLayout.CENTER);
		contentPane.add(panelGeneralViewAllItems);
	}
	
	private LoboPanel createItemPanel(final BrowserFrame frame) {

		final LoboPanel panel_3 = new LoboPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(0, 191, 750, 70);

		int debutCpDesc = 15;
		int debutCpTitle = 15;
		int debutCpUrl = 15;
		int debutCpSeparator = 58;
		final int incrementNouvelleLigne = 67;

		try {
			final BookmarksStore history = new BookmarksStore();
			final List<BookmarkInfo> allEntries = history.getBookmarks(100);
			for (final BookmarkInfo binfo : allEntries) {
				final String url = binfo.getUrl();

				final LoboTextField description = new LoboTextField();
				description.setText(binfo.getDescription());
				description.setToolTipText(binfo.getDescription());
				description.setFont(new Font("Tahoma", Font.BOLD, 12));
				description.setEditable(false);
				description.setColumns(10);
				description.setBorder(null);
				description.setBounds(12, debutCpDesc, 180, 22);
				panel_3.add(description);

				final LoboTextField title = new LoboTextField();
				title.setText(binfo.getTitle());
				title.setToolTipText(binfo.getTitle());
				title.setFont(new Font("Tahoma", Font.BOLD, 12));
				title.setEditable(false);
				title.setColumns(10);
				title.setBorder(null);
				title.setBounds(190, debutCpTitle, 150, 22);
				panel_3.add(title);

				final LoboButton edit = new LoboButton();
				edit.setText("Edit");
				edit.setActionCommand("okButton");
				edit.setBounds(370, debutCpUrl, 40, 40);
				edit.addActionListener(e -> {
					desc.setText(description.getText());
					titleEditTxtFld.setText(title.getText());
					urlEditTxtFld.setText(url);
					tmpUrl = url;

				});
				panel_3.add(edit);

				final LoboButton delete = new LoboButton();
				delete.setText("Delete");
				delete.setActionCommand("okButton");
				delete.setBounds(410, debutCpUrl, 50, 40);
				delete.addActionListener(e -> {
					final BookmarksStore book = new BookmarksStore();
					book.deleteBookmark(binfo.getUrl());
					JOptionPane.showMessageDialog(panel_3, "Delete Ok!");
					setVisible(false);
					dispose();
					new ShowBookmarksWindow(frame, 100).setVisible(true);

				});
				panel_3.add(delete);
				
				final LoboButton go = new LoboButton();
				go.setText("Go");
				go.setActionCommand("goButton");
				go.setBounds(460, debutCpUrl, 40, 40);
				go.addActionListener(e -> {
					final BrowserPanel panel = frame.getPanel();
					final int indexPanel = panel.getTabbedPane().getIndex() + 1;
					final ITabbedPane tabbedPane = panel.getTabbedPane();
					final HtmlPanel hpanel = NavigatorFrame.createHtmlPanel(panel, url);
					final HTMLDocumentImpl nodeImpl = (HTMLDocumentImpl) hpanel.getRootNode();
					final String htmlTitle = Strings.isNotBlank(nodeImpl.getTitle()) ? nodeImpl.getTitle() : "New Tab";
					tabbedPane.setComponentPopupMenu(panel);
					tabbedPane.insertTab(htmlTitle, null, hpanel, htmlTitle, indexPanel);
					tabbedPane.setSelectedIndex(indexPanel);
					TabStore.insertTab(indexPanel, url, htmlTitle);
					NavigationManager.insertHistory(url, htmlTitle, indexPanel);
				});
				panel_3.add(go);
				
				debutCpDesc = debutCpDesc + incrementNouvelleLigne;
				debutCpTitle = debutCpTitle + incrementNouvelleLigne;
				debutCpUrl = debutCpUrl + incrementNouvelleLigne;
				debutCpSeparator = debutCpSeparator + incrementNouvelleLigne;
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

		panel_3.setPreferredSize(new Dimension(0, 1000));
		panel_3.revalidate();
		panel_3.repaint();

		return panel_3;
	}
	
	private BookmarkInfo getinfo() {
		final BookmarkInfo binfo = new BookmarkInfo();
		binfo.setUrl(urlEditTxtFld.getText());
		binfo.setTitle(titleEditTxtFld.getText());
		binfo.setDescription(desc.getText());
		return binfo;
	}
}
