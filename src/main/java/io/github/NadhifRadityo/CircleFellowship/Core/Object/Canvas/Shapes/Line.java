package io.github.NadhifRadityo.CircleFellowship.Core.Object.Canvas.Shapes;

import java.awt.Graphics;

import io.github.NadhifRadityo.CircleFellowship.Core.Object.Canvas.Sprite;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Line extends Sprite {
	protected int x2, y2;
	public Line(int x, int y, int x2, int y2) {
		super(x, y);
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getX2() {
		return x2;
	}
	public int getY2() {
		return y2;
	}
	
	public double getDistance() {
		double ac = Math.abs(y2 - y);
		double cb = Math.abs(x2 - x);
		return Math.hypot(ac, cb);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawLine(x, y, x2, y2);
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Line))
			return false;
		if (!super.equals(other))
			return false;
		Line castOther = (Line) other;
		return Objects.equals(x2, castOther.x2) && Objects.equals(y2, castOther.y2);
	}
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), x2, y2);
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append("x2", x2).append("y2", y2).toString();
	}
	
	public static Line extend(Point lockPoint, Point startPoint, int length) {
//		int lengthAB = (int) Math.sqrt((lockPoint.getX() - startPoint.getX()) ^ 2 + (lockPoint.getY() - startPoint.getY()) ^ 2); 
//		int x = startPoint.getX() + (startPoint.getX() - lockPoint.getX()) / lengthAB * length;
//		int y = startPoint.getY() + (startPoint.getY() - lockPoint.getY()) / lengthAB * length;
		int x = startPoint.getX(), y = startPoint.getY();
		double alpha = Math.atan2(y - lockPoint.getX(), x - lockPoint.getY());
		x = (int) (lockPoint.getX() + length * Math.cos(alpha));
		y = (int) (lockPoint.getY() + length * Math.sin(alpha));
		return new Line(lockPoint.getX(), lockPoint.getY(), x, y);
	}
}
