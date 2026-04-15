package view.modal.books_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.BookController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class AddBook extends JPanel {

	static final Color MAROON = new Color(132, 43, 40);
	static final Color LIGHT_PINK = new Color(250, 236, 238);
	static final Color DARK_TEXT = new Color(109, 35, 33);
	static final Color FIELD_BORDER = new Color(146, 76, 74);

	static final int PANEL_RADIUS = 20;
	static final int FIELD_RADIUS = 15;

	public RoundedTextField callNumberField;
	public RoundedTextField titleField;
	public RoundedTextField authorField;
	public RoundedTextField seriesField;

	public RoundedComboBox<String> yearCombo;
	public RoundedComboBox<String> classCombo;
	public RoundedComboBox<String> collectionCombo;
	public RoundedComboBox<String> typeCombo;

	JPanel innerBody;
	GridBagConstraints gbc;
	int seriesRowIndex;

	List<Component> dynamicComponents = new ArrayList<>();

	

	Font introRust26 = new Fonts("IntroRust", 36f).getAppliedFont();
	Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
	Font poppins16 = new Fonts("Poppins", 16f).getAppliedFont();
	Font poppins12 = new Fonts("Poppins", 12f).getAppliedFont();
	Font poppins10 = new Fonts("Poppins", 10f).getAppliedFont();

	public AddBook() {

		setOpaque(false);
		setLayout(new BorderLayout());

		

		RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
		modal.setLayout(new BorderLayout());
		modal.setPreferredSize(new Dimension(500, 600));
		modal.setBackground(LIGHT_PINK);

		

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

		JLabel headerLabel = new JLabel("BOOKS");
		headerLabel.setFont(introRust26);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));

		headerWrapper.add(logo, BorderLayout.WEST);
		headerWrapper.add(headerLabel, BorderLayout.CENTER);
		header.add(headerWrapper);

		

		JPanel body = new JPanel(new BorderLayout());
		body.setOpaque(false);
		body.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel bodyTitle = new JLabel("ADD A BOOK RECORD", SwingConstants.CENTER);
		bodyTitle.setFont(introRust24);
		bodyTitle.setForeground(DARK_TEXT);
		body.add(bodyTitle, BorderLayout.NORTH);

		innerBody = new JPanel(new GridBagLayout());
		innerBody.setOpaque(false);
		innerBody.setBorder(new EmptyBorder(10, 10, 10, 10));

		gbc = new GridBagConstraints();
		gbc.gridy = -1;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		

		gbc.gridy++;
		gbc.gridx = 0;
		JLabel callNumberLabel = new JLabel("Call Number:");
		callNumberLabel.setFont(poppins16);
		callNumberLabel.setForeground(DARK_TEXT);
		innerBody.add(callNumberLabel, gbc);

		gbc.gridx = 1;
		callNumberField = new RoundedTextField(19, FIELD_RADIUS);
		callNumberField.setFont(poppins10);
		callNumberField.setPlaceholder("Enter call number");
		callNumberField.setBorderColor(FIELD_BORDER);
		callNumberField.setBorderThickness(1);
		innerBody.add(callNumberField, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		JLabel titleLabel = new JLabel("Title:");
		titleLabel.setFont(poppins16);
		titleLabel.setForeground(DARK_TEXT);
		innerBody.add(titleLabel, gbc);

		gbc.gridx = 1;
		titleField = new RoundedTextField(19, FIELD_RADIUS);
		titleField.setFont(poppins10);
		titleField.setPlaceholder("Enter book title");
		titleField.setBorderColor(FIELD_BORDER);
		titleField.setBorderThickness(1);
		innerBody.add(titleField, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		JLabel authorLabel = new JLabel("Author:");
		authorLabel.setFont(poppins16);
		authorLabel.setForeground(DARK_TEXT);
		innerBody.add(authorLabel, gbc);

		gbc.gridx = 1;
		authorField = new RoundedTextField(19, FIELD_RADIUS);
		authorField.setFont(poppins10);
		authorField.setPlaceholder("Enter author name");
		authorField.setBorderColor(FIELD_BORDER);
		authorField.setBorderThickness(1);
		innerBody.add(authorField, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		JLabel yearLabel = new JLabel("Publication Year:");
		yearLabel.setFont(poppins16);
		yearLabel.setForeground(DARK_TEXT);
		innerBody.add(yearLabel, gbc);

		int startYear = 1980;
		int endYear = 2026;

		String[] years = new String[endYear - startYear + 1];

		for (int i = 0; i < years.length; i++) {
			years[i] = String.valueOf(endYear - i);
		}

		gbc.gridx = 1;
		yearCombo = new RoundedComboBox<>(years, FIELD_RADIUS);
		yearCombo.setFont(poppins10);
		yearCombo.setPlaceholder("Select");
		yearCombo.setBorderColor(FIELD_BORDER);
		yearCombo.setBorderThickness(1);
		yearCombo.setPreferredSize(new Dimension(200, 30));
		innerBody.add(yearCombo, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		JLabel classLabel = new JLabel("Classification Code:");
		classLabel.setFont(poppins16);
		classLabel.setForeground(DARK_TEXT);
		innerBody.add(classLabel, gbc);

		new BookController(this, gbc, poppins10, innerBody, FIELD_RADIUS, FIELD_BORDER);

		gbc.gridy++;
		gbc.gridx = 0;
		JLabel collectionLabel = new JLabel("Collection Code:");
		collectionLabel.setFont(poppins16);
		collectionLabel.setForeground(DARK_TEXT);
		innerBody.add(collectionLabel, gbc);

		String[] initial = { "Bulacaniana Collection", "General Circulation Collection", "Fiction Collection",
				"Filipiniana Collection", "Reference Collection", "Reserve Collection", "Theses and Dissertations" };

		gbc.gridx = 1;
		collectionCombo = new RoundedComboBox<>(initial, FIELD_RADIUS);
		collectionCombo.setFont(poppins10);
		collectionCombo.setPlaceholder("Select");
		collectionCombo.setBorderColor(FIELD_BORDER);
		collectionCombo.setBorderThickness(1);
		collectionCombo.setPreferredSize(new Dimension(200, 30));
		innerBody.add(collectionCombo, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		JLabel typeLabel = new JLabel("Book Type:");
		typeLabel.setFont(poppins16);
		typeLabel.setForeground(DARK_TEXT);
		innerBody.add(typeLabel, gbc);

		gbc.gridx = 1;
		typeCombo = new RoundedComboBox<>(new String[] { "BORROWABLE", "NON-BORROWABLE" }, FIELD_RADIUS);
		typeCombo.setFont(poppins10);
		typeCombo.setPlaceholder("Select");
		typeCombo.setBorderColor(FIELD_BORDER);
		typeCombo.setBorderThickness(1);
		typeCombo.setPreferredSize(new Dimension(200, 30));
		typeCombo.addActionListener(e -> {
			reloadCollection();
		});
		innerBody.add(typeCombo, gbc);
		reloadCollection();
		seriesRowIndex = gbc.gridy + 1;
		typeCombo.addActionListener(e -> updateSeriesRow());
		reloadCollection();
		body.add(innerBody, BorderLayout.CENTER);

		

		JPanel footer = new JPanel(new GridLayout(2, 1, 0, 10));
		footer.setBorder(new EmptyBorder(0, 35, 10, 35));
		footer.setOpaque(false);

		RoundedButton confirmBtn = new RoundedButton("CONFIRM & SAVE", FIELD_RADIUS);
		confirmBtn.setFont(poppins12);
		confirmBtn.setBackground(MAROON);
		confirmBtn.setForeground(Color.WHITE);
		confirmBtn.addActionListener(e -> {

			String callNumber = callNumberField.getRealText().trim();
			String title = titleField.getRealText().trim();
			String author = authorField.getRealText().trim();
			String year = yearCombo.getSelectedItem() != null ? yearCombo.getSelectedItem().toString() : null;
			String classCode = classCombo.getSelectedItem() != null ? classCombo.getSelectedItem().toString() : null;
			String collection = collectionCombo.getSelectedItem() != null ? collectionCombo.getSelectedItem().toString()
					: null;
			String type = typeCombo.getSelectedItem() != null ? typeCombo.getSelectedItem().toString() : null;

			String[] classArr = classCode.split("-");

			String series = null;
			if ("NON-BORROWABLE".equals(type)) {
				if (seriesField != null) {
					series = seriesField.getRealText().trim();
				}
			}

			if (callNumber.isEmpty() || title.isEmpty() || author.isEmpty() || year == null || classCode == null
					|| collection == null || type == null
					|| ("NON-BORROWABLE".equals(type) && (series == null || series.isEmpty()))) {

				JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Missing Information",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			BookController comp = new BookController(callNumber);
			if (comp.checkIfExists()) {
				JOptionPane.showMessageDialog(this, "Book with that call number already exists.", "Existence error",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (!isValidYear(year)) {
				JOptionPane.showMessageDialog(this, "Enter a valid year (e.g. 2026)", "Format Error",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			String[] bookDetails = { callNumber, title, author, year, type, collection, classArr[0].trim(), series };

			new BookController(bookDetails);

			Window w = SwingUtilities.getWindowAncestor(this);
			if (w instanceof JDialog)
				w.dispose();
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

		modal.add(header, BorderLayout.NORTH);
		modal.add(body, BorderLayout.CENTER);
		modal.add(footer, BorderLayout.SOUTH);

		add(modal, BorderLayout.CENTER);
		reloadCollection();
	}
	
	public void reloadCollection() {
		if (typeCombo.getSelectedIndex() == 1) {
			String[] NBB = { "Reference Collection", "Reserve Collection", "Theses and Dissertations" };
			collectionCombo.setModel(new DefaultComboBoxModel<>(NBB));
		} else {
			String[] BB = { "Bulacaniana Collection", "General Circulation Collection", "Fiction Collection",
			"Filipiniana Collection" };
			collectionCombo.setModel(new DefaultComboBoxModel<>(BB));
		}
	}

	

	private void updateSeriesRow() {

		for (Component c : dynamicComponents) {
			innerBody.remove(c);
		}
		dynamicComponents.clear();

		if ("NON-BORROWABLE".equals(typeCombo.getSelectedItem())) {

			gbc.gridy = seriesRowIndex;
			gbc.gridx = 0;

			JLabel lbl = new JLabel("Series Title:");
			lbl.setFont(poppins16);
			lbl.setForeground(DARK_TEXT);

			innerBody.add(lbl, gbc);
			dynamicComponents.add(lbl);

			gbc.gridx = 1;
			seriesField = new RoundedTextField(19, FIELD_RADIUS);
			seriesField.setFont(new Fonts("Poppins", 10f).getAppliedFont());
			seriesField.setPlaceholder("Enter series title");
			seriesField.setBorderColor(FIELD_BORDER);
			seriesField.setBorderThickness(1);
			innerBody.add(seriesField, gbc);
			dynamicComponents.add(seriesField);
		}

		innerBody.revalidate();
		innerBody.repaint();
	}

	public static boolean isValidYear(String year) {
		if (year == null) {
			return false;
		}

		year = year.trim();

		if (year.length() != 4) {
			return false;
		}

		for (int i = 0; i < year.length(); i++) {
			if (!Character.isDigit(year.charAt(i))) {
				return false;
			}
		}

		return true;
	}

}