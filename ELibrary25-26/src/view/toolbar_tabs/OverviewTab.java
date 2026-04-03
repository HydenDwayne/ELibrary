package view.toolbar_tabs;

import controller.MainFunctions;
import view.RoundedComponents.*;
import view.analytics.*;
import view.fonts.Fonts;
import view.front_pages.FilePath;
import view.modal.books_modal.BorrowBookModal;
import view.modal.books_modal.ReturnBookModal;

import java.time.*;
import java.awt.*;
import javax.swing.*;

public class OverviewTab extends JPanel{
	static String imgFilePath = FilePath.getImgFilePath();
	
    public JLabel activeBooksData = new JLabel();
    public JLabel overdueBooksData = new JLabel();
    public JLabel borrowedBooksData = new JLabel();
    public JLabel activePatronsData = new JLabel();
    JPanel calendarGrid;
    MainFunctions comp;
    
    private JLabel monthText;
    private YearMonth currentMonth = YearMonth.now();

    public OverviewTab() {
        reloadData();
        int panelRadius = 20;
        JPanel overviewTab = new JPanel();

        overviewTab.setLayout(new GridBagLayout());

        GridBagConstraints gbcContainer = new GridBagConstraints();

        gbcContainer.gridx = 0;
        gbcContainer.gridy = 0;
        gbcContainer.gridheight = 1;
        gbcContainer.insets = new Insets(10, 10, 10, 10);

        int smallWidgetWidth = 250;
        int smallWidgetHeight = 130;

        // available books
        RoundedPanel availableBooks = new RoundedPanel(panelRadius);
        JLabel availableBooksLabel = new JLabel("Available Books");
        availableBooks.setPreferredSize(new Dimension(smallWidgetWidth, smallWidgetHeight));
        availableBooks.setBackground(Color.WHITE);
        availableBooks.setLayout(new BoxLayout(availableBooks, BoxLayout.Y_AXIS));
        availableBooks.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // borrowed books
        RoundedPanel borrowedBooks = new RoundedPanel(panelRadius);
        JLabel borrowedBooksLabel = new JLabel("Borrowed Books");
        
        borrowedBooks.setPreferredSize(new Dimension(smallWidgetWidth, smallWidgetHeight));
        borrowedBooks.setBackground(Color.WHITE);
        borrowedBooks.setLayout(new BoxLayout(borrowedBooks, BoxLayout.Y_AXIS));
        borrowedBooks.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // overdue books
        RoundedPanel overdueBooks = new RoundedPanel(panelRadius);
        JLabel overdueBooksLabel = new JLabel("Overdue Books");
        
        overdueBooks.setPreferredSize(new Dimension(smallWidgetWidth, smallWidgetHeight));
        overdueBooks.setBackground(Color.WHITE);
        overdueBooks.setLayout(new BoxLayout(overdueBooks, BoxLayout.Y_AXIS));
        overdueBooks.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // active patrons
        RoundedPanel activePatrons = new RoundedPanel(panelRadius);
        JLabel activePatronsLabel = new JLabel("Active Patrons");
        
        activePatrons.setPreferredSize(new Dimension(smallWidgetWidth, smallWidgetHeight));
        activePatrons.setBackground(Color.WHITE);
        activePatrons.setLayout(new BoxLayout(activePatrons, BoxLayout.Y_AXIS));
        activePatrons.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // content of the widgets
        availableBooksLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        activeBooksData.setAlignmentX(Component.LEFT_ALIGNMENT);

        borrowedBooksLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        borrowedBooksData.setAlignmentX(Component.LEFT_ALIGNMENT);

        overdueBooksLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        overdueBooksData.setAlignmentX(Component.LEFT_ALIGNMENT);

        activePatronsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        activePatronsData.setAlignmentX(Component.LEFT_ALIGNMENT);

        Fonts introRust18 = new Fonts("IntroRust", 18f);
        Fonts introRust32 = new Fonts("IntroRust", 32f);
        
        Font introRust18Style = introRust18.getAppliedFont();
        Font introRust32Style = introRust32.getAppliedFont();
        
        
    	availableBooksLabel.setFont(introRust18Style);
        borrowedBooksLabel.setFont(introRust18Style);
        overdueBooksLabel.setFont(introRust18Style);
        activePatronsLabel.setFont(introRust18Style);

        activeBooksData.setFont(introRust32Style);
        borrowedBooksData.setFont(introRust32Style);
        overdueBooksData.setFont(introRust32Style);
        activePatronsData.setFont(introRust32Style);

        availableBooks.add(availableBooksLabel);
        availableBooks.add(Box.createVerticalStrut(8));
        availableBooks.add(activeBooksData);

        borrowedBooks.add(borrowedBooksLabel);
        borrowedBooks.add(Box.createVerticalStrut(8));
        borrowedBooks.add(borrowedBooksData);

        overdueBooks.add(overdueBooksLabel);
        overdueBooks.add(Box.createVerticalStrut(8));
        overdueBooks.add(overdueBooksData);

        activePatrons.add(activePatronsLabel);
        activePatrons.add(Box.createVerticalStrut(8));
        activePatrons.add(activePatronsData);

        // colors
        availableBooksLabel.setForeground(Color.decode("#6d2321"));
        borrowedBooksLabel.setForeground(Color.decode("#6d2321"));
        overdueBooksLabel.setForeground(Color.decode("#6d2321"));
        activePatronsLabel.setForeground(Color.decode("#6d2321"));

        activeBooksData.setForeground(Color.decode("#00723b"));
        borrowedBooksData.setForeground(Color.decode("#e17938"));
        overdueBooksData.setForeground(Color.decode("#b83d3d"));
        activePatronsData.setForeground(Color.decode("#164f9b"));

        // add to container
        gbcContainer.gridy++;
        overviewTab.add(availableBooks, gbcContainer);
        gbcContainer.gridy++;
        overviewTab.add(borrowedBooks, gbcContainer);
        gbcContainer.gridy++;
        overviewTab.add(overdueBooks, gbcContainer);
        gbcContainer.gridy++;
        overviewTab.add(activePatrons, gbcContainer);
        // analytics
        gbcContainer.gridx++;
        gbcContainer.gridheight = 2;

        int analyticsWidth = 700;
        int analyticsHeight = (smallWidgetHeight * 2) + 20;

        // upper analytics panel -------------------------------------------------
        RoundedPanel analyticsTop = new RoundedPanel(panelRadius);
        analyticsTop.setLayout(new GridBagLayout());
        GridBagConstraints gbcAT = new GridBagConstraints();
        gbcAT.insets = new Insets(20, 20, 0, 20);
        JLabel analyticsOne = new JLabel("Book Usage");

        gbcAT.gridx = 1;
        gbcAT.gridy = 1;
        analyticsTop.add(analyticsOne, gbcAT);

        // actual bounds of the analytics
        BookTraffic bookTraffic = new BookTraffic();
        bookTraffic.setPreferredSize(new Dimension(analyticsWidth - 40, analyticsHeight - 60));


        gbcAT.gridx = 1;
        gbcAT.gridy = 2;
        gbcAT.gridwidth = 2;
        gbcAT.insets = new Insets(0, 20, 20, 20);

        analyticsTop.add(bookTraffic, gbcAT);
        gbcContainer.gridy = 1;
        overviewTab.add(analyticsTop, gbcContainer);
        gbcContainer.gridheight = 2;

        // bottom analytics panel
        RoundedPanel analyticsBottom = new RoundedPanel(panelRadius);
        analyticsBottom.setLayout(new GridBagLayout());
        GridBagConstraints gbcAB = new GridBagConstraints();
        gbcAB.insets = new Insets(20, 20, 0, 20);
        JLabel analyticsTwo = new JLabel("Patron Foot Traffic");

        gbcAB.gridx = 1;
        gbcAB.gridy = 1;
        analyticsBottom.add(analyticsTwo, gbcAB);

        // actual bounds of the analytics

        PatronTraffic patTraffic = new PatronTraffic();
        patTraffic.setPreferredSize(new Dimension(analyticsWidth - 40, analyticsHeight - 60));

        gbcAB.gridx = 1;
        gbcAB.gridy = 2;
        gbcAB.gridwidth = 2;
        gbcAB.insets = new Insets(0, 20, 20, 20);

        analyticsBottom.add(patTraffic, gbcAB);
        gbcContainer.gridy = 3;
        overviewTab.add(analyticsBottom, gbcContainer);
        gbcContainer.gridheight = 2;
        overviewTab.add(analyticsBottom, gbcContainer);
            
        analyticsOne.setFont(introRust18Style);
        analyticsTwo.setFont(introRust18Style);
            
        analyticsOne.setForeground(Color.decode("#6d2321"));
        analyticsTwo.setForeground(Color.decode("#6d2321"));

        // other widgets
        gbcContainer.gridx++;

        RoundedPanel quickActions = new RoundedPanel(panelRadius);
        quickActions.setPreferredSize(new Dimension(300, smallWidgetHeight));
        quickActions.setLayout(new FlowLayout(FlowLayout.LEFT));
        quickActions.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        gbcContainer.gridheight = 1;
        gbcContainer.gridy = 1;

        JLabel qckLabel = new JLabel("Quick Actions");
        qckLabel.setForeground(Color.decode("#6d2321"));
        
        qckLabel.setFont(introRust18Style);

        quickActions.add(qckLabel);

        RoundedButton qckBtn1 = new RoundedButton("Lend a book", 20);
        qckBtn1.setPreferredSize(new Dimension(280, 25));
        qckBtn1.setBackground(Color.decode("#f8c169"));
        qckBtn1.setHorizontalAlignment(SwingConstants.LEFT);
        qckBtn1.setForeground(Color.decode("#842b28"));
        qckBtn1.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            new BorrowBookModal(window);
        });
        
        quickActions.add(qckBtn1);

        RoundedButton qckBtn2 = new RoundedButton("Return a book", 20);
        qckBtn2.setPreferredSize(new Dimension(280, 25));
        qckBtn2.setBackground(Color.decode("#f8c169"));
        qckBtn2.setHorizontalAlignment(SwingConstants.LEFT);
        qckBtn2.setForeground(Color.decode("#842b28"));
        qckBtn2.addActionListener(e -> {
		    Window parent = SwingUtilities.getWindowAncestor(this);
		    new ReturnBookModal(parent);
		});
        quickActions.add(qckBtn2);

        RoundedButton qckBtn3 = new RoundedButton("Record a lost item", 20);
        qckBtn3.setPreferredSize(new Dimension(280, 25));
        qckBtn3.setBackground(Color.decode("#f8c169"));
        qckBtn3.setHorizontalAlignment(SwingConstants.LEFT);
        qckBtn3.setForeground(Color.decode("#842b28"));
        quickActions.add(qckBtn3); 

        overviewTab.add(quickActions, gbcContainer);

        // calendar
        RoundedPanel calendar = new RoundedPanel(panelRadius);
        calendar.setPreferredSize(new Dimension(300, (smallWidgetHeight * 3) + 40));
        calendar.setLayout(new BorderLayout());
        calendar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel calLabel = new JLabel("Calendar");
        calLabel.setForeground(Color.decode("#6d2321"));
        
        calLabel.setFont(introRust18Style);

        calendar.add(calLabel, BorderLayout.NORTH);
        
        JPanel calendarCont = new JPanel();
        calendarCont.setPreferredSize(new Dimension(300,(smallWidgetHeight * 3)));
        calendarCont.setOpaque(false);
        calendarCont.setLayout(new BorderLayout());
        
        Fonts introRust = new Fonts("IntroRust", 14f);
        Font introRustStyleFont = introRust.getAppliedFont();
        
        monthText = new JLabel("", SwingConstants.CENTER);
        monthText.setFont(introRustStyleFont);
        monthText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        monthText.setForeground(Color.decode("#6d2321"));
        
        calendarCont.add(monthText, BorderLayout.NORTH);
        
        calendarGrid = new JPanel();
        calendarGrid.setLayout(new GridLayout(7,7));
        
        calendarCont.add(calendarGrid, BorderLayout.CENTER);
        
        calendar.add(calendarCont, BorderLayout.CENTER);
        
        
        
        generateCalendar();

        gbcContainer.gridheight = 3;
        gbcContainer.gridy = 2;
        overviewTab.add(calendar, gbcContainer);

        setBackground(Color.decode("#c4c4c4"));
        overviewTab.setOpaque(false);
        add(overviewTab);
    }
    
    private void generateCalendar() {

        calendarGrid.removeAll();

        String[] weekLabel = {
            "Sun", "Mon", "Tues",
            "Wed", "Thu", "Fri", "Sat"
        };

        // header
        for (String label : weekLabel) {
            JPanel labelRow = new JPanel();
            labelRow.setLayout(new GridBagLayout());
            labelRow.setBackground(Color.decode("#842b28"));
            JLabel day = new JLabel(label, SwingConstants.CENTER);

            if (label.equals("Sun")) {
                day.setForeground(Color.RED);
            } else {
                day.setForeground(Color.WHITE);
            }

            Fonts poppins = new Fonts("Poppins", 12f);
            Font poppins12 = poppins.getAppliedFont();
            day.setFont(poppins12);

            labelRow.add(day);
            calendarGrid.add(labelRow);
        }

        LocalDate firstDay = currentMonth.atDay(1);

        int daysInMonth = currentMonth.lengthOfMonth();

        int startColumn = firstDay.getDayOfWeek().getValue() % 7;

        // start days buffer
        for (int i = 0; i < startColumn; i++) {
            calendarGrid.add(new JLabel(""));
        }

        // days
        for (int day = 1; day <= daysInMonth; day++) {

            JPanel cell = new JPanel(new BorderLayout());

            cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            JLabel dateLabel = new JLabel(String.valueOf(day));
            dateLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            cell.add(dateLabel, BorderLayout.NORTH);

            calendarGrid.add(cell);
        }

        int cellsFilled = startColumn + daysInMonth;

        // end days buffer
        for (int i = cellsFilled; i < 42; i++) {
            calendarGrid.add(new JLabel(""));
        }

        monthText.setText(currentMonth.getMonth() + " " + currentMonth.getYear());

        calendarGrid.revalidate();
        calendarGrid.repaint();
    }
    
    public void reloadData() {
        comp = new MainFunctions(this);

    }
}
 