import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import graph.Graph;
import graph.Line;

public class SinNetwork{
	private static DeepNeuralNetwork network;
	public static void main(String[] args) {
		Graph g = new Graph(-12,-2,12,2);
		ArrayList<Point2D.Double> sin = new ArrayList<Point2D.Double>();
		for(double j=-12;j<12;j+=.01) {
			sin.add(new Point2D.Double(j,f(j)));
		}
		g.addLine(new Line(sin,Color.GRAY));
		g.repaint();
		network = new DeepNeuralNetwork(1,new int[] {100},1,"relu","linear");
		for(int i=0;i<1000000;i++) {
			double value = Math.random()*7-3.5;
			network.train(new double[] {value},new double[] {f(value)}, .005);
			if(i%1000==0) {
				ArrayList<Point2D.Double> approximation = new ArrayList<Point2D.Double>();
				g.removeLine(Color.GREEN);
				for(double j=-12;j<12;j+=.01) {
					approximation.add(new Point2D.Double(j,network.run(new double[] {j})[0]));
				}
				g.addLine(new Line(approximation,Color.GREEN));
				g.repaint();
			}
		}
		//network.printEquation();
	}
	
	private static double f(double x) {
		return Math.sin(x);
	}
}
