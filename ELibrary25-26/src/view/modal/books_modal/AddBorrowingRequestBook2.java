package view.modal.books_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.BookController;

import java.awt.*;

import view.RoundedComponents.RoundedButton;
import view.RoundedComponents.RoundedPanel;
import view.RoundedComponents.RoundedTextField;
import view.front_pages.FilePath;
import view.fonts.Fonts;

public class AddBorrowingRequestBook2 extends JPanel {

    static final Color MAROON = new Color(132, 43, 40);
    static final Color LIGHT_PINK = new Color(250, 236, 238);
    static final Color WHITE = Color.WHITE;
    static final Color DARK_TEXT = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    String callNumber = "";
    String patronID = "";
    
    private final BorrowBookModal dialog;
    
    public RoundedTextField bookTitleField;
    public RoundedTextField authorField;
    public RoundedTextField collectionField;
    public RoundedTextField statusField;
    public RoundedTextField callNumField;
    public RoundedTextField patronField;
    public RoundedTextField dueDateField;
    
    
    
    

    public AddBorrowingRequestBook2(BorrowBookModal dialog, String callNumber, String patronID) {
        this.dialog = dialog;
        this.callNumber = callNumber;
        this.patronID = patronID;

        setOpaque(false);
        setLayout(new BorderLayout());

        /* ================= FONTS ================= */

        Font introRust26 = new Fonts("IntroRust", 36f).getAppliedFont();
        Font introRust24 = new Fonts("IntroRust", 24f).getAppliedFont();
        Font poppins16   = new Fonts("Poppins", 16f).getAppliedFont();
        Font poppins12   = new Fonts("Poppins", 12f).getAppliedFont();
        Font poppins10   = new Fonts("Poppins", 10f).getAppliedFont();

        /* ================= MODAL CONTAINER ================= */

        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(500, 550));
        modal.setBackground(LIGHT_PINK);

        /* ================= HEADER (COPIED FROM STEP 1) ================= */

