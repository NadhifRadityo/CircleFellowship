package io.github.NadhifRadityo.CircleFellowship.Core.Object.Canvas.Shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Rectangle {
	public Point(int x, int y) {
		super(x, y, 1, 1);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		super.draw(g);
		g.setColor(Color.BLACK);
	}
}
