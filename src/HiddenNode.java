import java.util.ArrayList;

public class HiddenNode extends Node {
	private ArrayList<Weight> outputs = new ArrayList<>(), inputs = new ArrayList<>();
	private String activator;
	public HiddenNode(String hiddenActivator) {
		activator = hiddenActivator;
	}

	public void addInput(Weight input) {
		inputs.add(input);
	}

	public void addOutput(Weight output) {
		outputs.add(output);
	}

	public double getInput() {
		double output = 0;
		for (Weight w : inputs) {
			output += w.getOutput();
		}
		return output;
	}

	public double getOutput() {
		if(activator.equals("relu")) {
		return super.relu(getInput());
		} else if(activator.equals("sigmoid")) {
			return super.sigmoid(getInput());
		} else {
			System.out.println("UNKNOWN ACTIVATION FUNCTION");
			return super.relu(getInput());
		}
	}

	public double gradient() {
		double gradient = 0;
		for (Weight w : outputs) {
			gradient += w.getWeight() * w.getOutputNode().gradient();
		}
		if(activator.equals("relu")) {
			return gradient * super.reluPrime(getInput());
			} else if(activator.equals("sigmoid")) {
				return gradient * super.sigmoidPrime(getInput());
			} else {
				System.out.println("UNKNOWN ACTIVATION FUNCTION");
				return gradient * super.reluPrime(getInput());
			}
	}

	public ArrayList<Weight> getInputWeights() {
		return inputs;
	}

	@Override
	public String getEquation() {
		String equation = "";
		if(activator.equals("relu")) {
			equation+="max(0,";
			for(int w=0;w<inputs.size();w++) {
				equation += inputs.get(w).getEquation();
				if(w!=inputs.size()-1) {
					equation += "+";
				}
			}
			equation+=")";
		} else if(activator.equals("sigmoid")) {
			equation+="1/(1+e^(-(";
			for(int w=0;w<inputs.size();w++) {
				equation += inputs.get(w).getEquation();
				if(w!=inputs.size()-1) {
					equation += "+";
				}
			}
			equation+="))";
		} else {
			System.out.println("UNKNOWN ACTIVATION FUNCTION");
			return "UNKNOWN ACTIVATION FUNCTION";
		}
		return equation;
	}
}
