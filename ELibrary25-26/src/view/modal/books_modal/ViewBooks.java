package view.modal.books_modal;

import javax.swing.*;
import java.awt.*;
import controller.BookController;import javax.swing.border.EmptyBorder;
import view.RoundedComponents.RoundedButton;
import view.RoundedComponents.RoundedPanel;
import view.RoundedComponents.RoundedTextField;
import view.RoundedComponents.RoundedComboBox;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class ViewBooks extends JPanel {

    /* ================= CONSTANTS ================= */

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    /* ================= FIELDS ================= */

    public RoundedTextField bookTitleField;
    public RoundedTextField authorField;
    public RoundedComboBox<String> collectionCombo;
    public RoundedTextField seriesField;

    public RoundedTextField yearField;
    public RoundedComboBox<String> typeCombo;
    public RoundedComboBox<String> availCombo;
    public RoundedTextField classField;

    public RoundedButton saveBtn;
    public RoundedButton closeBtn;

    public ViewBooks(String callNumber) {

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
        modal.setPreferredSize(new Dimension(500, 600));
        modal.setBackground(LIGHT_PINK);

        /* ================= HEADER ================= */

        JPanel header = new JPanel();
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(500, 100));
        header.setBorder(new EmptyBorder(10, 0, 10, 10));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setOpaque(false);
        headerWrapper.setPreferredSize(new Dimension(450, 80));

        ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
        Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel headerLabel = new JLabel("BOOKS");
        headerLabel.setFont(introRust26);
        headerLabel.setForeground(WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));

        headerWrapper.add(logo, BorderLayout.WEST);
        headerWrapper.add(headerLabel, BorderLayout.CENTER);
        header.add(headerWrapper);

        /* ================= BODY ================= */

        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("DETAILED VIEW FOR A BOOK", SwingConstants.CENTER);
        title.setFont(introRust24);
        title.setForeground(DARK_TEXT);
        body.add(title, BorderLayout.NORTH);

        JPanel innerBody = new JPanel(new GridBagLayout());
        innerBody.setOpaque(false);
        innerBody.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = -1;

        /* ================= FIELDS ================= */

        // ---- Book Title ----
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel bookTitleLbl = new JLabel("Book Title:");
        bookTitleLbl.setFont(poppins16);
        bookTitleLbl.setForeground(DARK_TEXT);
        innerBody.add(bookTitleLbl, gbc);

        gbc.gridx = 1;
        bookTitleField = new RoundedTextField(19, FIELD_RADIUS);
        bookTitleField.setFont(poppins10);
        bookTitleField.setBorderColor(FIELD_BORDER);
        bookTitleField.setBorderThickness(1);
        innerBody.add(bookTitleField, gbc);

        // ---- Author ----
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel authorLbl = new JLabel("Author:");
        authorLbl.setFont(poppins16);
        authorLbl.setForeground(DARK_TEXT);
        innerBody.add(authorLbl, gbc);

        gbc.gridx = 1;
        authorField = new RoundedTextField(19, FIELD_RADIUS);
        authorField.setFont(poppins10);
        authorField.setBorderColor(FIELD_BORDER);
        authorField.setBorderThickness(1);
        innerBody.add(authorField, gbc);

        // ---- Publication Year (2000–2016) ----
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel yearLbl = new JLabel("Publication Year:");
        yearLbl.setFont(poppins16);
        yearLbl.setForeground(DARK_TEXT);
        innerBody.add(yearLbl, gbc);

        gbc.gridx = 1;
        Integer[] years = new Integer[17];
        for (int i = 0; i < years.length; i++) years[i] = 2000 + i;

        yearField = new RoundedTextField(19, FIELD_RADIUS);
        yearField.setFont(poppins10);
        yearField.setBorderColor(FIELD_BORDER);
        yearField.setBorderThickness(1);
        yearField.setEnabled(false);
        innerBody.add(yearField, gbc);

        // ---- Book Type ----
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel typeLbl = new JLabel("Book Type:");
        typeLbl.setFont(poppins16);
        typeLbl.setForeground(DARK_TEXT);
        innerBody.add(typeLbl, gbc);

        gbc.gridx = 1;
        typeCombo = new RoundedComboBox<>(new String[]{"NBB", "BB"}, FIELD_RADIUS);
        typeCombo.setFont(poppins10);
        typeCombo.setBorderColor(FIELD_BORDER);
        typeCombo.setBorderThickness(1);
        typeCombo.addActionListener(e -> {
        	if (typeCombo.getSelectedIndex() == 0) {
        		availCombo.setEnabled(false);
        		seriesField.setEnabled(true);
        	}
        	if (typeCombo.getSelectedIndex() == 1) {
        		availCombo.setEnabled(true);
        		seriesField.setEnabled(false);
        	}
        });
        innerBody.add(typeCombo, gbc);

        // ---- Collection Code ----
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel collectionLbl = new JLabel("Collection Code:");
        collectionLbl.setFont(poppins16);
        collectionLbl.setForeground(DARK_TEXT);
        innerBody.add(collectionLbl, gbc);

        String[] collections = {
        		"Bulacaniana Collection", 
        		"General Circulation Section", 
        		"Fiction Collection",
        		"Filipiniana Collection",
        		"Reference Collection",
        		"Reserve Collection",
        		"Theses and Dissertations"
        		};
        
        gbc.gridx = 1;
        collectionCombo = new RoundedComboBox<>(collections, FIELD_RADIUS);
        collectionCombo.setFont(poppins10);
        collectionCombo.setBorderColor(FIELD_BORDER);
        collectionCombo.setBorderThickness(1);
        innerBody.add(collectionCombo, gbc);

        // ---- Classification Code (DAO‑populated) ----
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel classLbl = new JLabel("Classification Code:");
        classLbl.setFont(poppins16);
        classLbl.setForeground(DARK_TEXT);
        innerBody.add(classLbl, gbc);

        gbc.gridx = 1;
        classField = new RoundedTextField(19, FIELD_RADIUS);
        classField.setFont(poppins10);
        classField.setBorderColor(FIELD_BORDER);
        classField.setBorderThickness(1);
        classField.setEnabled(false);
        innerBody.add(classField, gbc);

        // ---- Availability Status ----
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel availLbl = new JLabel("Availability Status:");
        availLbl.setFont(poppins16);
        availLbl.setForeground(DARK_TEXT);
        innerBody.add(availLbl, gbc);

        gbc.gridx = 1;
        availCombo = new RoundedComboBox<>(
            new String[]{"Available", "Borrowed", "Archived"},
            FIELD_RADIUS
        );
        availCombo.setFont(poppins10);
        availCombo.setBorderColor(FIELD_BORDER);
        availCombo.setBorderThickness(1);
        innerBody.add(availCombo, gbc);

        // ---- Series Title ----
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel seriesLbl = new JLabel("Series Title:");
        seriesLbl.setFont(poppins16);
        seriesLbl.setForeground(DARK_TEXT);
        innerBody.add(seriesLbl, gbc);

        gbc.gridx = 1;
        seriesField = new RoundedTextField(19, FIELD_RADIUS);
        seriesField.setFont(poppins10);
        seriesField.setBorderColor(FIELD_BORDER);
        seriesField.setBorderThickness(1);
        innerBody.add(seriesField, gbc);

        /* ================= CONTROLLER ================= */

        new BookController(this, callNumber); // loads data + classification codes

        body.add(innerBody, BorderLayout.CENTER);

        /* ================= FOOTER ================= */

        JPanel footer = new JPanel(new GridLayout(2, 1, 0, 10));
        footer.setBorder(new EmptyBorder(10, 35, 10, 35));
        footer.setOpaque(false);

        saveBtn = new RoundedButton("SAVE", FIELD_RADIUS);
        saveBtn.setFont(poppins12);
        saveBtn.setBackground(MAROON);
        saveBtn.setForeground(WHITE);
        footer.add(saveBtn);

        closeBtn = new RoundedButton("CLOSE", FIELD_RADIUS);
        closeBtn.setFont(poppins12);
        closeBtn.setForeground(MAROON);
        closeBtn.setBorderColor(MAROON);
        closeBtn.setBorderThickness(1);
        closeBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });
        footer.add(closeBtn);

        /* ================= ASSEMBLY ================= */

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }
}

