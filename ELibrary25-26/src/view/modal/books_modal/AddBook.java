package view.modal.books_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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


    RoundedTextField callNumberField;
    RoundedTextField titleField;
    RoundedTextField authorField;
    RoundedTextField seriesField;

    JPanel innerBody;
    GridBagConstraints gbc;
    int seriesRowIndex;

    RoundedComboBox<String> typeCombo;

    List<Component> dynamicComponents = new ArrayList<>();

    public AddBook() {

        setOpaque(false);
        setLayout(new BorderLayout());

        /* ================= FONTS ================= */

        Font introRust26 = new Fonts("IntroRust", 36f).getAppliedFont();
        Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
        Font poppins16   = new Fonts("Poppins", 16f).getAppliedFont();
        Font poppins12   = new Fonts("Poppins", 12f).getAppliedFont();
        Font poppins10   = new Fonts("Poppins", 10f).getAppliedFont();

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

        /* ================= BODY ================= */

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

        /* ================= STATIC FIELDS ================= */

        // Call Number
        gbc.gridy++;
        gbc.gridx = 0;
        innerBody.add(label("Call Number:", poppins16), gbc);

        gbc.gridx = 1;
        callNumberField = textField("Enter call number", poppins10);
        innerBody.add(callNumberField, gbc);

        // Title
        gbc.gridy++;
        gbc.gridx = 0;
        innerBody.add(label("Title:", poppins16), gbc);

        gbc.gridx = 1;
        titleField = textField("Enter book title", poppins10);
        innerBody.add(titleField, gbc);

        // Author
        gbc.gridy++;
        gbc.gridx = 0;
        innerBody.add(label("Author:", poppins16), gbc);

        gbc.gridx = 1;
        authorField = textField("Enter author name", poppins10);
        innerBody.add(authorField, gbc);

        // Classification
        gbc.gridy++;
        gbc.gridx = 0;
        innerBody.add(label("Classification Code:", poppins16), gbc);

        gbc.gridx = 1;
        RoundedComboBox<String> classCombo =
                new RoundedComboBox<>(new String[]{"A","B","C","D","E","F"}, FIELD_RADIUS);
        styleCombo(classCombo, poppins10);
        innerBody.add(classCombo, gbc);

        // ✅ NEW COMBO ROW (always visible)
        gbc.gridy++;
        gbc.gridx = 0;
        innerBody.add(label("Collection Code:", poppins16), gbc);

        gbc.gridx = 1;
        RoundedComboBox<String> collectionCombo =
                new RoundedComboBox<>(new String[]{
                        "Bulacaniana",
                        "General Circulation",
                        "Fiction",
                        "Filipiniana",
                        "Reference",
                        "Reserve"
                }, FIELD_RADIUS);
        styleCombo(collectionCombo, poppins10);
        innerBody.add(collectionCombo, gbc);

        // Book Type
        gbc.gridy++;
        gbc.gridx = 0;
        innerBody.add(label("Book Type:", poppins16), gbc);

        gbc.gridx = 1;
        typeCombo = new RoundedComboBox<>(
                new String[]{"BORROWABLE", "NON-BORROWABLE"}, FIELD_RADIUS);
        styleCombo(typeCombo, poppins10);
        innerBody.add(typeCombo, gbc);

        // Save index for Series Title insertion
        seriesRowIndex = gbc.gridy + 1;

        typeCombo.addActionListener(e -> updateSeriesRow());

        body.add(innerBody, BorderLayout.CENTER);

        /* ================= FOOTER ================= */

        JPanel footer = new JPanel(new GridLayout(2, 1, 0, 10));
        footer.setBorder(new EmptyBorder(0, 35, 10, 35));
        footer.setOpaque(false);

        RoundedButton confirmBtn = new RoundedButton("CONFIRM & SAVE", FIELD_RADIUS);
        confirmBtn.setFont(poppins12);
        confirmBtn.setBackground(MAROON);
        confirmBtn.setForeground(Color.WHITE);
        footer.add(confirmBtn);

        RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
        cancelBtn.setFont(poppins12);
        cancelBtn.setForeground(MAROON);
        cancelBtn.setBorderColor(MAROON);
        cancelBtn.setBorderThickness(1);
        cancelBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w instanceof JDialog) w.dispose();
        });
        footer.add(cancelBtn);

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }

    /* ================= DYNAMIC SERIES ROW ================= */

    private void updateSeriesRow() {

        // Remove old dynamic components
        for (Component c : dynamicComponents) {
            innerBody.remove(c);
        }
        dynamicComponents.clear();

        if ("NON-BORROWABLE".equals(typeCombo.getSelectedItem())) {

            gbc.gridy = seriesRowIndex;
            gbc.gridx = 0;

            JLabel seriesLbl = label("Series Title:", new Fonts("Poppins", 16f).getAppliedFont());
            innerBody.add(seriesLbl, gbc);
            dynamicComponents.add(seriesLbl);

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

    /* ================= SMALL INLINE UTILITIES (NOT HELPERS FOR ROWS) ================= */

    private JLabel label(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        lbl.setForeground(DARK_TEXT);
        return lbl;
    }

    private RoundedTextField textField(String placeholder, Font font) {
        RoundedTextField tf = new RoundedTextField(19, FIELD_RADIUS);
        tf.setFont(font);
        tf.setPlaceholder(placeholder);
        tf.setBorderColor(FIELD_BORDER);
        tf.setBorderThickness(1);
        return tf;
    }

    private void styleCombo(RoundedComboBox<String> combo, Font font) {
        combo.setFont(font);
        combo.setPlaceholder("Select");
        combo.setBorderColor(FIELD_BORDER);
        combo.setBorderThickness(1);
        combo.setPreferredSize(new Dimension(200, 30));
    }
}