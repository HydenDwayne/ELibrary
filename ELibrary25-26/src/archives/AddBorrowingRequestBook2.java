package archives;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.RoundedComponents.RoundedButton;
import view.RoundedComponents.RoundedPanel;
import view.RoundedComponents.RoundedTextField;

public class AddBorrowingRequestBook2 extends JFrame {

    static final Color MAROON       = new Color(132, 43, 40);      // #842b28
    static final Color LIGHT_PINK   = new Color(250, 236, 238);  // #faecee
    static final Color WHITE        = Color.WHITE;
    static final Color DARK_TEXT    = new Color(109, 35, 33);      // #6d2321
    static final Color FIELD_BORDER = new Color(146, 76, 74);    // #924c4a

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    public AddBorrowingRequestBook2() {
        // Background panel
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(210, 180, 180));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        panel.setLayout(new GridBagLayout());

        // Main modal panel using RoundedPanel
        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(500, 550));
        modal.setBackground(LIGHT_PINK);

        // Header panel with logo and "BOOKS" title
        JPanel header = new JPanel();
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(500, 100));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        header.setLayout(new BorderLayout());

        // Logo on the left (placeholder - replace with actual logo path)
        // If you have the logo file, uncomment and use:
        // ImageIcon icon = new ImageIcon(FilePath.getImgFilePath() + "library_logo.png");
        // Image image = icon.getImage();
        // Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        // icon = new ImageIcon(scaledImage);
        // JLabel logoLabel = new JLabel(icon);
        
        // Placeholder logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(MAROON);
        logoPanel.setPreferredSize(new Dimension(80, 80));
        JLabel logoPlaceholder = new JLabel("📚", SwingConstants.CENTER);
        logoPlaceholder.setFont(new Font("Arial", Font.PLAIN, 40));
        logoPlaceholder.setForeground(WHITE);
        logoPanel.add(logoPlaceholder);
        
        header.add(logoPanel, BorderLayout.WEST);

        // "BOOKS" title on the right side of header
        JLabel booksTitle = new JLabel("BOOKS", SwingConstants.CENTER);
        booksTitle.setForeground(WHITE);
        booksTitle.setFont(new Font("Arial", Font.BOLD, 36));
        header.add(booksTitle, BorderLayout.CENTER);

        // Body panel
        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BorderLayout());
        body.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Main title
        JLabel mainTitle = new JLabel("BORROWING REQUEST FOR A BOOK", SwingConstants.CENTER);
        mainTitle.setForeground(DARK_TEXT);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
  
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        titlePanel.add(mainTitle);
       
        
        body.add(titlePanel, BorderLayout.NORTH);

        // Inner body with form sections
        JPanel innerBody = new JPanel();
        innerBody.setOpaque(false);
        innerBody.setLayout(new BoxLayout(innerBody, BoxLayout.Y_AXIS));
        innerBody.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // ========== BOOK DETAILS Section ==========
        RoundedPanel bookDetailsPanel = new RoundedPanel(10);
        bookDetailsPanel.setLayout(new BoxLayout(bookDetailsPanel, BoxLayout.Y_AXIS));
        bookDetailsPanel.setBackground(WHITE);
        bookDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        bookDetailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        JLabel bookDetailsTitle = new JLabel("BOOK DETAILS", SwingConstants.CENTER);
        bookDetailsTitle.setForeground(DARK_TEXT);
        bookDetailsTitle.setFont(new Font("Arial", Font.BOLD, 12));
        bookDetailsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookDetailsPanel.add(bookDetailsTitle);
        bookDetailsPanel.add(Box.createVerticalStrut(10));

        // Book Details Grid
        JPanel bookDetailsGrid = new JPanel(new GridLayout(0, 2, 10, 8));
        bookDetailsGrid.setBackground(WHITE);
        bookDetailsGrid.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Book Title (display only)
        bookDetailsGrid.add(darkLabel("Book Title:"));
        RoundedTextField bookTitleField = new RoundedTextField(15, FIELD_RADIUS);
        bookTitleField.setPlaceholder("Book Title");
        bookTitleField.setBorderColor(FIELD_BORDER);
        bookTitleField.setEditable(false);
        bookTitleField.setBorderThickness(1);
        bookDetailsGrid.add(bookTitleField);

        // Author (display only)
        bookDetailsGrid.add(darkLabel("Author:"));
        RoundedTextField authorField = new RoundedTextField(15, FIELD_RADIUS);
        authorField.setPlaceholder("Author");
        authorField.setBorderColor(FIELD_BORDER);
        authorField.setEditable(false);
        authorField.setBorderThickness(1);
        bookDetailsGrid.add(authorField);

        // Collection Code (display only)
        bookDetailsGrid.add(darkLabel("Collection Code:"));
        RoundedTextField collectionField = new RoundedTextField(15, FIELD_RADIUS);
        collectionField.setPlaceholder("FILIPINIANA");
        collectionField.setBorderColor(FIELD_BORDER);
        collectionField.setEditable(false);
        collectionField.setBorderThickness(1);
        bookDetailsGrid.add(collectionField);

        // Availability Status (display only)
        bookDetailsGrid.add(darkLabel("Availability Status"));
        RoundedTextField statusField = new RoundedTextField(15, FIELD_RADIUS);
        statusField.setPlaceholder("AVAILABLE");
        statusField.setBorderColor(FIELD_BORDER);
        statusField.setEditable(false);
        statusField.setBorderThickness(1);
        bookDetailsGrid.add(statusField);

        bookDetailsPanel.add(bookDetailsGrid);
        innerBody.add(bookDetailsPanel);
        innerBody.add(Box.createVerticalStrut(15));

        // ========== BOOK LOAN DETAILS Section ==========
        RoundedPanel loanDetailsPanel = new RoundedPanel(10);
        loanDetailsPanel.setLayout(new BoxLayout(loanDetailsPanel, BoxLayout.Y_AXIS));
        loanDetailsPanel.setBackground(WHITE);
        loanDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        loanDetailsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        JLabel loanDetailsTitle = new JLabel("BOOK LOAN DETAILS", SwingConstants.CENTER);
        loanDetailsTitle.setForeground(DARK_TEXT);
        loanDetailsTitle.setFont(new Font("Arial", Font.BOLD, 12));
        loanDetailsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        loanDetailsPanel.add(loanDetailsTitle);
        loanDetailsPanel.add(Box.createVerticalStrut(10));

        // Loan Details Grid with input fields
        JPanel loanDetailsGrid = new JPanel(new GridLayout(0, 2, 10, 8));
        loanDetailsGrid.setBackground(WHITE);
        loanDetailsGrid.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Book Call Number (input field)
        loanDetailsGrid.add(darkLabel("Book Call Number:"));
        RoundedTextField callNumField = new RoundedTextField(15, FIELD_RADIUS);
        callNumField.setPlaceholder("DS210.L86 2020");
        callNumField.setEditable(false);
        callNumField.setBorderColor(FIELD_BORDER);
        callNumField.setBorderThickness(1);
        loanDetailsGrid.add(callNumField);

        // Patron ID (input field)
        loanDetailsGrid.add(darkLabel("Patron ID:"));
        RoundedTextField patronField = new RoundedTextField(15, FIELD_RADIUS);
        patronField.setPlaceholder("2024100015");
        patronField.setEditable(false);
        patronField.setBorderColor(FIELD_BORDER);
        patronField.setBorderThickness(1);
        loanDetailsGrid.add(patronField);

        // Borrow Due Date (input field)
        loanDetailsGrid.add(darkLabel("Borrow Due Date"));
        RoundedTextField dueDateField = new RoundedTextField(15, FIELD_RADIUS);
        dueDateField.setPlaceholder("Due Date");
        patronField.setEditable(false);
        dueDateField.setBorderColor(FIELD_BORDER);
        dueDateField.setBorderThickness(1);
        loanDetailsGrid.add(dueDateField);

        loanDetailsPanel.add(loanDetailsGrid);
        innerBody.add(loanDetailsPanel);

        body.add(innerBody, BorderLayout.CENTER);

        // Footer panel with buttons
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(500, 55));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        footer.setLayout(new GridLayout(1, 2, 10, 0));
        footer.setOpaque(false);

        // Back button (outlined style)
        RoundedButton backBtn = new RoundedButton("< BACK", FIELD_RADIUS);
        backBtn.setForeground(MAROON);
        backBtn.setBorderColor(MAROON);
        backBtn.setBorderThickness(1);
        backBtn.setBackground(LIGHT_PINK);
        backBtn.setFont(new Font("Arial", Font.BOLD, 12));
        backBtn.setFocusPainted(false);
        footer.add(backBtn);

        // Submit button (filled style)
        RoundedButton submitBtn = new RoundedButton("SUBMIT BORROW REQUEST", FIELD_RADIUS);
        submitBtn.setBackground(MAROON);
        submitBtn.setForeground(WHITE);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 12));
        submitBtn.setFocusPainted(false);
        footer.add(submitBtn);

        // Assemble modal
        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        panel.add(modal);

        // Frame settings
        add(panel);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                modal.requestFocusInWindow();
            }
        });

        setTitle("E-Library Management System");
        setContentPane(panel);
        pack();

        if (getWidth() < 1120 || getHeight() < 600) {
            setSize(1120, 600);
        }

        setMinimumSize(new Dimension(1120, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel darkLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(DARK_TEXT);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        return lbl;
    }

    private JLabel infoLabel(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setForeground(DARK_TEXT);
        lbl.setFont(new Font("Arial", Font.ITALIC, 12));
        return lbl;
    }

    public static void main(String[] args) {
        new AddBorrowingRequestBook2();
    }
}