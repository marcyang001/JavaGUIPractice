package javaapplet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BarChartSample extends JPanel {
	private double[] values;
	private String[] names;
	private String title;
	int Xleft = 10;
	int Xright = 500;
	int Ytop = 10;
	int Ybottom = 600; // y value entries can be up to 150

	public BarChartSample(double[] v, String[] n, String t) {
		names = n;
		values = v;
		title = t;
		setBackground(Color.LIGHT_GRAY);

	}
	
	public void drawAxes (Graphics g)
	{
	     g.drawLine(Xleft, Ytop, Xleft, Ybottom);
	     g.drawLine(Xleft, Ybottom, Xright, Ybottom);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//drawAxes(g);
		if (values == null || values.length == 0)
			return;
		double minValue = 0;
		double maxValue = 0;
		for (int i = 0; i < values.length; i++) {
			if (minValue > values[i])
				minValue = values[i];
			if (maxValue < values[i])
				maxValue = values[i];
		}

		Dimension d = getSize();
		int clientWidth = d.width;
		int clientHeight = d.height;
		int barWidth = clientWidth / values.length;

		Font titleFont = new Font("SansSerif", Font.BOLD, 20);
		FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
		Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
		FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

		int titleWidth = titleFontMetrics.stringWidth(title);
		int y = titleFontMetrics.getAscent();
		int x = (clientWidth - titleWidth) / 2;
		g.setFont(titleFont);
		g.drawString(title, x, y);

		int top = titleFontMetrics.getHeight();
		int bottom = labelFontMetrics.getHeight();
		if (maxValue == minValue)
			return;
		double scale = (clientHeight - top - bottom) / (maxValue - minValue);
		y = clientHeight - labelFontMetrics.getDescent();
		g.setFont(labelFont);
		
		
		for (int i = 0; i < values.length; i++) {
			int valueX = i * barWidth + 1;
			int valueY = top;
			int height = (int) (values[i] * scale);
			if (values[i] >= 0)
				valueY += (int) ((maxValue - values[i]) * scale);
			else {
				valueY += (int) (maxValue * scale);
				height = -height;
			}

			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(valueX, valueY - 20, barWidth - 80, height);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY - 20, barWidth - 80, height);

			g.setColor(Color.blue);
			g.fillRect(valueX, valueY + 20, barWidth - 80, height - 20);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY + 20, barWidth - 80, height - 20);
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);
			
			g.drawString(Double.toString(values[i]), valueX, valueY);
			
		}
	}

	
	
	public static void main(String[] argv) {
		JFrame f = new JFrame();
		//f.setPreferredSize(new Dimension(900, 1000));
		f.setSize(900, 1000);
		double[] values = new double[5];
		String[] names = new String[5];
		values[0] = 1;
		names[0] = "Item 1";

		values[1] = 2;
		names[1] = "Item 2";

		values[2] = 4;
		names[2] = "Item 3";

		values[3] = 10;
		names[3] = "Item 4";
		
		values[4] = 8;
		names[4] = "Item 5";
		BarChartSample b = new BarChartSample(values, names, "bar graph");
		f.getContentPane().add(b);

		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		f.addWindowListener(wndCloser);
		f.setVisible(true);
	
		JPanel panel = new JPanel()
	    {
	        @Override
	        public Dimension getPreferredSize() {
	            return new Dimension(800, 1000);
	        }
	    };
	    panel.add(new JLabel("Test1"));
	    panel.add(new JLabel("Test2"));
	    //b.setSize(new Dimension(900, 1000));
	    //f.getContentPane().add(new JScrollPane(f), BorderLayout.CENTER);
	    
	    
	    //f.setSize(600, 800);
	    //f.setVisible(true);
	
	
	}
}