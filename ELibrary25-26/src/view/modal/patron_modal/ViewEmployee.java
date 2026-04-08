package view.modal.patron_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.ArchiveController;
import controller.PatronController;

import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.toolbar_tabs.PatronsTab;
import view.fonts.Fonts;

public class ViewEmployee extends JPanel {

	static final Color MAROON = new Color(132, 43, 40);
	static final Color LIGHT_PINK = new Color(250, 236, 238);
	static final Color WHITE = Color.WHITE;
	static final Color DARK_TEXT = new Color(109, 35, 33);
	static final Color FIELD_BORDER = new Color(146, 76, 74);

	static final int PANEL_RADIUS = 20;
	static final int FIELD_RADIUS = 15;

	public JCheckBox adminCheck, libraryStaffCheck, facultyCheck;
	JPanel assignmentCodeRow, adminPositionRow, libPositionRow, facultyRankRow, collegeRow, campusRow;
	
	JLabel apLbl;
	JLabel acLbl;
	JLabel lpLbl;
	JLabel frLbl;
	public JLabel cLbl;
	JLabel ccLbl;
	
	PatronController comp;

	
	public RoundedTextField pidField = new RoundedTextField(19, FIELD_RADIUS);
	public RoundedTextField firstNameField = new RoundedTextField(19, FIELD_RADIUS);
	public RoundedTextField middleField = new RoundedTextField(5, FIELD_RADIUS);
	public RoundedTextField lastNameField = new RoundedTextField(19, FIELD_RADIUS);
	public RoundedTextField emailField = new RoundedTextField(19, FIELD_RADIUS);
	public RoundedTextField contactField = new RoundedTextField(19, FIELD_RADIUS);
	public RoundedTextField addressField = new RoundedTextField(19, FIELD_RADIUS);

	
	public RoundedTextField assignmentCodeField;
	public RoundedTextField adminPositionField;
	public RoundedTextField libPositionField;
	public RoundedTextField facultyRankField;
	public RoundedComboBox<String> collegeField;
	public RoundedComboBox<String> campusField;
	
	String[] colleges = {""};

