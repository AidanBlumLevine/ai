package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graph extends JPanel{
	private ArrayList<Line> lines = new ArrayList<Line>();
	private double xScale,yScale,xMin,yMin;
	public Graph(double xLow,double yLow,double xHigh,double yHigh) {
		JFrame frame = new JFrame("Graph");
		frame.add(this);
		frame.setSize(500, 500);
	    frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		
		xScale = 500/(xHigh-xLow);
		yScale = 500/(yHigh-yLow);
		xMin = xLow*xScale;
		yMin = yLow*yScale;
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
		super.paint(g2d);
		for(int l=0;l<lines.size();l++) {
			lines.get(l).draw(g2d, xScale, yScale, xMin, yMin);
		}
	}
	public void addLine(Line l) {
		lines.add(l);
	}
	public void removeLine(Color lineColor) {
		for(int l=0;l<lines.size();l++) {
			if(lines.get(l).getColor().equals(lineColor)) {
				lines.remove(l);
				l--;
			}
		}
	}
}
