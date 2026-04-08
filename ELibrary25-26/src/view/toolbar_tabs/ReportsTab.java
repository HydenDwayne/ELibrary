package view.toolbar_tabs;

import view.RoundedComponents.*;
import view.fonts.Fonts;
import view.modal.archive_modal.ArchivedModal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.ArchiveController;
import view.report_panels.*;
import view.report_panels.Modals.FacilityModal;


public class ReportsTab extends JPanel implements ActionListener {

    RoundedComboBox<String> dropdownFacility;
    JPanel slctdFaciCont;

    private ReportLibAmphi amp;
    private ReportsDiscRoom1 dr1;
    private ReportsDiscRoom2 dr2;
    private ReportsLogin li;
    private ReportsISR isr;
    private ReportsIPadArea ia;
    private ReportsLSect ls;
    private ReportsPWD pwd;
    private ReportsMH1 mh1;
    private ReportsMH2 mh2;
    private ReportsRelaxRoom rr;
    private ReportsSDZ sdz;
    private ReportsSLR1 slr1;
    private ReportsSLR2 slr2;
    private ReportsTeleconRoom tcr;
    private ReportsBookLoan bl;
    private ReportsReturnBook rb;
    private ReportsBook b;
    private ReportsIMS ims;
    private ReportsLoanIMS lims;
    private ReportsPatrons p;
    private ReportsOverdueBooks ob;
    private ReportsBorrowedBooks bb;
    
    JLabel tabLabel = new JLabel("");
    
    String reportPage = "";
    JTable table;
    String tableName;
    
    JPanel southPanel;
    
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

        RoundedPanel facContainer = new RoundedPanel(panelRadius);
        facContainer.setPreferredSize(new Dimension(1280, 560));
        facContainer.setLayout(new BorderLayout());
        facContainer.setBackground(Color.WHITE);

