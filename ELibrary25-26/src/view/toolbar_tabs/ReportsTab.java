package view.toolbar_tabs;

import view.RoundedComponents.*;
//import view.book_panels.*;
//import view.facility_panels.*;
import view.fonts.Fonts;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import view.report_panels.*;
import view.report_panels.Modals.FacilityModal;


public class ReportsTab extends JPanel implements ActionListener {

    RoundedComboBox<String> dropdownFacility;
    JPanel slctdFaciCont;

    ReportLibAmphi amp = new ReportLibAmphi();
    ReportsDiscRoom1 dr1 = new ReportsDiscRoom1();
    ReportsDiscRoom2 dr2 = new ReportsDiscRoom2();
    ReportsLogin li = new ReportsLogin();
    ReportsISR isr = new ReportsISR();
    ReportsIPadArea ia = new ReportsIPadArea();
    ReportsLSect ls = new ReportsLSect();
    ReportsPWD pwd = new ReportsPWD();
    ReportsMH1 mh1 = new ReportsMH1();
    ReportsMH2 mh2 = new ReportsMH2();
    ReportsRelaxRoom rr = new ReportsRelaxRoom();
    ReportsSDZ sdz = new ReportsSDZ();
    ReportsSLR1 slr1 = new ReportsSLR1();
    ReportsSLR2 slr2 = new ReportsSLR2();
    ReportsTeleconRoom tcr = new ReportsTeleconRoom();
    ReportsBookLoan bl = new ReportsBookLoan();
    ReportsReturnBook rb = new ReportsReturnBook();
    ReportsBook b = new ReportsBook();
    ReportsIMS ims = new ReportsIMS();
    ReportsLoanIMS lims = new ReportsLoanIMS();
    ReportsPatrons p = new ReportsPatrons();
    
    JLabel tabLabel = new JLabel("");

