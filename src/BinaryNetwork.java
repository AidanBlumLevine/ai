import graph.Graph3D;

public class BinaryNetwork {
	private static DeepNeuralNetwork network = new DeepNeuralNetwork(2,new int[] {20,9},1,"relu","linear");
	public static void main(String[] args) throws InterruptedException {
		Graph3D graph= new Graph3D();
		for(int i=0;i<1000000;i++) {
//			int output=0;
//			int a = (int) (Math.random()*2);
//			int b = (int) (Math.random()*2); 
//			if(a != b && (a==1 || b==1)) {
//				output=1;
//			}
//			if(a==1 && b==1) {
//				output=1;
//			}
			double a = Math.random();
			double b = Math.random();
			double output = 0;
			if((a-.5)*(a-.5)+(b-.5)*(b-.5)<.1) {
				output=1;
			}
//			if(a<.5 && b<.5 || a>.5 && b>.5) {
//			output=1;
//		}
			network.train(new double[] {a,b},new double[] {output},.05);
			if(i%100==0) {
				double[][] points = new double[500][500];
				for(int x=0;x<500;x++) {
					for(int y=0;y<500;y++) {
						points[x][y]=network.run(new double[] {x/500d,y/500d})[0];
					}
				}
				graph.updatePoints(points);
				graph.repaint();
			}
		}
		
	}
}
