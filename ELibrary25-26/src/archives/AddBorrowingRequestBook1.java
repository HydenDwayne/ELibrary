package archives;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.RoundedComponents.RoundedButton;
import view.RoundedComponents.RoundedPanel;
import view.RoundedComponents.RoundedTextField;
import view.front_pages.FilePath;
import view.fonts.*;

public class AddBorrowingRequestBook1 extends JFrame {

	static final Color MAROON = new Color(132, 43, 40); // #842b28 from sample
	static final Color LIGHT_PINK = new Color(250, 236, 238); // #faecee from sample
	static final Color WHITE = Color.WHITE;
	static final Color DARK_TEXT = new Color(109, 35, 33); // #6d2321 from sample
	static final Color FIELD_BORDER = new Color(146, 76, 74); // #924c4a from sample

	static final int PANEL_RADIUS = 20;
	static final int FIELD_RADIUS = 15;

	static String imgFilePath = FilePath.getImgFilePath();

	public AddBorrowingRequestBook1() {
		// Background panel with blurred background (similar to SampleModal)
		JPanel panel = new JPanel() {
			// Image backgroundImage = new ImageIcon(FilePath.getImgFilePath() +
			// "blurred_bg.jpg").getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// If you have background image:
				// g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				// For now, using a gradient or solid color background
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setColor(new Color(210, 180, 180));
				g2.fillRect(0, 0, getWidth(), getHeight());
				g2.dispose();
			}
		};
		panel.setLayout(new GridBagLayout());

		// Main modal panel using RoundedPanel
		RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
		modal.setLayout(new BorderLayout());
		modal.setPreferredSize(new Dimension(500, 400));
		modal.setBackground(LIGHT_PINK);

		JPanel header = new JPanel();
		header.setBackground(Color.decode("#842b28"));
		header.setPreferredSize(new Dimension(500, 100));
		header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		
		JPanel headerWrapper = new JPanel();
		headerWrapper.setLayout(new BorderLayout());
		headerWrapper.setPreferredSize(new Dimension(450,80));
		headerWrapper.setOpaque(false);

		// elib logo
		ImageIcon icon = new ImageIcon(imgFilePath + "elib_logo.png");
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(110, 50, Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImage);
		JLabel elibLogo = new JLabel(icon);
		elibLogo.setHorizontalAlignment(SwingConstants.CENTER);
		headerWrapper.add(elibLogo, BorderLayout.WEST);

		Fonts introRust36 = new Fonts("IntroRust", 36f);
		Font introRustStyle26 = introRust36.getAppliedFont();

