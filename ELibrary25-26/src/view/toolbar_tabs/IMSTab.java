package view.toolbar_tabs;

import view.RoundedComponents.*;
import view.book_panels.*;
import view.fonts.Fonts;
import view.front_pages.Dashboard;
import view.front_pages.FilePath;
import view.modal.ims_modal.AddIMSModal;
import view.modal.ims_modal.AddRequestItemModal;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import controller.MainFunctions;
import model.DAOs.ActiveRequest.DAOActiveRequest;

public class IMSTab extends JPanel {
	
	static String imgFilePath = FilePath.getImgFilePath();

    private int minColumnWidth = 35;
    private int minColumnHeight = 25;
    
    JPanel tableData;
    JPanel cardData;
    MainFunctions comp;
    
    JPanel cardContainer;
    MainFunctions cardComp;
    
    String searchQuery = "";

    public IMSTab() {

        Fonts poppins = new Fonts("Poppins", 12f);
        Fonts introRust = new Fonts("IntroRust", 22f);

        Font poppinsStyle = poppins.getAppliedFont();
        Font introRustStyle = introRust.getAppliedFont();

        int panelRadius = 20;
        JPanel patronsTab = new JPanel();
        patronsTab.setOpaque(false);
        patronsTab.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(40, 10, 10, 10);

        RoundedPanel imsContainer = new RoundedPanel(panelRadius);
        imsContainer.setPreferredSize(new Dimension(844, 560));
        imsContainer.setLayout(new BorderLayout());
        imsContainer.setBackground(Color.WHITE);

        // no red part
        JLabel imsLabel = new JLabel("Instructional Media Services");
        imsLabel.setForeground(Color.decode("#6d2321"));

        imsLabel.setFont(introRustStyle);
     // search + filter container
        JPanel searchFilter = new JPanel();
        searchFilter.setLayout(new BorderLayout());
        searchFilter.setPreferredSize(new Dimension(375, 30));
        searchFilter.setOpaque(false);
        // search item
        JPanel searchContainer = new JPanel();
        searchContainer.setOpaque(false);
        searchContainer.setPreferredSize(new Dimension(275, 20));

        RoundedTextField searchItem = new RoundedTextField(20, panelRadius);
        searchItem.setPlaceholder("Search item");
        searchItem.setBackground(Color.decode("#a6a6a6"));
        searchItem.setForeground(Color.WHITE);
        
        searchItem.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				searchQuery = searchItem.getText();
				reloadData(searchQuery);
			}

		});

        Fonts poppins12 = new Fonts("Poppins", 10f);
        Font poppins12Style = poppins12.getAppliedFont();
        searchItem.setFont(poppins12Style);

        searchContainer.add(searchItem);

     // sort by
        ImageIcon sortByIcon = new ImageIcon(imgFilePath + "sort_by_icon.png");
        Image sortByImage = sortByIcon.getImage();
        Image scaledImageSortBy = sortByImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
        sortByIcon = new ImageIcon(scaledImageSortBy);
        JButton sortByLogo = new JButton(sortByIcon);
        sortByLogo.setBackground(new Color(0, 0, 0, 0));
        sortByLogo.setContentAreaFilled(false);
        sortByLogo.setBorderPainted(false);
        sortByLogo.setFocusPainted(false);
        sortByLogo.setHorizontalAlignment(SwingConstants.CENTER);
        
        ImageIcon archiveIcon = new ImageIcon(imgFilePath + "archive.png");
        Image archiveImage = archiveIcon.getImage();
        Image scaledImageArchive = archiveImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
        archiveIcon = new ImageIcon(scaledImageArchive);
        JButton archiveLogo = new JButton(archiveIcon);
        archiveLogo.setBackground(new Color(0, 0, 0, 0));
        archiveLogo.setContentAreaFilled(false);
        archiveLogo.setBorderPainted(false);
        archiveLogo.setFocusPainted(false);
        archiveLogo.setHorizontalAlignment(SwingConstants.CENTER);
        
        ImageIcon reloadIcon = new ImageIcon(imgFilePath + "reload.png");
        Image reloadImage = reloadIcon.getImage();
        Image scaledImageReload = reloadImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
        reloadIcon = new ImageIcon(scaledImageReload);
        JButton reloadLogo = new JButton(reloadIcon);
        reloadLogo.setBackground(new Color(0, 0, 0, 0));
        reloadLogo.setContentAreaFilled(false);
        reloadLogo.setBorderPainted(false);
        reloadLogo.setFocusPainted(false);
        reloadLogo.setHorizontalAlignment(SwingConstants.CENTER);
        reloadLogo.addActionListener(e -> {
        	reloadData(searchQuery);
        	reloadCards();
        });
        
        JPanel iconsPanel = new JPanel();
        iconsPanel.setOpaque(false);
        iconsPanel.setLayout(new GridLayout(1, 3));
        iconsPanel.setPreferredSize(new Dimension(100,30));
        
        iconsPanel.add(sortByLogo);
        iconsPanel.add(archiveLogo);
        iconsPanel.add(reloadLogo);
        
        // add book button
        JPanel outerBtnCont = new JPanel();
        outerBtnCont.setLayout(new BorderLayout());
        outerBtnCont.setOpaque(false);
        
        Fonts poppinsBtn = new Fonts("Poppins", 10f);
        Font poppinsStyle10 = poppinsBtn.getAppliedFont();
        
        RoundedButton addLostItem = new RoundedButton("Add Item",panelRadius);
        addLostItem.setFont(poppinsStyle10);
        addLostItem.setBackground(Color.decode("#842b28"));
        addLostItem.setForeground(Color.WHITE);
        addLostItem.setPreferredSize(new Dimension(110, 30) );
        addLostItem.addActionListener(e -> {
        	Window parent = SwingUtilities.getWindowAncestor(this);
        	new AddIMSModal(parent);
        	
        });

        outerBtnCont.add(addLostItem, BorderLayout.EAST);
        outerBtnCont.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));

        // right side of the top wrapper
        JPanel topRight = new JPanel();
        topRight.setOpaque(false);
        topRight.setPreferredSize(new Dimension(390, 50));
        topRight.setLayout(new BorderLayout());
        
        iconsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
        
        searchFilter.add(searchContainer, BorderLayout.WEST);
        searchFilter.add(iconsPanel, BorderLayout.EAST);
        
