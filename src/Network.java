public class Network {
	public static void main(String[] args) {
		NeuralNetwork n = new NeuralNetwork(1,10,1);
		for(int i=0;i<100000;i++) {
				double v = Math.random()*4;
				n.train(new double[] {v},new double[] {Math.sin(v)}, .05);
		}
		System.out.println("0: "+n.run(new double[] {0})[0]);
		System.out.println("2: "+n.run(new double[] {2})[0]);
		System.out.println("1: "+n.run(new double[] {1})[0]);
	}

}
