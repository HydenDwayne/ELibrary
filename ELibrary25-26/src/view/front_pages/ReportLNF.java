package view.front_pages;

import javax.swing.*;

import controller.LNFController;
import view.RoundedComponents.*;
import view.fonts.Fonts;

import controller.LNFController;


import java.awt.*;
import java.awt.event.*;

public class ReportLNF extends JFrame{
	
	RoundedTextField itemField;
	RoundedTextField nameField;
	RoundedTextArea descriptionField;
	RoundedComboBox<String> floorField;
	RoundedComboBox<String> statusField;
	RoundedTextField lastSeenField;
	
	
	public ReportLNF(Dashboard frame, LoginWindow lw) {
		int panelRadius = 20;
		JPanel panel = new JPanel() {
			Image backgroundImage = new ImageIcon(FilePath.image("blurred_bg.jpg")).getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridBagLayout());
		
		RoundedPanel modal = new RoundedPanel(panelRadius);
		modal.setLayout(new BorderLayout());
		modal.setPreferredSize(new Dimension(500,550));
		modal.setBackground(Color.decode("#faecee"));
		
		JPanel header = new JPanel();
		header.setBackground(Color.decode("#842b28"));
		header.setPreferredSize(new Dimension(500, 100));
		header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		
		JPanel headerWrapper = new JPanel();
		headerWrapper.setLayout(new BorderLayout());
		headerWrapper.setPreferredSize(new Dimension(450,80));
		headerWrapper.setOpaque(false);
		
		
		ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(110, 50, Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImage);
		JLabel elibLogo = new JLabel(icon);
		elibLogo.setHorizontalAlignment(SwingConstants.CENTER);
		headerWrapper.add(elibLogo, BorderLayout.WEST);
		
		Fonts introRust36 = new Fonts("IntroRust", 36f);
		Font introRustStyle26 = introRust36.getAppliedFont();
		
		JLabel headerLabel = new JLabel("LOST AND FOUND");
		headerLabel.setFont(introRustStyle26);
		headerLabel.setForeground(Color.WHITE);
		
		headerWrapper.add(headerLabel, BorderLayout.EAST);
		
		header.add(headerWrapper);
		
		
		JPanel body = new JPanel();
		body.setOpaque(false);
		body.setLayout(new BorderLayout());
		body.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Fonts introRust = new Fonts("IntroRust", 24f);
		Font introRustStyle = introRust.getAppliedFont();
		
		JLabel bodyTitle = new JLabel("REPORT A MISSING ITEM", SwingConstants.CENTER);
		bodyTitle.setForeground(Color.decode("#6d2321"));
		bodyTitle.setFont(introRustStyle);
		
		body.add(bodyTitle, BorderLayout.NORTH);
		
		JPanel innerBody = new JPanel();
		innerBody.setOpaque(false);
		innerBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerBody.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		
		
		

		JPanel itemLabelWrapper = new JPanel();
		itemLabelWrapper.setOpaque(false);
		itemLabelWrapper.setPreferredSize(new Dimension(210, 30));
		itemLabelWrapper.setLayout(new BorderLayout());
		JLabel itemLabel = new JLabel("Missing Item:");
		
		itemLabelWrapper.add(itemLabel, BorderLayout.WEST);
		
		innerBody.add(itemLabelWrapper, gbc);
		
		gbc.gridx = 1;
		itemField = new RoundedTextField(19, 15);
		itemField.setPlaceholder("Enter Lost Item Name");
		itemField.setBorderColor(Color.decode("#924c4a"));
		itemField.setBorderThickness(1);
		innerBody.add(itemField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		JPanel nameLabelWrapper = new JPanel();
		nameLabelWrapper.setOpaque(false);
		nameLabelWrapper.setPreferredSize(new Dimension(210, 30));
		nameLabelWrapper.setLayout(new BorderLayout());
		JLabel nameLabel = new JLabel("Owner Name");
		
		nameLabelWrapper.add(nameLabel, BorderLayout.WEST);
		
		innerBody.add(nameLabelWrapper, gbc);
		
		gbc.gridx = 1;
		nameField = new RoundedTextField(19, 15);
		nameField.setPlaceholder("(Optional)");
		nameField.setBorderColor(Color.decode("#924c4a"));
		nameField.setBorderThickness(1);
		innerBody.add(nameField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		JPanel descriptionLabelWrapper = new JPanel();
		descriptionLabelWrapper.setOpaque(false);
		descriptionLabelWrapper.setPreferredSize(new Dimension(210, 30));
		descriptionLabelWrapper.setLayout(new BorderLayout());
		JLabel descriptionLabel = new JLabel("Item Description");
		
		descriptionLabelWrapper.add(descriptionLabel, BorderLayout.WEST);
		
		innerBody.add(descriptionLabelWrapper, gbc);
		
		gbc.gridx = 1;
		descriptionField = new RoundedTextArea(3, 19, 15);
		descriptionField.setPlaceholder("Enter Item Description");
		descriptionField.setBorderColor(Color.decode("#924c4a"));
		descriptionField.setBorderThickness(1);
		innerBody.add(descriptionField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		JPanel floorLabelWrapper = new JPanel();
		floorLabelWrapper.setOpaque(false);
		floorLabelWrapper.setPreferredSize(new Dimension(210, 30));
		floorLabelWrapper.setLayout(new BorderLayout());
		JLabel floorLabel = new JLabel("Lost on Floor?");
		
		floorLabelWrapper.add(floorLabel, BorderLayout.WEST);
		
		innerBody.add(floorLabelWrapper, gbc);
		String[] floorLevels = {
				"1st",
				"2nd",
				"3rd",
				"4th",
				"5th",
				"6th",
				"7th",
		};
		
		gbc.gridx = 1;
		 floorField = new RoundedComboBox<String>(floorLevels, 15);
		floorField.setPreferredSize(new Dimension(210, 30));
		floorField.setBorderColor(Color.decode("#924c4a"));
		floorField.setBorderThickness(1);
		innerBody.add(floorField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		JPanel statusLabelWrapper = new JPanel();
		statusLabelWrapper.setOpaque(false);
		statusLabelWrapper.setPreferredSize(new Dimension(210, 30));
		statusLabelWrapper.setLayout(new BorderLayout());
		JLabel statusLabel = new JLabel("Item Status");
		
		statusLabelWrapper.add(statusLabel, BorderLayout.WEST);
		
		innerBody.add(statusLabelWrapper, gbc);
		
		String[] statuses = {
				"Missing",
				"Surrendered"
		};
		gbc.gridx = 1;
		 statusField = new RoundedComboBox<String>(statuses, 15);
		statusField.setPreferredSize(new Dimension(210, 30));
		statusField.setBorderColor(Color.decode("#924c4a"));
		statusField.setBorderThickness(1);
		innerBody.add(statusField, gbc);

		

		gbc.gridy++;
		gbc.gridx = 0;
		JPanel lastSeenLabelWrapper = new JPanel();
		lastSeenLabelWrapper.setOpaque(false);
		lastSeenLabelWrapper.setPreferredSize(new Dimension(210, 30));
		lastSeenLabelWrapper.setLayout(new BorderLayout());
		JLabel lastSeenLabel = new JLabel("Last Seen");
		
		lastSeenLabelWrapper.add(lastSeenLabel, BorderLayout.WEST);
		
		innerBody.add(lastSeenLabelWrapper, gbc);
		
		gbc.gridx = 1;
		 lastSeenField = new RoundedTextField(19, 15);
		lastSeenField.setPlaceholder("yyyy-mm-dd");
		lastSeenField.setBorderColor(Color.decode("#924c4a"));
		lastSeenField.setBorderThickness(1);
		innerBody.add(lastSeenField, gbc);

		
		Fonts poppins16 = new Fonts("Poppins", 16f);
		Font poppinsStyle16 = poppins16.getAppliedFont();
		
		itemLabel.setFont(poppinsStyle16);
		nameLabel.setFont(poppinsStyle16);
		descriptionLabel.setFont(poppinsStyle16);
		floorLabel.setFont(poppinsStyle16);
		statusLabel.setFont(poppinsStyle16);
		lastSeenLabel.setFont(poppinsStyle16);
		
		itemLabel.setForeground(Color.decode("#842b28"));
		nameLabel.setForeground(Color.decode("#842b28"));
		descriptionLabel.setForeground(Color.decode("#842b28"));
		floorLabel.setForeground(Color.decode("#842b28"));
		statusLabel.setForeground(Color.decode("#842b28"));
		lastSeenLabel.setForeground(Color.decode("#842b28"));
		
		Fonts poppins12 = new Fonts("Poppins", 10f);
		Font poppinsStyle = poppins12.getAppliedFont();
		
		
		itemField.setFont(poppinsStyle);
		nameField.setFont(poppinsStyle);
		descriptionField.setFont(poppinsStyle);
		floorField.setFont(poppinsStyle);
		statusField.setFont(poppinsStyle);
		lastSeenField.setFont(poppinsStyle);
		
		
		body.add(innerBody);
		
		JPanel footer = new JPanel();
		footer.setPreferredSize(new Dimension(500, 100));
		footer.setBorder(BorderFactory.createEmptyBorder(0, 35, 10, 35));
		footer.setLayout(new BorderLayout());
		
		
		Fonts poppins12btn= new Fonts("Poppins", 12f);
		Font poppinsStyle12 = poppins12btn.getAppliedFont();
		
		RoundedButton submitBtn = new RoundedButton("SUBMIT REPORT", 15);
		submitBtn.setPreferredSize(new Dimension(500, 40));
		submitBtn.setBackground(Color.decode("#842b28"));
		submitBtn.setForeground(Color.WHITE);
		submitBtn.setFont(poppinsStyle12);
		submitBtn.addActionListener(e -> {
		    
		    String item = itemField.getRealText().trim();
		    String owner = nameField.getRealText().trim();
		    String desc = descriptionField.getRealText().trim();
		    String floorTxt = floorField.getSelectedItem().toString();
		    String statusTxt = statusField.getSelectedItem().toString();
		    String date = lastSeenField.getRealText().trim();

		    
		    System.out.println(item);
		    if (item.isEmpty() || date.isEmpty()) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Please fill in Item and Date fields.",
		            "Missing Information",
		            JOptionPane.WARNING_MESSAGE
		        );
		        return;
		    }

		    
		    String normalizedDate = validateAndNormalizeDate(date);
		    if (normalizedDate == null) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Please fill in the date field with a valid yyyy-mm-dd format.",
		            "Incorrect/Invalid Date Format",
		            JOptionPane.WARNING_MESSAGE
		        );
		        return;
		    }

		    if (owner.isEmpty()) owner = null;
		    if (desc.isEmpty()) desc = null;

		    
		    String[] reportDetails = {
		        item,
		        owner,
		        desc,
		        floorTxt,
		        statusTxt,
		        normalizedDate
		    };
		    
		    new LNFController(reportDetails);
		    Window w = SwingUtilities.getWindowAncestor(this);
		    if (w instanceof JDialog) {
		    	w.dispose();
		    	new PopUpMessageModal(w, "Report filed!");
		    	clearFields();
		    }
		    
		});

		footer.add(submitBtn, BorderLayout.NORTH);
		
		RoundedButton clearBtn = new RoundedButton("CLEAR", 15);
		clearBtn.setForeground(Color.decode("#842b28"));
		clearBtn.setBorderColor(Color.decode("#842b28"));
		clearBtn.setBorderThickness(1);
		clearBtn.setPreferredSize(new Dimension(500, 40));
		clearBtn.setFont(poppinsStyle12);
		clearBtn.addActionListener(e -> {
		    clearFields();
		});

		footer.add(clearBtn, BorderLayout.SOUTH);
		
		
		
		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);
		
		
		
