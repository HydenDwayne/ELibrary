package view.modal.function_hall_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class ViewHallReservation extends JPanel {

    static final Color MAROON     = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE      = Color.WHITE;
    static final Color DARK_TEXT  = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;
    static final String imgFilePath = FilePath.getImgFilePath();

    public ViewHallReservation() {

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
        modal.setPreferredSize(new Dimension(520, 560));
        modal.setBackground(LIGHT_PINK);

        /* ================= HEADER ================= */

        JPanel header = new JPanel();
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(520, 100));
        header.setBorder(new EmptyBorder(10, 0, 10, 10));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setPreferredSize(new Dimension(470, 80));
        headerWrapper.setOpaque(false);

        ImageIcon icon = new ImageIcon(imgFilePath + "elib_logo.png");
        Image scaled = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        headerWrapper.add(logo, BorderLayout.WEST);

        JLabel headerLabel = new JLabel("FACILITIES");
        headerLabel.setFont(introRust36);
        headerLabel.setForeground(WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));
        headerWrapper.add(headerLabel, BorderLayout.CENTER);

        header.add(headerWrapper);

        /* ================= BODY ================= */

        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel bodyTitle = new JLabel("VIEW HALL RESERVATION DETAILS", SwingConstants.CENTER);
        bodyTitle.setFont(introRust24);
        bodyTitle.setForeground(DARK_TEXT);
        body.add(bodyTitle, BorderLayout.NORTH);

        JPanel innerBody = new JPanel(new GridBagLayout());
        innerBody.setOpaque(false);
        innerBody.setBorder(new EmptyBorder(10, 20, 10, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = -1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        /* ================= ROWS ================= */

        // Chosen Date
        gbc.gridy++; gbc.gridx = 0;
        addLabel(innerBody, gbc, "Chosen Date:", poppins16);
        gbc.gridx = 1;
        innerBody.add(readOnlyField("2026-03-13", poppins10), gbc);

        // Reservation Number
        gbc.gridy++; gbc.gridx = 0;
        addLabel(innerBody, gbc, "Reservation No.:", poppins16);
        gbc.gridx = 1;
        innerBody.add(readOnlyField("2026-0002", poppins10), gbc);

        // Event
        gbc.gridy++; gbc.gridx = 0;
        addLabel(innerBody, gbc, "Event(s):", poppins16);
        gbc.gridx = 1;
        innerBody.add(readOnlyField("Group Study Meetup", poppins10), gbc);

        // Reserved By
        gbc.gridy++; gbc.gridx = 0;
        addLabel(innerBody, gbc, "Reserved By:", poppins16);
        gbc.gridx = 1;
        innerBody.add(readOnlyField("2024100016", poppins10), gbc);

        // Approved By
        gbc.gridy++; gbc.gridx = 0;
        addLabel(innerBody, gbc, "Approved By:", poppins16);
        gbc.gridx = 1;
        innerBody.add(readOnlyField("2024100004", poppins10), gbc);

        // Start Time
        gbc.gridy++; gbc.gridx = 0;
        addLabel(innerBody, gbc, "Start Time:", poppins16);
        gbc.gridx = 1;
        innerBody.add(readOnlyField("13:00", poppins10), gbc);

        // End Time
        gbc.gridy++; gbc.gridx = 0;
        addLabel(innerBody, gbc, "End Time:", poppins16);
        gbc.gridx = 1;
        innerBody.add(readOnlyField("15:00", poppins10), gbc);

        body.add(innerBody, BorderLayout.CENTER);

        /* ================= FOOTER ================= */

        JPanel footer = new JPanel(new GridLayout(1, 1));
        footer.setBorder(new EmptyBorder(10, 35, 10, 35));
        footer.setOpaque(false);

        RoundedButton backBtn = new RoundedButton("< BACK", FIELD_RADIUS);
        backBtn.setFont(poppins12);
        backBtn.setForeground(MAROON);
        backBtn.setBorderColor(MAROON);
        backBtn.setBorderThickness(1);
        backBtn.addActionListener(e -> {
			Window w = SwingUtilities.getWindowAncestor(this);
			if (w instanceof JDialog)
				w.dispose();
		});

        footer.add(backBtn);

        /* ================= ASSEMBLY ================= */

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }

    /* ================= UTILITIES ================= */

    private void addLabel(JPanel parent, GridBagConstraints gbc, String text, Font font) {
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setPreferredSize(new Dimension(200, 30));
        wrap.setOpaque(false);

        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        lbl.setForeground(DARK_TEXT);
        wrap.add(lbl, BorderLayout.WEST);

        parent.add(wrap, gbc);
    }

    private RoundedTextField readOnlyField(String value, Font font) {
        RoundedTextField field = new RoundedTextField(19, FIELD_RADIUS);
        field.setFont(font);
        field.setText(value);
        field.setEditable(false);
        field.setBorderColor(FIELD_BORDER);
        field.setBorderThickness(1);
        return field;
    }
}