package view.modal.books_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import view.RoundedComponents.RoundedButton;
import view.RoundedComponents.RoundedPanel;
import view.RoundedComponents.RoundedTextField;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class ReturnBookConfirm extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;


    private final ReturnBookModal dialog;

    public ReturnBookConfirm(ReturnBookModal dialog) {
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

        /* ================= HEADER (SAME AS OTHER MODALS) ================= */

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

        JLabel title = new JLabel("RETURNING BORROWED BOOK", SwingConstants.CENTER);
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

        /* ================= CONFIRM DETAILS ================= */

        addRow(innerBody, gbc, "Transaction ID:", ro("TXN-2024-0001", poppins10));
        addRow(innerBody, gbc, "Book Call Number:", ro("DS210.L86 2020", poppins10));
        addRow(innerBody, gbc, "Patron ID:", ro("2024100015", poppins10));
        addRow(innerBody, gbc, "Borrow Due Date:", ro("Within Due Date", poppins10));
        addRow(innerBody, gbc, "Due Date Status:", ro("ON TIME", poppins10));

        body.add(innerBody, BorderLayout.CENTER);

        /* ================= FOOTER ================= */

        JPanel footer = new JPanel(new GridLayout(1, 2, 10, 0));
        footer.setBorder(new EmptyBorder(10, 35, 10, 35));
        footer.setOpaque(false);

        RoundedButton backBtn = new RoundedButton("< BACK", FIELD_RADIUS);
        backBtn.setFont(poppins12);
        backBtn.setForeground(MAROON);
        backBtn.setBorderColor(MAROON);
        backBtn.setBorderThickness(1);
        backBtn.addActionListener(e -> dialog.setStep1());

        RoundedButton confirmBtn = new RoundedButton("CONFIRM BOOK RETURN", FIELD_RADIUS);
        confirmBtn.setFont(poppins12);
        confirmBtn.setBackground(MAROON);
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.addActionListener(e -> dialog.dispose());

        footer.add(backBtn);
        footer.add(confirmBtn);

        /* ================= ASSEMBLY ================= */

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }

    /* ================= HELPERS ================= */

    private void addRow(JPanel parent, GridBagConstraints gbc,
                        String labelText, JComponent field) {
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 0;

        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setOpaque(false);
        wrap.setPreferredSize(new Dimension(210, 30));

        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Fonts("Poppins", 16f).getAppliedFont());
        lbl.setForeground(DARK_TEXT);

        wrap.add(lbl, BorderLayout.WEST);
        parent.add(wrap, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        parent.add(field, gbc);
    }

    private RoundedTextField ro(String value, Font font) {
        RoundedTextField tf = new RoundedTextField(19, FIELD_RADIUS);
        tf.setPlaceholder(value);
        tf.setEditable(false);
        tf.setFont(font);
        tf.setBorderColor(FIELD_BORDER);
        tf.setBorderThickness(1);
        return tf;
    }
}