	public ViewEmployee(String patronID, PatronsTab patTab) {

		setOpaque(false);
		setLayout(new BorderLayout());

		/* ================= FONTS ================= */
		Font introRust36 = new Fonts("IntroRust", 36f).getAppliedFont();
		Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
		Font poppins16 = new Fonts("Poppins", 16f).getAppliedFont();
		Font poppins10 = new Fonts("Poppins", 10f).getAppliedFont();

		Dimension fieldDim = new Dimension(pidField.getPreferredSize());

		
		pidField.setFont(poppins10);
		pidField.setBorderColor(FIELD_BORDER);
		pidField.setPreferredSize(fieldDim);
		pidField.setEnabled(false);
		firstNameField.setFont(poppins10);
		firstNameField.setBorderColor(FIELD_BORDER);
		firstNameField.setPreferredSize(fieldDim);
		middleField.setFont(poppins10);
		middleField.setBorderColor(FIELD_BORDER);
		middleField.setPreferredSize(fieldDim);
		lastNameField.setFont(poppins10);
		lastNameField.setBorderColor(FIELD_BORDER);
		lastNameField.setPreferredSize(fieldDim);
		emailField.setFont(poppins10);
		emailField.setBorderColor(FIELD_BORDER);
		emailField.setPreferredSize(fieldDim);
		contactField.setFont(poppins10);
		contactField.setBorderColor(FIELD_BORDER);
		contactField.setPreferredSize(fieldDim);
		addressField.setFont(poppins10);
		addressField.setBorderColor(FIELD_BORDER);
		addressField.setPreferredSize(fieldDim);

		/* ================= MODAL ================= */
		RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
		modal.setLayout(new BorderLayout());
		modal.setPreferredSize(new Dimension(900, 550));
		modal.setBackground(LIGHT_PINK);

		/* ================= HEADER ================= */
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(MAROON);
		header.setPreferredSize(new Dimension(900, 100));
		header.setBorder(new EmptyBorder(10, 0, 10, 10));

		JLabel logo = new JLabel(new ImageIcon(new ImageIcon(FilePath.image("elib_logo.png")).getImage()
				.getScaledInstance(110, 50, Image.SCALE_SMOOTH)));

		JLabel headerLabel = new JLabel("PATRONS");
		headerLabel.setFont(introRust36);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setBorder(new EmptyBorder(0, 260, 0, 0));

		header.add(logo, BorderLayout.WEST);
		header.add(headerLabel, BorderLayout.CENTER);

		/* ================= BODY ================= */
		JPanel body = new JPanel(new BorderLayout());
		body.setOpaque(false);
		body.setBorder(new EmptyBorder(10, 15, 10, 15));

		JLabel title = new JLabel("DETAILED VIEW FOR EMPLOYEE", SwingConstants.CENTER);
		title.setFont(introRust24);
		title.setForeground(DARK_TEXT);
		body.add(title, BorderLayout.NORTH);

		JPanel content = new JPanel(new GridLayout(1, 2, 20, 0));
		content.setOpaque(false);
		content.setBorder(new EmptyBorder(15, 5, 5, 5));

		/* ================= LEFT PANEL ================= */
		RoundedPanel left = new RoundedPanel(12);
		left.setBackground(WHITE);
		left.setBorder(new EmptyBorder(15, 15, 15, 15));
		left.setLayout(new GridBagLayout());

		GridBagConstraints l = new GridBagConstraints();
		l.insets = new Insets(6, 6, 6, 6);
		l.fill = GridBagConstraints.HORIZONTAL;
		l.weightx = 0.5;

		int row = 0;
		l.gridx = 0;
		l.gridy = row;
		JLabel pidLbl = new JLabel("Patron ID:");
		pidLbl.setFont(poppins16);
		pidLbl.setForeground(DARK_TEXT);
		left.add(pidLbl, l);
		l.gridx = 1;
		left.add(pidField, l);

		row++;
		l.gridx = 0;
		l.gridy = row;
		JLabel fnLbl = new JLabel("First Name:");
		fnLbl.setFont(poppins16);
		fnLbl.setForeground(DARK_TEXT);
		left.add(fnLbl, l);
		l.gridx = 1;
		left.add(firstNameField, l);

		row++;
		l.gridx = 0;
		l.gridy = row;
		JLabel miLbl = new JLabel("Middle Initial:");
		miLbl.setFont(poppins16);
		miLbl.setForeground(DARK_TEXT);
		left.add(miLbl, l);
		l.gridx = 1;
		left.add(middleField, l);

		row++;
		l.gridx = 0;
		l.gridy = row;
		JLabel lnLbl = new JLabel("Last Name:");
		lnLbl.setFont(poppins16);
		lnLbl.setForeground(DARK_TEXT);
		left.add(lnLbl, l);
		l.gridx = 1;
		left.add(lastNameField, l);

		row++;
		l.gridx = 0;
		l.gridy = row;
		JLabel emailLbl = new JLabel("Email Address:");
		emailLbl.setFont(poppins16);
		emailLbl.setForeground(DARK_TEXT);
		left.add(emailLbl, l);
		l.gridx = 1;
		left.add(emailField, l);

		row++;
		l.gridx = 0;
		l.gridy = row;
		JLabel contactLbl = new JLabel("Contact Number:");
		contactLbl.setFont(poppins16);
		contactLbl.setForeground(DARK_TEXT);
		left.add(contactLbl, l);
		l.gridx = 1;
		left.add(contactField, l);

		row++;
		l.gridx = 0;
		l.gridy = row;
		JLabel addressLbl = new JLabel("Home Address:");
		addressLbl.setFont(poppins16);
		addressLbl.setForeground(DARK_TEXT);
		left.add(addressLbl, l);
		l.gridx = 1;
		left.add(addressField, l);

		/* ================= RIGHT PANEL ================= */
		RoundedPanel right = new RoundedPanel(12);
        right.setLayout(new GridBagLayout());
        right.setBackground(WHITE);
        right.setBorder(new EmptyBorder(15, 15, 15, 15));

        GridBagConstraints r = new GridBagConstraints();
        r.insets = new Insets(6, 6, 6, 6);
        r.fill = GridBagConstraints.HORIZONTAL;
        r.weightx = 0.5;
        r.gridy = 0;

		int rRow = 0;

		r.gridy = rRow;
		JLabel typeLbl = new JLabel("Employee Type:");
		typeLbl.setFont(poppins16);
		typeLbl.setForeground(DARK_TEXT);
		right.add(typeLbl, r);

		r.gridx = 1;
		JPanel checkPanel = new JPanel();
		checkPanel.setOpaque(false);
		checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));

		
		adminCheck = new JCheckBox("Administrator");
		adminCheck.setFont(poppins10);
		adminCheck.setForeground(DARK_TEXT);
		adminCheck.setOpaque(false);
		checkPanel.add(adminCheck);

		libraryStaffCheck = new JCheckBox("Library Staff");
		libraryStaffCheck.setFont(poppins10);
		libraryStaffCheck.setForeground(DARK_TEXT);
		libraryStaffCheck.setOpaque(false);
		checkPanel.add(libraryStaffCheck);

		facultyCheck = new JCheckBox("Faculty");
		facultyCheck.setFont(poppins10);
		facultyCheck.setForeground(DARK_TEXT);
		facultyCheck.setOpaque(false);
		checkPanel.add(facultyCheck);

		right.add(checkPanel, r);

		
		assignmentCodeField = new RoundedTextField(19, FIELD_RADIUS);
		assignmentCodeField.setFont(poppins10);
		assignmentCodeField.setBorderColor(FIELD_BORDER);
		adminPositionField = new RoundedTextField(19, FIELD_RADIUS);
		adminPositionField.setFont(poppins10);
		adminPositionField.setBorderColor(FIELD_BORDER);
		libPositionField = new RoundedTextField(19, FIELD_RADIUS);
		libPositionField.setFont(poppins10);
		libPositionField.setBorderColor(FIELD_BORDER);
		facultyRankField = new RoundedTextField(19, FIELD_RADIUS);
		facultyRankField.setFont(poppins10);
		facultyRankField.setBorderColor(FIELD_BORDER);
		collegeField = new RoundedComboBox<>(colleges, FIELD_RADIUS);
		collegeField.setFont(poppins10);
		collegeField.setBorderColor(FIELD_BORDER);
		campusField = new RoundedComboBox<>(new String[]{"Main", "Bustos", "Hagonoy", "Meneses", "Sarmiento", "San Rafael"}, FIELD_RADIUS);
		campusField.setFont(poppins10);
		campusField.setBorderColor(FIELD_BORDER);
		
		campusField.setPreferredSize(new Dimension(210, 30));
		collegeField.setPreferredSize(new Dimension(210, 30));

		



		 acLbl = new JLabel("Assignment Code:");








		
		r.gridy++;
		acLbl.setFont(poppins16);
		acLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(acLbl, r);

        r.gridx = 1;
        right.add(assignmentCodeField, r);
        

		



		 apLbl = new JLabel("Admin Position:");






		
		r.gridy++;
		apLbl.setFont(poppins16);
		apLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(apLbl, r);

        r.gridx = 1;
        right.add(adminPositionField, r);

		



		 lpLbl = new JLabel("Library Position:");







		r.gridy++;
		lpLbl.setFont(poppins16);
		lpLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(lpLbl, r);

        r.gridx = 1;
        right.add(libPositionField, r);
        
		



		 frLbl = new JLabel("Faculty Rank:");







		r.gridy++;
		frLbl.setFont(poppins16);
		frLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(frLbl, r);

        r.gridx = 1;
        right.add(facultyRankField, r);
        
        
		



		 cLbl = new JLabel("College:");







		r.gridy++;
		cLbl.setFont(poppins16);
		cLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(cLbl, r);

        r.gridx = 1;
        right.add(collegeField, r);

		



		 ccLbl = new JLabel("Campus:");






		
		r.gridy++;
		ccLbl.setFont(poppins16);
		ccLbl.setForeground(DARK_TEXT);

        r.gridx = 0;
        right.add(ccLbl, r);

        r.gridx = 1;
        right.add(campusField, r);
        

		
		acLbl.setVisible(false);
		apLbl.setVisible(false);
		lpLbl.setVisible(false);
		frLbl.setVisible(false);
		cLbl.setVisible(false);
		ccLbl.setVisible(false);
		
		assignmentCodeField.setVisible(false);
		adminPositionField.setVisible(false);
		libPositionField.setVisible(false);
		facultyRankField.setVisible(false);
		collegeField.setVisible(false);
		campusField.setVisible(false);
		

		
		adminCheck.addActionListener(e -> toggleRows());
		libraryStaffCheck.addActionListener(e -> toggleRows());
		facultyCheck.addActionListener(e -> toggleRows());

		content.add(left);
		content.add(right);
		body.add(content, BorderLayout.CENTER);

		/* ================= FOOTER ================= */
		JPanel footer = new JPanel(new GridLayout(1, 3, 10, 0));
		footer.setPreferredSize(new Dimension(900, 60));
		footer.setBorder(new EmptyBorder(10, 20, 10, 20));
		footer.setOpaque(false);

		RoundedButton backBtn = new RoundedButton("< BACK", FIELD_RADIUS);
		backBtn.setFont(poppins10);
		backBtn.setForeground(MAROON);
		backBtn.setBorderColor(MAROON);
		backBtn.setBorderThickness(1);
		backBtn.addActionListener(e -> {
			Window w = SwingUtilities.getWindowAncestor(this);
			if (w instanceof JDialog)
				w.dispose();
		});
        
        RoundedButton archiveBtn = new RoundedButton("ARCHIVE PATRON", FIELD_RADIUS);
        archiveBtn.setFont(poppins10);
        archiveBtn.setForeground(MAROON);
        archiveBtn.setBorderColor(MAROON);
        archiveBtn.setBorderThickness(1);
        archiveBtn.addActionListener(e -> {
        	ArchiveController comp = new ArchiveController("Patrons", patronID);
        	
        	boolean isSuccessful = comp.setArchived();
        	if(isSuccessful) {
        		patTab.reloadData(patTab.searchQuery);
        		Window w = SwingUtilities.getWindowAncestor(this);
        		if (w != null) w.dispose();
        	}
        });

		RoundedButton submitBtn = new RoundedButton("SAVE DETAILS", FIELD_RADIUS);
		submitBtn.setFont(poppins10);
		submitBtn.setBackground(MAROON);
		submitBtn.setForeground(WHITE);
		submitBtn.addActionListener(e -> {

		    
		    String firstName = firstNameField.getRealText().trim();
		    String lastName = lastNameField.getRealText().trim();

		    
		    if (firstName.isEmpty() || lastName.isEmpty()) {
		        JOptionPane.showMessageDialog(
		            this,
		            "First Name and Last Name are required.",
		            "Missing Information",
		            JOptionPane.WARNING_MESSAGE
		        );
		        return;
		    }

		    
		    if (!adminCheck.isSelected() && !libraryStaffCheck.isSelected() && !facultyCheck.isSelected()) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Select at least one role.",
		            "Missing Role",
		            JOptionPane.WARNING_MESSAGE
		        );
		        return;
		    }

		    
		    String middle = middleField.getRealText().trim();
		    if (middle.isEmpty()) middle = null;

		    String email = emailField.getRealText().trim();
		    if (email.isEmpty()) email = null;

		    String contact = contactField.getRealText().trim();
		    if (contact.isEmpty()) contact = null;

		    String address = addressField.getRealText().trim();
		    if (address.isEmpty()) address = null;

		    String adminPos = adminPositionField.getRealText().trim();
		    if (adminPos.isEmpty()) adminPos = null;

		    String assignment = assignmentCodeField.getRealText().trim();
		    if (assignment.isEmpty()) assignment = null;

		    String libPos = libPositionField.getRealText().trim();
		    if (libPos.isEmpty()) libPos = null;

		    String facultyRank = facultyRankField.getRealText().trim();
		    if (facultyRank.isEmpty()) facultyRank = null;

		    
		    String[] employeeDetails = {
		        pidField.getRealText(),                        
		        firstName,                                     
		        middle,                                        
		        lastName,                                      
		        email,                                         
		        contact,                                       
		        address,                                       
		        campusField.getSelectedItem() != null
		            ? campusField.getSelectedItem().toString()
		            : null,                                    

		        String.valueOf(adminCheck.isSelected()),       
		        String.valueOf(libraryStaffCheck.isSelected()),
		        String.valueOf(facultyCheck.isSelected()),     

		        adminPos,                                      
		        assignment,                                    
		        libPos,                                        
		        facultyRank,                                   
		        collegeField.getSelectedItem() != null
		            ? collegeField.getSelectedItem().toString()
		            : null                                     
		    };

		    
		    boolean isSuccessful = comp.updatePatronEmployee(employeeDetails);

		    if (isSuccessful) {
		        patTab.reloadData(patTab.searchQuery);
		        Window w = SwingUtilities.getWindowAncestor(this);
		        if (w instanceof JDialog) w.dispose();
		    } else {
		    	JOptionPane.showMessageDialog(
			            this,
			            "Please fill in all fields for roles.",
			            "Missing Information",
			            JOptionPane.WARNING_MESSAGE
			        );
		    }
		});

		footer.add(backBtn);
		footer.add(archiveBtn);
		footer.add(submitBtn);

		comp = new PatronController(this, patronID);
		
		toggleRows();
		
		campusField.setSelectedIndex(0);
		
		comp.reloadCollege(this, campusField.getSelectedItem().toString());

		campusField.addActionListener(e -> {
			comp.reloadCollege(this, campusField.getSelectedItem().toString());
		});

		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);

		add(modal, BorderLayout.CENTER);
	}


	private void toggleRows() {
		boolean a = adminCheck.isSelected();
		boolean l = libraryStaffCheck.isSelected();
		boolean f = facultyCheck.isSelected();

		acLbl.setVisible(l);
		apLbl.setVisible(a);
		lpLbl.setVisible(l);
		frLbl.setVisible(f);
		cLbl.setVisible(f);
		ccLbl.setVisible(f);
		
		assignmentCodeField.setVisible(l);
		adminPositionField.setVisible(a);
		libPositionField.setVisible(l);
		facultyRankField.setVisible(f);
		collegeField.setVisible(f);
		campusField.setVisible(f);
		
		

		revalidate();
		repaint();
	}
	
	public void setColleges(String[] colleges) {
        if (colleges != null) {
            this.collegeField.setModel(new DefaultComboBoxModel<>(colleges));
            if (colleges.length > 0) this.collegeField.setSelectedIndex(0);
        }
    }
}