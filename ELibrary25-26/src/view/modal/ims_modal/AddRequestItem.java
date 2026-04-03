package view.modal.ims_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class AddRequestItem extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);
    static final Color GRAY_HINT = Color.GRAY;

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    static final String imgFilePath = FilePath.getImgFilePath();

    // Fields
    RoundedTextField serialField;
    RoundedTextField patronField;
    RoundedTextField venueField;
    RoundedTextField borrowDateField;

    public AddRequestItem() {

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

        ImageIcon icon = new ImageIcon(imgFilePath + "elib_logo.png");
        Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        headerWrapper.add(logo, BorderLayout.WEST);

        JLabel headerLabel = new JLabel("IMS");
        headerLabel.setFont(introRust26);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));
        headerWrapper.add(headerLabel, BorderLayout.CENTER);

        header.add(headerWrapper);

        /* ================= BODY ================= */

        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel bodyTitle = new JLabel(
                "REQUEST TO BORROW EQUIPMENT",
                SwingConstants.CENTER
        );
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

        /* ================= FORM ROWS ================= */

        // Serial Number
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel serialLbl = new JLabel("Serial Number:");
        serialLbl.setFont(poppins16);
        serialLbl.setForeground(DARK_TEXT);
        innerBody.add(serialLbl, gbc);

        gbc.gridx = 1;
        serialField = new RoundedTextField(19, FIELD_RADIUS);
        serialField.setFont(poppins10);
        serialField.setPlaceholder("Enter serial number");
        serialField.setBorderColor(FIELD_BORDER);
        serialField.setBorderThickness(1);
        innerBody.add(serialField, gbc);

        // Patron ID
        gbc.gridy++;
        gbc.gridx = 0;
        JPanel patronLblWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        patronLblWrap.setOpaque(false);

        JLabel patronLbl = new JLabel("Patron ID:");
        patronLbl.setFont(poppins16);
        patronLbl.setForeground(DARK_TEXT);

        JLabel patronHint = new JLabel(" (2026XXXXXX/20-XXXX)");
        patronHint.setFont(poppins10);
        patronHint.setForeground(GRAY_HINT);

        patronLblWrap.add(patronLbl);
        patronLblWrap.add(patronHint);
        innerBody.add(patronLblWrap, gbc);

        gbc.gridx = 1;
        patronField = new RoundedTextField(19, FIELD_RADIUS);
        patronField.setFont(poppins10);
        patronField.setPlaceholder("Enter patron ID");
        patronField.setBorderColor(FIELD_BORDER);
        patronField.setBorderThickness(1);
        innerBody.add(patronField, gbc);

        // Venue
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel venueLbl = new JLabel("Venue:");
        venueLbl.setFont(poppins16);
        venueLbl.setForeground(DARK_TEXT);
        innerBody.add(venueLbl, gbc);

        gbc.gridx = 1;
        venueField = new RoundedTextField(19, FIELD_RADIUS);
        venueField.setFont(poppins10);
        venueField.setPlaceholder("Enter venue");
        venueField.setBorderColor(FIELD_BORDER);
        venueField.setBorderThickness(1);
        innerBody.add(venueField, gbc);

        // ✅ Borrow Date (NEW)
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel dateLbl = new JLabel("Borrow Date:");
        dateLbl.setFont(poppins16);
        dateLbl.setForeground(DARK_TEXT);
        innerBody.add(dateLbl, gbc);

        gbc.gridx = 1;
        borrowDateField = new RoundedTextField(19, FIELD_RADIUS);
        borrowDateField.setFont(poppins10);
        borrowDateField.setPlaceholder("yyyy-MM-dd");
        borrowDateField.setBorderColor(FIELD_BORDER);
        borrowDateField.setBorderThickness(1);
        innerBody.add(borrowDateField, gbc);

        body.add(innerBody, BorderLayout.CENTER);

        /* ================= FOOTER ================= */

        JPanel footer = new JPanel(new GridLayout(2, 1, 0, 10));
        footer.setBorder(new EmptyBorder(0, 35, 10, 35));
        footer.setOpaque(false);

        RoundedButton confirmBtn =
                new RoundedButton("CONFIRM REQUEST DETAILS", FIELD_RADIUS);
        confirmBtn.setFont(poppins12);
        confirmBtn.setBackground(MAROON);
        confirmBtn.setForeground(WHITE);
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

        /* ================= ASSEMBLY ================= */

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }
}
