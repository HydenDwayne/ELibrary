package view.front_pages.patron_registration;

import javax.swing.*;

import controller.PatronRegistrationController;
import view.RoundedComponents.*;
import view.fonts.Fonts;
import view.front_pages.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentModal extends JPanel implements ActionListener {

	JRadioButton studentBtn;
	JRadioButton employeeBtn;

	// Labels for each row
//	JLabel row1Label;
	JLabel row2Label;
	JLabel row3Label;
	JLabel row4Label;
	JLabel row5Label;
	JLabel row6Label;
	JLabel row7Label;
	JLabel row8Label;
	JLabel row9Label;
	JLabel row10Label;
	JLabel row11Label;

	// Input fields for each row
//	RoundedTextField row1Field;
	public RoundedTextField row2Field;
	public RoundedComboBox<String> row3Field;
	public RoundedComboBox<String> row4Field;
	public RoundedComboBox<String> row5Field;
	public RoundedComboBox<String> row6Field;
	public RoundedComboBox<String> row7Field;
	public RoundedTextField row8Field;
	public RoundedComboBox<String> row9Field;
	public RoundedTextField row10Field;
	public RoundedTextField row11Field;

	// Label wrapper panels — stored as fields so updateVisibility() can toggle them
	JPanel row4LabelWrapper;
	JPanel row5LabelWrapper;
	JPanel row6LabelWrapper;
	JPanel row7LabelWrapper;
	JPanel row8LabelWrapper;
	JPanel row9LabelWrapper;
	JPanel row10LabelWrapper;
	
	String[] college = { "" };
	
	private PatronRegistrationController controller;
	
	
	
	public void setPrograms(String[] programs) {
	    if (programs.length > 0) {
	        row5Field.setModel(new DefaultComboBoxModel<>(programs));
	    } else {
	        row5Field.setModel(new DefaultComboBoxModel<>(new String[]{""}));
	    }
	}
	
	private String campus;
	private final String[] allStudentTypes = { "UNDERGRADUATE", "GRADUATE", "LABHIGH", "ALUMNI" };
	
	public void setCampusCode(String campus) {
	    this.campus = campus;

	    if (controller == null) {
	        controller = new PatronRegistrationController(this, campus, genModal);
	    } else {
	        controller.setCampusCode(campus);
	        controller.reloadStudentCollegesAndPrograms();
	        
	    }

	    updateStudentTypeOptions();
	    updateVisibility();
	    resizeModal();
	}
	
	

	public void setCollege(String[] colleges) {
	    this.college = colleges;
	    if (colleges.length > 0) {
	        row4Field.setModel(new DefaultComboBoxModel<>(colleges));
	        row4Field.setSelectedIndex(0);
	    } else {
	        row4Field.setModel(new DefaultComboBoxModel<>(new String[] {""}));
	    }
	}
	
	private void updateStudentTypeOptions() {
	    if (row3Field != null) {
	        String[] options;

	        if (campus != null && campus.equalsIgnoreCase("Main")) {
	            options = allStudentTypes;
	        } else {
	            options = new String[] { "UNDERGRADUATE", "ALUMNI" };
	        }

	        row3Field.setModel(new DefaultComboBoxModel<>(options));
	        row3Field.setSelectedIndex(0);
	    }
	}
	
	private void resetStudentType() {
	    // Add a placeholder option at the top
	    String[] options;
	    if (campus != null && campus.equalsIgnoreCase("Main")) {
	        options = new String[] { "UNDERGRADUATE", "GRADUATE", "LABHIGH", "ALUMNI" };
	    } else {
	        options = new String[] { "UNDERGRADUATE", "ALUMNI" };
	    }
	    row3Field.setModel(new DefaultComboBoxModel<>(options));
	    row3Field.setSelectedIndex(0); // Select the empty placeholder
	}

	

	// The modal panel itself — stored as a field so resizeModal() can update its height
	RoundedPanel modal;

	ButtonGroup bg;

	RegisterPatron rp;
	GeneralModal genModal;

	static String imgFilePath = FilePath.getImgFilePath();

	public StudentModal(RegisterPatron rp, GeneralModal genModal) {
		this.genModal = genModal;
		this.rp = rp;
		setOpaque(false);

		int panelRadius = 20;
		modal = new RoundedPanel(panelRadius);
		modal.setLayout(new BorderLayout());
		modal.setPreferredSize(new Dimension(500, 550));
		modal.setBackground(Color.decode("#faecee"));

		// Header
		JPanel header = new JPanel();
		header.setBackground(Color.decode("#842b28"));
		header.setPreferredSize(new Dimension(500, 100));
		header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		header.setLayout(new BorderLayout());

		ImageIcon icon = new ImageIcon(imgFilePath + "elib_logo.png");
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(110, 50, Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImage);
		JLabel elibLogo = new JLabel(icon);
		elibLogo.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(elibLogo, BorderLayout.CENTER);

		// ── Body ────────────────────────────────────────────────────────────────
		JPanel body = new JPanel();
		body.setOpaque(false);
		body.setLayout(new BorderLayout());
		body.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		Fonts introRust = new Fonts("IntroRust", 24f);
		Font introRustStyle = introRust.getAppliedFont();

		JLabel bodyTitle = new JLabel("PATRON REGISTRATION", SwingConstants.CENTER);
		bodyTitle.setForeground(Color.decode("#6d2321"));
		bodyTitle.setFont(introRustStyle);
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

		Fonts stepPoppins = new Fonts("PoppinsBold", 16f);
		Font stepPoppinsStyle = stepPoppins.getAppliedFont();

		// Step header
		JLabel step = new JLabel("Step 2: Student Information");
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

//		// ── Row 1: Student ID ────────────────────────────────────────────────────
//		gbc.gridy++;
//		gbc.gridx = 0;
//		JPanel row1LabelWrapper = new JPanel(new BorderLayout());
//		row1LabelWrapper.setOpaque(false);
//		row1LabelWrapper.setPreferredSize(new Dimension(210, 30));
//		row1Label = new JLabel("Student ID");
//		row1LabelWrapper.add(row1Label, BorderLayout.WEST);
//		innerBody.add(row1LabelWrapper, gbc);
//
//		gbc.gridx = 1;
//		row1Field = new RoundedTextField(19, 15);
//		row1Field.setPlaceholder("Enter Student ID");
//		row1Field.setBorderColor(Color.decode("#924c4a"));
//		row1Field.setBorderThickness(1);
//		innerBody.add(row1Field, gbc);

		// ── Row 2: Year Enrolled ─────────────────────────────────────────────────
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel row2LabelWrapper = new JPanel(new BorderLayout());
		row2LabelWrapper.setOpaque(false);
		row2LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row2Label = new JLabel("Year Enrolled");
		row2LabelWrapper.add(row2Label, BorderLayout.WEST);
		innerBody.add(row2LabelWrapper, gbc);

		gbc.gridx = 1;
		row2Field = new RoundedTextField(19, 15);
		row2Field.setPlaceholder("e.g. 2026");
		row2Field.setBorderColor(Color.decode("#924c4a"));
		row2Field.setBorderThickness(1);
		innerBody.add(row2Field, gbc);

		// ── Row 3: Student Type (the trigger for dynamic visibility) ─────────────
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel row3LabelWrapper = new JPanel(new BorderLayout());
		row3LabelWrapper.setOpaque(false);
		row3LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row3Label = new JLabel("Student Type");
		row3LabelWrapper.add(row3Label, BorderLayout.WEST);
		innerBody.add(row3LabelWrapper, gbc);

		String[] studentType = { "UNDERGRADUATE", "GRADUATE", "LABHIGH", "ALUMNI" };

		gbc.gridx = 1;
		row3Field = new RoundedComboBox<String>(studentType, 15);
		row3Field.setPreferredSize(new Dimension(210, 30));
		row3Field.setBorderColor(Color.decode("#924c4a"));
		row3Field.setBorderThickness(1);
		innerBody.add(row3Field, gbc);

		// Update visibility and resize the modal whenever the selection changes
		row3Field.addActionListener(e -> {
		    updateVisibility();
		    resizeModal();
		});

		// ── Row 4: College (visible for UNDERGRAD=0, GRADUATE=1) ─────────────────
		gbc.gridy++;
		gbc.gridx = 0;
		row4LabelWrapper = new JPanel(new BorderLayout());
		row4LabelWrapper.setOpaque(false);
		row4LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row4Label = new JLabel("College");
		row4LabelWrapper.add(row4Label, BorderLayout.WEST);
		innerBody.add(row4LabelWrapper, gbc);

		

		gbc.gridx = 1;
		row4Field = new RoundedComboBox<String>(college, 15);
		row4Field.setPreferredSize(new Dimension(210, 30));
		row4Field.setBorderColor(Color.decode("#924c4a"));
		row4Field.setBorderThickness(1);
		innerBody.add(row4Field, gbc);

		// ── Row 5: Program (visible for UNDERGRAD=0, GRADUATE=1) ─────────────────
		gbc.gridy++;
		gbc.gridx = 0;
		row5LabelWrapper = new JPanel(new BorderLayout());
		row5LabelWrapper.setOpaque(false);
		row5LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row5Label = new JLabel("Program");
		row5LabelWrapper.add(row5Label, BorderLayout.WEST);
		innerBody.add(row5LabelWrapper, gbc);

		

		gbc.gridx = 1;
		row5Field = new RoundedComboBox<>(new String[]{""}, 15);
		row5Field.setPreferredSize(new Dimension(210, 30));
		row5Field.setBorderColor(Color.decode("#924c4a"));
		row5Field.setBorderThickness(1);
		innerBody.add(row5Field, gbc);

		// ── Row 6: Year Level (visible for UNDERGRAD=0 only) ─────────────────────
		gbc.gridy++;
		gbc.gridx = 0;
		row6LabelWrapper = new JPanel(new BorderLayout());
		row6LabelWrapper.setOpaque(false);
		row6LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row6Label = new JLabel("Year Level");
		row6LabelWrapper.add(row6Label, BorderLayout.WEST);
		innerBody.add(row6LabelWrapper, gbc);

		String[] yearLevel = { "1st", "2nd", "3rd", "4th", "5th" };

		gbc.gridx = 1;
		row6Field = new RoundedComboBox<String>(yearLevel, 15);
		row6Field.setPreferredSize(new Dimension(210, 30));
		row6Field.setBorderColor(Color.decode("#924c4a"));
		row6Field.setBorderThickness(1);
		innerBody.add(row6Field, gbc);

		// ── Row 7: Degree (visible for GRADUATE=1 only) ───────────────────────────
		gbc.gridy++;
		gbc.gridx = 0;
		row7LabelWrapper = new JPanel(new BorderLayout());
		row7LabelWrapper.setOpaque(false);
		row7LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row7Label = new JLabel("Degree");
		row7LabelWrapper.add(row7Label, BorderLayout.WEST);
		innerBody.add(row7LabelWrapper, gbc);

		String[] degree = { "Masteral", "Doctoral" };

		gbc.gridx = 1;
		row7Field = new RoundedComboBox<String>(degree, 15);
		row7Field.setPreferredSize(new Dimension(210, 30));
		row7Field.setBorderColor(Color.decode("#924c4a"));
		row7Field.setBorderThickness(1);
		innerBody.add(row7Field, gbc);

		// ── Row 8: Thesis Title (visible for GRADUATE=1, ALUMNI=3) ───────────────
		gbc.gridy++;
		gbc.gridx = 0;
		row8LabelWrapper = new JPanel(new BorderLayout());
		row8LabelWrapper.setOpaque(false);
		row8LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row8Label = new JLabel("Thesis Title");
		row8LabelWrapper.add(row8Label, BorderLayout.WEST);
		innerBody.add(row8LabelWrapper, gbc);

		gbc.gridx = 1;
		row8Field = new RoundedTextField(19, 15);
		row8Field.setPlaceholder("Enter Thesis Title");
		row8Field.setBorderColor(Color.decode("#924c4a"));
		row8Field.setBorderThickness(1);
		innerBody.add(row8Field, gbc);

		// ── Row 9: Grade Level (visible for LABHIGH=2 only) ──────────────────────
		gbc.gridy++;
		gbc.gridx = 0;
		row9LabelWrapper = new JPanel(new BorderLayout());
		row9LabelWrapper.setOpaque(false);
		row9LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row9Label = new JLabel("Grade Level");
		row9LabelWrapper.add(row9Label, BorderLayout.WEST);
		innerBody.add(row9LabelWrapper, gbc);

		String[] gradeLevel = { "7th", "8th", "9th", "10th" };

		gbc.gridx = 1;
		row9Field = new RoundedComboBox<String>(gradeLevel, 15);
		row9Field.setPreferredSize(new Dimension(210, 30));
		row9Field.setBorderColor(Color.decode("#924c4a"));
		row9Field.setBorderThickness(1);
		innerBody.add(row9Field, gbc);

		// ── Row 10: Year Graduated (visible for GRADUATE=1, ALUMNI=3) ────────────
		gbc.gridy++;
		gbc.gridx = 0;
		row10LabelWrapper = new JPanel(new BorderLayout());
		row10LabelWrapper.setOpaque(false);
		row10LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row10Label = new JLabel("Year Graduated");
		row10LabelWrapper.add(row10Label, BorderLayout.WEST);
		innerBody.add(row10LabelWrapper, gbc);

		gbc.gridx = 1;
		row10Field = new RoundedTextField(19, 15);
		row10Field.setPlaceholder("e.g. 2026");
		row10Field.setBorderColor(Color.decode("#924c4a"));
		row10Field.setBorderThickness(1);
		innerBody.add(row10Field, gbc);

		updateVisibility();

		body.add(innerBody);

		// Footer 
		JPanel footer = new JPanel();
		footer.setPreferredSize(new Dimension(500, 100));
		footer.setBorder(BorderFactory.createEmptyBorder(0, 35, 10, 35));
		footer.setLayout(new BorderLayout());
		footer.setOpaque(false);

		Fonts poppins12btn = new Fonts("Poppins", 12f);
		Font poppinsStyle12 = poppins12btn.getAppliedFont();

		RoundedButton submitBtn = new RoundedButton("NEXT", 15);
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

		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);

		add(modal);
	}

	public void updateVisibility() {
	    int idx = row3Field.getSelectedIndex();

	    boolean isMainOrBustos = campus != null && 
	            (campus.equalsIgnoreCase("Main") || campus.equalsIgnoreCase("Bustos"));

	    boolean showCollege = (idx == 0 || idx == 1) && isMainOrBustos;
	    boolean showProgram    = idx == 0 || idx == 1;
	    boolean showYearLevel  = idx == 0;
	    boolean showDegree     = idx == 1;
	    boolean showThesis     = idx == 1 || idx == 3;
	    boolean showGradeLevel = idx == 2;
	    boolean showYearGrad   = idx == 1 || idx == 3;

	    row4LabelWrapper.setVisible(showCollege);
	    row4Field.setVisible(showCollege);

	    row5LabelWrapper.setVisible(showProgram);
	    row5Field.setVisible(showProgram);

	    row6LabelWrapper.setVisible(showYearLevel);
	    row6Field.setVisible(showYearLevel);

	    row7LabelWrapper.setVisible(showDegree);
	    row7Field.setVisible(showDegree);

	    row8LabelWrapper.setVisible(showThesis);
	    row8Field.setVisible(showThesis);

	    row9LabelWrapper.setVisible(showGradeLevel);
	    row9Field.setVisible(showGradeLevel);

	    row10LabelWrapper.setVisible(showYearGrad);
	    row10Field.setVisible(showYearGrad);
	    
	    
//	    int idx = row3Field.getSelectedIndex();
//	    boolean isPlaceholder = row3Field.getSelectedIndex() == 0; // first option is placeholder
//
//	    row4LabelWrapper.setVisible(!isPlaceholder && showCollege);
//	    row4Field.setVisible(!isPlaceholder && showCollege);
//
//	    row5LabelWrapper.setVisible(!isPlaceholder && showProgram);
//	    row5Field.setVisible(!isPlaceholder && showProgram);
//
//	    row6LabelWrapper.setVisible(!isPlaceholder && showYearLevel);
//	    row6Field.setVisible(!isPlaceholder && showYearLevel);
//
//	    row7LabelWrapper.setVisible(!isPlaceholder && showDegree);
//	    row7Field.setVisible(!isPlaceholder && showDegree);
//
//	    row8LabelWrapper.setVisible(!isPlaceholder && showThesis);
//	    row8Field.setVisible(!isPlaceholder && showThesis);
//
//	    row9LabelWrapper.setVisible(!isPlaceholder && showGradeLevel);
//	    row9Field.setVisible(!isPlaceholder && showGradeLevel);
//
//	    row10LabelWrapper.setVisible(!isPlaceholder && showYearGrad);
//	    row10Field.setVisible(!isPlaceholder && showYearGrad);

	    revalidate();
	    repaint();
	}

	public void resizeModal() {
		int idx = row3Field.getSelectedIndex();
		int newHeight;

		if (idx == 0) {
			newHeight = 550;
		} else if (idx == 1) {
			newHeight = 630;
		} else if (idx == 2) {
			newHeight = 470;
		} else {
			newHeight = 510;
		}

		modal.setPreferredSize(new Dimension(500, newHeight));

		modal.revalidate();
		modal.repaint();
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
		case "NEXT":

			boolean isValidYearEnrolled = isValidYear(row2Field.getText());
		    
			if (isValidYearEnrolled) {
				boolean success = controller.saveStudentRecord();

				if (success) {
				    JOptionPane.showMessageDialog(
				        this,
				        "Student record successfully saved!",
				        "Success",
				        JOptionPane.INFORMATION_MESSAGE
				    );

				    // ✅ CLEAR FORMS
				    genModal.clearFields();
				    clearFields();

				    // ✅ GO BACK TO STEP 1
				    rp.showCard("general");
				} else {
			        JOptionPane.showMessageDialog(
			                this,
			                "Error",
			                "Failed to save student record.",
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
	    row2Field.setText("");
	    row2Field.setPlaceholder("e.g. 2026");

	    row8Field.setText("");
	    row8Field.setPlaceholder("Enter Thesis Title");

	    row10Field.setText("");
	    row10Field.setPlaceholder("e.g. 2026");

	    // Combo boxes
	    row3Field.setSelectedIndex(0);
	    row4Field.setSelectedIndex(0);
	    row5Field.setSelectedIndex(0);
	    row6Field.setSelectedIndex(0);
	    row7Field.setSelectedIndex(0);
	    row9Field.setSelectedIndex(0);

	    updateVisibility();
	    resizeModal();
	}
	
	
	
	
}
