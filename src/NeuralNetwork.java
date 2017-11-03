import java.util.ArrayList;

public class NeuralNetwork {
	private ArrayList<HiddenNode> hiddenLayer = new ArrayList<>();
	private ArrayList<InputNode> inputLayer = new ArrayList<>();
	private ArrayList<OutputNode> outputLayer = new ArrayList<>();
	public NeuralNetwork(int inputs,int hidden,int outputs) {
		ArrayList<Weight> weights = new ArrayList<>();
		
		for(int i=0;i<inputs;i++) {
			inputLayer.add(new InputNode());
		}
		InputNode b = new InputNode();
		b.setValue(1);
		
		for(int i=0;i<(inputs*hidden)+(hidden*outputs)+hidden+outputs;i++){
			weights.add(new Weight(1));
		}
		
		for(int i=0;i<hidden;i++) {
			Weight[] inputWeights = new Weight[inputs];
			Weight[] outputWeights = new Weight[outputs];
			for(int n=0;n<inputs;n++) {
				inputWeights[n] = weights.get(n);
			}
			for(int n=0;n<inputs;n++) {
				weights.remove(0);
			}
			for(int n=0;n<outputs;n++) {
				outputWeights[n] = weights.get(n);
			}
			for(int n=0;n<outputs;n++) {
				weights.remove(0);
			}
			HiddenNode h = new HiddenNode(inputWeights,outputWeights);
			hiddenLayer.add(h);
		}
	}	
}
