package io.github.NadhifRadityo.CircleFellowship.Core.Object.Canvas.Shapes;

import java.awt.Graphics;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Circle extends Rectangle {
	protected boolean center;
	public Circle(int x, int y, int width, int height, boolean center) {
		super(x, y, width, height);
		this.center = center;
		if(center) {
			this.x -= width / 2;
			this.y -= height / 2;
		}
	}
	public Circle(int x, int y, int width, int height) {
		this(x, y, width, height, false);
	}
	public Circle(int x, int y, int r) {
		this(x, y, r, r, true);
	}
	
	public int getX(boolean center) {
		return center ? this.x + width / 2 : (this.center ? this.x - width / 2 : this.x);
	}
	public int getY(boolean center) {
		return center ? this.y + height / 2 : (this.center ? this.y - height / 2 : this.y);
	}
	public boolean isCenter() {
		return center;
	}
	public Point getCenterPoint() {
		return new Point(getX(true), getY(true));
	}
	
	public Point[] getPoints(int numPoints) {
		final Point[] points = new Point[numPoints];
		int x = getX(true);
		int y = getY(true);
		for (int i = 0; i < points.length; ++i) {
			double angle = Math.toRadians(((double) i / points.length) * 360d);
			points[i] = new Point((int) (Math.cos(angle) * (width / 2)) + x, (int) (Math.sin(angle) * (height / 2)) + y);
		}
		return points;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawOval(x, y, width, height);
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Circle))
			return false;
		if (!super.equals(other))
			return false;
		Circle castOther = (Circle) other;
		return Objects.equals(center, castOther.center);
	}
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), center);
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append("center", center).toString();
	}

}
