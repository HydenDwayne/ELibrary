package view.modal.books_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.BookController;

import java.awt.*;
import java.time.LocalDate;

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
    
    public RoundedTextField statusField;
    public RoundedTextField dueField;
    public RoundedTextField patronField;
    public RoundedTextField callField;
    public RoundedTextField txnField;

    public ReturnBookConfirm(ReturnBookModal dialog, String[] borrowDetails) {
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
        gbc.gridy = 0;

        /* ================= TRANSACTION ID ================= */

        JLabel txnLabel = new JLabel("Transaction ID:");
        txnLabel.setFont(poppins16);
        txnLabel.setForeground(DARK_TEXT);
        gbc.gridx = 0;
        gbc.weightx = 0;
        innerBody.add(txnLabel, gbc);

         txnField = new RoundedTextField(19, FIELD_RADIUS);
        txnField.setPlaceholder("TXN-2024-0001");
        txnField.setEditable(false);
        txnField.setFont(poppins10);
        txnField.setBorderColor(FIELD_BORDER);
        txnField.setBorderThickness(1);
        gbc.gridx = 1;
        gbc.weightx = 1;
        innerBody.add(txnField, gbc);

        /* ================= BOOK CALL NUMBER ================= */

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 0;
        JLabel callLabel = new JLabel("Book Call Number:");
        callLabel.setFont(poppins16);
        callLabel.setForeground(DARK_TEXT);
        innerBody.add(callLabel, gbc);

         callField = new RoundedTextField(19, FIELD_RADIUS);
        callField.setPlaceholder("DS210.L86 2020");
        callField.setEditable(false);
        callField.setFont(poppins10);
        callField.setBorderColor(FIELD_BORDER);
        callField.setBorderThickness(1);
        gbc.gridx = 1;
        gbc.weightx = 1;
        innerBody.add(callField, gbc);

        /* ================= PATRON ID ================= */

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 0;
        JLabel patronLabel = new JLabel("Patron ID:");
        patronLabel.setFont(poppins16);
        patronLabel.setForeground(DARK_TEXT);
        innerBody.add(patronLabel, gbc);

         patronField = new RoundedTextField(19, FIELD_RADIUS);
        patronField.setPlaceholder("2024100015");
        patronField.setEditable(false);
        patronField.setFont(poppins10);
        patronField.setBorderColor(FIELD_BORDER);
        patronField.setBorderThickness(1);
        gbc.gridx = 1;
        gbc.weightx = 1;
        innerBody.add(patronField, gbc);

        /* ================= BORROW DUE DATE ================= */

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 0;
        JLabel dueLabel = new JLabel("Due Date:");
        dueLabel.setFont(poppins16);
        dueLabel.setForeground(DARK_TEXT);
        innerBody.add(dueLabel, gbc);

         dueField = new RoundedTextField(19, FIELD_RADIUS);
        dueField.setPlaceholder("2026-04-08");
        dueField.setEditable(false);
        dueField.setFont(poppins10);
        dueField.setBorderColor(FIELD_BORDER);
        dueField.setBorderThickness(1);
        gbc.gridx = 1;
        gbc.weightx = 1;
        innerBody.add(dueField, gbc);

        /* ================= DUE DATE STATUS ================= */

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 0;
        JLabel statusLabel = new JLabel("Due Date Status:");
        statusLabel.setFont(poppins16);
        statusLabel.setForeground(DARK_TEXT);
        innerBody.add(statusLabel, gbc);

         statusField = new RoundedTextField(19, FIELD_RADIUS);
        statusField.setPlaceholder("ON TIME");
        statusField.setEditable(false);
        statusField.setFont(poppins10);
        statusField.setBorderColor(FIELD_BORDER);
        statusField.setBorderThickness(1);
        gbc.gridx = 1;
        gbc.weightx = 1;
        innerBody.add(statusField, gbc);

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
        confirmBtn.addActionListener(e -> {
        	new BookController(this, borrowDetails[0], borrowDetails[1]);
        	dialog.dispose();
        });

        footer.add(backBtn);
        footer.add(confirmBtn);

        /* ================= ASSEMBLY ================= */

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);
        





        
        dueField.setText(borrowDetails[3]);
        patronField.setText(borrowDetails[4]);
        callField.setText(borrowDetails[1]);
        txnField.setText(borrowDetails[0]);
        

        String dateStr = borrowDetails[2];

        
     
        dateStr = dateStr.substring(0, 10);

        
        LocalDate inputDate = LocalDate.parse(dateStr);

        
        LocalDate today = LocalDate.now();

        
        if (!inputDate.isBefore(today)) {
            statusField.setText("ON TIME");
        } else {
            statusField.setText("OVERDUE");
        }

        add(modal, BorderLayout.CENTER);
        
    }
}