		JLabel headerLabel = new JLabel("BOOKS");
		headerLabel.setFont(introRustStyle26);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));

		headerWrapper.add(headerLabel, BorderLayout.CENTER);

		header.add(headerWrapper);
		// Body panel
		JPanel body = new JPanel();
		body.setOpaque(false);
		body.setLayout(new BorderLayout());
		body.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Body title
		JLabel bodyTitle = new JLabel("BORROWING REQUEST FOR A BOOK", SwingConstants.CENTER);
		bodyTitle.setForeground(DARK_TEXT);
		bodyTitle.setFont(new Font("Arial", Font.BOLD, 18));
		body.add(bodyTitle, BorderLayout.NORTH);

		// Inner body with form fields using GridBagLayout (following SampleModal
		// pattern)
		JPanel innerBody = new JPanel();
		innerBody.setOpaque(false);
		innerBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		innerBody.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = -1;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Book Call Number row
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel callNumLabelWrapper = new JPanel();
		callNumLabelWrapper.setOpaque(false);
		callNumLabelWrapper.setPreferredSize(new Dimension(210, 30));
		callNumLabelWrapper.setLayout(new BorderLayout());
		JLabel callNumLabel = new JLabel("Book Call Number:");
		callNumLabel.setForeground(DARK_TEXT);
		callNumLabelWrapper.add(callNumLabel, BorderLayout.WEST);
		innerBody.add(callNumLabelWrapper, gbc);

		gbc.gridx = 1;
		RoundedTextField callNumField = new RoundedTextField(19, FIELD_RADIUS);
		callNumField.setPlaceholder("Enter Call Number");
		callNumField.setBorderColor(FIELD_BORDER);
		callNumField.setBorderThickness(1);
		innerBody.add(callNumField, gbc);

		// Equipment Name row
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel equipLabelWrapper = new JPanel();
		equipLabelWrapper.setOpaque(false);
		equipLabelWrapper.setPreferredSize(new Dimension(210, 30));
		equipLabelWrapper.setLayout(new BorderLayout());
		JLabel equipLabel = new JLabel("Equipment Name:");
		equipLabel.setForeground(DARK_TEXT);
		equipLabelWrapper.add(equipLabel, BorderLayout.WEST);
		innerBody.add(equipLabelWrapper, gbc);

		gbc.gridx = 1;
		RoundedTextField equipField = new RoundedTextField(19, FIELD_RADIUS);
		equipField.setPlaceholder("Enter Equipment Name");
		equipField.setBorderColor(FIELD_BORDER);
		equipField.setBorderThickness(1);
		innerBody.add(equipField, gbc);

		// Patron ID row with hint
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel patronLabelWrapper = new JPanel();
		patronLabelWrapper.setOpaque(false);
		patronLabelWrapper.setPreferredSize(new Dimension(210, 30));
		patronLabelWrapper.setLayout(new BorderLayout());

		JPanel twoPartLabelPanel = new JPanel();
		twoPartLabelPanel.setLayout(new BoxLayout(twoPartLabelPanel, BoxLayout.X_AXIS));
		twoPartLabelPanel.setOpaque(false);
		JLabel patronMainLabel = new JLabel("Patron ID:");
		patronMainLabel.setForeground(DARK_TEXT);
		JLabel patronHintLabel = new JLabel(" (2026XXXXXX/20-XXXX)");
		patronHintLabel.setForeground(Color.GRAY);
		twoPartLabelPanel.add(patronMainLabel);
		twoPartLabelPanel.add(patronHintLabel);

		patronLabelWrapper.add(twoPartLabelPanel, BorderLayout.WEST);
		innerBody.add(patronLabelWrapper, gbc);

		gbc.gridx = 1;
		RoundedTextField patronField = new RoundedTextField(19, FIELD_RADIUS);
		patronField.setPlaceholder("Enter Patron ID");
		patronField.setBorderColor(FIELD_BORDER);
		patronField.setBorderThickness(1);
		innerBody.add(patronField, gbc);

		body.add(innerBody, BorderLayout.CENTER);

		// Footer panel with buttons
		JPanel footer = new JPanel();
		footer.setPreferredSize(new Dimension(500, 100));
		footer.setBorder(BorderFactory.createEmptyBorder(0, 35, 10, 35));
		footer.setLayout(new GridLayout(2, 1, 0, 10));
		footer.setOpaque(false);

		// Confirm button (filled style)
		RoundedButton confirmBtn = new RoundedButton("CONFIRM REQUEST DETAILS", FIELD_RADIUS);
		confirmBtn.setPreferredSize(new Dimension(500, 40));
		confirmBtn.setBackground(MAROON);
		confirmBtn.setForeground(WHITE);
		confirmBtn.setFont(new Font("Arial", Font.BOLD, 12));
		confirmBtn.setFocusPainted(false);
		footer.add(confirmBtn);

		// Cancel button (outlined style)
		RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
		cancelBtn.setForeground(MAROON);
		cancelBtn.setBorderColor(MAROON);
		cancelBtn.setBorderThickness(1);
		cancelBtn.setPreferredSize(new Dimension(500, 40));
		cancelBtn.setFont(new Font("Arial", Font.BOLD, 12));
		cancelBtn.setFocusPainted(false);
		cancelBtn.addActionListener(e -> {
			// Clear all fields
			callNumField.setText("");
			equipField.setText("");
			patronField.setText("");

			// Reset placeholders
			callNumField.setPlaceholder("Enter Call Number");
			equipField.setPlaceholder("Enter Equipment Name");
			patronField.setPlaceholder("Enter Patron ID");
		});
		footer.add(cancelBtn);

		// Assemble modal
		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);

		panel.add(modal);

		// Frame settings
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
	}

	public static void main(String[] args) {
		new AddBorrowingRequestBook1();
	}
}
