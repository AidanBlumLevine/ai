package graph;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Graph{
	private ArrayList<Point> points = new ArrayList<Point>();
	private double xLow,xHigh,yLow,yHigh,streams;
	private double xScale,yScale;
	private JPanel panel;
	public Graph(double xLow,double xHigh,double yLow,double yHigh,int streams){
		this.xLow=xLow;
		this.xHigh=xHigh;
		this.yLow=yLow;
		this.yHigh=yHigh;
		this.streams=streams;
		xScale=500/(xHigh-xLow);
		yScale=500/(yHigh-yLow);
		
		JFrame frame = new JFrame("Graph");
		panel = new JPanel(){
			@Override
			public void paint(Graphics g){
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);   
				g2d.setStroke(new BasicStroke(3));
				for(int p=0;p<points.size()-streams;p++){
					g2d.setColor(points.get(p).getColor());
					g2d.drawLine((int)(points.get(p).getX()*xScale), (int)(points.get(p).getY()*yScale), (int)(points.get(p+streams).getX()*xScale), (int)(points.get(p+streams).getY()*yScale));
				}
			}
		};
		frame.add(panel);
		frame.setSize(500, 500);
	    frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	}

	
	public void addPoint(double x,double y,Color color){
		points.add(new Point(x,y, color));
	}

	public void update(){
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	panel.repaint();
		    }});
	}


	public void clearLine(Color color) {
		for(int p=0;p<points.size();p++){
			if(points.get(p).getColor().equals(color)){
				points.remove(p);
				p--;
			}
		}
		
	}
}
