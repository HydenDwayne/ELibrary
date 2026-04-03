package view.modal.patron_modal;

import javax.swing.*;

import view.RoundedComponents.*;
import view.fonts.Fonts;
import view.front_pages.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralModal extends JPanel implements ActionListener {

	JRadioButton studentBtn;
	JRadioButton employeeBtn;

	JLabel row1Label;
	JLabel row2_1Label;
	JLabel row2_2Label;
	JLabel row4Label;
	JLabel row5Label;
	JLabel row6Label;
	JLabel row7Label;
	JLabel row8Label;
	RoundedComboBox<String> row8Field;

	RoundedTextField row1Field;
	RoundedTextField row3_1Field;
	RoundedTextField row3_2Field;
	RoundedTextField row4Field;
	RoundedTextField row5Field;
	RoundedTextField row6Field;
	RoundedTextField row7Field;

	ButtonGroup bg;

	LoginWindow lw;
	RegisterPatron rp;
	
	// In RegisterPatron
	public String getPatronID() {
	    return row1Field.getText().trim();
	}

	public String getFirstName() {
	    return row3_1Field.getText().trim();
	}

	public String getMiddleInitial() {
	    return row4Field.getText().trim();
	}

	public String getLastName() {
	    return row3_2Field.getText().trim();
	}

	public String getEmail() {
	    return row5Field.getText().trim();
	}

	public String getContact() {
	    return row6Field.getText().trim();
	}

	public String getAddress() {
	    return row7Field.getText().trim();
	}

	String campusCode = "Main";
	
	public String getCampusCode() {
		return campusCode;
	}
	
	String[] campuses = { "Main", "Bustos", "Hagonoy", "Meneses", "San Rafael", "Sarmiento" };




	public GeneralModal(RegisterPatron rp) {
		setOpaque(false);
		this.lw = lw;
		this.rp = rp;
		int panelRadius = 20;
		RoundedPanel modal = new RoundedPanel(panelRadius);
		modal.setLayout(new BorderLayout());
		modal.setPreferredSize(new Dimension(500, 700));
		modal.setBackground(Color.decode("#faecee"));

		JPanel header = new JPanel();

		header.setBackground(Color.decode("#842b28"));
		header.setPreferredSize(new Dimension(500, 100));
		header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

		// elib logo -- GAMITIN MO TO IF ELIB LOGO LANG
		header.setLayout(new BorderLayout());

		ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(110, 50, Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImage);
		JLabel elibLogo = new JLabel(icon);
		elibLogo.setHorizontalAlignment(SwingConstants.CENTER);

		header.add(elibLogo, BorderLayout.CENTER);

		// hanggang here

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

		JLabel step = new JLabel("Step 1: Patron Information");
		step.setFont(stepPoppinsStyle);
		step.setForeground(Color.decode("#842b28"));
		JPanel stepWrapper = new JPanel();
		stepWrapper.setLayout(new BorderLayout());
		stepWrapper.setOpaque(false);
		stepWrapper.setPreferredSize(new Dimension(430, 30));
		stepWrapper.add(step, BorderLayout.WEST);
		gbc.gridwidth = 2;
		gbc.gridy++;
		innerBody.add(stepWrapper, gbc);

		gbc.gridy++;

		JSeparator sep1 = new JSeparator();
		sep1.setPreferredSize(new Dimension(1, 1));
		sep1.setBackground(Color.decode("#842b28"));
		sep1.setOpaque(true);
		innerBody.add(sep1, gbc);

		gbc.gridwidth = 1;

		// from start to end, yan yung per row na label and text field
//		start
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel row1LabelWrapper = new JPanel();
		row1LabelWrapper.setOpaque(false);
		row1LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row1LabelWrapper.setLayout(new BorderLayout());
		row1Label = new JLabel("Patron ID");

		row1LabelWrapper.add(row1Label, BorderLayout.WEST);

		innerBody.add(row1LabelWrapper, gbc);

		gbc.gridx = 1;
		row1Field = new RoundedTextField(19, 15);
		row1Field.setPlaceholder("Enter Patron ID");
		row1Field.setBorderColor(Color.decode("#924c4a"));
		row1Field.setBorderThickness(1);
		innerBody.add(row1Field, gbc);
//		end

//		start
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel row2_1LabelWrapper = new JPanel();
		row2_1LabelWrapper.setOpaque(false);
		row2_1LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row2_1LabelWrapper.setLayout(new BorderLayout());
		row2_1Label = new JLabel("Full Name");

		row2_1LabelWrapper.add(row2_1Label, BorderLayout.WEST);

		innerBody.add(row2_1LabelWrapper, gbc);

		gbc.gridx = 1;
		JPanel row2_2LabelWrapper = new JPanel();
		row2_2LabelWrapper.setOpaque(false);
		row2_2LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row2_2LabelWrapper.setLayout(new BorderLayout());
		row2_2Label = new JLabel("Last Name");

		row2_2LabelWrapper.add(row2_2Label, BorderLayout.WEST);

		innerBody.add(row2_2LabelWrapper, gbc);
//		end

//		start
		gbc.gridy++;
		gbc.gridx = 0;
		row3_1Field = new RoundedTextField(19, 15);
		row3_1Field.setPlaceholder("Enter First Name");
		row3_1Field.setBorderColor(Color.decode("#924c4a"));
		row3_1Field.setBorderThickness(1);
		innerBody.add(row3_1Field, gbc);

		gbc.gridx = 1;
		row3_2Field = new RoundedTextField(19, 15);
		row3_2Field.setPlaceholder("Enter Last Name");
		row3_2Field.setBorderColor(Color.decode("#924c4a"));
		row3_2Field.setBorderThickness(1);
		innerBody.add(row3_2Field, gbc);
//		end

//		start
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel row4LabelWrapper = new JPanel();
		row4LabelWrapper.setOpaque(false);
		row4LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row4LabelWrapper.setLayout(new BorderLayout());
		row4Label = new JLabel("Middle Initial");

		row4LabelWrapper.add(row4Label, BorderLayout.WEST);

		innerBody.add(row4LabelWrapper, gbc);

		gbc.gridx = 1;
		row4Field = new RoundedTextField(19, 15);
		row4Field.setPlaceholder("Enter Middle Initial");
		row4Field.setBorderColor(Color.decode("#924c4a"));
		row4Field.setBorderThickness(1);
		innerBody.add(row4Field, gbc);
//		end

//		start
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel row5LabelWrapper = new JPanel();
		row5LabelWrapper.setOpaque(false);
		row5LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row5LabelWrapper.setLayout(new BorderLayout());
		row5Label = new JLabel("Email Address");

		row5LabelWrapper.add(row5Label, BorderLayout.WEST);

		innerBody.add(row5LabelWrapper, gbc);

		gbc.gridx = 1;
		row5Field = new RoundedTextField(19, 15);
		row5Field.setPlaceholder("Enter Email Address");
		row5Field.setBorderColor(Color.decode("#924c4a"));
		row5Field.setBorderThickness(1);
		innerBody.add(row5Field, gbc);
//		end

//		start
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel row6LabelWrapper = new JPanel();
		row6LabelWrapper.setOpaque(false);
		row6LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row6LabelWrapper.setLayout(new BorderLayout());
		row6Label = new JLabel("Contact Number");

		row6LabelWrapper.add(row6Label, BorderLayout.WEST);

		innerBody.add(row6LabelWrapper, gbc);

		gbc.gridx = 1;
		row6Field = new RoundedTextField(19, 15);
		row6Field.setPlaceholder("Enter Contact Number");
		row6Field.setBorderColor(Color.decode("#924c4a"));
		row6Field.setBorderThickness(1);
		innerBody.add(row6Field, gbc);
//		end

//		start
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel row7LabelWrapper = new JPanel();
		row7LabelWrapper.setOpaque(false);
		row7LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row7LabelWrapper.setLayout(new BorderLayout());
		row7Label = new JLabel("Home Address");

		row7LabelWrapper.add(row7Label, BorderLayout.WEST);

		innerBody.add(row7LabelWrapper, gbc);

		gbc.gridx = 1;
		row7Field = new RoundedTextField(19, 15);
		row7Field.setPlaceholder("Enter Home Address");
		row7Field.setBorderColor(Color.decode("#924c4a"));
		row7Field.setBorderThickness(1);
		innerBody.add(row7Field, gbc);
//		end

//		start
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel row8LabelWrapper = new JPanel();
		row8LabelWrapper.setOpaque(false);
		row8LabelWrapper.setPreferredSize(new Dimension(210, 30));
		row8LabelWrapper.setLayout(new BorderLayout());
		row8Label = new JLabel("Campus Code");

		row8LabelWrapper.add(row8Label, BorderLayout.WEST);

		innerBody.add(row8LabelWrapper, gbc);

		

		gbc.gridx = 1;
		row8Field = new RoundedComboBox<String>(campuses, 15);
		row8Field.setPreferredSize(new Dimension(210, 30));
		row8Field.setBorderColor(Color.decode("#924c4a"));
		row8Field.setBorderThickness(1);
		innerBody.add(row8Field, gbc);
//		end

		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy++;

		JSeparator sep2 = new JSeparator();
		sep2.setPreferredSize(new Dimension(1, 1));
		sep2.setBackground(Color.decode("#842b28"));
		sep2.setOpaque(true);
		innerBody.add(sep2, gbc);

		JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		radioPanel.setBackground(new Color(250, 235, 235));
		radioPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
		bg = new ButtonGroup();
		studentBtn = new JRadioButton("Student");
		employeeBtn = new JRadioButton("Employee");
		studentBtn.setSelected(true);
		studentBtn.setBackground(new Color(250, 235, 235));
		employeeBtn.setBackground(new Color(250, 235, 235));
		studentBtn.setForeground(new Color(60, 20, 20));
		employeeBtn.setForeground(new Color(60, 20, 20));
		bg.add(studentBtn);
		bg.add(employeeBtn);
		radioPanel.add(studentBtn);
		radioPanel.add(employeeBtn);

		gbc.gridy++;

		innerBody.add(radioPanel, gbc);

		gbc.gridy++;
		JSeparator sep3 = new JSeparator();
		sep3.setPreferredSize(new Dimension(1, 1));
		sep3.setBackground(Color.decode("#842b28"));
		sep3.setOpaque(true);
		innerBody.add(sep3, gbc);

		Fonts poppins16 = new Fonts("Poppins", 16f);
		Font poppinsStyle16 = poppins16.getAppliedFont();

		row1Label.setFont(poppinsStyle16);
		row2_1Label.setFont(poppinsStyle16);
		row2_2Label.setFont(poppinsStyle16);
		row4Label.setFont(poppinsStyle16);
		row5Label.setFont(poppinsStyle16);
		row6Label.setFont(poppinsStyle16);
		row7Label.setFont(poppinsStyle16);
		row8Label.setFont(poppinsStyle16);

		row1Label.setForeground(Color.decode("#842b28"));
		row2_1Label.setForeground(Color.decode("#842b28"));
		row2_2Label.setForeground(Color.decode("#842b28"));
		row4Label.setForeground(Color.decode("#842b28"));
		row5Label.setForeground(Color.decode("#842b28"));
		row6Label.setForeground(Color.decode("#842b28"));
		row7Label.setForeground(Color.decode("#842b28"));
		row8Label.setForeground(Color.decode("#842b28"));

		Fonts poppins12 = new Fonts("Poppins", 10f);
		Font poppinsStyle = poppins12.getAppliedFont();

		row1Field.setFont(poppinsStyle);
		row3_1Field.setFont(poppinsStyle);
		row3_2Field.setFont(poppinsStyle);
		row4Field.setFont(poppinsStyle);
		row5Field.setFont(poppinsStyle);
		row6Field.setFont(poppinsStyle);
		row7Field.setFont(poppinsStyle);
		row8Field.setFont(poppinsStyle);

		Fonts radioPoppins = new Fonts("Poppins", 12);
		Font radioPoppinsStyle = radioPoppins.getAppliedFont();

		studentBtn.setForeground(Color.decode("#842b28"));
		employeeBtn.setForeground(Color.decode("#842b28"));

		studentBtn.setFont(radioPoppinsStyle);
		employeeBtn.setFont(radioPoppinsStyle);

		body.add(innerBody);

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

		RoundedButton clearBtn = new RoundedButton("CLEAR", 15);
		clearBtn.setForeground(Color.decode("#842b28"));
		clearBtn.setBorderColor(Color.decode("#842b28"));
		clearBtn.setBorderThickness(1);
		clearBtn.setPreferredSize(new Dimension(500, 40));
		clearBtn.setFont(poppinsStyle12);
		clearBtn.addActionListener(this);
		footer.add(clearBtn, BorderLayout.SOUTH);

		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);

		add(modal);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "NEXT":
			String row1PlaceHolder = "Enter Patron ID";
			String row3_1PlaceHolder = "Enter First Name";
			String row3_2PlaceHolder = "Enter Last Name";

			if (row1Field.getText().equals(row1PlaceHolder) || row3_1Field.getText().equals(row3_1PlaceHolder)
					|| row3_2Field.getText().equals(row3_2PlaceHolder)) {

				JOptionPane.showMessageDialog(null, "Fill in the required fields!");
			} else {

				if (studentBtn.isSelected()) {
				    campusCode = campuses[row8Field.getSelectedIndex()];
				    rp.setCampus(campusCode);

				    rp.studModal.setCampusCode(campusCode); // ✅ REQUIRED
				    rp.showCard("student");
				}

				if (employeeBtn.isSelected()) {
				    campusCode = campuses[row8Field.getSelectedIndex()];
				    rp.setCampus(campusCode);

				    rp.empModal.setCampusCode(campusCode); // ✅ REQUIRED
				    rp.showCard("employee");
				}
			}
			break;

		case "CLEAR":
			if (row1Field.getText().equals("exit")) {
				Window w = SwingUtilities.getWindowAncestor(this);
				if (w instanceof JDialog)
					w.dispose();
			} else {
				clearFields();
			}
			break;

		default:
			break;
		}
	}
	
	public void clearFields() {

	    row1Field.setText("");
	    row1Field.setPlaceholder("Enter Patron ID");

	    row3_1Field.setText("");
	    row3_1Field.setPlaceholder("Enter First Name");

	    row3_2Field.setText("");
	    row3_2Field.setPlaceholder("Enter Last Name");

	    row4Field.setText("");
	    row4Field.setPlaceholder("Enter Middle Initial");

	    row5Field.setText("");
	    row5Field.setPlaceholder("Enter Email Address");

	    row6Field.setText("");
	    row6Field.setPlaceholder("Enter Contact Number");

	    row7Field.setText("");
	    row7Field.setPlaceholder("Enter Home Address");

	    row8Field.setSelectedIndex(0);
	    bg.setSelected(studentBtn.getModel(), true);
	}

}
