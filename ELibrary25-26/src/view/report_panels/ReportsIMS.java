package view.report_panels;

import view.fonts.Fonts;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ReportsController;

public class ReportsIMS extends JPanel {
	 static final Color MAROON       = new Color(132, 43, 40);      
	    static final Color LIGHT_PINK   = new Color(250, 236, 238);  
	    static final Color WHITE        = Color.WHITE;
	    static final Color DARK_TEXT    = new Color(109, 35, 33);      
	    static final Color FIELD_BORDER = new Color(146, 76, 74);    

	    static final int PANEL_RADIUS = 20;
	    static final int FIELD_RADIUS = 15;
	    
    public ReportsIMS() {
        setLayout(new BorderLayout());

     
        String[] columnNames = {
                "Serial Number", "Equipment Name", "Item Type"
            };

            
        ReportsController comp = new ReportsController(null);
        Object[][] data = comp.getIMSData();
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
        	@Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  
        table.setRowHeight(35);
        Fonts poppins = new Fonts("Poppins", 10f);
	    Font poppinsStyle = poppins.getAppliedFont();
	    
	    Fonts introRust = new Fonts("IntroRust", 12f);
	    Font introRustStyle = introRust.getAppliedFont();
        table.setFont(poppinsStyle);
        table.getTableHeader().setFont(introRustStyle);
        table.getTableHeader().setBackground(MAROON); 
        table.getTableHeader().setForeground(WHITE);            
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
        
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1180, 400)); 

        
        add(scrollPane, BorderLayout.CENTER);
    }

   
}
