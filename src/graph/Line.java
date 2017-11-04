package graph;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Line {
	private ArrayList<Point2D.Double> points;
	private Color color;
	public Line(ArrayList<Point2D.Double> points,Color color){
		this.points=points;
		this.color=color;
	}
	public void draw(Graphics2D g2d,double xScale,double yScale,double xMin,double yMin) {
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(color);
		for(int p=0;p<points.size()-1;p++) {
			g2d.drawLine((int)(points.get(p).x*xScale-xMin), (int)(points.get(p).y*yScale-yMin),
					(int)(points.get(p+1).x*xScale-xMin), (int)(points.get(p+1).y*yScale-yMin));
		}
	}
	public Color getColor() {
		return color;
	}
}