//        searchFilter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
        
//        topRight.add(searchFilter, BorderLayout.NORTH);
//        topRight.add(outerBtnCont, BorderLayout.SOUTH);
        // container of the red part
        JPanel topWrapper = new JPanel();
        topWrapper.setLayout(new BorderLayout());
        topWrapper.setPreferredSize(new Dimension(1280, 90));
        topWrapper.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 40));
        topWrapper.setOpaque(false);
        
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setPreferredSize(new Dimension(1200, 30));
        northPanel.setLayout(new BorderLayout());
        northPanel.add(imsLabel, BorderLayout.WEST);
        northPanel.add(searchFilter, BorderLayout.EAST);
        
        JPanel southPanel = new JPanel();
        southPanel.setOpaque(false);
        southPanel.setPreferredSize(new Dimension(1200, 30));
        southPanel.setLayout(new BorderLayout());
        southPanel.add(outerBtnCont, BorderLayout.EAST);
        
        topWrapper.add(northPanel, BorderLayout.NORTH);
        topWrapper.add(southPanel, BorderLayout.SOUTH);

        imsContainer.add(topWrapper, BorderLayout.NORTH);

        // selected patrons items
        JPanel slctdPatrons = new JPanel();
        slctdPatrons.setOpaque(false);

// Create labels
        JPanel tableHeader = new JPanel();
        slctdPatrons.setLayout(new BorderLayout());
        tableHeader.setLayout(new GridBagLayout());
        JLabel serialNo = new JLabel("Serial No.");
        JLabel itemType = new JLabel("Item Type");
        JLabel itemLabel = new JLabel("Item Label");
        JLabel action = new JLabel("Action");

        serialNo.setFont(poppinsStyle);
        itemType.setFont(poppinsStyle);
        itemLabel.setFont(poppinsStyle);
        action.setFont(poppinsStyle);

        serialNo.setForeground(Color.decode("#737373"));
        itemType.setForeground(Color.decode("#737373"));
        itemLabel.setForeground(Color.decode("#737373"));
        action.setForeground(Color.decode("#737373"));

        JPanel serialNoPanel = new JPanel();
        JPanel itemTypePanel = new JPanel();
        JPanel itemLabelePanel = new JPanel();
        JPanel actionPanel = new JPanel();

        serialNoPanel.add(serialNo);
        itemTypePanel.add(itemType);
        itemLabelePanel.add(itemLabel);
        actionPanel.add(action);

        serialNoPanel.setOpaque(false);
        itemTypePanel.setOpaque(false);
        itemLabelePanel.setOpaque(false);
        actionPanel.setOpaque(false);

        serialNoPanel.setPreferredSize(new Dimension(minColumnWidth + 80, minColumnHeight));
        itemTypePanel.setPreferredSize(new Dimension(minColumnWidth * 5, minColumnHeight));
        itemLabelePanel.setPreferredSize(new Dimension(minColumnWidth * 7, minColumnHeight));
        actionPanel.setPreferredSize(new Dimension(minColumnWidth + 55, minColumnHeight));

        GridBagConstraints gbcPat = new GridBagConstraints();
        gbcPat.gridy = 0;
        gbcPat.gridx = 0;
        gbcPat.insets = new Insets(10, 10, 10, 10);

        gbcPat.gridy = 1;
        gbcPat.gridx = 1;
        tableHeader.add(serialNoPanel, gbcPat);

        gbcPat.gridx = 2;
        tableHeader.add(itemTypePanel, gbcPat);

        gbcPat.gridx = 3;
        tableHeader.add(itemLabelePanel, gbcPat);

        gbcPat.gridx = 4;
        tableHeader.add(actionPanel, gbcPat);

        tableHeader.setOpaque(false);
        tableHeader.setPreferredSize(new Dimension(844, 50));

        slctdPatrons.add(tableHeader, BorderLayout.NORTH);

        // actual table data ============================
        tableData = new JPanel();
        tableData.setOpaque(false);
        tableData.setLayout(new BorderLayout());

        reloadData(searchQuery);

        JScrollPane scrollBar = new JScrollPane(tableData);
        scrollBar.setOpaque(false);
        scrollBar.setPreferredSize(new Dimension(1240, 400));
        scrollBar.setBorder(BorderFactory.createEmptyBorder(0, 65, 40, 65));
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        scrollBar.getVerticalScrollBar().setUI(new RoundedScrollbar());

        slctdPatrons.add(scrollBar, BorderLayout.CENTER);

        imsContainer.add(slctdPatrons, BorderLayout.CENTER);

        patronsTab.add(imsContainer, gbc);

        //card container ===========================================================
        cardContainer = new RoundedPanel(panelRadius);
    	cardContainer.setPreferredSize(new Dimension(417, 560));
    	cardContainer.setLayout(new BorderLayout());

        // label
        JLabel requestLabel = new JLabel("Active Request");
        requestLabel.setForeground(Color.decode("#6d2321"));

        requestLabel.setFont(introRustStyle);
        JPanel reqLabelCont = new JPanel();
        reqLabelCont.setOpaque(false);
        reqLabelCont.setLayout(new BorderLayout());
        reqLabelCont.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 40));
        reqLabelCont.add(requestLabel, BorderLayout.WEST);
        cardContainer.add(reqLabelCont, BorderLayout.NORTH);
        
        cardComp = new MainFunctions(this, "cards", null);
        cardComp.setOpaque(false);
        
        
     // actual table cards  ============================
        cardData = new JPanel();
        cardData.setOpaque(false);
        cardData.setLayout(new BorderLayout());

        reloadCards();

        
        
        
     // card panel
        JScrollPane cardScrollbar = new JScrollPane(cardData);
        cardScrollbar.setOpaque(false);
        cardScrollbar.getViewport().setOpaque(false);
        cardScrollbar.setPreferredSize(new Dimension(500, 420));
        cardScrollbar.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));
        cardScrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardScrollbar.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        cardScrollbar.getVerticalScrollBar().setUI(new RoundedScrollbar());
        
        cardContainer.add(cardScrollbar, BorderLayout.CENTER);
        
        
        
        

        // add request card button
        RoundedButton addCard = new RoundedButton("Request Item", panelRadius);
        addCard.setBackground(Color.decode("#5d1513"));
        addCard.setForeground(Color.WHITE);
        addCard.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        addCard.setPreferredSize(new Dimension(140, 40));
        addCard.addActionListener(e -> {
        	Window parent = SwingUtilities.getWindowAncestor(this);
        	new AddRequestItemModal(parent);
        });
        
        
        JPanel bottomBtn = new JPanel();
        bottomBtn.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
        bottomBtn.setLayout(new BorderLayout());
        bottomBtn.setOpaque(false);
        bottomBtn.add(addCard, BorderLayout.WEST);

        cardContainer.add(bottomBtn, BorderLayout.SOUTH);

        Fonts btnPoppins = new Fonts("Poppins", 14f);
        Font btnPoppinsStyle = btnPoppins.getAppliedFont();
        
        addCard.setFont(btnPoppinsStyle);

        
        gbc.gridx = 2;
        patronsTab.add(cardContainer, gbc);

        //============================================== 
        setBackground(Color.decode("#c4c4c4"));
        add(patronsTab);

    }

    public int getMinColumnWidth() {
        return minColumnWidth;
    }

    public int getMinColumnHeight() {
        return minColumnHeight;
    }
    
    public void reloadData(String searchQuery) {
        tableData.removeAll();
        comp = new MainFunctions(this, "rows", searchQuery);
        tableData.add(comp, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
    
    public void reloadCards() {
    	cardData.removeAll();
        cardComp = new MainFunctions(this, "cards", null);
        cardData.add(cardComp, BorderLayout.CENTER);
        
        revalidate();
        repaint();
    }

}
