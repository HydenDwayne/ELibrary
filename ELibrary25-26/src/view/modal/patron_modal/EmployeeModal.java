package view.modal.patron_modal;

import javax.swing.*;

import controller.PatronController;
import controller.PatronRegistrationController;
import view.RoundedComponents.*;
import view.fonts.Fonts;
import view.front_pages.*;

import java.awt.*;
import java.awt.event.*;


public class EmployeeModal extends JPanel implements ActionListener {

    // Role checkboxes
	public JCheckBox adminCheck;
	public JCheckBox staffCheck;
	public JCheckBox facultyCheck;

    // Labels
//    JLabel row1Label; // Employee ID
    JLabel row2Label; // Date Hired
    JLabel row3Label; // Roles
    JLabel row4Label; // Assignment Code
    JLabel row5Label; // Position
    JLabel row6Label; // Faculty Rank
//    JLabel row7Label; // Campus
    JLabel row8Label; // College

    // Fields
//    public RoundedTextField row1Field;
    public RoundedTextField row2Field;
    public RoundedTextField row4Field;
    public RoundedTextField row5Field;
    public RoundedTextField row6Field;
//    RoundedComboBox<String> row7Field;
    public RoundedComboBox<String> row8Field;

    // Wrappers (same pattern as StudentModal)
    JPanel row4LabelWrapper;
    JPanel row5LabelWrapper;
    JPanel row6LabelWrapper;
//    JPanel row7LabelWrapper;
    JPanel row8LabelWrapper;

    RoundedPanel modal;
    RegisterPatron rp;
    GeneralModal genModal;
    
    private String campus;
    
    PatronController controller;


    public void setCampusCode(String campus) {
	    this.campus = campus;

	    if (controller == null) {
	        controller = new PatronController(this, campus, genModal);
	    } else {
	        controller.setCampusCode(campus);
	        controller.reloadEmployeeColleges();
	    }
	    updateVisibility();
	    resizeModal();
	}


    public EmployeeModal(RegisterPatron rp, GeneralModal genModal) {
    	this.genModal = genModal;
    	this.rp = rp;
        setOpaque(false);

        modal = new RoundedPanel(20);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(500, 600));
        modal.setBackground(Color.decode("#faecee"));

     // Header
     		JPanel header = new JPanel();
     		header.setBackground(Color.decode("#842b28"));
     		header.setPreferredSize(new Dimension(500, 100));
     		header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
     		header.setLayout(new BorderLayout());

     		ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
     		Image image = icon.getImage();
     		Image scaledImage = image.getScaledInstance(110, 50, Image.SCALE_SMOOTH);
     		icon = new ImageIcon(scaledImage);
     		JLabel elibLogo = new JLabel(icon);
     		elibLogo.setHorizontalAlignment(SwingConstants.CENTER);
     		header.add(elibLogo, BorderLayout.CENTER);
     		
     		modal.add(header, BorderLayout.NORTH);

        // ── Body ───────────────────────────────────────────────
        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Fonts introRust = new Fonts("IntroRust", 24f);
        JLabel bodyTitle = new JLabel("PATRON REGISTRATION", SwingConstants.CENTER);
        bodyTitle.setFont(introRust.getAppliedFont());
        bodyTitle.setForeground(Color.decode("#6d2321"));
        body.add(bodyTitle, BorderLayout.NORTH);

     // ── Inner body (form rows) ───────────────────────────────────────────────
        JPanel innerBody = new JPanel();
        innerBody.setOpaque(false);
        innerBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        innerBody.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = -1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Step header
        Fonts stepPoppins = new Fonts("PoppinsBold", 16f);
        Font stepPoppinsStyle = stepPoppins.getAppliedFont();

        JLabel step = new JLabel("Step 2: Employee Information");
        step.setFont(stepPoppinsStyle);
        step.setForeground(Color.decode("#842b28"));

        JPanel stepWrapper = new JPanel(new BorderLayout());
        stepWrapper.setOpaque(false);
        stepWrapper.setPreferredSize(new Dimension(430, 30));
        stepWrapper.add(step, BorderLayout.WEST);

