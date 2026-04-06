package view.modal.archive_modal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.ArchiveController;

import java.awt.*;

import view.RoundedComponents.*;
import view.fonts.Fonts;

public class ArchivedIMS extends JPanel {

    static final Color MAROON       = new Color(132, 43, 40);
    static final Color LIGHT_PINK   = new Color(250, 236, 238);
    static final Color WHITE        = Color.WHITE;
    static final Color DARK_TEXT    = new Color(109, 35, 33);
    static final Color FIELD_BORDER = new Color(146, 76, 74);

    static final int PANEL_RADIUS = 20;
    static final int FIELD_RADIUS = 15;

    public JTable table;

    public ArchivedIMS() {

        setOpaque(false);
        setLayout(new BorderLayout());

        /* ================= FONTS ================= */
        Font introRust26 = new Fonts("IntroRust", 36f).getAppliedFont();
        Font introRust24 = new Fonts("IntroRust", 20f).getAppliedFont();
        Font poppins12   = new Fonts("Poppins", 12f).getAppliedFont();

        /* ================= MODAL ================= */
        RoundedPanel modal = new RoundedPanel(PANEL_RADIUS);
        modal.setLayout(new BorderLayout());
        modal.setPreferredSize(new Dimension(700, 500));
        modal.setBackground(LIGHT_PINK);

        /* ================= HEADER ================= */
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MAROON);
        header.setPreferredSize(new Dimension(700, 100));
        header.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("📚");
        logo.setFont(new Font("Arial", Font.PLAIN, 40));
        logo.setForeground(WHITE);

        JLabel title = new JLabel("ARCHIVES", SwingConstants.CENTER);
        title.setFont(introRust26);
        title.setForeground(WHITE);

        header.add(logo, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);

        /* ================= BODY ================= */
        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel bodyTitle = new JLabel(
            "ALL ARCHIVED RECORDS OF IMS",
            SwingConstants.CENTER
        );
        bodyTitle.setFont(introRust24);
        bodyTitle.setForeground(DARK_TEXT);

        body.add(bodyTitle, BorderLayout.NORTH);

        /* ================= TABLE ================= */
        String[] columnHeader = {"Facility Code", "Serial Number", "Equipment Name","Status"};
        ArchiveController comp = new ArchiveController(null);
        Object[][] data = comp.getIMSData();

        DefaultTableModel model = new DefaultTableModel(data, columnHeader) {
        	@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make every cell non-editable
            }
        };
        
        
        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(poppins12);
        table.setGridColor(FIELD_BORDER);
        table.setShowGrid(true);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JTableHeader headerTable = table.getTableHeader();
        headerTable.setBackground(MAROON);
        headerTable.setForeground(WHITE);
        headerTable.setFont(poppins12);

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
        cancelBtn.setFont(poppins12);
        cancelBtn.setForeground(MAROON);
        cancelBtn.setBorderColor(MAROON);
        cancelBtn.setBorderThickness(1);
        cancelBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w instanceof JDialog) w.dispose();
        });

        RoundedButton unarchiveBtn = new RoundedButton("UNARCHIVE", FIELD_RADIUS);
        unarchiveBtn.setFont(poppins12);
        unarchiveBtn.setBackground(MAROON);
        unarchiveBtn.setForeground(WHITE);

        footer.add(cancelBtn);
        footer.add(unarchiveBtn);

        /* ================= ASSEMBLY ================= */
        modal.add(header, BorderLayout.NORTH);
        modal.add(body, BorderLayout.CENTER);
        modal.add(footer, BorderLayout.SOUTH);

        add(modal, BorderLayout.CENTER);
    }
}