        JPanel header = new JPanel();
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(500, 100));
        header.setBorder(new EmptyBorder(10, 0, 10, 10));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setOpaque(false);
        headerWrapper.setPreferredSize(new Dimension(450, 80));

        ImageIcon icon = new ImageIcon(FilePath.image("elib_logo.png"));
        Image scaledImage = icon.getImage().getScaledInstance(110, 50, Image.SCALE_SMOOTH);
        JLabel elibLogo = new JLabel(new ImageIcon(scaledImage));
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

        JLabel bodyTitle = new JLabel(
                "BORROWING REQUEST FOR A BOOK",
                SwingConstants.CENTER
        );
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
        
        gbc.gridy++;

        JPanel bookTitleLabelWrapper = new JPanel();
        bookTitleLabelWrapper.setOpaque(false);
        bookTitleLabelWrapper.setPreferredSize(new Dimension(210, 30));
        bookTitleLabelWrapper.setLayout(new BorderLayout());

        JLabel bookTitleLbl = new JLabel("Book Title:");
        bookTitleLbl.setFont(poppins16);
        bookTitleLbl.setForeground(DARK_TEXT);
        bookTitleLabelWrapper.add(bookTitleLbl, BorderLayout.WEST);

        innerBody.add(bookTitleLabelWrapper, gbc);

        gbc.gridx = 1;
         bookTitleField = new RoundedTextField(19, FIELD_RADIUS);
        bookTitleField.setText("Book Title");
        bookTitleField.setEditable(false);
        bookTitleField.setFont(poppins10);
        bookTitleField.setBorderColor(FIELD_BORDER);
        bookTitleField.setBorderThickness(1);

        innerBody.add(bookTitleField, gbc);


        /* Author */
        gbc.gridy++;
        gbc.gridx = 0;

        JPanel authorLabelWrapper = new JPanel();
        authorLabelWrapper.setOpaque(false);
        authorLabelWrapper.setPreferredSize(new Dimension(210, 30));
        authorLabelWrapper.setLayout(new BorderLayout());

        JLabel authorLbl = new JLabel("Author:");
        authorLbl.setFont(poppins16);
        authorLbl.setForeground(DARK_TEXT);
        authorLabelWrapper.add(authorLbl, BorderLayout.WEST);

        innerBody.add(authorLabelWrapper, gbc);

        gbc.gridx = 1;
         authorField = new RoundedTextField(19, FIELD_RADIUS);
        authorField.setText("Author");
        authorField.setEditable(false);
        authorField.setFont(poppins10);
        authorField.setBorderColor(FIELD_BORDER);
        authorField.setBorderThickness(1);

        innerBody.add(authorField, gbc);


        /* Collection Code */
        gbc.gridy++;
        gbc.gridx = 0;

        JPanel collectionLabelWrapper = new JPanel();
        collectionLabelWrapper.setOpaque(false);
        collectionLabelWrapper.setPreferredSize(new Dimension(210, 30));
        collectionLabelWrapper.setLayout(new BorderLayout());

        JLabel collectionLbl = new JLabel("Collection Code:");
        collectionLbl.setFont(poppins16);
        collectionLbl.setForeground(DARK_TEXT);
        collectionLabelWrapper.add(collectionLbl, BorderLayout.WEST);

        innerBody.add(collectionLabelWrapper, gbc);

        gbc.gridx = 1;
         collectionField = new RoundedTextField(19, FIELD_RADIUS);
        collectionField.setText("FILIPINIANA");
        collectionField.setEditable(false);
        collectionField.setFont(poppins10);
        collectionField.setBorderColor(FIELD_BORDER);
        collectionField.setBorderThickness(1);

        innerBody.add(collectionField, gbc);


        /* Availability Status */
        gbc.gridy++;
        gbc.gridx = 0;

        JPanel statusLabelWrapper = new JPanel();
        statusLabelWrapper.setOpaque(false);
        statusLabelWrapper.setPreferredSize(new Dimension(210, 30));
        statusLabelWrapper.setLayout(new BorderLayout());

        JLabel statusLbl = new JLabel("Availability Status:");
        statusLbl.setFont(poppins16);
        statusLbl.setForeground(DARK_TEXT);
        statusLabelWrapper.add(statusLbl, BorderLayout.WEST);

        innerBody.add(statusLabelWrapper, gbc);

        gbc.gridx = 1;
         statusField = new RoundedTextField(19, FIELD_RADIUS);
        statusField.setText("AVAILABLE");
        statusField.setEditable(false);
        statusField.setFont(poppins10);
        statusField.setBorderColor(FIELD_BORDER);
        statusField.setBorderThickness(1);

        innerBody.add(statusField, gbc);


        /* ================= LOAN DETAILS ================= */

        /* Book Call Number */
        gbc.gridy++;
        gbc.gridx = 0;

        JPanel callNumLabelWrapper = new JPanel();
        callNumLabelWrapper.setOpaque(false);
        callNumLabelWrapper.setPreferredSize(new Dimension(210, 30));
        callNumLabelWrapper.setLayout(new BorderLayout());

        JLabel callNumLbl = new JLabel("Book Call Number:");
        callNumLbl.setFont(poppins16);
        callNumLbl.setForeground(DARK_TEXT);
        callNumLabelWrapper.add(callNumLbl, BorderLayout.WEST);

        innerBody.add(callNumLabelWrapper, gbc);

        gbc.gridx = 1;
         callNumField = new RoundedTextField(19, FIELD_RADIUS);
        callNumField.setText("DS210.L86 2020");
        callNumField.setEditable(false);
        callNumField.setFont(poppins10);
        callNumField.setBorderColor(FIELD_BORDER);
        callNumField.setBorderThickness(1);

        innerBody.add(callNumField, gbc);


        /* Patron ID */
        gbc.gridy++;
        gbc.gridx = 0;

        JPanel patronLabelWrapper = new JPanel();
        patronLabelWrapper.setOpaque(false);
        patronLabelWrapper.setPreferredSize(new Dimension(210, 30));
        patronLabelWrapper.setLayout(new BorderLayout());

        JLabel patronLbl = new JLabel("Patron ID:");
        patronLbl.setFont(poppins16);
        patronLbl.setForeground(DARK_TEXT);
        patronLabelWrapper.add(patronLbl, BorderLayout.WEST);

        innerBody.add(patronLabelWrapper, gbc);

        gbc.gridx = 1;
         patronField = new RoundedTextField(19, FIELD_RADIUS);
        patronField.setText("2024100015");
        patronField.setEditable(false);
        patronField.setFont(poppins10);
        patronField.setBorderColor(FIELD_BORDER);
        patronField.setBorderThickness(1);

        innerBody.add(patronField, gbc);


        /* Borrow Due Date */
        gbc.gridy++;
        gbc.gridx = 0;

        JPanel dueDateLabelWrapper = new JPanel();
        dueDateLabelWrapper.setOpaque(false);
        dueDateLabelWrapper.setPreferredSize(new Dimension(210, 30));
        dueDateLabelWrapper.setLayout(new BorderLayout());

        JLabel dueDateLbl = new JLabel("Borrow Due Date:");
        dueDateLbl.setFont(poppins16);
        dueDateLbl.setForeground(DARK_TEXT);
        dueDateLabelWrapper.add(dueDateLbl, BorderLayout.WEST);

        innerBody.add(dueDateLabelWrapper, gbc);

        gbc.gridx = 1;
         dueDateField = new RoundedTextField(19, FIELD_RADIUS);
        dueDateField.setText("Due Date");
        dueDateField.setEditable(false);
        dueDateField.setFont(poppins10);
        dueDateField.setBorderColor(FIELD_BORDER);
        dueDateField.setBorderThickness(1);

        innerBody.add(dueDateField, gbc);

        body.add(innerBody, BorderLayout.CENTER);

        gbc.gridy++;
        gbc.gridx = 0;

        new BookController(this, this.callNumber, this.patronID);

        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(500, 100));
        footer.setBorder(new EmptyBorder(0, 35, 10, 35));
        footer.setLayout(new GridLayout(2, 1, 0, 10));
        footer.setOpaque(false);

        RoundedButton submitBtn = new RoundedButton("SUBMIT BORROW REQUEST", FIELD_RADIUS);
        submitBtn.setFont(poppins12);
        submitBtn.setBackground(MAROON);
        submitBtn.setForeground(WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.addActionListener(e -> {
        	
    	String[] borrowDetails = {
        	callNumField.getText(),
        	dueDateField.getText(),
        	patronField.getText(),
        	"CircDesk"
    	};
    	
    	
        	dialog.setStep3(borrowDetails);
        });

        RoundedButton backBtn = new RoundedButton("< BACK", FIELD_RADIUS);
        backBtn.setFont(poppins12);
        backBtn.setForeground(MAROON);
        backBtn.setBorderColor(MAROON);
        backBtn.setBorderThickness(1);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> dialog.setStep1());

        footer.add(submitBtn);
        footer.add(backBtn);

        /* ================= ASSEMBLY ================= */

        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }
}