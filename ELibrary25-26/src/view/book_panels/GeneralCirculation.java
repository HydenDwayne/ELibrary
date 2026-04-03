package view.book_panels;

import controller.MainFunctions;
import view.RoundedComponents.*;
import view.fonts.Fonts;
import java.awt.*;
import javax.swing.*;

public class GeneralCirculation extends JPanel {

    JLabel col1 = new JLabel("");
    JLabel col2 = new JLabel("");
    JLabel col3 = new JLabel("");
    JLabel col4 = new JLabel("");
    JLabel col5 = new JLabel("");
    JLabel col6 = new JLabel("");
    
    JPanel tableData;
    MainFunctions comp;

    int minColumnWidth = 59;
    int minColumnHeight = 25;
    
    private String filterClass = "";
    private String filterStartYear ="";
    private String filterEndYear = "";

	public void setFilterClass(String filterClass) {
		this.filterClass = filterClass;
	}

	public void setFilterStartYear(String filterStartYear) {
		this.filterStartYear = filterStartYear;
	}
	
	public void setFilterEndYear(String filterEndYear) {
		this.filterEndYear = filterEndYear;
	}
    
    String searchQuery = "";

    public GeneralCirculation() {

        int panelRadius = 20;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 10, 10);

        RoundedPanel bookContainer = new RoundedPanel(panelRadius);
        bookContainer.setPreferredSize(new Dimension(1280, 560));
        bookContainer.setLayout(new BorderLayout());
        bookContainer.setBackground(Color.WHITE);

        // selected book items
        JPanel slctdBooks = new JPanel();
        slctdBooks.setOpaque(false);

// Create labels
        JPanel tableHeader = new JPanel();
        slctdBooks.setLayout(new BorderLayout());
        tableHeader.setLayout(new GridBagLayout());
        JLabel callNumber = new JLabel("Call Number");
        JLabel title = new JLabel("Title");
        JLabel author = new JLabel("Author");
        JLabel year = new JLabel("Publication Year");
        JLabel classification = new JLabel("Classification");
        JLabel borrowable = new JLabel("Borrowable?");
        JLabel action = new JLabel("Action");

        Fonts poopinsBold12 = new Fonts("Poppins", 12f);
        Font poopinsBold12Style = poopinsBold12.getAppliedFont();

        callNumber.setFont(poopinsBold12Style);
        title.setFont(poopinsBold12Style);
        author.setFont(poopinsBold12Style);
        year.setFont(poopinsBold12Style);
        classification.setFont(poopinsBold12Style);
        borrowable.setFont(poopinsBold12Style);
        action.setFont(poopinsBold12Style);

        callNumber.setForeground(Color.decode("#737373"));
        title.setForeground(Color.decode("#737373"));
        author.setForeground(Color.decode("#737373"));
        year.setForeground(Color.decode("#737373"));
        classification.setForeground(Color.decode("#737373"));
        borrowable.setForeground(Color.decode("#737373"));
        action.setForeground(Color.decode("#737373"));

        JPanel callNumberPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel authorPanel = new JPanel();
        JPanel yearPanel = new JPanel();
        JPanel classificationPanel = new JPanel();
        JPanel borrowablePanel = new JPanel();
        JPanel actionPanel = new JPanel();

        callNumberPanel.add(callNumber);
        titlePanel.add(title);
        authorPanel.add(author);
        yearPanel.add(year);
        classificationPanel.add(classification);
        borrowablePanel.add(borrowable);
        actionPanel.add(action);

        callNumberPanel.setOpaque(false);
        titlePanel.setOpaque(false);
        authorPanel.setOpaque(false);
        yearPanel.setOpaque(false);
        classificationPanel.setOpaque(false);
        borrowablePanel.setOpaque(false);
        actionPanel.setOpaque(false);

        callNumberPanel.setPreferredSize(new Dimension(minColumnWidth * 2, minColumnHeight));
        titlePanel.setPreferredSize(new Dimension((minColumnWidth * 5) + 40, minColumnHeight));
        authorPanel.setPreferredSize(new Dimension(minColumnWidth * 3, minColumnHeight));
        yearPanel.setPreferredSize(new Dimension(minColumnWidth + 38, minColumnHeight));
        classificationPanel.setPreferredSize(new Dimension(minColumnWidth + 25, minColumnHeight));
        borrowablePanel.setPreferredSize(new Dimension(minColumnWidth + 20, minColumnHeight));
        actionPanel.setPreferredSize(new Dimension(minColumnWidth + 45, minColumnHeight));

        GridBagConstraints gbcBook = new GridBagConstraints();
        gbcBook.gridy = 0;
        gbcBook.gridx = 0;
        gbcBook.insets = new Insets(10, 10, 10, 10);

        gbcBook.gridy = 1;
        gbcBook.gridx = 1;
        tableHeader.add(callNumberPanel, gbcBook);

        gbcBook.gridx = 2;
        tableHeader.add(titlePanel, gbcBook);

        gbcBook.gridx = 3;
        tableHeader.add(authorPanel, gbcBook);

        gbcBook.gridx = 4;
        tableHeader.add(yearPanel, gbcBook);

        gbcBook.gridx = 5;
        tableHeader.add(classificationPanel, gbcBook);

        gbcBook.gridx = 6;
        tableHeader.add(borrowablePanel, gbcBook);

        gbcBook.gridx = 7;
        tableHeader.add(actionPanel, gbcBook);

        tableHeader.setOpaque(false);
        tableHeader.setPreferredSize(new Dimension(1240, 50));

        slctdBooks.add(tableHeader, BorderLayout.NORTH);

        // actual table data ============================
        tableData = new JPanel();
        tableData.setOpaque(false);

        reloadData(searchQuery);
        

        tableData.setLayout(new BorderLayout());

        JScrollPane scrollBar = new JScrollPane(tableData);
        scrollBar.setOpaque(false);
        scrollBar.setPreferredSize(new Dimension(1240, 200));
        scrollBar.setBorder(BorderFactory.createEmptyBorder(0, 65, 40, 65));
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        scrollBar.getVerticalScrollBar().setUI(new RoundedScrollbar());

        slctdBooks.add(scrollBar, BorderLayout.CENTER);

        bookContainer.add(slctdBooks, BorderLayout.CENTER);

        setBackground(Color.BLUE);

        //============================================== 
        add(bookContainer, gbc);
        setBackground(Color.decode("#ffffff"));

    }

    public int getMinColumnWidth() {
        return minColumnWidth;
    }

    public int getMinColumnHeight() {
        return minColumnHeight;
    }
    
    public void reloadData(String searchQuery) {
    	String[] filters = {filterClass, filterStartYear, filterEndYear};
        tableData.removeAll();
        comp = new MainFunctions(this, searchQuery, filters);
        tableData.add(comp, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
}
