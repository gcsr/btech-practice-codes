import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;




public class DrawChart extends JFrame {
	
	
	int[] actual;
	int[] predictions;

	int[] xaxis;
	
	public DrawChart(List<GraphObject> objects){
		
		super("Existing System - ProposedSystem");
		
		actual=new int[objects.size()];
		predictions=new int[objects.size()];
		xaxis=new int[objects.size()];
		
		Iterator<GraphObject> itr=objects.iterator();
		int i=0;
		while(itr.hasNext()){
			GraphObject obj=itr.next();
			actual[i]=(int)obj.getNormalTime();
			predictions[i]=(int)obj.getBasestationTime();
			xaxis[i]=obj.getMessages();
			i++;
		}
		
		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);
		
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public DrawChart(int[] actual,int[] predictions,int[] xaxis) {
		super("Network LifeTime Chart");
		
		
		this.actual=actual;
		this.predictions=predictions;
		this.xaxis=xaxis;
		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);
		
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private JPanel createChartPanel() {
		String chartTitle = "Lifetime Comparision";
		String xAxisLabel = "X";
		String yAxisLabel = "Y";
		
		XYDataset dataset = createDataset();
		
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, 
				xAxisLabel, yAxisLabel, dataset);
		
//		boolean showLegend = false;
//		boolean createURL = false;
//		boolean createTooltip = false;
//		
//		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, 
//				xAxisLabel, yAxisLabel, dataset, 
//				PlotOrientation.HORIZONTAL, showLegend, createTooltip, createURL);
		
		customizeChart(chart);
		
		// saves the chart as an image files
		File imageFile = new File("XYLineChart.png");
		int width = 640;
		int height = 480;
		
		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		return new ChartPanel(chart);
	}

	private XYDataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("existing system");
		XYSeries series2 = new XYSeries("proposed system");
		//XYSeries series3 = new XYSeries("Object 3");
		
		
		for(int i=0;i<actual.length;i++){
			series1.add(xaxis[i],actual[i]);
		}
		
		for(int i=0;i<actual.length;i++){
			series2.add(xaxis[i],predictions[i]);
		}
		
		
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		
		
		return dataset;
	}
	
	private void customizeChart(JFreeChart chart) {
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.BLUE);
		renderer.setSeriesPaint(2, Color.YELLOW);

		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		
		// sets paint color for plot outlines
		plot.setOutlinePaint(Color.BLUE);
		plot.setOutlineStroke(new BasicStroke(2.0f));
		
		// sets renderer for lines
		plot.setRenderer(renderer);
		
		// sets plot background
		plot.setBackgroundPaint(Color.DARK_GRAY);
		
		// sets paint color for the grid lines
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//new DrawChart().setVisible(true);
			}
		});
	}
}