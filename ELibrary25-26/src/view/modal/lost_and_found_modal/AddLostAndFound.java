package view.modal.lost_and_found_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.LNFController;

import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class AddLostAndFound extends JPanel {

	static final Color MAROON = new Color(132, 43, 40);
	static final Color LIGHT_PINK = new Color(250, 236, 238);
	static final Color WHITE = Color.WHITE;
	static final Color DARK_TEXT = new Color(109, 35, 33);
	static final Color FIELD_BORDER = new Color(146, 76, 74);
	static final Color GRAY_HINT = Color.GRAY;

	static final int PANEL_RADIUS = 20;
	static final int FIELD_RADIUS = 15;


	public AddLostAndFound() {

		setOpaque(false);
		setLayout(new BorderLayout());

		/* ================= FONTS ================= */

		Font introRust26 = new Fonts("IntroRust", 36f).getAppliedFont();
		Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
		Font poppins16 = new Fonts("Poppins", 16f).getAppliedFont();
		Font poppins12 = new Fonts("Poppins", 12f).getAppliedFont();
		Font poppins10 = new Fonts("Poppins", 10f).getAppliedFont();

		/* ================= MODAL ================= */

		RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
		modal.setLayout(new BorderLayout());
		modal.setPreferredSize(new Dimension(500, 480));
		modal.setBackground(LIGHT_PINK);

		/* ================= HEADER ================= */

		JPanel header = new JPanel();
		header.setBackground(MAROON);
		header.setPreferredSize(new Dimension(500, 100));
		header.setBorder(new EmptyBorder(10, 0, 10, 10));

		JPanel headerWrapper = new JPanel(new BorderLayout());
		headerWrapper.setPreferredSize(new Dimension(450, 80));
		headerWrapper.setOpaque(false);

		ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
		Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
		JLabel logo = new JLabel(new ImageIcon(scaled));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		headerWrapper.add(logo, BorderLayout.WEST);

		JLabel headerLabel = new JLabel("IMS");
		headerLabel.setFont(introRust26);
		headerLabel.setForeground(WHITE);
		headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));
		headerWrapper.add(headerLabel, BorderLayout.CENTER);

		header.add(headerWrapper);

		/* ================= BODY ================= */

		JPanel body = new JPanel(new BorderLayout());
		body.setOpaque(false);
		body.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel bodyTitle = new JLabel("REPORT A MISSING ITEM", SwingConstants.CENTER);
		bodyTitle.setFont(introRust24);
		bodyTitle.setForeground(DARK_TEXT);
		body.add(bodyTitle, BorderLayout.NORTH);

		JPanel innerBody = new JPanel(new GridBagLayout());
		innerBody.setOpaque(false);
		innerBody.setBorder(new EmptyBorder(10, 10, 10, 10));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = -1;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Reference height (for combo sizing)
		RoundedTextField heightRef = new RoundedTextField(19, FIELD_RADIUS);
		int fieldHeight = heightRef.getPreferredSize().height;

		/* ================= FORM ROWS ================= */

		// Missing Item
		gbc.gridy++;
		gbc.gridx = 0;
		JLabel itemLbl = new JLabel("Missing Item:");
		itemLbl.setFont(poppins16);
		itemLbl.setForeground(DARK_TEXT);
		innerBody.add(itemLbl, gbc);

		gbc.gridx = 1;
		RoundedTextField itemField = new RoundedTextField(19, FIELD_RADIUS);
		itemField.setFont(poppins10);
		itemField.setPlaceholder("Enter lost item name");
		itemField.setBorderColor(FIELD_BORDER);
		itemField.setBorderThickness(1);
		innerBody.add(itemField, gbc);

		// Owner Name (optional)
		gbc.gridy++;
		gbc.gridx = 0;
		JPanel ownerLblWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		ownerLblWrap.setOpaque(false);
		JLabel ownerLbl = new JLabel("Owner Name:");
		ownerLbl.setFont(poppins16);
		ownerLbl.setForeground(DARK_TEXT);
		JLabel ownerHint = new JLabel(" (Optional)");
		ownerHint.setFont(poppins10);
		ownerHint.setForeground(GRAY_HINT);
		ownerLblWrap.add(ownerLbl);
		ownerLblWrap.add(ownerHint);
		innerBody.add(ownerLblWrap, gbc);

		gbc.gridx = 1;
		RoundedTextField ownerField = new RoundedTextField(19, FIELD_RADIUS);
		ownerField.setFont(poppins10);
		ownerField.setPlaceholder("Enter owner name");
		ownerField.setBorderColor(FIELD_BORDER);
		ownerField.setBorderThickness(1);
		innerBody.add(ownerField, gbc);

		// Description
		gbc.gridy++;
		gbc.gridx = 0;
		JLabel descLbl = new JLabel("Item Description:");
		descLbl.setFont(poppins16);
		descLbl.setForeground(DARK_TEXT);
		innerBody.add(descLbl, gbc);

		gbc.gridx = 1;
		RoundedTextField descField = new RoundedTextField(19, FIELD_RADIUS);
		descField.setFont(poppins10);
		descField.setPlaceholder("Enter description");
		descField.setBorderColor(FIELD_BORDER);
		descField.setBorderThickness(1);
		innerBody.add(descField, gbc);

		// Floor
		gbc.gridy++;
		gbc.gridx = 0;
		JLabel floorLbl = new JLabel("Lost on Floor:");
		floorLbl.setFont(poppins16);
		floorLbl.setForeground(DARK_TEXT);
		innerBody.add(floorLbl, gbc);

		String[] floor = { "1st", "2nd", "3rd", "4th", "5th", "6th", "7th" };

		gbc.gridx = 1;
		RoundedComboBox<String> floorField = new RoundedComboBox<>(floor, FIELD_RADIUS);
		floorField.setFont(poppins10);
		floorField.setBorderColor(FIELD_BORDER);
		floorField.setBorderThickness(1);
		floorField.setPreferredSize(new Dimension(200, fieldHeight));
		innerBody.add(floorField, gbc);

		// Status
		gbc.gridy++;
		gbc.gridx = 0;
		JLabel statusLbl = new JLabel("Item Status:");
		statusLbl.setFont(poppins16);
		statusLbl.setForeground(DARK_TEXT);
		innerBody.add(statusLbl, gbc);
		String[] status = { "Missing", "Surrendered", "Found" };
		
		gbc.gridx = 1;
		RoundedComboBox<String> statusField = new RoundedComboBox<>(status, FIELD_RADIUS);
		statusField.setFont(poppins10);
		statusField.setBorderColor(FIELD_BORDER);
		statusField.setBorderThickness(1);
		statusField.setPreferredSize(new Dimension(200, fieldHeight));
		innerBody.add(statusField, gbc);

		// Last Seen Date
		gbc.gridy++;
		gbc.gridx = 0;
		JLabel dateLbl = new JLabel("Last Seen:");
		dateLbl.setFont(poppins16);
		dateLbl.setForeground(DARK_TEXT);
		innerBody.add(dateLbl, gbc);

		gbc.gridx = 1;
		RoundedTextField dateField = new RoundedTextField(19, FIELD_RADIUS);
		dateField.setFont(poppins10);
		dateField.setPlaceholder("yyyy-MM-dd");
		dateField.setBorderColor(FIELD_BORDER);
		dateField.setBorderThickness(1);
		innerBody.add(dateField, gbc);

		body.add(innerBody, BorderLayout.CENTER);

		/* ================= FOOTER ================= */

		JPanel footer = new JPanel(new GridLayout(2, 1, 0, 10));
		footer.setBorder(new EmptyBorder(0, 35, 10, 35));
		footer.setOpaque(false);

		RoundedButton confirmBtn = new RoundedButton("CONFIRM & SAVE", FIELD_RADIUS);
		confirmBtn.setFont(poppins12);
		confirmBtn.setBackground(MAROON);
		confirmBtn.setForeground(WHITE);
		confirmBtn.addActionListener(e -> {
		    // Get all field values
		    String item = itemField.getRealText().trim();
		    String owner = ownerField.getRealText().trim();
		    String desc = descField.getRealText().trim();
		    String floorTxt = floorField.getSelectedItem().toString();
		    String statusTxt = statusField.getSelectedItem().toString();
		    String date = dateField.getRealText().trim();

		    // Validate required fields
		    if (item.isEmpty() || date.isEmpty()) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Please fill in Item and Date fields.",
		            "Missing Information",
		            JOptionPane.WARNING_MESSAGE
		        );
		        return;
		    }

		    // Normalize/validate date
		    String normalizedDate = validateAndNormalizeDate(date);
		    if (normalizedDate == null) {
		        JOptionPane.showMessageDialog(
		            this,
		            "Please fill in the date field with a valid yyyy-mm-dd date format.",
		            "Incorrect/Invalid Date Format",
		            JOptionPane.WARNING_MESSAGE
		        );
		        return;
		    }

		    // Optional fields: store as null if empty
		    if (owner.isEmpty()) owner = null;
		    if (desc.isEmpty()) desc = null;

		    // Prepare report details
		    String[] reportDetails = {
		        item,
		        owner,
		        desc,
		        floorTxt,
		        statusTxt,
		        normalizedDate
		    };
		    
		    // Pass to controller and close modal
		    new LNFController(reportDetails);
		    Window w = SwingUtilities.getWindowAncestor(this);
		    if (w instanceof JDialog) w.dispose();
		});
		footer.add(confirmBtn);

		RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
		cancelBtn.setFont(poppins12);
		cancelBtn.setForeground(MAROON);
		cancelBtn.setBorderColor(MAROON);
		cancelBtn.setBorderThickness(1);
		cancelBtn.addActionListener(e -> {
			Window w = SwingUtilities.getWindowAncestor(this);
			if (w instanceof JDialog)
				w.dispose();
		});
		footer.add(cancelBtn);

		/* ================= ASSEMBLY ================= */

		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);

		add(modal, BorderLayout.CENTER);
	}
	
	public static String validateAndNormalizeDate(String date) {
	    if (date == null) {
	        return null;
	    }

	    date = date.trim();
	    String[] parts = date.split("-");

	    // Must be year-month-day
	    if (parts.length != 3) {
	        return null;
	    }

	    String yearPart  = parts[0];
	    String monthPart = parts[1];
	    String dayPart   = parts[2];

	    // Year must be exactly 4 digits
	    if (!yearPart.matches("\\d{4}")) {
	        return null;
	    }

	    // Month and day: 1 or 2 digits only
	    if (!monthPart.matches("\\d{1,2}") || !dayPart.matches("\\d{1,2}")) {
	        return null;
	    }

	    int year  = Integer.parseInt(yearPart);
	    int month = Integer.parseInt(monthPart);
	    int day   = Integer.parseInt(dayPart);

	    // Month range
	    if (month < 1 || month > 12) {
	        return null;
	    }

	    int[] daysInMonth = {
	        31, 28, 31, 30, 31, 30,
	        31, 31, 30, 31, 30, 31
	    };

	    // Leap year adjustment
	    boolean isLeapYear =
	        (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);

	    if (month == 2 && isLeapYear) {
	        daysInMonth[1] = 29;
	    }

	    // Day range
	    if (day < 1 || day > daysInMonth[month - 1]) {
	        return null;
	    }

	    // ✅ Normalize to YYYY-MM-DD
	    String normalizedMonth = (month < 10 ? "0" : "") + month;
	    String normalizedDay   = (day   < 10 ? "0" : "") + day;

	    return year + "-" + normalizedMonth + "-" + normalizedDay;
	}
}