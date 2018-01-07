import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DeepNeuralNetwork {
	private ArrayList<HiddenNode>[] hiddenLayer;
	private ArrayList<InputNode> inputLayer = new ArrayList<>();
	private ArrayList<OutputNode> outputLayer = new ArrayList<>();
	private ArrayList<Weight> weights = new ArrayList<>();

	public DeepNeuralNetwork(int inputs, int[] hiddenLayers, int outputs,String hiddenActivator,String outputActivator) {
		hiddenLayer = (ArrayList<HiddenNode>[])new ArrayList[hiddenLayers.length];
		BiasNode bias = new BiasNode();
		for (int i = 0; i < inputs; i++) {
			inputLayer.add(new InputNode());
		}
		for(int n=0;n<hiddenLayer.length;n++) {
			hiddenLayer[n] = new ArrayList<HiddenNode>();
			for (int i = 0; i < hiddenLayers[n]; i++) {
				HiddenNode h = new HiddenNode(hiddenActivator);
				Weight w = new Weight(Math.random(), bias, h);
				weights.add(w);
				h.addInput(w);
				hiddenLayer[n].add(h);
			}
		}
		for (int i = 0; i < outputs; i++) {
			OutputNode o = new OutputNode(outputActivator);
			Weight w = new Weight(Math.random()-.5, bias, o);
			weights.add(w);
			o.addInput(w);
			outputLayer.add(o);
		}
		for (int i = 0; i < inputs; i++) {
			for (int n = 0; n < hiddenLayer[0].size(); n++) {
				Weight w = new Weight(Math.random(), inputLayer.get(i), hiddenLayer[0].get(n));
				weights.add(w);
				hiddenLayer[0].get(n).addInput(w);
			}
		}
		for(int j=0;j<hiddenLayer.length-1;j++) {
			for (int i = 0; i < hiddenLayer[j].size(); i++) {
				for (int n = 0; n < hiddenLayer[j+1].size(); n++) {
					Weight w = new Weight(Math.random(), hiddenLayer[j].get(i), hiddenLayer[j+1].get(n));
					weights.add(w);
					hiddenLayer[j].get(i).addOutput(w);
					hiddenLayer[j+1].get(n).addInput(w);
				}
			}
		}
		for (int i = 0; i < hiddenLayer[hiddenLayer.length-1].size(); i++) {
			for (int n = 0; n < outputs; n++) {
				Weight w = new Weight(Math.random(), hiddenLayer[hiddenLayer.length-1].get(i), outputLayer.get(n));
				weights.add(w);
				hiddenLayer[hiddenLayer.length-1].get(i).addOutput(w);
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
		System.out.println("Inputs"+Arrays.toString(inputs));
		System.out.println("Outputs"+Arrays.toString(targets));

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
	
	public void save(String fileName) {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            StringBuilder sb = new StringBuilder();
            String image = "";
            for (Weight w : weights) {
            	image += w.getWeight()+",";
            }
            image = image.substring(0,image.length()-1);
            br.write(image);
            br.close();
            System.out.println("Saved Network");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void load(String fileName) {
		try {
			String line = "";
			Scanner data = new Scanner(new File(fileName)).useDelimiter("\n");
			while (data.hasNext()) {
			      line = data.next();
			    }
			data.close();
			if(line.split(",").length != weights.size()) {
				System.out.println("INCORRECT INPUT DATA TO LOAD");
			}
			for(int i=0;i<line.split(",").length;i++) {
				weights.get(i).setWeight(Double.valueOf(line.split(",")[i]));
			}
			System.out.println("Loaded Network");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
