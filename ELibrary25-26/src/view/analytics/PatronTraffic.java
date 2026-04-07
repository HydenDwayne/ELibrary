package view.analytics;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.DefaultCategoryDataset;

import model.DAOs.Overview.DAOPatronFootTraffic;
import model.DAOs.Overview.OverviewDAOImp;

import org.jfree.chart.plot.CategoryPlot;
import java.awt.geom.Ellipse2D;
import java.util.Date;
import java.util.List;
import view.fonts.Fonts;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import java.text.SimpleDateFormat;

public class PatronTraffic extends JPanel {

    public PatronTraffic() {
        Fonts poppins8 = new Fonts("Poppins", 8f);
        Font poppins = poppins8.getAppliedFont();
        
        int width = 700;
        int height = 280;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        OverviewDAOImp dao = new OverviewDAOImp();
        List<DAOPatronFootTraffic> trafficData = dao.getTraffic();

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mma");

        // You can use real system time or a fake time for testing
        Date now = new Date();
//        Date fakeNow = new Date(now.getTime() + (10 * 60 * 60 * 1000)); // 10 hours ahead
//        System.out.println("Fake Now: " + fakeNow);

        // Define all labels you want on X-axis
        String[] allTimes = { 
            "7:00AM", "8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00PM",
            "1:00PM", "2:00PM", "3:00PM", "4:00PM", "5:00PM", "6:00PM", "7:00PM"
        };

        // Initialize dataset with all labels (null values)
        for (String timeLabel : allTimes) {
            dataset.addValue(null, "", timeLabel);
        }

        // Fill in data only if ≤ fakeNow
        for (DAOPatronFootTraffic data : trafficData) {
            String formattedTime = timeFormat.format(data.getTime());

            if (!data.getTime().after(now)) {
                dataset.setValue(data.getCount(), "", formattedTime);
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