    public ReportsTab(String tab) {
        int panelRadius = 20;
        setOpaque(false);

        JPanel facilitiesTab = new JPanel();
        facilitiesTab.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(40, 10, 10, 10);

        // container of the entire center widget
        RoundedPanel facContainer = new RoundedPanel(panelRadius);
        facContainer.setPreferredSize(new Dimension(1280, 560));
        facContainer.setLayout(new BorderLayout());
        facContainer.setBackground(Color.WHITE);

        setBackground(Color.decode("#c4c4c4"));
        // red part
        RoundedPanel slctReports = new RoundedPanel(panelRadius);
        slctReports.setLayout(new GridBagLayout());
        GridBagConstraints gbcRed = new GridBagConstraints();
        gbcRed.gridy = 1;
        gbcRed.gridx = 1;
        gbcRed.insets = new Insets(10, 30, 10, 10);
        slctReports.setBackground(Color.decode("#842b28"));
        slctReports.setPreferredSize(new Dimension(450, 80));

        JLabel selectReports = new JLabel("SELECT REPORTS");
        selectReports.setForeground(Color.WHITE);

        Fonts introRust = new Fonts("IntroRust", 12f);
        Font introRustStyle = introRust.getAppliedFont();
        selectReports.setFont(introRustStyle);

        slctReports.add(selectReports, gbcRed);

        // combobox
        JPanel cmbbxPanel = new JPanel();
        cmbbxPanel.setOpaque(false);
        
        if (tab.equals("facilities")) {
        	String[] facilityItems = {
                    "Amphitheater",
                    "Discussion Room 1",
                    "Discussion Room 2",
                    "Multipurpose Hall 1",
                    "Multipurpose Hall 2",
                    "Smart Learning Room 1",
                    "Smart Learning Room 2",
                    "Teleconferencing Room"
                };
        	dropdownFacility = new RoundedComboBox<>(facilityItems, panelRadius);
        } else if (tab.equals("patron")) {
        	String[] facilityItems = {
                    "Patrons"
                };
        	dropdownFacility = new RoundedComboBox<>(facilityItems, panelRadius);
        } else if (tab.equals("books")) {
        	String[] facilityItems = {
                    "Book Loans",
                    "Book Returns",
                    "Books",
                };
        	dropdownFacility = new RoundedComboBox<>(facilityItems, panelRadius);
        } else if (tab.equals("ims")) {
        	String[] facilityItems = {
                    "Instructional Media Services",
                    "Equipment Loans"
                };
        	dropdownFacility = new RoundedComboBox<>(facilityItems, panelRadius);
        }

        
        
        dropdownFacility.setPlaceholder("none");
        dropdownFacility.setPreferredSize(new Dimension(200, 30));
        dropdownFacility.addActionListener(this);

        gbcRed.gridx++;

        cmbbxPanel.add(dropdownFacility);
        slctReports.add(cmbbxPanel, gbcRed);

        // container of the red part
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setOpaque(false);
        topWrapper.add(slctReports, BorderLayout.WEST);

        // no red part
        tabLabel.setText("Amphitheather Schedule");
        tabLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        tabLabel.setForeground(Color.decode("#6d2321"));

        Fonts introRust22 = new Fonts("IntroRust", 22f);
        Font introRust22Style = introRust22.getAppliedFont();
        tabLabel.setFont(introRust22Style);

        topWrapper.add(tabLabel, BorderLayout.CENTER);

        // see archive button on the right
        RoundedButton seeArchiveBtn = new RoundedButton("See Archive", panelRadius);
        seeArchiveBtn.setPreferredSize(new Dimension(150, 40));
        seeArchiveBtn.setBackground(Color.decode("#842b28"));
        seeArchiveBtn.setForeground(Color.WHITE);
        seeArchiveBtn.setFont(introRustStyle);
        seeArchiveBtn.addActionListener(e -> {
        	Window w = SwingUtilities.getWindowAncestor(this);
			if (w instanceof JDialog)
				w.dispose();
        });
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 60));
        btnPanel.add(seeArchiveBtn);
        
        topWrapper.add(btnPanel, BorderLayout.EAST);

        facContainer.add(topWrapper, BorderLayout.NORTH);

        // selected facility items
        JPanel slctdPadding = new JPanel();
        slctdPadding.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        // slctdPadding.setOpaque(false);
        slctdFaciCont = new JPanel();
        slctdFaciCont.setPreferredSize(new Dimension(1200, 400));
        slctdFaciCont.setOpaque(false);
        slctdPadding.setOpaque(false);

        slctdPadding.add(slctdFaciCont, BorderLayout.CENTER);
        facContainer.add(slctdPadding, BorderLayout.CENTER);

        amp.setVisible(false);
        slctdFaciCont.add(amp);

        dr1.setVisible(false);
        slctdFaciCont.add(dr1);
//
        dr2.setVisible(false);
        slctdFaciCont.add(dr2);
//
        isr.setVisible(false);
        slctdFaciCont.add(isr);
//
        ia.setVisible(false);
        slctdFaciCont.add(ia);
        
        li.setVisible(false);
        slctdFaciCont.add(li);
        
        ls.setVisible(false);
        slctdFaciCont.add(ls);
        
        pwd.setVisible(false);
        slctdFaciCont.add(pwd);
//
        mh1.setVisible(false);
        slctdFaciCont.add(mh1);
//
        mh2.setVisible(false);
        slctdFaciCont.add(mh2);
//
        rr.setVisible(false);
        slctdFaciCont.add(rr);
//
        sdz.setVisible(false);
        slctdFaciCont.add(sdz);
