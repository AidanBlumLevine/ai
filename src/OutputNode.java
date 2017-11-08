import java.util.ArrayList;

public class OutputNode extends Node {
	private ArrayList<Weight> inputs = new ArrayList<>();
	private double target;
	private String activator;
	public OutputNode(String outputActivator) {
		activator = outputActivator;
	}
	public void addInput(Weight input) {
		inputs.add(input);
	}

	@Override
	public double gradient() {
		if(activator.equals("softmax")) {
			return super.softmaxPrime(getInput()) * (getOutput() - target);
		} else if(activator.equals("sigmoid")) {
			return super.sigmoidPrime(getInput()) * (getOutput() - target);
		} else if(activator.equals("linear")) {
			return super.linearPrime(getInput()) * (getOutput() - target);
		} else {
			System.out.println("UNKNOWN ACTIVATION FUNCTION");
			return super.linearPrime(getInput()) * (getOutput() - target);
		}
	}

	@Override
	public double getInput() {
		double output = 0;
		for (Weight w : inputs) {
			output += w.getOutput();
		}
		return output;
	}

	public void setTarget(double target) {
		this.target = target;
	}

	@Override
	public double getOutput() {
		if(activator.equals("softmax")) {
			return super.softmax(getInput());
		} else if(activator.equals("sigmoid")) {
			return super.sigmoid(getInput());
		} else if(activator.equals("linear")) {
			return super.linear(getInput());
		} else {
			System.out.println("UNKNOWN ACTIVATION FUNCTION");
			return super.linear(getInput());
		}
	}

	public double error() {
		return Math.pow(getOutput() - target, 2) / 2d;
	}

	public String getEquation() {
		String equation = "";
		if(activator.equals("softmax")) {
			equation+="softmax(";
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
		} else if(activator.equals("linear")) {
			for(int w=0;w<inputs.size();w++) {
				equation += inputs.get(w).getEquation();
				if(w!=inputs.size()-1) {
					equation += "+";
				}
			}
		} else {
			System.out.println("UNKNOWN ACTIVATION FUNCTION");
			return "UNKNOWN ACTIVATION FUNCTION";
		}
		return equation;
	}

}
