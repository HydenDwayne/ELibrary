package view.report_panels;

import view.fonts.Fonts;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ReportsController;

public class ReportsLSect extends JPanel {
	 static final Color MAROON       = new Color(132, 43, 40);      // #842b28
	    static final Color LIGHT_PINK   = new Color(250, 236, 238);  // #faecee
	    static final Color WHITE        = Color.WHITE;
	    static final Color DARK_TEXT    = new Color(109, 35, 33);      // #6d2321
	    static final Color FIELD_BORDER = new Color(146, 76, 74);    // #924c4a

	    static final int PANEL_RADIUS = 20;
	    static final int FIELD_RADIUS = 15;
	    
    public ReportsLSect() {
        setLayout(new BorderLayout());

        // Column names (excluding isArchived)
        String[] columnNames = {
            "Facility Code", "Log ID", "Patron Time In", "Patron Time Out", "Patron ID", "Card No"
        };

        // Multiple dummy ISR records
        ReportsController comp = new ReportsController("LSect");
        Object[][] data = comp.getTableDataFL();

        // Table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // JTable
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
  
        table.setRowHeight(35);
        Fonts poppins = new Fonts("Poppins", 10f);
	    Font poppinsStyle = poppins.getAppliedFont();
	    
	    Fonts introRust = new Fonts("IntroRust", 12f);
	    Font introRustStyle = introRust.getAppliedFont();
        table.setFont(poppinsStyle);
        table.getTableHeader().setFont(introRustStyle);
        table.getTableHeader().setBackground(MAROON); // maroon header
        table.getTableHeader().setForeground(WHITE);            // white text
        table.setGridColor(FIELD_BORDER);
        table.setShowGrid(false);

        table.getTableHeader().setPreferredSize(new Dimension(
        	    table.getTableHeader().getWidth(), 40
        	));
        
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Scroll pane with preferred size
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1180, 400)); // fits under 1280x560

        // Add to panel
        add(scrollPane, BorderLayout.CENTER);
    }

   
}
