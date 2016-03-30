package javaapplet;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

import javax.swing.JComponent;
import javax.swing.JPanel;


class ChartComponent extends JPanel{
	private double[] values;
	private String[] names;
	private String title;
	public ChartComponent(double[] v, String[] n, String t){
		names = n;
		values = v;
		title = t;
	}
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		if(values == null)
			return;
		double minValue = 0;
		double maxValue = 0;
		for(double d : values){
			if(minValue > d)
				minValue =d;
			if(maxValue < d)
				maxValue = d;
		}
		if(maxValue == minValue)
			return;
		int panelWidth = getWidth();
		int panelHeight = getHeight();
		Font titleFont = new Font("SansSerif", Font.BOLD, 20);
		Font labelFont = new Font("SansSerif", Font.PLAIN,10);
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D titleBounds = titleFont.getStringBounds(title, context);
		double titleWidth = titleBounds.getWidth();
		double top = titleBounds.getHeight();
		double y = -titleBounds.getY();
		double x = (panelWidth - titleWidth)/2;
		g2.setFont(titleFont);
		g2.drawString(title, (float)x,(float)y);
		LineMetrics labelMetrics = labelFont.getLineMetrics("", context);
		double bottom = labelMetrics.getHeight();
		y = panelHeight - labelMetrics.getDescent();
		g2.setFont(labelFont);
		double scale = (panelHeight - top - bottom)/(maxValue - minValue);
		int barWidth = panelWidth/values.length;

		int i = 0;
		for(double d : values){
			double x1 = i * barWidth + 1;
			double y1 = top;
			double height = d * scale;
			if(d >= 0)
				y1 += (maxValue - d) * scale;
			else{
				y1 += maxValue * scale;
				height = -height;
			}
			Rectangle2D rect = new Rectangle2D.Double(x1, y1, barWidth - 2, height);
			g2.setPaint(Color.RED);
			g2.fill(rect);
			g2.setPaint(Color.BLACK);
			g2.draw(rect);
			Rectangle2D labelBounds = labelFont.getStringBounds(names[i], context);
			double labelWidth = labelBounds.getWidth();
			x = i * barWidth + (barWidth - labelWidth)/2;
			g2.drawString(names[i], (float)x, (float)y);
			i++;
		}
	}
}