		panel.add(modal);
		
		
		add(panel);
		
		
		
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                
                modal.requestFocusInWindow();
            }
        });

		setTitle("E-Library Management System");
		setContentPane(panel);
		pack();

		if (getWidth() < 1120 || getHeight() < 600) {
			setSize(1120, 600);
		}

		setMinimumSize(new Dimension(1120, 600));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public void clearFields() {
		
	    itemField.setText("");
	    nameField.setText("");
	    descriptionField.setText("");
	    lastSeenField.setText("");

	    
	    itemField.setPlaceholder("Enter Lost Item Name");
	    nameField.setPlaceholder("(Optional)");
	    descriptionField.setPlaceholder("Enter Item Description");
	    lastSeenField.setPlaceholder("yyyy-mm-dd");

	    
	    floorField.setSelectedIndex(0);
	    statusField.setSelectedIndex(0); 
	}
	
	public static String validateAndNormalizeDate(String date) {
	    if (date == null) {
	        return null;
	    }

	    date = date.trim();
	    String[] parts = date.split("-");

	    
	    if (parts.length != 3) {
	        return null;
	    }

	    String yearPart  = parts[0];
	    String monthPart = parts[1];
	    String dayPart   = parts[2];

	    
	    if (!yearPart.matches("\\d{4}")) {
	        return null;
	    }

	    
	    if (!monthPart.matches("\\d{1,2}") || !dayPart.matches("\\d{1,2}")) {
	        return null;
	    }

	    int year  = Integer.parseInt(yearPart);
	    int month = Integer.parseInt(monthPart);
	    int day   = Integer.parseInt(dayPart);

	    
	    if (month < 1 || month > 12) {
	        return null;
	    }

	    int[] daysInMonth = {
	        31, 28, 31, 30, 31, 30,
	        31, 31, 30, 31, 30, 31
	    };

	    
	    boolean isLeapYear =
	        (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);

	    if (month == 2 && isLeapYear) {
	        daysInMonth[1] = 29;
	    }

	    
	    if (day < 1 || day > daysInMonth[month - 1]) {
	        return null;
	    }

	    
	    String normalizedMonth = (month < 10 ? "0" : "") + month;
	    String normalizedDay   = (day   < 10 ? "0" : "") + day;

	    return year + "-" + normalizedMonth + "-" + normalizedDay;
	}
}