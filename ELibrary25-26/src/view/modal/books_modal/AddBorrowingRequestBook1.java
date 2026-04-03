package view.modal.books_modal;

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

public class AddBorrowingRequestBook1 extends JPanel {

	static final Color MAROON = new Color(132, 43, 40); // #842b28 from sample
	static final Color LIGHT_PINK = new Color(250, 236, 238); // #faecee from sample
	static final Color WHITE = Color.WHITE;
	static final Color DARK_TEXT = new Color(109, 35, 33); // #6d2321 from sample
	static final Color FIELD_BORDER = new Color(146, 76, 74); // #924c4a from sample

	static final int PANEL_RADIUS = 20;
	static final int FIELD_RADIUS = 15;
	
	private final BorrowBookModal dialog;

	static String imgFilePath = FilePath.getImgFilePath();

	public AddBorrowingRequestBook1(BorrowBookModal dialog) {
		this.dialog = dialog;

		setBackground(Color.LIGHT_GRAY);
		setOpaque(false);

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
		headerWrapper.setPreferredSize(new Dimension(450, 80));
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

		Fonts introRust24 = new Fonts("IntroRust", 24f);
		Font introRustStyle24 = introRust24.getAppliedFont();

		Fonts poppins16 = new Fonts("Poppins", 16f);
		Font poppinsStyle16 = poppins16.getAppliedFont();

		Fonts poppins12 = new Fonts("Poppins", 12f);
		Font poppinsStyle12 = poppins12.getAppliedFont();

		Fonts poppins10 = new Fonts("Poppins", 10f);
		Font poppinsStyle10 = poppins10.getAppliedFont();

		// Body panel
		JPanel body = new JPanel();
		body.setOpaque(false);
		body.setLayout(new BorderLayout());
		body.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Body title
		JLabel bodyTitle = new JLabel("BORROWING REQUEST FOR A BOOK", SwingConstants.CENTER);
		bodyTitle.setForeground(DARK_TEXT);
		bodyTitle.setFont(introRustStyle24);
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

		callNumLabel.setFont(poppinsStyle16);
		equipLabel.setFont(poppinsStyle16);
		patronMainLabel.setFont(poppinsStyle16);
		patronHintLabel.setFont(poppinsStyle10);

		callNumLabel.setForeground(DARK_TEXT);
		equipLabel.setForeground(DARK_TEXT);
		patronMainLabel.setForeground(DARK_TEXT);
		patronHintLabel.setForeground(Color.GRAY);

		callNumField.setFont(poppinsStyle10);
		equipField.setFont(poppinsStyle10);
		patronField.setFont(poppinsStyle10);

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
		confirmBtn.addActionListener(e -> {
			dialog.setStep2();
		});
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
			Window window = SwingUtilities.getWindowAncestor(this);
			if (window instanceof JDialog) {
				window.dispose();
			}
		});

		footer.add(cancelBtn);

		confirmBtn.setFont(poppinsStyle12);
		cancelBtn.setFont(poppinsStyle12);

		// Assemble modal
		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);

		add(modal);

		// Frame settings

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				requestFocusInWindow();
			}
		});
	}
}
