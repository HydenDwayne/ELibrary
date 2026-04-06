package view.modal.lost_and_found_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.LNFController;

import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class MarkAsFound extends JPanel {

	static final Color MAROON = new Color(132, 43, 40);
	static final Color LIGHT_PINK = new Color(250, 236, 238);
	static final Color WHITE = Color.WHITE;
	static final Color DARK_TEXT = new Color(109, 35, 33);
	static final Color FIELD_BORDER = new Color(146, 76, 74);

	static final int PANEL_RADIUS = 20;
	static final int FIELD_RADIUS = 15;

	private String patronNumber = "";
	private String itemNum;

	public MarkAsFound(String itemNum) {
		this.itemNum = itemNum;
		setOpaque(false);
		setLayout(new BorderLayout());

		/* ================= FONTS ================= */

		Font introRust36 = new Fonts("IntroRust", 36f).getAppliedFont();
		Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
		Font poppins16 = new Fonts("Poppins", 16f).getAppliedFont();
		Font poppins12 = new Fonts("Poppins", 12f).getAppliedFont();
		Font poppins10 = new Fonts("Poppins", 10f).getAppliedFont();

		/* ================= MODAL ================= */

		RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
		modal.setLayout(new BorderLayout());
		modal.setPreferredSize(new Dimension(520, 250)); // smaller height
		modal.setBackground(LIGHT_PINK);

		/* ================= HEADER ================= */

		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(MAROON);
		header.setPreferredSize(new Dimension(520, 100));
		header.setBorder(new EmptyBorder(10, 0, 10, 10));

		ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
		Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(scaled));

		JLabel headerLabel = new JLabel("LOST AND FOUND");
		headerLabel.setFont(introRust36);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));

		header.add(logo, BorderLayout.WEST);
		header.add(headerLabel, BorderLayout.CENTER);

		/* ================= BODY ================= */

		JPanel body = new JPanel();
		body.setOpaque(false);
		body.setBorder(new EmptyBorder(10, 20, 10, 20));

		JLabel bodyTitle = new JLabel("ENTER PATRON NUMBER", SwingConstants.CENTER);
		bodyTitle.setFont(introRust24);
		bodyTitle.setForeground(DARK_TEXT);
		body.add(bodyTitle, BorderLayout.NORTH);

		JPanel innerBody = new JPanel(new GridBagLayout());
		innerBody.setOpaque(false);
		innerBody.setBorder(new EmptyBorder(15, 25, 15, 25));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.insets = new Insets(6, 6, 6, 6);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.weightx = 0.5;

		Dimension labelSize = new Dimension(220, 40);

		/* ================= SINGLE ROW ================= */

		// Wrap label in a panel to match field width
		JPanel labelWrapper = new JPanel(new BorderLayout());
		labelWrapper.setOpaque(false);
		labelWrapper.setPreferredSize(new Dimension(250, 30)); // same width as the field
		JLabel patronLbl = new JLabel("Claimed by Patron Number:");
		patronLbl.setFont(poppins16);
		patronLbl.setForeground(DARK_TEXT);
		labelWrapper.add(patronLbl, BorderLayout.WEST);

		gbc.gridx = 0;
		innerBody.add(labelWrapper, gbc);

		gbc.gridx = 1;
		RoundedTextField patronField = new RoundedTextField(19, FIELD_RADIUS);
		patronField.setFont(poppins10);
		patronField.setPlaceholder("Enter Patron Number");
		patronField.setBorderColor(FIELD_BORDER);
		patronField.setBorderThickness(1);
		innerBody.add(patronField, gbc);

		body.add(innerBody);

		/* ================= FOOTER ================= */

		JPanel footer = new JPanel(new GridLayout(1, 2, 10, 0));
		footer.setBorder(new EmptyBorder(10, 35, 10, 35));
		footer.setOpaque(false);

		RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
		cancelBtn.setFont(poppins12);
		cancelBtn.setForeground(MAROON);
		cancelBtn.setBorderColor(MAROON);
		cancelBtn.setBorderThickness(1);

		cancelBtn.addActionListener(e -> {
			Window w = SwingUtilities.getWindowAncestor(this);
			if (w instanceof JDialog) {
				w.dispose();
			}
		});

		RoundedButton confirmBtn = new RoundedButton("CONFIRM", FIELD_RADIUS);
		confirmBtn.setFont(poppins12);
		confirmBtn.setBackground(MAROON);
		confirmBtn.setForeground(WHITE);

		confirmBtn.addActionListener(e -> {
			if (patronField.getRealText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter Patron Number");
			} else {
				patronNumber = patronField.getRealText();

				new LNFController(this, patronNumber, itemNum);
				Window w = SwingUtilities.getWindowAncestor(this);
				if (w instanceof JDialog) {
					w.dispose();
				}
			}
		});

		footer.add(cancelBtn);
		footer.add(confirmBtn);

		/* ================= ASSEMBLY ================= */

		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);

		add(modal, BorderLayout.CENTER);
	}

	public String getPatronNumber() {
		return patronNumber;
	}
}