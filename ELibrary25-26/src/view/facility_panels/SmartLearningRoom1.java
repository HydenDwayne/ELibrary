package view.facility_panels;

import view.fonts.Fonts;
import java.awt.*;
import java.time.*;
import javax.swing.*;

import controller.FunctionHallController;

public class SmartLearningRoom1 extends JPanel {

    private JPanel calendarGrid;
    private JLabel monthText;
    private YearMonth currentMonth;
    
    public YearMonth getCurrentMonth() {
    	return currentMonth;
    }

    JButton dateCont = new JButton();

    public SmartLearningRoom1() {
    	Timer timer = new Timer(15000, e -> generateCalendar());
		timer.start();
        setOpaque(false);

        setLayout(new BorderLayout());

        currentMonth = YearMonth.now();

        JPanel calendarCont = new JPanel(new BorderLayout());
        calendarCont.setOpaque(false);
        calendarCont.setPreferredSize(new Dimension(1200, 400));

        JPanel monthPanel = new JPanel();
        monthPanel.setOpaque(false);
        monthPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        monthPanel.setLayout(new GridBagLayout());

        JPanel monthCtrl = new JPanel(new BorderLayout());
        monthCtrl.setOpaque(false);

        JButton leftBtn = new JButton("<");
        JButton rightBtn = new JButton(">");

        leftBtn.setContentAreaFilled(false);
        leftBtn.setBorderPainted(false);
        leftBtn.setFocusPainted(false);

        rightBtn.setContentAreaFilled(false);
        rightBtn.setBorderPainted(false);
        rightBtn.setFocusPainted(false);

        monthCtrl.add(leftBtn, BorderLayout.WEST);
        monthCtrl.add(rightBtn, BorderLayout.EAST);

        monthText = new JLabel("", SwingConstants.CENTER);
        Fonts introRust = new Fonts("IntroRust", 16f);

        JPanel monthTextCont = new JPanel();
        monthTextCont.setOpaque(false);
        monthTextCont.setPreferredSize(new Dimension(200, 20));
        monthTextCont.add(monthText);

        monthText.setFont(introRust.getAppliedFont());

        monthCtrl.add(monthTextCont, BorderLayout.CENTER);
        monthPanel.add(monthCtrl);

        // calendar grid
        calendarGrid = new JPanel(new GridLayout(7, 7));
        calendarGrid.setBackground(Color.decode("#e4e4e4"));

        calendarCont.add(monthPanel, BorderLayout.NORTH);
        calendarCont.add(calendarGrid, BorderLayout.CENTER);

        add(calendarCont, BorderLayout.CENTER);

        // generate initial calendar
        generateCalendar();

        // month navigation
        leftBtn.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1);
            generateCalendar();
        });

        rightBtn.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1);
            generateCalendar();
        });
    }

    private void generateCalendar() {

        calendarGrid.removeAll();

        String[] weekLabel = {
            "Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday"
        };

        // header
        for (String label : weekLabel) {
            JPanel labelRow = new JPanel();
            labelRow.setLayout(new GridBagLayout());
            labelRow.setBackground(Color.decode("#842b28"));
            JLabel day = new JLabel(label, SwingConstants.CENTER);

            if (label.equals("Sunday")) {
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
        FunctionHallController comp = new FunctionHallController(this, calendarGrid);
        
        int cellsFilled = startColumn + daysInMonth;

        // end days buffer
        for (int i = cellsFilled; i < 42; i++) {
            calendarGrid.add(new JLabel(""));
        }

        monthText.setText(currentMonth.getMonth() + " " + currentMonth.getYear());

        calendarGrid.revalidate();
        calendarGrid.repaint();
    }
}
