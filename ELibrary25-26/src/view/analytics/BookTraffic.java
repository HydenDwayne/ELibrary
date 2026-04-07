package view.analytics;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.DefaultCategoryDataset;

import model.DAOs.Overview.DAOBookTraffic;
import model.DAOs.Overview.OverviewDAOImp;

import org.jfree.chart.plot.CategoryPlot;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import view.fonts.Fonts;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

public class BookTraffic extends JPanel {

    public BookTraffic() {
        Fonts poppins8 = new Fonts("Poppins", 8f);
        Font poppins = poppins8.getAppliedFont();
        
        int width = 700;
        int height = 280;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
        OverviewDAOImp dao = new OverviewDAOImp();
        List<DAOBookTraffic> trafficData = dao.getBookTraffic();

        // Define all weekdays for X-axis
        String[] allDays = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

        // Initialize dataset with null values so all days appear
        for (String day : allDays) {
            dataset.addValue(null, "", day);
        }

        // Determine today (e.g., Calendar.MONDAY=2, ... Calendar.SUNDAY=1)
        Calendar cal = Calendar.getInstance();
        int today = cal.get(Calendar.DAY_OF_WEEK); // Sunday=1, Monday=2, ..., Saturday=7

        // Map Calendar day to string day
        String[] calendarToDay = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        for (DAOBookTraffic data : trafficData) {
            String dayName = data.getDayName();

            // Only show data if the day is on or before today
            boolean showData = false;
            for (int i = 0; i < calendarToDay.length; i++) {
                if (calendarToDay[i].equals(dayName) && i <= today - 1) {
                    showData = true;
                    break;
                }
            }

            if (showData) {
                dataset.setValue(data.getCount(), "", dayName);
            }
        }

        JFreeChart chart = ChartFactory.createLineChart("", "", "", dataset);
        chart.removeLegend();
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getDomainAxis().setCategoryLabelPositions(
            CategoryLabelPositions.UP_45
        );
        
        NumberAxis range = (NumberAxis) plot.getRangeAxis();

     // Force integers only
        range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.decode("#842b28"));
        renderer.setSeriesStroke(0, new BasicStroke(1.5f));
        Shape circle = new Ellipse2D.Double(-3, -3, 6, 6);
        renderer.setSeriesShape(0, circle);
        renderer.setSeriesShapesVisible(0, true);

        plot.getDomainAxis().setTickLabelFont(poppins);
        plot.getRangeAxis().setTickLabelFont(poppins);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setOpaque(false);
        chartPanel.setPreferredSize(new Dimension(width - 40, height - 60));
        setOpaque(false);
        add(chartPanel);
    }
}