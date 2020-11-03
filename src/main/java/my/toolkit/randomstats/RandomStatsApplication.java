package my.toolkit.randomstats;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * cf https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html
 * @author michel
 *
 */
@SpringBootApplication
public class RandomStatsApplication extends JFrame {

	public RandomStatsApplication() {
		initUI();
	}

	private void initUI() {

		var quitButton = new JButton("Quit");

		quitButton.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		createLayout(quitButton);

		setTitle("Dashboard");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createLayout(JComponent... arg) {

		var pane = getContentPane();
		var gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);

//		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));
//
//		gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]));
		
		XYDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(chartPanel));
		gl.setVerticalGroup(gl.createSequentialGroup().addComponent(chartPanel));

		
		
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
				      .addComponent(arg[0])
				      .addComponent(chartPanel)
//				      .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
//				           .addComponent(c3)
//				           .addComponent(c4))
				);
		gl.setVerticalGroup(
				gl.createSequentialGroup()
				      .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
				           .addComponent(arg[0])
				           .addComponent(chartPanel))
//				           .addComponent(c3))
//				      .addComponent(c4)
				);
		
//		add(chartPanel);
		
		
//		pack();
//		setTitle("Line chart");
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private XYDataset createDataset() {
		var series = new XYSeries("2016");
		series.add(18, 567);
		series.add(20, 612);
		series.add(25, 800);
		series.add(30, 980);
		series.add(40, 1410);
		series.add(50, 2350);
		var dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		return dataset;
	}
	
	private JFreeChart createChart(XYDataset dataset) {
		JFreeChart chart = ChartFactory.createXYLineChart(
				"Average salary per age",
				"Age",
				"Salary (€)",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		);
		
		XYPlot plot = chart.getXYPlot();
		var renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		chart.getLegend().setFrame(BlockBorder.NONE);
		chart.setTitle(
			new TextTitle("Average Salary per Age",
				new Font("Serif", java.awt.Font.BOLD, 18)
			)
		);
		return chart;
	}
	

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(RandomStatsApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext ctx = builder.run(args);

		EventQueue.invokeLater(() -> {

			var ex = ctx.getBean(RandomStatsApplication.class);
			ex.setVisible(true);
		});
	}
}
