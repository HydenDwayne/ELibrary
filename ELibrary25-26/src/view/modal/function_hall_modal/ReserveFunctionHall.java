package view.modal.function_hall_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class ReserveFunctionHall extends JPanel {

    static final Color MAROON       = new Color(132, 43, 40);
    static final Color LIGHT_PINK   = new Color(250, 236, 238);
    static final Color WHITE        = Color.WHITE;
    static final Color DARK_TEXT    = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    public ReserveFunctionHall() {

        setOpaque(false);
        setLayout(new BorderLayout());

        /* ================= FONTS ================= */

        Font introRust36 = new Fonts("IntroRust", 36f).getAppliedFont();
        Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
        Font poppins16   = new Fonts("Poppins", 16f).getAppliedFont();
        Font poppins12   = new Fonts("Poppins", 12f).getAppliedFont();
        Font poppins10   = new Fonts("Poppins", 10f).getAppliedFont();

        /* ================= MODAL ================= */

        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(520, 580));
        modal.setBackground(LIGHT_PINK);

        /* ================= HEADER ================= */

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(520, 100));
        header.setBorder(new EmptyBorder(10, 0, 10, 10));

        ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
        Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));

        JLabel headerLabel = new JLabel("FACILITIES");
        headerLabel.setFont(introRust36);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));

        header.add(logo, BorderLayout.WEST);
        header.add(headerLabel, BorderLayout.CENTER);

        /* ================= BODY ================= */

        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel bodyTitle = new JLabel("RESERVE FUNCTION HALL", SwingConstants.CENTER);
        bodyTitle.setFont(introRust24);
        bodyTitle.setForeground(DARK_TEXT);
        body.add(bodyTitle, BorderLayout.NORTH);

        JPanel innerBody = new JPanel(new GridBagLayout());
        innerBody.setOpaque(false);
        innerBody.setBorder(new EmptyBorder(15, 20, 15, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        /* ✅ Equal width for both columns */
        gbc.weightx = 0.5;

        Dimension labelSize = new Dimension(220, 30); // ✅ forces equal column feel

        /* ================= ROWS ================= */

        // Event Name
        gbc.gridx = 0;
        JLabel eventLbl = new JLabel("Event Name:");
        eventLbl.setFont(poppins16);
        eventLbl.setForeground(DARK_TEXT);
        eventLbl.setPreferredSize(labelSize);
        innerBody.add(eventLbl, gbc);

        gbc.gridx = 1;
        RoundedTextField eventField = new RoundedTextField(19, FIELD_RADIUS);
        eventField.setFont(poppins10);
        eventField.setPlaceholder("Enter Event Name");
        eventField.setBorderColor(FIELD_BORDER);
        eventField.setBorderThickness(1);
        innerBody.add(eventField, gbc);

        // Start Time
        gbc.gridy++; gbc.gridx = 0;
        JLabel startLbl = new JLabel("Start Time (00:00):");
        startLbl.setFont(poppins16);
        startLbl.setForeground(DARK_TEXT);
        startLbl.setPreferredSize(labelSize);
        innerBody.add(startLbl, gbc);

        gbc.gridx = 1;
        RoundedTextField startField = new RoundedTextField(19, FIELD_RADIUS);
        startField.setFont(poppins10);
        startField.setPlaceholder("Enter Start Time");
        startField.setBorderColor(FIELD_BORDER);
        startField.setBorderThickness(1);
        innerBody.add(startField, gbc);

        // End Time
        gbc.gridy++; gbc.gridx = 0;
        JLabel endLbl = new JLabel("End Time (00:00):");
        endLbl.setFont(poppins16);
        endLbl.setForeground(DARK_TEXT);
        endLbl.setPreferredSize(labelSize);
        innerBody.add(endLbl, gbc);

        gbc.gridx = 1;
        RoundedTextField endField = new RoundedTextField(19, FIELD_RADIUS);
        endField.setFont(poppins10);
        endField.setPlaceholder("Enter End Time");
        endField.setBorderColor(FIELD_BORDER);
        endField.setBorderThickness(1);
        innerBody.add(endField, gbc);

        // Chosen Date
        gbc.gridy++; gbc.gridx = 0;
        JLabel dateLbl = new JLabel("Chosen Date (YYYY-MM-DD):");
        dateLbl.setFont(poppins16);
        dateLbl.setForeground(DARK_TEXT);
        dateLbl.setPreferredSize(labelSize);
        innerBody.add(dateLbl, gbc);

        gbc.gridx = 1;
        RoundedTextField dateField = new RoundedTextField(19, FIELD_RADIUS);
        dateField.setFont(poppins10);
        dateField.setPlaceholder("Enter Date");
        dateField.setBorderColor(FIELD_BORDER);
        dateField.setBorderThickness(1);
        innerBody.add(dateField, gbc);

        // Reserved By
        gbc.gridy++; gbc.gridx = 0;
        JLabel reservedLbl = new JLabel("Reserved By (ID):");
        reservedLbl.setFont(poppins16);
        reservedLbl.setForeground(DARK_TEXT);
        reservedLbl.setPreferredSize(labelSize);
        innerBody.add(reservedLbl, gbc);

        gbc.gridx = 1;
        RoundedTextField reservedField = new RoundedTextField(19, FIELD_RADIUS);
        reservedField.setFont(poppins10);
        reservedField.setPlaceholder("Enter Reserver ID");
        reservedField.setBorderColor(FIELD_BORDER);
        reservedField.setBorderThickness(1);
        innerBody.add(reservedField, gbc);

        // Approved By
        gbc.gridy++; gbc.gridx = 0;
        JLabel approvedLbl = new JLabel("Approved By (ID):");
        approvedLbl.setFont(poppins16);
        approvedLbl.setForeground(DARK_TEXT);
        approvedLbl.setPreferredSize(labelSize);
        innerBody.add(approvedLbl, gbc);

        gbc.gridx = 1;
        RoundedTextField approvedField = new RoundedTextField(19, FIELD_RADIUS);
        approvedField.setFont(poppins10);
        approvedField.setPlaceholder("Enter Librarian ID");
        approvedField.setBorderColor(FIELD_BORDER);
        approvedField.setBorderThickness(1);
        innerBody.add(approvedField, gbc);

        body.add(innerBody, BorderLayout.CENTER);

        /* ================= FOOTER ================= */

        JPanel footer = new JPanel(new GridLayout(1, 2, 10, 0));
        footer.setBorder(new EmptyBorder(10, 35, 10, 35));
        footer.setOpaque(false);

        RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
        cancelBtn.setFont(poppins12);
        cancelBtn.setForeground(MAROON);
        cancelBtn.setBorderColor(MAROON);
        cancelBtn.setBorderThickness(1);

        /* ✅ Close modal on cancel */
        cancelBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w instanceof JDialog) {
                w.dispose();
            }
        });

        RoundedButton confirmBtn = new RoundedButton("CONFIRM & SAVE", FIELD_RADIUS);
        confirmBtn.setFont(poppins12);
        confirmBtn.setBackground(MAROON);
        confirmBtn.setForeground(WHITE);

        footer.add(cancelBtn);
        footer.add(confirmBtn);

        /* ================= ASSEMBLY ================= */

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }
}