        gbc.gridwidth = 2;
        gbc.gridy++;
        innerBody.add(stepWrapper, gbc);

        // Separator under step header
        gbc.gridy++;
        JSeparator sep1 = new JSeparator();
        sep1.setPreferredSize(new Dimension(1, 1));
        sep1.setBackground(Color.decode("#842b28"));
        sep1.setOpaque(true);
        innerBody.add(sep1, gbc);

        gbc.gridwidth = 1;

//        // ── Row 1: Employee ID ───────────────────────────────────────────────
//        gbc.gridy++;
//        gbc.gridx = 0;
//        JPanel row1LabelWrapper = new JPanel(new BorderLayout());
//        row1LabelWrapper.setOpaque(false);
//        row1LabelWrapper.setPreferredSize(new Dimension(210, 30));
//        row1Label = new JLabel("Employee ID");
//        row1LabelWrapper.add(row1Label, BorderLayout.WEST);
//        innerBody.add(row1LabelWrapper, gbc);
//
//        gbc.gridx = 1;
//        row1Field = new RoundedTextField(20, 15);
//        innerBody.add(row1Field, gbc);

        // ── Row 2: Date Hired ───────────────────────────────────────────────
        gbc.gridy++;
        gbc.gridx = 0;
        JPanel row2LabelWrapper = new JPanel(new BorderLayout());
        row2LabelWrapper.setOpaque(false);
        row2LabelWrapper.setPreferredSize(new Dimension(210, 30));
        row2Label = new JLabel("Date Hired");
        row2LabelWrapper.add(row2Label, BorderLayout.WEST);
        innerBody.add(row2LabelWrapper, gbc);

        gbc.gridx = 1;
        row2Field = new RoundedTextField(20, 15);
        row2Field.setPlaceholder("YYYY");
        innerBody.add(row2Field, gbc);

        // ── Row 3: Roles ─────────────────────────────────────────────────────
        gbc.gridy++;
        gbc.gridx = 0;
        JPanel row3LabelWrapper = new JPanel(new BorderLayout());
        row3LabelWrapper.setOpaque(false);
        row3LabelWrapper.setPreferredSize(new Dimension(210, 30));
        row3Label = new JLabel("Select Role(s)");
        row3LabelWrapper.add(row3Label, BorderLayout.WEST);
        innerBody.add(row3LabelWrapper, gbc);

        gbc.gridx = 1;
        JPanel rolePanel = new JPanel(new GridLayout(3, 1));
        rolePanel.setOpaque(false);

        adminCheck = new JCheckBox("Administrator");
        staffCheck = new JCheckBox("Library Staff");
        facultyCheck = new JCheckBox("Faculty");

        adminCheck.setOpaque(false);
        staffCheck.setOpaque(false);
        facultyCheck.setOpaque(false);

        rolePanel.add(adminCheck);
        rolePanel.add(staffCheck);
        rolePanel.add(facultyCheck);

        innerBody.add(rolePanel, gbc);

        // ── DYNAMIC ROWS (IDENTICAL STYLE TO STUDENT MODAL) ──────────────────

        // Assignment Code
        gbc.gridy++;
        gbc.gridx = 0;
        row4LabelWrapper = new JPanel(new BorderLayout());
        row4LabelWrapper.setOpaque(false);
        row4LabelWrapper.setPreferredSize(new Dimension(210, 30));
        row4Label = new JLabel("Assignment Code");
        row4LabelWrapper.add(row4Label, BorderLayout.WEST);
        innerBody.add(row4LabelWrapper, gbc);

        gbc.gridx = 1;
        row4Field = new RoundedTextField(20, 15);
        innerBody.add(row4Field, gbc);

        // Position
        gbc.gridy++;
        gbc.gridx = 0;
        row5LabelWrapper = new JPanel(new BorderLayout());
        row5LabelWrapper.setOpaque(false);
        row5LabelWrapper.setPreferredSize(new Dimension(210, 30));
        row5Label = new JLabel("Position");
        row5LabelWrapper.add(row5Label, BorderLayout.WEST);
        innerBody.add(row5LabelWrapper, gbc);

