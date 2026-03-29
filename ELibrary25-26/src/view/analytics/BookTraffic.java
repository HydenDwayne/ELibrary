package view.analytics;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.CategoryPlot;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import view.fonts.Fonts;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

public class BookTraffic extends JPanel {

	public BookTraffic() {
		Fonts poppins8 = new Fonts("Poppins", 8f);
		Font poppins = poppins8.getAppliedFont();
		
		int width = 700;
		int height = 280;
		String[] time = { "7:00AM", "8:00", "9:00", "10:00", "11:00", "12:00", 
				"1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00PM" };
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (String hour: time) {
			
			Random rand = new Random();
			int randomInt = rand.nextInt((1600 - 500) + 1) + 500;
			dataset.addValue(randomInt, "", hour);
		}
		JFreeChart chart = ChartFactory.createLineChart("", "", "", dataset);
		chart.removeLegend();
		
		CategoryPlot plot = chart.getCategoryPlot();
		plot.getDomainAxis().setCategoryLabelPositions(
		        CategoryLabelPositions.UP_45
		);
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
		chartPanel.setPreferredSize(new Dimension(width-40, height-60));
		setOpaque(false);
		add(chartPanel);
	}
}