//
        slr1.setVisible(false);
        slctdFaciCont.add(slr1);

        slr2.setVisible(false);
        slctdFaciCont.add(slr2);

        tcr.setVisible(false);
        slctdFaciCont.add(tcr);
        
        bl.setVisible(false);
        slctdFaciCont.add(bl);
        
        rb.setVisible(false);
        slctdFaciCont.add(rb);
        
        b.setVisible(false);
        slctdFaciCont.add(b);
        
        ims.setVisible(false);
        slctdFaciCont.add(ims);
        
        lims.setVisible(false);
        slctdFaciCont.add(lims);
        
        p.setVisible(false);
        slctdFaciCont.add(p);
        
        loadSelection();

        // =========================
        facilitiesTab.add(facContainer, gbc);
        facilitiesTab.setOpaque(false);
        add(facContainer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	loadSelection();
        
    }
    
    public void loadSelection() {
    	amp.setVisible(false);
        dr1.setVisible(false);
        dr2.setVisible(false);
        isr.setVisible(false);
        ia.setVisible(false);
        li.setVisible(false);
        ls.setVisible(false);
        pwd.setVisible(false);
        mh1.setVisible(false);
        mh2.setVisible(false);
        rr.setVisible(false);
        sdz.setVisible(false);
        slr1.setVisible(false);
        slr2.setVisible(false);
        tcr.setVisible(false);
        bl.setVisible(false);
        rb.setVisible(false);
        b.setVisible(false);
        ims.setVisible(false);
        lims.setVisible(false);
        p.setVisible(false);
               
        

        String selectedFac = (String) dropdownFacility.getSelectedItem();

        switch (selectedFac) {
            case "Amphitheater":
                tabLabel.setText("Amphitheater Reports");
                amp.setVisible(true);
                break;
            case "Discussion Room 1":
                tabLabel.setText("Discussion Room 1 Reports");
                dr1.setVisible(true);
                break;
            case "Discussion Room 2":
                tabLabel.setText("Discussion Room 2 Reports");
                dr2.setVisible(true);
                break;
            case "iPad Area":
                tabLabel.setText("iPad Area Reports");
                ia.setVisible(true);
                break;
            case "Individual Study Room":
                tabLabel.setText("Individual Study Room Reports");
                isr.setVisible(true);
                break;
            case "E-Library Login":
                tabLabel.setText("E-Library Login Reports");
                li.setVisible(true);
                break;
            case "Laptop Section":
                tabLabel.setText("Laptop Section Reports");
                ls.setVisible(true);
                break;
            case "PWD Area":
                tabLabel.setText("PWD Area Reports");
                pwd.setVisible(true);
                break;
            case "Multipurpose Hall 1":
                tabLabel.setText("Multipurpose Hall 1 Reports");
                mh1.setVisible(true);
                break;
            case "Multipurpose Hall 2":
                tabLabel.setText("Multipurpose Hall 2 Reports");
                mh2.setVisible(true);
                break;
            case "Relaxation Room":
                tabLabel.setText("Relaxation Room Reports");
                rr.setVisible(true);
                break;
            case "Smart Device Zone":
                tabLabel.setText("Smart Device Zone Reports");
                sdz.setVisible(true);
                break;
            case "Smart Learning Room 1":
                tabLabel.setText("Smart Learning Room 1 Reports");
                slr1.setVisible(true);
                break;
            case "Smart Learning Room 2":
                tabLabel.setText("Smart Learning Room 2 Reports");
                slr2.setVisible(true);
                break;
            case "Teleconferencing Room":
                tabLabel.setText("Teleconferencing Room Reports");
                tcr.setVisible(true);
                break;
            case "Book Loans":
                tabLabel.setText("Book Loans Reports");
                bl.setVisible(true);
                break;
            case "Book Returns":
                tabLabel.setText("Book Returns Reports");
                rb.setVisible(true);
                break;
            case "Books":
                tabLabel.setText("Books Reports");
                b.setVisible(true);
                break;
            case "Instructional Media Services":
                tabLabel.setText("Instructional Media Services Reports");
                ims.setVisible(true);
                break;
            case "Equipment Loans":
                tabLabel.setText("Equipment Loans Reports");
                lims.setVisible(true);
                break;
            case "Patrons":
                tabLabel.setText("Patrons Reports");
                p.setVisible(true);
                break;
            default:
                throw new AssertionError("Unknown facility selected: " + selectedFac);
        }
    }


    
}