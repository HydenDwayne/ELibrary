package view.modal.ims_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.IMSController;

import java.awt.*;

import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class AddIMS extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);
    static final Color GRAY_HINT = Color.GRAY;

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;


    RoundedTextField serialField;
    RoundedTextField equipmentField;
    RoundedTextField itemTypeField;

    public AddIMS() {

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
        modal.setPreferredSize(new Dimension(500, 400));
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

        JLabel bodyTitle = new JLabel(
                "ADD NEW EQUIPMENT",
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

        // Equipment Name
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel equipLbl = new JLabel("Equipment Name:");
        equipLbl.setFont(poppins16);
        equipLbl.setForeground(DARK_TEXT);
        innerBody.add(equipLbl, gbc);

        gbc.gridx = 1;
        equipmentField = new RoundedTextField(19, FIELD_RADIUS);
        equipmentField.setFont(poppins10);
        equipmentField.setPlaceholder("Enter equipment name");
        equipmentField.setBorderColor(FIELD_BORDER);
        equipmentField.setBorderThickness(1);
        innerBody.add(equipmentField, gbc);

        // Item Type (optional)
        gbc.gridy++;
        gbc.gridx = 0;
        JPanel typeLblWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        typeLblWrap.setOpaque(false);
        JLabel typeLbl = new JLabel("Item Type:");
        typeLbl.setFont(poppins16);
        typeLbl.setForeground(DARK_TEXT);
        JLabel typeHint = new JLabel(" (Optional)");
        typeHint.setFont(poppins10);
        typeHint.setForeground(GRAY_HINT);
        typeLblWrap.add(typeLbl);
        typeLblWrap.add(typeHint);
        innerBody.add(typeLblWrap, gbc);

        gbc.gridx = 1;
        itemTypeField = new RoundedTextField(19, FIELD_RADIUS);
        itemTypeField.setFont(poppins10);
        itemTypeField.setPlaceholder("Enter item type");
        itemTypeField.setBorderColor(FIELD_BORDER);
        itemTypeField.setBorderThickness(1);
        innerBody.add(itemTypeField, gbc);

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

            // ================= GET VALUES =================
            String serial = serialField.getRealText().trim();
            String equipment = equipmentField.getRealText().trim();
            String itemType = itemTypeField.getRealText().trim();

            // ================= REQUIRED VALIDATION =================
            if (serial.isEmpty() || equipment.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Serial Number and Equipment Name are required.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // ================= OPTIONAL → NULL =================
            if (itemType.isEmpty()) itemType = null;

            // ================= BUILD ARRAY =================
            String[] details = {
                serial,
                equipment,
                itemType
            };

            // ================= SAVE =================
            IMSController comp = new IMSController(this);
            comp.addNewEquipment(details);

            // ================= CLOSE MODAL =================
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
}
