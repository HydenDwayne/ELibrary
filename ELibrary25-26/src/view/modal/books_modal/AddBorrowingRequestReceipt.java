package view.modal.books_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import view.RoundedComponents.RoundedButton;
import view.RoundedComponents.RoundedPanel;
import view.RoundedComponents.RoundedTextField;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class AddBorrowingRequestReceipt extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;



    private final BorrowBookModal dialog;

    public AddBorrowingRequestReceipt(BorrowBookModal dialog) {
        this.dialog = dialog;

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
        modal.setPreferredSize(new Dimension(500, 420));
        modal.setBackground(LIGHT_PINK);

        /* ================= HEADER (EXACT COPY) ================= */

        JPanel header = new JPanel();
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(500, 100));
        header.setBorder(new EmptyBorder(10, 0, 10, 10));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setOpaque(false);
        headerWrapper.setPreferredSize(new Dimension(450, 80));

        ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
        Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
        JLabel elibLogo = new JLabel(new ImageIcon(scaled));
        elibLogo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel headerLabel = new JLabel("BOOKS");
        headerLabel.setFont(introRust26);
        headerLabel.setForeground(WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));

        headerWrapper.add(elibLogo, BorderLayout.WEST);
        headerWrapper.add(headerLabel, BorderLayout.CENTER);
        header.add(headerWrapper);

        /* ================= BODY ================= */

        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel bodyTitle = new JLabel("BORROWED BOOK RECEIPT", SwingConstants.CENTER);
        bodyTitle.setFont(introRust24);
        bodyTitle.setForeground(DARK_TEXT);
        body.add(bodyTitle, BorderLayout.NORTH);

        JPanel innerBody = new JPanel(new GridBagLayout());
        innerBody.setOpaque(false);
        innerBody.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = -1;

        /* ================= RECEIPT DETAILS ================= */

        addRow(innerBody, gbc, "Transaction ID:", roField("TXN-2024-0001", poppins10));
        addRow(innerBody, gbc, "Book Call Number:", roField("DS210.L86 2020", poppins10));
        addRow(innerBody, gbc, "Patron ID:", roField("2024100015", poppins10));
        addRow(innerBody, gbc, "Borrow Due Date:", roField("2026-01-15", poppins10));

        body.add(innerBody, BorderLayout.CENTER);

        /* ================= FOOTER ================= */

        JPanel footer = new JPanel(new GridLayout(1, 1));
        footer.setBorder(new EmptyBorder(10, 35, 10, 35));
        footer.setOpaque(false);

        RoundedButton okBtn = new RoundedButton("OKAY", FIELD_RADIUS);
        okBtn.setFont(poppins12);
        okBtn.setForeground(MAROON);
        okBtn.setBorderColor(MAROON);
        okBtn.setBorderThickness(1);
        okBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });

        footer.add(okBtn);

        /* ================= ASSEMBLY ================= */

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }

    /* ================= HELPERS ================= */

    private void addRow(JPanel parent, GridBagConstraints gbc, String labelText, JComponent field) {
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 0;

        JPanel labelWrapper = new JPanel(new BorderLayout());
        labelWrapper.setOpaque(false);
        labelWrapper.setPreferredSize(new Dimension(210, 30));

        JLabel label = new JLabel(labelText);
        label.setFont(new Fonts("Poppins", 16f).getAppliedFont());
        label.setForeground(DARK_TEXT);

        labelWrapper.add(label, BorderLayout.WEST);
        parent.add(labelWrapper, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        parent.add(field, gbc);
    }

    private RoundedTextField roField(String text, Font font) {
        RoundedTextField tf = new RoundedTextField(19, FIELD_RADIUS);
        tf.setPlaceholder(text);
        tf.setEditable(false);
        tf.setFont(font);
        tf.setBorderColor(FIELD_BORDER);
        tf.setBorderThickness(1);
        return tf;
    }
}