        setBackground(Color.decode("#c4c4c4"));
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
                    "Teleconferencing Room",
                    "iPad Area",
                    "Laptop Section",
                    "Individual Study Room",
                    "Relaxation Room",
                    "Smart Device Zone",
                    "E-Library Login",
                    "PWD Area"
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
                    "Overdue Books",
                    "Borrowed Books"
                };
        	dropdownFacility = new RoundedComboBox<>(facilityItems, panelRadius);
        } else if (tab.equals("ims")) {
        	String[] facilityItems = {
                    "Instructional Media Services",
                    "Equipment Loans"
                };
        	dropdownFacility = new RoundedComboBox<>(facilityItems, panelRadius);
        }

        
        dropdownFacility.setSelectedIndex(0);
        dropdownFacility.setPlaceholder("none");
        dropdownFacility.setPreferredSize(new Dimension(200, 30));
        dropdownFacility.addActionListener(this);

        gbcRed.gridx++;

        cmbbxPanel.add(dropdownFacility);
        slctReports.add(cmbbxPanel, gbcRed);

        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setOpaque(false);
        topWrapper.add(slctReports, BorderLayout.WEST);

        tabLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        tabLabel.setForeground(Color.decode("#6d2321"));

        Fonts introRust22 = new Fonts("IntroRust", 22f);
        Font introRust22Style = introRust22.getAppliedFont();
        tabLabel.setFont(introRust22Style);

        topWrapper.add(tabLabel, BorderLayout.CENTER);

        RoundedButton seeArchiveBtn = new RoundedButton("See Archived Records", panelRadius);
        seeArchiveBtn.setPreferredSize(new Dimension(200, 40));
        seeArchiveBtn.setBackground(Color.decode("#842b28"));
        seeArchiveBtn.setForeground(Color.WHITE);
        seeArchiveBtn.setFont(introRustStyle);
        seeArchiveBtn.addActionListener(e -> {
        	Window parent = SwingUtilities.getWindowAncestor(this);
			new ArchivedModal(parent, reportPage);
			int selectedIdx = dropdownFacility.getSelectedIndex();
    		reloadData();
    		dropdownFacility.setSelectedIndex(selectedIdx);
        });
        
        RoundedButton closeBtn = new RoundedButton("X", panelRadius);
        closeBtn.setPreferredSize(new Dimension(50,50));
        closeBtn.setBorderColor(Color.decode("#842b28"));
        closeBtn.setBorderThickness(1);
        
        closeBtn.setForeground(Color.decode("#842b28"));
        closeBtn.setFont(introRustStyle);
        closeBtn.addActionListener(e -> {
        	Window w = SwingUtilities.getWindowAncestor(this);
			if (w instanceof JDialog)
				w.dispose();
        });
        
        JPanel btnPanelWrapper = new JPanel();
        btnPanelWrapper.setOpaque(false);
        btnPanelWrapper.setPreferredSize(new Dimension(320, 40));
        
        JPanel btnPanel = new JPanel(new BorderLayout());
        btnPanel.setPreferredSize(new Dimension(320, 50));
        btnPanel.setOpaque(false);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 60));
        btnPanel.add(seeArchiveBtn, BorderLayout.WEST);
        btnPanel.add(closeBtn, BorderLayout.EAST);
        
        btnPanelWrapper.add(btnPanel);
        
        topWrapper.add(btnPanelWrapper, BorderLayout.EAST);

        facContainer.add(topWrapper, BorderLayout.NORTH);

        JPanel slctdPadding = new JPanel();
        slctdPadding.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        slctdFaciCont = new JPanel();
        slctdFaciCont.setPreferredSize(new Dimension(1200, 400));
        slctdFaciCont.setOpaque(false);
        slctdPadding.setOpaque(false);

        slctdPadding.add(slctdFaciCont, BorderLayout.CENTER);
        facContainer.add(slctdPadding, BorderLayout.CENTER);
        
        reloadData();

        amp.setVisible(false);
        slctdFaciCont.add(amp);

        dr1.setVisible(false);
        slctdFaciCont.add(dr1);

        dr2.setVisible(false);
        slctdFaciCont.add(dr2);

        isr.setVisible(false);
        slctdFaciCont.add(isr);

        ia.setVisible(false);
        slctdFaciCont.add(ia);
        
        li.setVisible(false);
        slctdFaciCont.add(li);
        
        ls.setVisible(false);
        slctdFaciCont.add(ls);
        
        pwd.setVisible(false);
        slctdFaciCont.add(pwd);

        mh1.setVisible(false);
        slctdFaciCont.add(mh1);

        mh2.setVisible(false);
        slctdFaciCont.add(mh2);

        rr.setVisible(false);
        slctdFaciCont.add(rr);

        sdz.setVisible(false);
        slctdFaciCont.add(sdz);

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
        
        ob.setVisible(false);
        slctdFaciCont.add(ob);
        
        bb.setVisible(false);
        slctdFaciCont.add(bb);
        
        p.setVisible(false);
        slctdFaciCont.add(p);
        
        
        
        
        southPanel = new JPanel();
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 60));
        southPanel.setVisible(false);
        southPanel.setOpaque(false);
        southPanel.setLayout(new BorderLayout());
        
        loadSelection();
        
        RoundedButton archiveBtn = new RoundedButton("Archive Selected", panelRadius);
        archiveBtn.setPreferredSize(new Dimension(200, 40));
        archiveBtn.setBackground(Color.decode("#842b28"));
        archiveBtn.setForeground(Color.WHITE);
        archiveBtn.setFont(introRustStyle);
        archiveBtn.addActionListener(e -> {
        	
    	    if(getSelectedRow(table) != null) {
    	    	ArchiveController ac = new ArchiveController(tableName, getSelectedRow(table));
            	
            	boolean isSuccessful = ac.setArchived();
            	if(isSuccessful) {
            		int selectedIdx = dropdownFacility.getSelectedIndex();
            		reloadData();
            		dropdownFacility.setSelectedIndex(selectedIdx);
            	}
    	    } else {
    	    	JOptionPane.showMessageDialog(null, "Select a row first!");
    	    }
        });
        
        southPanel.add(archiveBtn, BorderLayout.EAST);
        facContainer.add(southPanel, BorderLayout.SOUTH);

        
        facilitiesTab.add(facContainer, gbc);
        facilitiesTab.setOpaque(false);
        add(facContainer);
    }
    
    public String getSelectedRow(JTable table) {
    	int selectedRow = table.getSelectedRow();
    	if (tableName.equals("Books_Loan")) {
    		if (selectedRow != -1) {
        	    Object value = table.getValueAt(selectedRow, 0); 
        	    String firstColumnValue = value.toString();
        	    return firstColumnValue;
        	}
    	} else {
    		if (selectedRow != -1) {
        	    Object value = table.getValueAt(selectedRow, 1); 
        	    String firstColumnValue = value.toString();
        	    return firstColumnValue;
        	}
		}
    	return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	loadSelection();
        
    }
    
    public void loadSelection() {
    	
    	
    	reloadData();
        String selectedFac = (String) dropdownFacility.getSelectedItem();

        
        switch (selectedFac) {
            case "Amphitheater":
                tabLabel.setText("Amphitheater Reports");
                reportPage = "LibAmphi";
                amp.setVisible(true);
                break;
            case "Discussion Room 1":
                tabLabel.setText("Discussion Room 1 Reports");
                reportPage = "DiscRoom1";
                dr1.setVisible(true);
                break;
            case "Discussion Room 2":
                tabLabel.setText("Discussion Room 2 Reports");
                reportPage = "DiscRoom2";
                dr2.setVisible(true);
                break;
            case "iPad Area":
                tabLabel.setText("iPad Area Reports");
                tableName = "FacilityLogin";
                table = ia.table;
                southPanel.setVisible(true);
                reportPage = "iPAD";
                ia.setVisible(true);
                break;
            case "Individual Study Room":
                tabLabel.setText("Individual Study Room Reports");
                tableName = "FacilityLogin";
                table = isr.table;
                southPanel.setVisible(true);
                reportPage = "ISR";
                isr.setVisible(true);
                break;
            case "E-Library Login":
                tabLabel.setText("E-Library Login Reports");
                tableName = "FacilityLogin";
                table = li.table;
                southPanel.setVisible(true);
                reportPage = "LOGIN";
                li.setVisible(true);
                break;
            case "Laptop Section":
                tabLabel.setText("Laptop Section Reports");
                tableName = "FacilityLogin";
                table = ls.table;
                southPanel.setVisible(true);
                reportPage = "LSect";
                ls.setVisible(true);
                break;
            case "PWD Area":
                tabLabel.setText("PWD Area Reports");
                tableName = "FacilityLogin";
                table = pwd.table;
                southPanel.setVisible(true);
                reportPage = "PWD";
                pwd.setVisible(true);
                break;
            case "Multipurpose Hall 1":
                tabLabel.setText("Multipurpose Hall 1 Reports");
                reportPage = "MH1";
                mh1.setVisible(true);
                break;
            case "Multipurpose Hall 2":
                tabLabel.setText("Multipurpose Hall 2 Reports");
                reportPage = "MH2";
                mh2.setVisible(true);
                break;
            case "Relaxation Room":
                tabLabel.setText("Relaxation Room Reports");
                tableName = "FacilityLogin";
                table = rr.table;
                southPanel.setVisible(true);
                reportPage = "RelaxRoom";
                rr.setVisible(true);
                break;
            case "Smart Device Zone":
                tabLabel.setText("Smart Device Zone Reports");
                tableName = "FacilityLogin";
                table = sdz.table;
                southPanel.setVisible(true);
                reportPage = "SDZ";
                sdz.setVisible(true);
                break;
            case "Smart Learning Room 1":
                tabLabel.setText("Smart Learning Room 1 Reports");
                reportPage = "SLR1";
                slr1.setVisible(true);
                break;
            case "Smart Learning Room 2":
                tabLabel.setText("Smart Learning Room 2 Reports");
                reportPage = "SLR2";
                slr2.setVisible(true);
                break;
            case "Teleconferencing Room":
                tabLabel.setText("Teleconferencing Room Reports");
                reportPage = "TeleconRoom";
                tcr.setVisible(true);
                break;
            case "Book Loans":
                tabLabel.setText("Book Loans Reports");
                tableName = "Books_Loan";
                table = bl.table;
                southPanel.setVisible(true);
                reportPage = "BookLoan";
                bl.setVisible(true);
                break;
            case "Book Returns":
                tabLabel.setText("Book Returns Reports");
                tableName = "ReturnBook";
                table = rb.table;
                southPanel.setVisible(true);
                reportPage = "ReturnBook";
                rb.setVisible(true);
                break;
            case "Books":
                tabLabel.setText("Books Reports");
                reportPage = "Books";
                b.setVisible(true);
                break;
            case "Instructional Media Services":
                tabLabel.setText("Instructional Media Services Reports");
                reportPage = "IMS";
                ims.setVisible(true);
                break;
            case "Equipment Loans":
                tabLabel.setText("Equipment Loans Reports");
                reportPage = "EL";
                lims.setVisible(true);
                break;
            case "Overdue Books":
                tabLabel.setText("Overdue Books Reports");
                tableName = "Books_Loan";
                table = ob.table;
                southPanel.setVisible(true);
                reportPage = "OB";
                ob.setVisible(true);
                break;
            case "Borrowed Books":
                tabLabel.setText("Borrowed Books Reports");
                tableName = "Books_Loan";
                table = bb.table;
                southPanel.setVisible(true);
                reportPage = "BB";
                bb.setVisible(true);
                break;
            case "Patrons":
                tabLabel.setText("Patrons Reports");
                reportPage = "Patrons";
                p.setVisible(true);
                break;
            default:
                throw new AssertionError("Unknown facility selected: " + selectedFac);
        }
    }

    public void reloadData() {
    	slctdFaciCont.removeAll();
    	
        amp = new ReportLibAmphi();
        dr1 = new ReportsDiscRoom1();
        dr2 = new ReportsDiscRoom2();
        li = new ReportsLogin();
        isr = new ReportsISR();
        ia = new ReportsIPadArea();
        ls = new ReportsLSect();
        pwd = new ReportsPWD();
        mh1 = new ReportsMH1();
        mh2 = new ReportsMH2();
        rr = new ReportsRelaxRoom();
        sdz = new ReportsSDZ();
        slr1 = new ReportsSLR1();
        slr2 = new ReportsSLR2();
        tcr = new ReportsTeleconRoom();
        bl = new ReportsBookLoan();
        rb = new ReportsReturnBook();
        b = new ReportsBook();
        ims = new ReportsIMS();
        lims = new ReportsLoanIMS();
        p = new ReportsPatrons();
        ob = new ReportsOverdueBooks();
        bb = new ReportsBorrowedBooks();
        
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
        
        ob.setVisible(false);
        bb.setVisible(false);

        JComponent[] reports = {
            amp, dr1, dr2, li, isr, ia, ls, pwd, mh1, mh2, rr, sdz,
            slr1, slr2, tcr, bl, rb, b, ims, lims, p, ob, bb
        };

        for (JComponent report : reports) {
        	report.setVisible(false);
            slctdFaciCont.add(report);
            report.revalidate();
            report.repaint();
        }
    }
    
}