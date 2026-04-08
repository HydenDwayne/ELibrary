package view.front_pages;

import view.RoundedComponents.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import view.fonts.*;

public class Toolbar extends JPanel implements ActionListener {
	

    private Dashboard dashboard;

    RoundedButton homeBtn;
    RoundedButton facilityBtn;
    RoundedButton booksBtn;
    RoundedButton lnfBtn;
    RoundedButton patronsBtn;
    RoundedButton imsBtn;

    Color inactiveBtn = Color.decode("#842b28");
    Color activeBtn = Color.decode("#6d2321");

    public Toolbar(Dashboard dashboard) {
        int panelRadius = 20;
        this.dashboard = dashboard;
        
        RoundedPanel toolbar = new RoundedPanel(50);
        toolbar.setPreferredSize(new Dimension(800, 100));
        toolbar.setBackground(Color.decode("#842b28"));
        toolbar.setLayout(new GridBagLayout());
        GridBagConstraints gbcToolbar = new GridBagConstraints();
        gbcToolbar.gridy = 1;
        gbcToolbar.gridx = 1;
        gbcToolbar.insets = new Insets(0, 10, 0, 10);

        Fonts poppins = new Fonts("Poppins", 14f);
        Font poppinsStyle14 = poppins.getAppliedFont();
        		

        ImageIcon homeIcon = new ImageIcon(FilePath.image("home.png"));
        Image homeImage = homeIcon.getImage();
        Image scaledImageHome = homeImage.getScaledInstance(60, 30, Image.SCALE_SMOOTH);
        homeIcon = new ImageIcon(scaledImageHome);
        homeBtn = new RoundedButton("Overview", homeIcon, panelRadius);
        homeBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        homeBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        homeBtn.setForeground(Color.WHITE);
        homeBtn.setBackground(activeBtn);
        homeBtn.setFont(poppinsStyle14);
        homeBtn.addActionListener(this);
        toolbar.add(homeBtn, gbcToolbar);

        gbcToolbar.gridx++;
        ImageIcon facilityIcon = new ImageIcon(FilePath.image("facilities.png"));
        Image facilityImage = facilityIcon.getImage();
        Image scaledImageFacility = facilityImage.getScaledInstance(60, 30, Image.SCALE_SMOOTH);
        facilityIcon = new ImageIcon(scaledImageFacility);
        facilityBtn = new RoundedButton("Facilities", facilityIcon, panelRadius);
        facilityBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        facilityBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        facilityBtn.setForeground(Color.WHITE);
        facilityBtn.setBackground(inactiveBtn);
        facilityBtn.setFont(poppinsStyle14);
        facilityBtn.addActionListener(this);
        toolbar.add(facilityBtn, gbcToolbar);

        gbcToolbar.gridx++;
        ImageIcon booksIcon = new ImageIcon(FilePath.image("books.png"));
        Image booksImage = booksIcon.getImage();
        Image scaledImageBooks = booksImage.getScaledInstance(60, 30, Image.SCALE_SMOOTH);
        booksIcon = new ImageIcon(scaledImageBooks);
        booksBtn = new RoundedButton("Books", booksIcon, panelRadius);
        booksBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        booksBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        booksBtn.setForeground(Color.WHITE);
        booksBtn.setBackground(inactiveBtn);
        booksBtn.setFont(poppinsStyle14);
        booksBtn.addActionListener(this);
        toolbar.add(booksBtn, gbcToolbar);

        gbcToolbar.gridx++;
        ImageIcon lnfIcon = new ImageIcon(FilePath.image("lost&found.png"));
        Image lnfImage = lnfIcon.getImage();
        Image scaledImageLnf = lnfImage.getScaledInstance(60, 30, Image.SCALE_SMOOTH);
        lnfIcon = new ImageIcon(scaledImageLnf);
        lnfBtn = new RoundedButton("Lost & Found", lnfIcon, panelRadius);
        lnfBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        lnfBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        lnfBtn.setForeground(Color.WHITE);
        lnfBtn.setBackground(inactiveBtn);
        lnfBtn.setFont(poppinsStyle14);
        lnfBtn.addActionListener(this);
        toolbar.add(lnfBtn, gbcToolbar);

        gbcToolbar.gridx++;
        ImageIcon patronsIcon = new ImageIcon(FilePath.image("patrons.png"));
        Image patronsImage = patronsIcon.getImage();
        Image scaledImagePatrons = patronsImage.getScaledInstance(60, 30, Image.SCALE_SMOOTH);
        patronsIcon = new ImageIcon(scaledImagePatrons);
        patronsBtn = new RoundedButton("Patrons", patronsIcon, panelRadius);
        patronsBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        patronsBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        patronsBtn.setForeground(Color.WHITE);
        patronsBtn.setBackground(inactiveBtn);
        patronsBtn.setFont(poppinsStyle14);
        patronsBtn.addActionListener(this);
        toolbar.add(patronsBtn, gbcToolbar);

        gbcToolbar.gridx++;
        ImageIcon imsIcon = new ImageIcon(FilePath.image( "ims.png"));
        Image imsImage = imsIcon.getImage();
        Image scaledImageIms = imsImage.getScaledInstance(60, 30, Image.SCALE_SMOOTH);
        imsIcon = new ImageIcon(scaledImageIms);
        imsBtn = new RoundedButton("IMS", imsIcon, panelRadius);
        imsBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        imsBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        imsBtn.setForeground(Color.WHITE);
        imsBtn.setBackground(inactiveBtn);
        imsBtn.setFont(poppinsStyle14);
        imsBtn.addActionListener(this);
        toolbar.add(imsBtn, gbcToolbar);
        setBackground(Color.decode("#c4c4c4"));
        add(toolbar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dashboard.ovTab.setVisible(false);
        dashboard.facTab.setVisible(false);
        dashboard.bookTab.setVisible(false);
        dashboard.lnfTab.setVisible(false);
        dashboard.patTab.setVisible(false);
        dashboard.imsTab.setVisible(false);
        homeBtn.setBackground(inactiveBtn);
        facilityBtn.setBackground(inactiveBtn);
        booksBtn.setBackground(inactiveBtn);
        lnfBtn.setBackground(inactiveBtn);
        patronsBtn.setBackground(inactiveBtn);
        imsBtn.setBackground(inactiveBtn);
        switch (e.getActionCommand()) {
            case "Overview":
                dashboard.ovTab.setVisible(true);
                homeBtn.setBackground(activeBtn);
                break;
            case "Facilities":
                dashboard.facTab.setVisible(true);
                facilityBtn.setBackground(activeBtn);

                break;
            case "Books":
                dashboard.bookTab.setVisible(true);
                booksBtn.setBackground(activeBtn);
                break;
            case "Lost & Found":
                dashboard.lnfTab.setVisible(true);
                lnfBtn.setBackground(activeBtn);
                break;
            case "Patrons":
                dashboard.patTab.setVisible(true);
                patronsBtn.setBackground(activeBtn);
                break;
            case "IMS":
                dashboard.imsTab.setVisible(true);
                imsBtn.setBackground(activeBtn);
                break;
            default:
                System.out.println("error");
                break;
        }
    }
}
