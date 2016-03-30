package javaapplet;

import java.applet.Applet;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

import javax.swing.JApplet;


public class DrawStringApplet extends Applet {

	public void init() {

		String v = getParameter("values");
		if (v == null)
			return;
		int n = Integer.parseInt(v);
		double[] values = new double[n];
		String[] names = new String[n];
		int i;
		for (i = 0; i < n; i++) {
			values[i] = Double.parseDouble(getParameter("value" + (i + 1)));
			names[i] = getParameter("name" + (i + 1));
		}
		add(new ChartComponent(values, names, getParameter("title")));
	}

}