        gbc.gridx = 1;
        row5Field = new RoundedTextField(20, 15);
        innerBody.add(row5Field, gbc);

        // Faculty Rank
        gbc.gridy++;
        gbc.gridx = 0;
        row6LabelWrapper = new JPanel(new BorderLayout());
        row6LabelWrapper.setOpaque(false);
        row6LabelWrapper.setPreferredSize(new Dimension(210, 30));
        row6Label = new JLabel("Faculty Rank");
        row6LabelWrapper.add(row6Label, BorderLayout.WEST);
        innerBody.add(row6LabelWrapper, gbc);

        gbc.gridx = 1;
        row6Field = new RoundedTextField(20, 15);
        innerBody.add(row6Field, gbc);

//        // Campus
//        gbc.gridy++;
//        gbc.gridx = 0;
//        row7LabelWrapper = new JPanel(new BorderLayout());
//        row7LabelWrapper.setOpaque(false);
//        row7LabelWrapper.setPreferredSize(new Dimension(210, 30));
//        row7Label = new JLabel("Campus");
//        row7LabelWrapper.add(row7Label, BorderLayout.WEST);
//        innerBody.add(row7LabelWrapper, gbc);
//
//        gbc.gridx = 1;
//        row7Field = new RoundedComboBox<>(new String[] {
//                "Main", "Bustos", "Meneses", "San Rafael"
//        }, 15);
//        row7Field.setPreferredSize(new Dimension(210, 30));
//        innerBody.add(row7Field, gbc);

        // College
        gbc.gridy++;
        gbc.gridx = 0;
        row8LabelWrapper = new JPanel(new BorderLayout());
        row8LabelWrapper.setOpaque(false);
        row8LabelWrapper.setPreferredSize(new Dimension(210, 30));
        row8Label = new JLabel("College");
        row8LabelWrapper.add(row8Label, BorderLayout.WEST);
        innerBody.add(row8LabelWrapper, gbc);

        gbc.gridx = 1;
        row8Field = new RoundedComboBox<>(new String[]{""}, 15);
        row8Field.setPreferredSize(new Dimension(210, 30));
        innerBody.add(row8Field, gbc);

        body.add(innerBody);
        modal.add(body, BorderLayout.CENTER);
        
     // Footer 
     		JPanel footer = new JPanel();
     		footer.setPreferredSize(new Dimension(500, 100));
     		footer.setBorder(BorderFactory.createEmptyBorder(0, 35, 10, 35));
     		footer.setLayout(new BorderLayout());
     		footer.setOpaque(false);

     		Fonts poppins12btn = new Fonts("Poppins", 12f);
     		Font poppinsStyle12 = poppins12btn.getAppliedFont();

     		RoundedButton submitBtn = new RoundedButton("SUBMIT", 15);
     		submitBtn.setPreferredSize(new Dimension(500, 40));
     		submitBtn.setBackground(Color.decode("#842b28"));
     		submitBtn.setForeground(Color.WHITE);
     		submitBtn.setFont(poppinsStyle12);
     		submitBtn.addActionListener(this);
     		footer.add(submitBtn, BorderLayout.NORTH);

     		JPanel southBtnWrapper = new JPanel(new BorderLayout());
     		southBtnWrapper.setOpaque(false);
     		southBtnWrapper.setPreferredSize(new Dimension(500, 40));

     		RoundedButton clearBtn = new RoundedButton("CLEAR", 15);
     		clearBtn.setForeground(Color.decode("#842b28"));
     		clearBtn.setBorderColor(Color.decode("#842b28"));
     		clearBtn.setBorderThickness(1);
     		clearBtn.setPreferredSize(new Dimension(210, 40));
     		clearBtn.setFont(poppinsStyle12);
     		clearBtn.addActionListener(this);
     		southBtnWrapper.add(clearBtn, BorderLayout.EAST);

     		RoundedButton backBtn = new RoundedButton("BACK", 15);
     		backBtn.setForeground(Color.decode("#842b28"));
     		backBtn.setBorderColor(Color.decode("#842b28"));
     		backBtn.setBorderThickness(1);
     		backBtn.setPreferredSize(new Dimension(210, 40));
     		backBtn.setFont(poppinsStyle12);
     		backBtn.addActionListener(this);
     		southBtnWrapper.add(backBtn, BorderLayout.WEST);

