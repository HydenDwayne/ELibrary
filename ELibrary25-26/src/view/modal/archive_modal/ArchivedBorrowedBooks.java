package view.modal.archive_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.ArchiveController;

import java.awt.*;

import view.RoundedComponents.*;

public class ArchivedBorrowedBooks extends JPanel {

    /* ================= COLORS ================= */
    static final Color MAROON       = new Color(132, 43, 40);
    static final Color LIGHT_PINK   = new Color(250, 236, 238);
    static final Color WHITE        = Color.WHITE;
    static final Color DARK_TEXT    = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    public JTable table;

    public ArchivedBorrowedBooks() {

        setOpaque(false);
        setLayout(new BorderLayout());

        /* ================= MODAL ================= */
        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(600, 500));
        modal.setBackground(LIGHT_PINK);

        /* ================= HEADER ================= */
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(500, 100));
        header.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("📚");
        logo.setFont(new Font("Arial", Font.PLAIN, 40));
        logo.setForeground(WHITE);

        JLabel title = new JLabel("ARCHIVES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(WHITE);

        header.add(logo, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);

        /* ================= BODY ================= */
        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel bodyTitle = new JLabel(
                "ALL ARCHIVED RECORDS OF BORROWED BOOK",
                SwingConstants.CENTER
        );
        bodyTitle.setFont(new Font("Arial", Font.BOLD, 20));
        bodyTitle.setForeground(DARK_TEXT);

        body.add(bodyTitle, BorderLayout.NORTH);

        /* ================= TABLE ================= */
        String[] columnHeader = {"Call Number", "Title", "Collection Code", "Status"};

        ArchiveController comp = new ArchiveController(null);
        Object[][] data = comp.getBorrowedBookData();

        DefaultTableModel model = new DefaultTableModel(data, columnHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setGridColor(FIELD_BORDER);
        table.setShowGrid(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(MAROON);
        tableHeader.setForeground(WHITE);
        tableHeader.setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 0, 10, 0));

        RoundedPanel tableWrapper = new RoundedPanel(10);
        tableWrapper.setLayout(new BorderLayout());
        tableWrapper.setBackground(WHITE);
        tableWrapper.setBorder(new EmptyBorder(10, 10, 10, 10));
        tableWrapper.add(scrollPane, BorderLayout.CENTER);

        body.add(tableWrapper, BorderLayout.CENTER);

        /* ================= FOOTER ================= */
        JPanel footer = new JPanel(new GridLayout(1, 2, 10, 0));
        footer.setBorder(new EmptyBorder(10, 30, 15, 30));
        footer.setOpaque(false);

        RoundedButton cancelBtn = new RoundedButton("CANCEL", FIELD_RADIUS);
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 12));
        cancelBtn.setForeground(MAROON);
        cancelBtn.setBorderColor(MAROON);
        cancelBtn.setBorderThickness(1);
        cancelBtn.setBackground(LIGHT_PINK);
        cancelBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w instanceof JDialog) w.dispose();
        });

        RoundedButton unarchiveBtn = new RoundedButton("UNARCHIVE", FIELD_RADIUS);
        unarchiveBtn.setFont(new Font("Arial", Font.BOLD, 12));
        unarchiveBtn.setBackground(MAROON);
        unarchiveBtn.setForeground(WHITE);

        unarchiveBtn.addActionListener(e -> {
        	int selectedRow = table.getSelectedRow();
        	if (selectedRow != -1) {
        	    Object value = table.getValueAt(selectedRow, 0); // column 0 = first column
        	    String firstColumnValue = value.toString();

        	    ArchiveController ac = new ArchiveController("Books_Loan", firstColumnValue);
            	
            	boolean isSuccessful = ac.setUnarchived();
            	if(isSuccessful) {
            		Window w = SwingUtilities.getWindowAncestor(this);
            		if (w != null) w.dispose();
            	}
        	}
        	
        });

        footer.add(cancelBtn);
        footer.add(unarchiveBtn);

        /* ================= ASSEMBLY ================= */
        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }
}