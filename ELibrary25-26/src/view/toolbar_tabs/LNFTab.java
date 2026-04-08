package view.toolbar_tabs;

import view.RoundedComponents.*;
import view.fonts.Fonts;
import view.front_pages.FilePath;
import view.modal.archive_modal.ArchivedLostAndFound;
import view.modal.archive_modal.ArchivedLostAndFoundModal;
import view.modal.archive_modal.ArchivedPatronModal;
import view.modal.lost_and_found_modal.AddLostAndFoundModal;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import controller.MainFunctions;

public class LNFTab extends JPanel {


    JLabel col1 = new JLabel("");
    JLabel col2 = new JLabel("");
    JLabel col3 = new JLabel("");
    JLabel col4 = new JLabel("");
    JLabel col5 = new JLabel("");
    JLabel col6 = new JLabel("");
    
    JPanel tableData;
    MainFunctions comp;
    
    int minColumnWidth = 59;
    int minColumnHeight = 25;

    int columnCount = 0;
    
    public String searchQuery = "";

    public LNFTab() {
        int panelRadius = 20;
        JPanel lnfTab = new JPanel();
        lnfTab.setOpaque(false);
        lnfTab.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(40, 10, 10, 10);

        RoundedPanel lnfContainer = new RoundedPanel(panelRadius);
        lnfContainer.setPreferredSize(new Dimension(1280, 560));
        lnfContainer.setLayout(new BorderLayout());
        lnfContainer.setBackground(Color.WHITE);

        JLabel tabLabel = new JLabel("LOST & FOUND ITEMS");
        tabLabel.setForeground(Color.decode("#6d2321"));

        Fonts introRust22 = new Fonts("IntroRust", 22f);
        Font introRust22Style = introRust22.getAppliedFont();
        tabLabel.setFont(introRust22Style);

        JPanel searchFilter = new JPanel();
        searchFilter.setLayout(new BorderLayout());
        searchFilter.setPreferredSize(new Dimension(375, 30));
        searchFilter.setOpaque(false);
        JPanel searchContainer = new JPanel();
        searchContainer.setOpaque(false);
        searchContainer.setPreferredSize(new Dimension(275, 20));

        RoundedTextField searchItem = new RoundedTextField(20, panelRadius);
        searchItem.setPlaceholder("Search item");
        searchItem.setBackground(Color.decode("#a6a6a6"));
        searchItem.setForeground(Color.WHITE);

        Fonts poppins12 = new Fonts("Poppins", 10f);
        Font poppins12Style = poppins12.getAppliedFont();
        searchItem.setFont(poppins12Style);
        searchItem.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				searchQuery = searchItem.getText();
				reloadData(searchQuery);
			}

		});

        searchContainer.add(searchItem);
        
        ImageIcon archiveIcon = new ImageIcon(FilePath.image("archive.png"));
        Image archiveImage = archiveIcon.getImage();
        Image scaledImageArchive = archiveImage.getScaledInstance(30, 15, Image.SCALE_SMOOTH);
        archiveIcon = new ImageIcon(scaledImageArchive);
        JButton archiveLogo = new JButton(archiveIcon);
        archiveLogo.setBackground(new Color(0, 0, 0, 0));
        archiveLogo.setContentAreaFilled(false);
        archiveLogo.setBorderPainted(false);
        archiveLogo.setFocusPainted(false);
        archiveLogo.setHorizontalAlignment(SwingConstants.CENTER);
        archiveLogo.addActionListener(e -> {
			Window parent = SwingUtilities.getWindowAncestor(this);
			new ArchivedLostAndFoundModal(parent);
			reloadData(searchQuery);
		});
        
        ImageIcon reloadIcon = new ImageIcon(FilePath.image("reload.png"));
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
        });
        
        JPanel iconsPanel = new JPanel();
        iconsPanel.setOpaque(false);
        iconsPanel.setLayout(new GridLayout(1, 3));
        iconsPanel.setPreferredSize(new Dimension(100,30));
        
        iconsPanel.add(archiveLogo);
        iconsPanel.add(reloadLogo);
        
        JPanel outerBtnCont = new JPanel();
        outerBtnCont.setLayout(new BorderLayout());
        outerBtnCont.setOpaque(false);
        
        Fonts poppinsBtn = new Fonts("Poppins", 10f);
        Font poppinsStyle10 = poppinsBtn.getAppliedFont();
        
        RoundedButton addLostItem = new RoundedButton("Add Lost Item",panelRadius);
        addLostItem.setFont(poppinsStyle10);
        addLostItem.setBackground(Color.decode("#842b28"));
        addLostItem.setForeground(Color.WHITE);
        addLostItem.setPreferredSize(new Dimension(110, 30) );
        addLostItem.addActionListener(e -> {
        	Window parent = SwingUtilities.getWindowAncestor(this);
        	new AddLostAndFoundModal(parent);
        	reloadData(searchQuery);
        });

        outerBtnCont.add(addLostItem, BorderLayout.EAST);
        outerBtnCont.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));

        JPanel topRight = new JPanel();
        topRight.setOpaque(false);
        topRight.setPreferredSize(new Dimension(390, 50));
        topRight.setLayout(new BorderLayout());
        
        iconsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
        
        searchFilter.add(searchContainer, BorderLayout.WEST);
        searchFilter.add(iconsPanel, BorderLayout.EAST);

        JPanel topWrapper = new JPanel();
        topWrapper.setLayout(new BorderLayout());
        topWrapper.setPreferredSize(new Dimension(1280, 90));
        topWrapper.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 40));
        topWrapper.setOpaque(false);
        
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setPreferredSize(new Dimension(1200, 30));
        northPanel.setLayout(new BorderLayout());
        northPanel.add(tabLabel, BorderLayout.WEST);
        northPanel.add(searchFilter, BorderLayout.EAST);
        
        JPanel southPanel = new JPanel();
        southPanel.setOpaque(false);
        southPanel.setPreferredSize(new Dimension(1200, 30));
        southPanel.setLayout(new BorderLayout());
        southPanel.add(outerBtnCont, BorderLayout.EAST);
        
        topWrapper.add(northPanel, BorderLayout.NORTH);
        topWrapper.add(southPanel, BorderLayout.SOUTH);

        lnfContainer.add(topWrapper, BorderLayout.NORTH);

        JPanel slctdLNF = new JPanel();
        slctdLNF.setOpaque(false);

        JPanel tableHeader = new JPanel();
        slctdLNF.setLayout(new BorderLayout());
        tableHeader.setLayout(new GridBagLayout());
        JLabel itemLost = new JLabel("Item Lost");
        JLabel nameOfOwner = new JLabel("Name of Owner");
        JLabel description = new JLabel("Description");
        JLabel lostOnFloor = new JLabel("Lost on Floor");
        JLabel status = new JLabel("Status");
        JLabel lastSeen = new JLabel("Last Seen");
        JLabel action = new JLabel("Action");

        Fonts poopinsBold12 = new Fonts("Poppins", 12f);
        Font poopinsBold12Style = poopinsBold12.getAppliedFont();

        itemLost.setFont(poopinsBold12Style);
        nameOfOwner.setFont(poopinsBold12Style);
        description.setFont(poopinsBold12Style);
        lostOnFloor.setFont(poopinsBold12Style);
        status.setFont(poopinsBold12Style);
        lastSeen.setFont(poopinsBold12Style);
        action.setFont(poopinsBold12Style);

        itemLost.setForeground(Color.decode("#737373"));
        nameOfOwner.setForeground(Color.decode("#737373"));
        description.setForeground(Color.decode("#737373"));
        lostOnFloor.setForeground(Color.decode("#737373"));
        status.setForeground(Color.decode("#737373"));
        lastSeen.setForeground(Color.decode("#737373"));
        action.setForeground(Color.decode("#737373"));

        JPanel itemLostPanel = new JPanel();
        JPanel nameOfOwnerPanel = new JPanel();
        JPanel descriptionPanel = new JPanel();
        JPanel lostOnFloorPanel = new JPanel();
        JPanel statusPanel = new JPanel();
        JPanel lastSeenPanel = new JPanel();
        JPanel actionPanel = new JPanel();

        itemLostPanel.add(itemLost);
        nameOfOwnerPanel.add(nameOfOwner);
        descriptionPanel.add(description);
        lostOnFloorPanel.add(lostOnFloor);
        statusPanel.add(status);
        lastSeenPanel.add(lastSeen);
        actionPanel.add(action);

        itemLostPanel.setOpaque(false);
        nameOfOwnerPanel.setOpaque(false);
        descriptionPanel.setOpaque(false);
        lostOnFloorPanel.setOpaque(false);
        statusPanel.setOpaque(false);
        lastSeenPanel.setOpaque(false);
        actionPanel.setOpaque(false);



        itemLostPanel.setPreferredSize(new Dimension(minColumnWidth * 2, minColumnHeight));
        nameOfOwnerPanel.setPreferredSize(new Dimension(minColumnWidth * 4, minColumnHeight));
        descriptionPanel.setPreferredSize(new Dimension((minColumnWidth * 5) + 19, minColumnHeight));
        lostOnFloorPanel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
        statusPanel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
        lastSeenPanel.setPreferredSize(new Dimension(minColumnWidth, minColumnHeight));
        actionPanel.setPreferredSize(new Dimension(minColumnWidth + 50, minColumnHeight));

        GridBagConstraints gbcLNF = new GridBagConstraints();
        gbcLNF.gridy = 0;
        gbcLNF.gridx = 0;
        gbcLNF.insets = new Insets(10, 10, 10, 10);

        gbcLNF.gridy = 1;
        gbcLNF.gridx = 1;
        tableHeader.add(itemLostPanel, gbcLNF);

        gbcLNF.gridx = 2;
        tableHeader.add(nameOfOwnerPanel, gbcLNF);

        gbcLNF.gridx = 3;
        tableHeader.add(descriptionPanel, gbcLNF);

        gbcLNF.gridx = 4;
        tableHeader.add(lostOnFloorPanel, gbcLNF);

        gbcLNF.gridx = 5;
        tableHeader.add(statusPanel, gbcLNF);

        gbcLNF.gridx = 6;
        tableHeader.add(lastSeenPanel, gbcLNF);

        gbcLNF.gridx = 7;
        tableHeader.add(actionPanel, gbcLNF);

        tableHeader.setOpaque(false);
        tableHeader.setPreferredSize(new Dimension(1240, 50));

        slctdLNF.add(tableHeader, BorderLayout.NORTH);

        tableData = new JPanel();
        tableData.setOpaque(false);

        reloadData(searchQuery);
        

        tableData.setLayout(new BorderLayout());
        tableData.add(comp, BorderLayout.NORTH);
        
        JScrollPane scrollBar = new JScrollPane(tableData);
        scrollBar.setOpaque(false);
        scrollBar.setPreferredSize(new Dimension(1240, 400));
        scrollBar.setBorder(BorderFactory.createEmptyBorder(0, 65, 40, 65));
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        scrollBar.getVerticalScrollBar().setUI(new RoundedScrollbar());

        slctdLNF.add(scrollBar, BorderLayout.CENTER);

        lnfContainer.add(slctdLNF, BorderLayout.CENTER);

        lnfTab.add(lnfContainer, gbc);
        setBackground(Color.decode("#c4c4c4"));
        add(lnfTab);

    }
    
    public int getMinColumnWidth() {
        return minColumnWidth;
    }

    public int getMinColumnHeight() {
        return minColumnHeight;
    }
    
    public void reloadData(String searchQuery) {
        tableData.removeAll();
        comp = new MainFunctions(this, searchQuery);
        tableData.add(comp, BorderLayout.NORTH);
        
        revalidate();
        repaint();
    }
    

}
