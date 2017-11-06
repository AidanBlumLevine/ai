package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graph3D extends JPanel{
	private double[][] points = new double[500][500];
	public Graph3D() {
		JFrame frame = new JFrame("Graph");
		frame.add(this);
		frame.setSize(500, 500);
	    frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
		super.paint(g2d);
		for(int x=0;x<500;x++) {
			for(int y=0;y<500;y++) {
				int red = (int)(Math.min(Math.max(points[x][y], 0),1)*255);
				g2d.setColor(new Color(red, 255-red, 255));
				g2d.drawLine(x, y, x, y);
			}
		}
	}
	public void updatePoints(double[][] points) {
		this.points=points;
	}
}
