import java.util.ArrayList;

public class NeuralNetwork {
	private ArrayList<HiddenNode> hiddenLayer = new ArrayList<>();
	private ArrayList<InputNode> inputLayer = new ArrayList<>();
	private ArrayList<OutputNode> outputLayer = new ArrayList<>();
	private ArrayList<Weight> weights = new ArrayList<>();

	public NeuralNetwork(int inputs, int hidden, int outputs,String hiddenActivator,String outputActivator) {
		BiasNode bias = new BiasNode();
		for (int i = 0; i < inputs; i++) {
			inputLayer.add(new InputNode());
		}
		for (int i = 0; i < hidden; i++) {
			HiddenNode h = new HiddenNode(hiddenActivator);
			Weight w = new Weight(Math.random(), bias, h);
			weights.add(w);
			h.addInput(w);
			hiddenLayer.add(h);
		}
		for (int i = 0; i < outputs; i++) {
			OutputNode o = new OutputNode(outputActivator);
			Weight w = new Weight(Math.random(), bias, o);
			weights.add(w);
			o.addInput(w);
			outputLayer.add(o);
		}
		for (int i = 0; i < inputs; i++) {
			for (int n = 0; n < hidden; n++) {
				Weight w = new Weight(Math.random(), inputLayer.get(i), hiddenLayer.get(n));
				weights.add(w);
				hiddenLayer.get(n).addInput(w);
			}
		}
		for (int i = 0; i < hidden; i++) {
			for (int n = 0; n < outputs; n++) {
				Weight w = new Weight(Math.random(), hiddenLayer.get(i), outputLayer.get(n));
				weights.add(w);
				hiddenLayer.get(i).addOutput(w);
				outputLayer.get(n).addInput(w);
			}
		}
	}

	public double[] run(double[] inputs) {
		if (inputs.length != inputLayer.size()) {
			System.out.println("INCORRECT INPUT DATA TO RUN");
		}

		for (int i = 0; i < inputLayer.size(); i++) {
			inputLayer.get(i).setValue(inputs[i]);
		}
		double[] outputs = new double[outputLayer.size()];
		for (int i = 0; i < outputLayer.size(); i++) {
			outputs[i] = outputLayer.get(i).getOutput();
		}
		return outputs;
	}

	public void train(double[] inputs, double[] targets, double learningRate) {
		if (inputs.length != inputLayer.size() || targets.length != outputLayer.size()) {
			System.out.println("INCORRECT INPUT DATA TO RUN");
		}

		for (int i = 0; i < inputLayer.size(); i++) {
			inputLayer.get(i).setValue(inputs[i]);
		}
		for (int i = 0; i < outputLayer.size(); i++) {
			outputLayer.get(i).setTarget(targets[i]);
		}
		double[] gradients = new double[weights.size()];
		for (int w = 0; w < weights.size(); w++) {
			gradients[w] = weights.get(w).gradient();
		}
		for (int w = 0; w < weights.size(); w++) {
			weights.get(w).setWeight(weights.get(w).getWeight() - gradients[w] * learningRate);
		}
	}

	public double error(double[] inputs, double[] targets) {
		if (inputs.length != inputLayer.size() || targets.length != outputLayer.size()) {
			System.out.println("INCORRECT INPUT DATA TO RUN");
		}

		for (int i = 0; i < inputLayer.size(); i++) {
			inputLayer.get(i).setValue(inputs[i]);
		}
		for (int i = 0; i < outputLayer.size(); i++) {
			outputLayer.get(i).setTarget(targets[i]);
		}
		double error = 0;
		for (OutputNode o : outputLayer) {
			error += o.error();
		}
		return error;
	}
	
	public void printEquation() {
		for(OutputNode o:outputLayer) {
			System.out.println(o.getEquation());
		}
	}
}
