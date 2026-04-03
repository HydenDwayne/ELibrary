package view.front_pages;

import javax.swing.*;

import view.RoundedComponents.*;
import view.fonts.Fonts;

import java.awt.*;
import java.awt.event.*;

public class ReportLNF extends JFrame{
	
	
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
		
		// elib logo
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
		
		
		
		
//		start
		JPanel itemLabelWrapper = new JPanel();
		itemLabelWrapper.setOpaque(false);
		itemLabelWrapper.setPreferredSize(new Dimension(210, 30));
		itemLabelWrapper.setLayout(new BorderLayout());
		JLabel itemLabel = new JLabel("Missing Item:");
		
		itemLabelWrapper.add(itemLabel, BorderLayout.WEST);
		
		innerBody.add(itemLabelWrapper, gbc);
		
		gbc.gridx = 1;
		RoundedTextField itemField = new RoundedTextField(19, 15);
		itemField.setPlaceholder("Enter Lost Item Name");
		itemField.setBorderColor(Color.decode("#924c4a"));
		itemField.setBorderThickness(1);
		innerBody.add(itemField, gbc);
//		end
		
//		start
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
		RoundedTextField nameField = new RoundedTextField(19, 15);
		nameField.setPlaceholder("(Optional)");
		nameField.setBorderColor(Color.decode("#924c4a"));
		nameField.setBorderThickness(1);
		innerBody.add(nameField, gbc);
//		end
		
//		start
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
		RoundedTextArea descriptionField = new RoundedTextArea(3, 19, 15);
		descriptionField.setPlaceholder("Enter Item Description");
		descriptionField.setBorderColor(Color.decode("#924c4a"));
		descriptionField.setBorderThickness(1);
		innerBody.add(descriptionField, gbc);
//		end
		
//		start
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
		RoundedComboBox<String> floorField = new RoundedComboBox<String>(floorLevels, 15);
		floorField.setPreferredSize(new Dimension(210, 30));
		floorField.setBorderColor(Color.decode("#924c4a"));
		floorField.setBorderThickness(1);
		innerBody.add(floorField, gbc);
//		end
		
//		start
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel statusLabelWrapper = new JPanel();
		statusLabelWrapper.setOpaque(false);
		statusLabelWrapper.setPreferredSize(new Dimension(210, 30));
		statusLabelWrapper.setLayout(new BorderLayout());
		JLabel statusLabel = new JLabel("Item Status");
		
		statusLabelWrapper.add(statusLabel, BorderLayout.WEST);
		
		innerBody.add(statusLabelWrapper, gbc);
		
		gbc.gridx = 1;
		RoundedTextField statusField = new RoundedTextField(19, 15);
		statusField.setPlaceholder("Surrendered");
		statusField.setBorderColor(Color.decode("#924c4a"));
		statusField.setBorderThickness(1);
		innerBody.add(statusField, gbc);
//		end
		
//		start
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
		RoundedTextField lastSeenField = new RoundedTextField(19, 15);
		lastSeenField.setPlaceholder("yyyy-mm-dd");
		lastSeenField.setBorderColor(Color.decode("#924c4a"));
		lastSeenField.setBorderThickness(1);
		innerBody.add(lastSeenField, gbc);
//		end
		
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
		footer.add(submitBtn, BorderLayout.NORTH);
		
		RoundedButton clearBtn = new RoundedButton("CLEAR", 15);
		clearBtn.setForeground(Color.decode("#842b28"));
		clearBtn.setBorderColor(Color.decode("#842b28"));
		clearBtn.setBorderThickness(1);
		clearBtn.setPreferredSize(new Dimension(500, 40));
		clearBtn.setFont(poppinsStyle12);
		clearBtn.addActionListener(e -> {
			
			if (itemField.getText().equals("exit")) {
	            lw.setVisible(true);
				dispose();
			} else {
				itemField.setText("");
				nameField.setText("");
				descriptionField.setText("");
				floorField.setSelectedIndex(0);
				statusField.setText("");
				lastSeenField.setText("");
				
				itemField.setPlaceholder("Enter Lost Item Name");
				nameField.setPlaceholder("(Optional)");
				descriptionField.setPlaceholder("Enter Item Description");
				statusField.setPlaceholder("Surrendered");
				lastSeenField.setPlaceholder("yyyy-mm-dd");
			}
            
        });
		footer.add(clearBtn, BorderLayout.SOUTH);
		
		
		
		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);
		
		
		
		panel.add(modal);
		
		
		add(panel);
		
		
		// Frame settings
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Request focus on something else, or a panel
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
	}
}
