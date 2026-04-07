package view.report_panels;

import view.fonts.Fonts;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ReportsController;

public class ReportsBorrowedBooks extends JPanel {

    static final Color MAROON       = new Color(132, 43, 40);  // #842b28
    static final Color LIGHT_PINK   = new Color(250, 236, 238);// #faecee
    static final Color WHITE        = Color.WHITE;
    static final Color DARK_TEXT    = new Color(109, 35, 33);  // #6d2321
    static final Color FIELD_BORDER = new Color(146, 76, 74);  // #924c4a

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    public JTable table;
    
    public ReportsBorrowedBooks() {
        setLayout(new BorderLayout());

        // ===== COLUMN NAMES =====
        String[] columnNames = {
            "Transaction ID",
            "Call Number",
            "Borrow Date",
            "Due Date",
            "Patron ID",
            "Circulation Code"
        };

        // ===== DATA =====
        ReportsController comp = new ReportsController(null);
        Object[][] data = comp.getBorrowedBooksData();

        // ===== TABLE MODEL =====
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // consistent with other reports
            }
        };

        // ===== TABLE =====
         table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Fonts
        Fonts poppins = new Fonts("Poppins", 10f);
        Font poppinsStyle = poppins.getAppliedFont();

        Fonts introRust = new Fonts("IntroRust", 12f);
        Font introRustStyle = introRust.getAppliedFont();

        table.setRowHeight(35);
        table.setFont(poppinsStyle);

        table.getTableHeader().setFont(introRustStyle);
        table.getTableHeader().setBackground(MAROON);
        table.getTableHeader().setForeground(WHITE);

        table.setGridColor(FIELD_BORDER);
        table.setShowGrid(false);

        table.getTableHeader().setPreferredSize(
            new Dimension(table.getTableHeader().getWidth(), 40)
        );

        // ===== CENTER ALIGNMENT =====
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // ===== SCROLL =====
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1180, 500));

        // ===== ADD =====
        add(scrollPane, BorderLayout.CENTER);
    }
}