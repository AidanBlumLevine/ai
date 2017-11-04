import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import graph.Graph;
import graph.Line;

public class Network{
	private static NeuralNetwork network;
	public static void main(String[] args) {
		Graph g = new Graph(-4,-2,4,2);
		ArrayList<Point2D.Double> sin = new ArrayList<Point2D.Double>();
		for(double j=-4;j<4;j+=.01) {
			sin.add(new Point2D.Double(j,Math.sin(j)));
		}
		g.addLine(new Line(sin,Color.GRAY));
		g.repaint();
		network = new NeuralNetwork(1,10,1);
		for(int i=0;i<10000000;i++) {
			double value = Math.random()*7-3.5;
			network.train(new double[] {value},new double[] {Math.sin(value)}, .05);
			if(i%100==0) {
				
			}
		}
		ArrayList<Point2D.Double> approximation = new ArrayList<Point2D.Double>();
		g.removeLine(Color.GREEN);
		for(double j=-4;j<4;j+=.01) {
			approximation.add(new Point2D.Double(j,network.run(new double[] {j})[0]));
		}
		g.addLine(new Line(approximation,Color.GREEN));
		g.repaint();
		System.out.println("DSF");
	}
}
