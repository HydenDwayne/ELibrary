package view.modal.archive_modal;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.RoundedComponents.RoundedButton;
import view.RoundedComponents.RoundedPanel;
import view.RoundedComponents.RoundedTextField;

public class ArchivedFacilityLogin extends JFrame {

    static final Color MAROON       = new Color(132, 43, 40);      // #842b28
    static final Color LIGHT_PINK   = new Color(250, 236, 238);  // #faecee
    static final Color WHITE        = Color.WHITE;
    static final Color DARK_TEXT    = new Color(109, 35, 33);      // #6d2321
    static final Color FIELD_BORDER = new Color(146, 76, 74);    // #924c4a

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    public ArchivedFacilityLogin() {
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
        modal.setPreferredSize(new Dimension(500, 390));
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
        JLabel archiveTitle = new JLabel("ARCHIVES", SwingConstants.CENTER);
        archiveTitle.setForeground(WHITE);
        archiveTitle.setFont(new Font("Arial", Font.BOLD, 36));
        header.add(archiveTitle, BorderLayout.CENTER);

        // Body panel
        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BorderLayout());
        body.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Main title
        JLabel mainTitle = new JLabel("ALL ARCHIVED RECORDS OF FACILITY LOGIN", SwingConstants.CENTER);
        mainTitle.setForeground(DARK_TEXT);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 20));
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
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


     // ========== BOOK LOAN DETAILS Section ==========
        RoundedPanel faciLoginArchivePanel = new RoundedPanel(10);
        faciLoginArchivePanel.setLayout(new BoxLayout(faciLoginArchivePanel, BoxLayout.Y_AXIS));
        faciLoginArchivePanel.setBackground(WHITE);
        faciLoginArchivePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        faciLoginArchivePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        

        // Column headers
        String[] columnHeader = {"Log ID", "Facility Code", "Patron ID","Status"};

        // Sample data rows
        Object[][] data = {
            {"7","LOGIN","2024100781", "Archived"},
            {"4", "ISR","2024105301","Archived"},
            {"1","RelaxRoom","2024100782","Archived"}
        };

        // Create the table model
        DefaultTableModel model = new DefaultTableModel(data, columnHeader);

        // Create the JTable with the model
        JTable table = new JTable(model);

        // Style the table
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(MAROON); // maroon header
        table.getTableHeader().setForeground(WHITE);            // white text
        table.setGridColor(FIELD_BORDER);
        table.setShowGrid(true);
        
        

        // Wrap the table in a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(450, 100));

        // Add scroll pane to imsDetailsPanel
        faciLoginArchivePanel.add(scrollPane);

        // ✅ Add imsDetailsPanel into innerBody
        innerBody.add(faciLoginArchivePanel);

        // ✅ Add innerBody into body
        body.add(innerBody, BorderLayout.CENTER);



        body.add(innerBody, BorderLayout.CENTER);

        // Footer panel with buttons
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(500, 55));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        footer.setLayout(new GridLayout(1, 2, 10, 0));
        footer.setOpaque(false);

        RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
        cancelBtn.setForeground(MAROON);
        cancelBtn.setBorderColor(MAROON);
        cancelBtn.setBorderThickness(1);
        cancelBtn.setBackground(LIGHT_PINK);
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 12));
        cancelBtn.setFocusPainted(false);
        footer.add(cancelBtn);
       
        RoundedButton unarchiveBtn = new RoundedButton("UNARCHIVE", FIELD_RADIUS);
        unarchiveBtn.setPreferredSize(new Dimension(500, 40));
        unarchiveBtn.setBackground(MAROON);
        unarchiveBtn.setForeground(WHITE);
        unarchiveBtn.setFont(new Font("Arial", Font.BOLD, 12));
        unarchiveBtn.setFocusPainted(false);
        footer.add(unarchiveBtn);

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

    public static void main(String[] args) {
        new ArchivedFacilityLogin();
    }
}