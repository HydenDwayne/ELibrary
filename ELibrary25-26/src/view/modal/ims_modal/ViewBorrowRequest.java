package view.modal.ims_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.IMSController;

import java.awt.*;
import view.RoundedComponents.*;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class ViewBorrowRequest extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;
    
    IMSController comp;
    
    public RoundedTextField loanIdField;
    public RoundedTextField serialField;
    public RoundedTextField patronField;
    public RoundedTextField venueField;
    public RoundedTextField dateField;
    public RoundedComboBox<String> statusCombo;
    


    public ViewBorrowRequest(String loanID) {

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
        modal.setPreferredSize(new Dimension(500, 540));
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
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(new EmptyBorder(0, 70, 0, 0));
        headerWrapper.add(headerLabel, BorderLayout.CENTER);

        header.add(headerWrapper);

        /* ================= BODY ================= */

        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel bodyTitle = new JLabel("VIEW BORROW REQUEST DETAILS", SwingConstants.CENTER);
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

        // Reference height for equal ComboBox/TextField height
        RoundedTextField heightRef = new RoundedTextField(19, FIELD_RADIUS);
        int fieldHeight = heightRef.getPreferredSize().height;

        /* ================= ROWS ================= */

        // Utility pattern: label wrapper
        JPanel labelWrap;
        JLabel lbl;

        // Loan ID
        gbc.gridy++; gbc.gridx = 0; gbc.weightx = 0.5;
        labelWrap = new JPanel(new BorderLayout());
        labelWrap.setPreferredSize(new Dimension(200, 30));
        labelWrap.setOpaque(false);
        lbl = new JLabel("Loan ID:");
        lbl.setFont(poppins16);
        lbl.setForeground(DARK_TEXT);
        labelWrap.add(lbl, BorderLayout.WEST);
        innerBody.add(labelWrap, gbc);

        gbc.gridx = 1; gbc.weightx = 0.5;
         loanIdField = new RoundedTextField(19, FIELD_RADIUS);
        loanIdField.setFont(poppins10);
        loanIdField.setText("IMS-00001");
        loanIdField.setEditable(false);
        loanIdField.setBorderColor(FIELD_BORDER);
        loanIdField.setBorderThickness(1);
        innerBody.add(loanIdField, gbc);

        // Serial Number
        gbc.gridy++; gbc.gridx = 0;
        labelWrap = new JPanel(new BorderLayout());
        labelWrap.setPreferredSize(new Dimension(200, 30));
        labelWrap.setOpaque(false);
        lbl = new JLabel("Serial Number:");
        lbl.setFont(poppins16);
        lbl.setForeground(DARK_TEXT);
        labelWrap.add(lbl, BorderLayout.WEST);
        innerBody.add(labelWrap, gbc);

        gbc.gridx = 1;
         serialField = new RoundedTextField(19, FIELD_RADIUS);
        serialField.setFont(poppins10);
        serialField.setText("SN-12345");
        serialField.setEditable(false);
        serialField.setBorderColor(FIELD_BORDER);
        serialField.setBorderThickness(1);
        innerBody.add(serialField, gbc);

        // Patron ID
        gbc.gridy++; gbc.gridx = 0;
        labelWrap = new JPanel(new BorderLayout());
        labelWrap.setPreferredSize(new Dimension(200, 30));
        labelWrap.setOpaque(false);
        lbl = new JLabel("Patron ID:");
        lbl.setFont(poppins16);
        lbl.setForeground(DARK_TEXT);
        labelWrap.add(lbl, BorderLayout.WEST);
        innerBody.add(labelWrap, gbc);

        gbc.gridx = 1;
         patronField = new RoundedTextField(19, FIELD_RADIUS);
        patronField.setFont(poppins10);
        patronField.setText("2024100015");
        patronField.setEditable(false);
        patronField.setBorderColor(FIELD_BORDER);
        patronField.setBorderThickness(1);
        innerBody.add(patronField, gbc);

        // Venue
        gbc.gridy++; gbc.gridx = 0;
        labelWrap = new JPanel(new BorderLayout());
        labelWrap.setPreferredSize(new Dimension(200, 30));
        labelWrap.setOpaque(false);
        lbl = new JLabel("Venue:");
        lbl.setFont(poppins16);
        lbl.setForeground(DARK_TEXT);
        labelWrap.add(lbl, BorderLayout.WEST);
        innerBody.add(labelWrap, gbc);

        gbc.gridx = 1;
         venueField = new RoundedTextField(19, FIELD_RADIUS);
        venueField.setFont(poppins10);
        venueField.setText("Room 304");
        venueField.setEditable(false);
        venueField.setBorderColor(FIELD_BORDER);
        venueField.setBorderThickness(1);
        innerBody.add(venueField, gbc);

        // Borrow Date
        gbc.gridy++; gbc.gridx = 0;
        labelWrap = new JPanel(new BorderLayout());
        labelWrap.setPreferredSize(new Dimension(200, 30));
        labelWrap.setOpaque(false);
        lbl = new JLabel("Borrow Date:");
        lbl.setFont(poppins16);
        lbl.setForeground(DARK_TEXT);
        labelWrap.add(lbl, BorderLayout.WEST);
        innerBody.add(labelWrap, gbc);

        gbc.gridx = 1;
         dateField = new RoundedTextField(19, FIELD_RADIUS);
        dateField.setFont(poppins10);
        dateField.setText("2026-04-01");
        dateField.setEditable(false);
        dateField.setBorderColor(FIELD_BORDER);
        dateField.setBorderThickness(1);
        innerBody.add(dateField, gbc);

        // Loan Status (ONLY editable)
        gbc.gridy++; gbc.gridx = 0;
        labelWrap = new JPanel(new BorderLayout());
        labelWrap.setPreferredSize(new Dimension(200, 30));
        labelWrap.setOpaque(false);
        lbl = new JLabel("Loan Status:");
        lbl.setFont(poppins16);
        lbl.setForeground(DARK_TEXT);
        labelWrap.add(lbl, BorderLayout.WEST);
        innerBody.add(labelWrap, gbc);

        gbc.gridx = 1;
        String[] statuses = { "Pending", "Approved", "Borrowed", "Returned", "Archive"};
        statusCombo = new RoundedComboBox<>(statuses, FIELD_RADIUS);
        statusCombo.setFont(poppins10);
        statusCombo.setBorderColor(FIELD_BORDER);
        statusCombo.setBorderThickness(1);
        statusCombo.setPreferredSize(new Dimension(200, fieldHeight));
        innerBody.add(statusCombo, gbc);

        body.add(innerBody, BorderLayout.CENTER);

        /* ================= FOOTER ================= */

        JPanel footer = new JPanel(new GridLayout(2, 1, 0, 10));
        footer.setBorder(new EmptyBorder(0, 35, 10, 35));
        footer.setOpaque(false);

        RoundedButton saveBtn = new RoundedButton("SAVE DETAILS", FIELD_RADIUS);
        saveBtn.setFont(poppins12);
        saveBtn.setBackground(MAROON);
        saveBtn.setForeground(WHITE);
        saveBtn.addActionListener(e -> {
        	String status = statusCombo.getSelectedItem().toString();
        	
        	comp.updateLoanStatus(loanID, status);
    		
        	Window w = SwingUtilities.getWindowAncestor(this);
            if (w instanceof JDialog) w.dispose();
        });
        footer.add(saveBtn);

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
        
        comp = new IMSController(this);
        comp.getLoanInfo(loanID);

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);
        add(modal, BorderLayout.CENTER);
    }
}