     		footer.add(southBtnWrapper, BorderLayout.SOUTH);
     		
     		modal.add(footer, BorderLayout.SOUTH);
     		
     	// ── Apply label fonts & colors (same as StudentModal) ─────────────
     		Fonts poppins16 = new Fonts("Poppins", 16f);
     		Font poppinsStyle16 = poppins16.getAppliedFont();

//     		row1Label.setFont(poppinsStyle16);
     		row2Label.setFont(poppinsStyle16);
     		row3Label.setFont(poppinsStyle16);
     		row4Label.setFont(poppinsStyle16);
     		row5Label.setFont(poppinsStyle16);
     		row6Label.setFont(poppinsStyle16);
//     		row7Label.setFont(poppinsStyle16);
     		row8Label.setFont(poppinsStyle16);

//     		row1Label.setForeground(Color.decode("#842b28"));
     		row2Label.setForeground(Color.decode("#842b28"));
     		row3Label.setForeground(Color.decode("#842b28"));
     		row4Label.setForeground(Color.decode("#842b28"));
     		row5Label.setForeground(Color.decode("#842b28"));
     		row6Label.setForeground(Color.decode("#842b28"));
//     		row7Label.setForeground(Color.decode("#842b28"));
     		row8Label.setForeground(Color.decode("#842b28"));
     		
     	// ── Apply field fonts ───────────────────────────────────────────
     		Fonts poppins12 = new Fonts("Poppins", 10f);
     		Font poppinsFieldStyle = poppins12.getAppliedFont();

//     		row1Field.setFont(poppinsFieldStyle);
     		row2Field.setFont(poppinsFieldStyle);
     		row4Field.setFont(poppinsFieldStyle);
     		row5Field.setFont(poppinsFieldStyle);
     		row6Field.setFont(poppinsFieldStyle);
//     		row7Field.setFont(poppinsFieldStyle);
     		row8Field.setFont(poppinsFieldStyle);

     		// ── Apply field borders (same color & thickness as StudentModal) ─
//     		row1Field.setBorderColor(Color.decode("#924c4a"));
//     		row1Field.setBorderThickness(1);

     		row2Field.setBorderColor(Color.decode("#924c4a"));
     		row2Field.setBorderThickness(1);

     		row4Field.setBorderColor(Color.decode("#924c4a"));
     		row4Field.setBorderThickness(1);

     		row5Field.setBorderColor(Color.decode("#924c4a"));
     		row5Field.setBorderThickness(1);

     		row6Field.setBorderColor(Color.decode("#924c4a"));
     		row6Field.setBorderThickness(1);

//     		row7Field.setBorderColor(Color.decode("#924c4a"));
//     		row7Field.setBorderThickness(1);

     		row8Field.setBorderColor(Color.decode("#924c4a"));
     		row8Field.setBorderThickness(1);
     		
     		Fonts radioPoppins = new Fonts("Poppins", 12);
     		Font radioPoppinsStyle = radioPoppins.getAppliedFont();

     		adminCheck.setFont(radioPoppinsStyle);
     		staffCheck.setFont(radioPoppinsStyle);
     		facultyCheck.setFont(radioPoppinsStyle);

     		adminCheck.setForeground(Color.decode("#842b28"));
     		staffCheck.setForeground(Color.decode("#842b28"));
     		facultyCheck.setForeground(Color.decode("#842b28"));

        
        add(modal);

        // Checkbox listeners (EXACT equivalent to row3Field listener)
        ItemListener l = e -> updateVisibility();
        adminCheck.addItemListener(l);
        staffCheck.addItemListener(l);
        facultyCheck.addItemListener(l);

