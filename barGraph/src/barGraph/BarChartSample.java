package barGraph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

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
		Graphics2D g2 = (Graphics2D) g;
		
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
		double x = (clientWidth - titleWidth) / 2;
		
		FontRenderContext context = g2.getFontRenderContext();
		g2.setFont(titleFont);
		g2.drawString(title, (float)x,(float)y);
		g2.setFont(labelFont);
		int top = titleFontMetrics.getHeight();
		int bottom = labelFontMetrics.getHeight();
		if (maxValue == minValue)
			return;
		double scale = (clientHeight - top - bottom) / (maxValue - minValue);
		y = clientHeight - labelFontMetrics.getDescent();
		g.setFont(labelFont);
		
		
		int i = 0;
		for(double d1 : values){
			double x1 = i * barWidth + 1;
			double y1 = top;
			double height = d1 * scale;
			if(d1 >= 0)
				y1 += (maxValue - d1) * scale;
			else{
				y1 += maxValue * scale;
				height = -height;
			}
			Rectangle2D rect = new Rectangle2D.Double(x1, y1, barWidth - 2, height);
			g2.setPaint(Color.pink);
			g2.fill(rect);
			g2.setPaint(Color.BLACK);
			g2.draw(rect);
			Rectangle2D labelBounds = labelFont.getStringBounds(names[i], context);
			double labelWidth = labelBounds.getWidth();
			x = (i * barWidth + (barWidth - labelWidth)/2);
			g2.drawString(names[i], (float)x, (float)y);
			i++;
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
	
	
	}
}