package io.github.NadhifRadityo.CircleFellowship;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.UIManager;

import io.github.NadhifRadityo.Objects.Canvas.CanvasPanel;
import io.github.NadhifRadityo.Objects.Canvas.RenderHints.AntiAlias;
import io.github.NadhifRadityo.Objects.Canvas.Shapes.Circle;
import io.github.NadhifRadityo.Objects.Canvas.Shapes.Line;
import io.github.NadhifRadityo.Objects.Canvas.Shapes.Oval;
import io.github.NadhifRadityo.Objects.Canvas.Shapes.Point;
import io.github.NadhifRadityo.Objects.Utilizations.DimensionUtils;

public class CircleFellowship extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 530839473864811306L;
	private Dimension windowDim;
	private CanvasPanel canvasPanel;
	
	public CircleFellowship() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		windowDim = new Dimension(766, 500);
		setSize(windowDim);
		setPreferredSize(windowDim);
		setLayout(new GridLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvasPanel = new CanvasPanel();
		canvasPanel.setSize(DimensionUtils.getMaxDimension());
		canvasPanel.setPreferredSize(DimensionUtils.getMaxDimension());
		add(canvasPanel);
		
		canvasPanel.addSprite(new AntiAlias(true), -1);
		drawInsideIntersection(new Circle(200, 200, 150, false), new Circle(400, 200, 75, true));
	}
	
	public void drawInsideIntersection(Circle c1, Circle c2) {
		canvasPanel.addSprite(c1);
		canvasPanel.addSprite(c2);
		Circle biggestCircle = c1.getR() > c2.getR() ? c1 : c2;
		Circle smallestCircle = c1.getR() < c2.getR() ? c1 : c2;
		
		// Circle center
		Point p1 = c1.getCenterPoint();
		Point p2 = c2.getCenterPoint();
		canvasPanel.addSprite(p1);
		canvasPanel.addSprite(p2);
		//Circle Point Line
		Line circlePointLine = new Line(p1, p2);
		int circlePointLineDistance = (int) circlePointLine.getDistance();
		canvasPanel.addSprite(circlePointLine);
		
		//Find Center of Circle Point Line *Manual
		int centerCirclePointLineWidth = new Random().nextInt((circlePointLineDistance * 2 - circlePointLineDistance) + 1) + circlePointLineDistance;
		Circle centerCirclePointLine1 = new Circle(p1, centerCirclePointLineWidth, true);
		Circle centerCirclePointLine2 = new Circle(p2, centerCirclePointLineWidth, true);
		canvasPanel.addSprite(centerCirclePointLine1);
		canvasPanel.addSprite(centerCirclePointLine2);
		Point[] centerCirclePointIntersect = compressPoint(checkIntersects(centerCirclePointLine1, centerCirclePointLine2, 5000));
		Line centerCirclePointLine = new Line(centerCirclePointIntersect[0], centerCirclePointIntersect[1]);
		canvasPanel.addSprite(centerCirclePointLine);
		
		//Draw circle with radius of half circle point line
		Circle centerCircleHalfPointLine = new Circle((circlePointLine.getX() + circlePointLine.getX2()) / 2, 
				(centerCirclePointLine.getY() + centerCirclePointLine.getY2()) / 2, circlePointLineDistance, true);
		canvasPanel.addSprite(centerCircleHalfPointLine);
		
		//Find point on biggest circle
		Circle smallCircleinB = new Circle(biggestCircle.getCenterPoint(), biggestCircle.getD() - smallestCircle.getD(), true);
		canvasPanel.addSprite(smallCircleinB);
		Point[] fPointB = compressPoint(checkIntersects(centerCircleHalfPointLine, smallCircleinB, 5000));
		canvasPanel.addSprite(fPointB[0]);
		canvasPanel.addSprite(fPointB[1]);
		Line fPointLineB1 = Line.extend(biggestCircle.getCenterPoint(), fPointB[0], biggestCircle.getR());
		Line fPointLineB2 = Line.extend(biggestCircle.getCenterPoint(), fPointB[1], biggestCircle.getR());
		canvasPanel.addSprite(fPointLineB1);
		canvasPanel.addSprite(fPointLineB2);
		
		//Draw line with smallest circle's center
		Line fFLine1 = new Line(fPointB[0], smallestCircle.getCenterPoint());
		Line fFLine2 = new Line(fPointB[1], smallestCircle.getCenterPoint());
		canvasPanel.addSprite(fFLine1);
		canvasPanel.addSprite(fFLine2);
		int ffLineDistance = (int) ((fFLine1.getDistance() + fFLine2.getDistance()) / 2);
		
		//Final Line
		Circle fCircle1 = new Circle(fPointLineB1.getX2(), fPointLineB1.getY2(), ffLineDistance * 2, true);
		Circle fCircle2 = new Circle(fPointLineB2.getX2(), fPointLineB2.getY2(), ffLineDistance * 2, true);
		canvasPanel.addSprite(fCircle1);
		canvasPanel.addSprite(fCircle2);
		Point[] fPointCircle1 = compressPoint(checkIntersects(fCircle1, smallestCircle, 5000));
		Point[] fPointCircle2 = compressPoint(checkIntersects(fCircle2, smallestCircle, 5000));
		Line fLine1 = new Line(fPointLineB1.getX2(), fPointLineB1.getY2(), fPointCircle1[0].getX(), fPointCircle1[0].getY());
		Line fLine2 = new Line(fPointLineB1.getX2(), fPointLineB1.getY2(), fPointCircle1[1].getX(), fPointCircle1[1].getY());
		Line fLine3 = new Line(fPointLineB2.getX2(), fPointLineB2.getY2(), fPointCircle2[0].getX(), fPointCircle2[0].getY());
		Line fLine4 = new Line(fPointLineB2.getX2(), fPointLineB2.getY2(), fPointCircle2[1].getX(), fPointCircle2[1].getY());
		canvasPanel.addSprite(fLine1);
		canvasPanel.addSprite(fLine2);
		canvasPanel.addSprite(fLine3);
		canvasPanel.addSprite(fLine4);
	}
	
	public Point[] checkIntersects(Oval c1, Oval c2, int numPoints) {
		List<Point> points = new ArrayList<>();
		Point[] p1s = c1.getPoints(numPoints);
		Point[] p2s = c2.getPoints(numPoints);
		for(Point p1 : p1s) { for(Point p2 : p2s) {
			if(p1.equals(p2)) points.add(p2);
		} } return points.toArray(new Point[points.size()]);
	}
	
	public Point[] compressPoint(Point[] points) {
		double minAvg = Double.MAX_VALUE;
		double maxAvg = Double.MIN_VALUE;
		Point[] result = new Point[2];
		for(Point point : points) {
			double avg = (point.getX() + point.getY()) / 2;
			if(avg < minAvg) { result[0] = point; minAvg = avg; }
			if(avg > maxAvg) { result[1] = point; maxAvg = avg; }
		} return result;
	}
	
	public static void main(String... strings) {
		try {
			CircleFellowship circleFellowship = new CircleFellowship();
			circleFellowship.setVisible(true);
		} catch (Exception e) { e.printStackTrace(); }
	}
}
