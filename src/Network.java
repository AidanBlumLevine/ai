public class Network {
	public static void main(String[] args) {
		InputNode i1 = new InputNode();
		InputNode i2 = new InputNode();

		InputNode b = new InputNode();
		b.setValue(1);
		
		Weight w1 = new Weight(.15);
		Weight w2 = new Weight(.20);
		Weight w3 = new Weight(.25);
		Weight w4 = new Weight(.30);

		
		Weight w5 = new Weight(.40);
		Weight w6 = new Weight(.45);
		Weight w7 = new Weight(.50);
		Weight w8 = new Weight(.55);

		
		Weight b1 = new Weight(.35);
		Weight b2 = new Weight(.35);
		Weight b3 = new Weight(.60);
		Weight b4 = new Weight(.60);
		
		HiddenNode h1 = new HiddenNode(new Weight[] {w1,w2,b1},new Weight[] {w5,w7});
		HiddenNode h2 = new HiddenNode(new Weight[] {w3,w4,b2},new Weight[] {w6,w8});
		
		OutputNode o1 = new OutputNode(new Weight[] {w5,w6,b3});
		OutputNode o2 = new OutputNode(new Weight[] {w7,w8,b4});
		
		w1.setNodes(i1, h1);
		w2.setNodes(i1, h2);
		w3.setNodes(i2, h1);
		w4.setNodes(i2, h2);

		w5.setNodes(h1, o1);
		w6.setNodes(h2, o1);
		w7.setNodes(h1, o2);
		w8.setNodes(h2, o2);

		b1.setNodes(b, h1);
		b2.setNodes(b, h2);
		b3.setNodes(b, o1);
		b4.setNodes(b, o2);

		i1.setValue(.05);
		i2.setValue(.10);
		o1.setTarget(.01);
		o2.setTarget(.99);
		
	}

}