        updateVisibility();
    }

    public void updateVisibility() {

        boolean admin   = adminCheck.isSelected();
        boolean staff   = staffCheck.isSelected();
        boolean faculty = facultyCheck.isSelected();

        boolean isMalolosOrBustos =
                campus != null &&
                (campus.equalsIgnoreCase("Main") ||
                 campus.equalsIgnoreCase("Bustos"));

        // Assignment Code → Library Staff
        row4LabelWrapper.setVisible(staff);
        row4Field.setVisible(staff);

        // Position → Admin or Staff
        row5LabelWrapper.setVisible(admin || staff);
        row5Field.setVisible(admin || staff);

        // Faculty Rank → Faculty
        row6LabelWrapper.setVisible(faculty);
        row6Field.setVisible(faculty);

        // College → ONLY Faculty in Malolos or Bustos
        row8LabelWrapper.setVisible(faculty && isMalolosOrBustos);
        row8Field.setVisible(faculty && isMalolosOrBustos);

        resizeModal();
        revalidate();
        repaint();
    }
    
    public void resizeModal() {

        boolean admin   = adminCheck.isSelected();
        boolean staff   = staffCheck.isSelected();
        boolean faculty = facultyCheck.isSelected();

        boolean isMalolosOrBustos =
                campus != null &&
                (campus.equalsIgnoreCase("Malolos") ||
                 campus.equalsIgnoreCase("Bustos"));

        int newHeight = 550;

        if (admin && !staff && !faculty) {
            newHeight = 590;
        } else if (!admin && staff && !faculty) {
            newHeight = 620;
        } else if (!admin && !staff && faculty) {
            newHeight = isMalolosOrBustos ? 650 : 610;
        } else if (admin && staff && !faculty) {
            newHeight = 620;
        } else if (!admin && staff && faculty) {
            newHeight = isMalolosOrBustos ? 690 : 680;
        } else if (admin && !staff && faculty) {
            newHeight = isMalolosOrBustos ? 680 : 640;
        } else if (admin && staff && faculty) {
            newHeight = isMalolosOrBustos ? 720 : 680;
        }

        modal.setPreferredSize(new Dimension(500, newHeight));
        modal.revalidate();
        modal.repaint();
    }
    
    public void setCollege(String[] colleges) {
        if (colleges != null && colleges.length > 0) {
            row8Field.setModel(new DefaultComboBoxModel<>(colleges));
            row8Field.setSelectedIndex(0);
        } else {
            row8Field.setModel(new DefaultComboBoxModel<>(new String[]{""}));
        }
    }
    
    private boolean isValidYear(String text) {
	    if (text == null || text.length() != 4) return false;

	    for (int i = 0; i < text.length(); i++) {
	        if (!Character.isDigit(text.charAt(i))) return false;
	    }

	    int year = Integer.parseInt(text);

	    if (year < 1904 || year > 2026) return false;

	    return true;
	}


    @Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "SUBMIT":
			
			
			boolean isValidYearEnrolled = isValidYear(row2Field.getText());
		    
			if (isValidYearEnrolled) {
				boolean success = controller.saveStudentRecord();

				if (success) {
			        JOptionPane.showMessageDialog(
			            this,
			            "Employee record successfully saved!",
			            "Success",
			            JOptionPane.INFORMATION_MESSAGE
			        );

			        genModal.clearFields();
			        clearFields();

			        rp.showCard("general");
			    } else {
			        JOptionPane.showMessageDialog(
			            this,
			            "Failed to save employee record.",
			            "Error",
			            JOptionPane.ERROR_MESSAGE
			        );
			    }
			} else {
				JOptionPane.showMessageDialog(
		                this,
		                "Please enter a valid year (e.g. 2026)",
		                "Error",
		                JOptionPane.INFORMATION_MESSAGE
		        );
			}
		    break;

		case "CLEAR":
			clearFields();
		    break;
		

		case "BACK":
			rp.showCard("general");
			break;

		default:
			break;
		}
	}
    
    public void clearFields() {

        // Text fields
//        row1Field.setText("");
        row2Field.setText("");
        row2Field.setPlaceholder("YYYY");

        row4Field.setText("");
        row5Field.setText("");
        row6Field.setText("");

        // Combo boxes
        row8Field.setSelectedIndex(0);

        // Checkboxes
        adminCheck.setSelected(false);
        staffCheck.setSelected(false);
        facultyCheck.setSelected(false);

        updateVisibility();
        resizeModal();
    }
}