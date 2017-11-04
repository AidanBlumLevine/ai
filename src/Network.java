import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import graph.Graph;
import graph.Point;

public class Network{
	private static ArrayList<Point> points = new ArrayList<Point>();
	public static void main(String[] args) {
		JFrame frame = new JFrame("Graph");
		JPanel panel = new JPanel(){
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
		NeuralNetwork network = new NeuralNetwork(1,10,1);
		for(int i=0;i<100000;i++) {
			double value = Math.random()*7-3.5;
			network.train(new double[] {value},new double[] {Math.sin(value)}, .05);
			if(i%100==0) {
				
			}
		}
		for(double j = -3.5;j<=3.5;j+=.1) {
			g.clearLine(Color.BLACK);
			g.addPoint(j, Math.sin(j), Color.BLACK);
			g.clearLine(Color.GREEN);
			g.addPoint(j, network.run(new double[] {j})[0], Color.GREEN);
			System.out.println( network.run(new double[] {j})[0]);
		}
		g.update();
	}

}
