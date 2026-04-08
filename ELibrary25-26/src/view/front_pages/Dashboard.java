package view.front_pages;

import view.toolbar_tabs.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import view.fonts.Fonts;
import view.front_pages.FilePath;
import view.modal.librarian_profile.ViewUserProfileModal;

public class Dashboard extends JFrame {
	

    JPanel mainContent;
    OverviewTab ovTab;
    FacilitiesTab facTab;
    BooksTab bookTab;
    LNFTab lnfTab;
    PatronsTab patTab;
    IMSTab imsTab;
    
    public JLabel accountEmail;
    public JLabel accountName;
    public String username = "";
    public String password = "";

    public Dashboard() {
    	setUndecorated(true);
        
        setLayout(new BorderLayout());

        
        JPanel container = new JPanel();
        container.setBackground(Color.decode("#c4c4c4"));

        add(container, BorderLayout.CENTER);

        
        Toolbar tb = new Toolbar(this);
        add(tb, BorderLayout.SOUTH);

        
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(getWidth(), 60));
        header.setBackground(Color.decode("#5d1513"));
        header.setLayout(new BorderLayout(0, 10));

        
        JPanel leftHeader = new JPanel();

        leftHeader.setLayout(new BorderLayout());
        leftHeader.setOpaque(false);

        ImageIcon elibIcon = new ImageIcon(FilePath.image("elib_logo.png"));
        Image elibImage = elibIcon.getImage();
        Image scaledImageElib = elibImage.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
        elibIcon = new ImageIcon(scaledImageElib);
        JLabel elibLogo = new JLabel(elibIcon);
        elibLogo.setHorizontalAlignment(SwingConstants.CENTER);
        leftHeader.add(elibLogo, BorderLayout.WEST);

        header.add(leftHeader, BorderLayout.WEST);

        
        JPanel rightHeader = new JPanel();
        rightHeader.setPreferredSize(new Dimension(280, 60));
        rightHeader.setLayout(new BorderLayout());
        rightHeader.setOpaque(false);

        JPanel account = new JPanel();
        account.setLayout(new BorderLayout());
        account.setOpaque(false);

        ImageIcon profileIcon = new ImageIcon(FilePath.image("acct_icon.png"));
        Image profileImage = profileIcon.getImage();
        Image scaledImageProfile = profileImage.getScaledInstance(60, 30, Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(scaledImageProfile);
        JButton profileLogo = new JButton(profileIcon);
        profileLogo.setBackground(new Color(0, 0, 0, 0));
        profileLogo.setContentAreaFilled(false);
        profileLogo.setBorderPainted(false);
        profileLogo.setFocusPainted(false);
        profileLogo.setHorizontalAlignment(SwingConstants.CENTER);
        
     
        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new BoxLayout(dropdownPanel, BoxLayout.Y_AXIS));
        dropdownPanel.setBackground(Color.LIGHT_GRAY);
        
        Dimension btnSize = new Dimension(120, 30); 
        
        JButton profileBtn = new JButton("See details");
        profileBtn.setPreferredSize(btnSize);
        profileBtn.setMaximumSize(btnSize);
        profileBtn.setMinimumSize(btnSize);
        profileBtn.setFocusPainted(false);
        profileBtn.setContentAreaFilled(false);
        profileBtn.setBorderPainted(false);
        profileBtn.setOpaque(false);
        profileBtn.addActionListener(e -> {
        	Window parent = SwingUtilities.getWindowAncestor(this);
	        new ViewUserProfileModal(parent, username, password, this);
        });
        dropdownPanel.add(profileBtn);
        
        
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setPreferredSize(btnSize);
        logoutBtn.setMaximumSize(btnSize);
        logoutBtn.setMinimumSize(btnSize);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setOpaque(false);
        logoutBtn.addActionListener(e -> {
        	LoginWindow login = new LoginWindow(this);
        	login.setVisible(true);
        	setVisible(false);
        });
        dropdownPanel.add(logoutBtn);
        
        



        
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setLayout(new BorderLayout());
        popupMenu.add(dropdownPanel, BorderLayout.CENTER);

        
        profileLogo.addActionListener(e -> {
            popupMenu.show(profileLogo, 0, profileLogo.getHeight());
        });
        
        

        account.add(profileLogo, BorderLayout.EAST);

        
        JPanel accountInfo = new JPanel();

        Fonts poppins = new Fonts("Poppins", 10f);
        Font poppinStyle10 = poppins.getAppliedFont();


        accountName = new JLabel("");
        accountEmail = new JLabel("");
        accountName.setFont(poppinStyle10);
        accountEmail.setFont(poppinStyle10);
        accountName.setForeground(Color.WHITE);
        accountEmail.setForeground(Color.LIGHT_GRAY);

        accountInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbcAccounts = new GridBagConstraints();
        gbcAccounts.gridy = 0;

        accountInfo.add(accountName, gbcAccounts);
        gbcAccounts.gridy++;
        accountInfo.add(accountEmail, gbcAccounts);
        accountInfo.setOpaque(false);

        account.add(accountInfo, BorderLayout.WEST);

        rightHeader.add(account, BorderLayout.WEST);

        header.add(rightHeader, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        
        mainContent = new JPanel();
        mainContent.setBackground(Color.decode("#c4c4c4"));

        ovTab = new OverviewTab();

        facTab = new FacilitiesTab();
        facTab.setVisible(false);

        bookTab = new BooksTab();
        bookTab.setVisible(false);

        lnfTab = new LNFTab();
        lnfTab.setVisible(false);

        patTab = new PatronsTab();
        patTab.setVisible(false);

        imsTab = new IMSTab();
        imsTab.setVisible(false);  

        mainContent.add(ovTab);
        mainContent.add(facTab);
        mainContent.add(bookTab);
        mainContent.add(lnfTab);
        mainContent.add(patTab);
        mainContent.add(imsTab);

        add(mainContent, BorderLayout.CENTER);

        
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                
                container.requestFocusInWindow();
            }
        });
        setTitle("E-Library Management System");
        pack();

        if (getWidth() < 1120 || getHeight() < 600) {
            setSize(1120, 600);
        }

        setMinimumSize(new Dimension(1120, 600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        setResizable(false);
        

    }

    public void hideAll() {
        ovTab.setVisible(false);
        facTab.setVisible(false);
        bookTab.setVisible(false);
        lnfTab.setVisible(false);
        patTab.setVisible(false);
        imsTab.setVisible(false);
    }

    public void showOverviewTab() {
        hideAll();
        ovTab.setVisible(true);

    }

    public void showFacilitiesTab() {
        hideAll();
        facTab.setVisible(true);

    }

    public void showBooksTab() {
        hideAll();
        bookTab.setVisible(true);

    }

    public void showLNFTab() {
        hideAll();
        lnfTab.setVisible(true);
    }

    public void showPatronsTab() {
        hideAll();
        patTab.setVisible(true);
    }

    public void showIMSTab() {
        hideAll();
        imsTab.setVisible(true);

    }
}
