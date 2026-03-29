package archives;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import view.fonts.Fonts;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

public class LineChartExample extends JFrame {

	public LineChartExample() {

		Fonts poppins8 = new Fonts("Poppins", 8f);
		Font poppins = poppins8.getAppliedFont();
		
		int width = 700;
		int height = 280;
		String[] time = { "7:00AM", "8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00NN", 
				"1:00PM", "2:00PM", "3:00PM", "4:00PM", "5:00PM", "6:00PM", "7:00PM" };
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for (String hour: time) {
			
			Random rand = new Random();
			int randomInt = rand.nextInt((1600 - 500) + 1) + 500;
			dataset.addValue(randomInt, "", hour);
		}
		JFreeChart chart = ChartFactory.createLineChart("", "", "", dataset);
		chart.removeLegend();
		
		CategoryPlot plot = chart.getCategoryPlot();

		plot.getDomainAxis().setTickLabelFont(poppins);
		plot.getRangeAxis().setTickLabelFont(poppins);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(width-40, height-60));
		add(chartPanel);
	}

	public static void main(String[] args) {
		LineChartExample example = new LineChartExample();
		example.setSize(800, 300);
		example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		example.setVisible(true);
	}
}