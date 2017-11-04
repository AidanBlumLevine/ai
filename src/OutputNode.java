import java.util.ArrayList;

public class OutputNode extends Node {
	private ArrayList<Weight> inputs = new ArrayList<>();
	private double target;

	public void addInput(Weight input) {
		inputs.add(input);
	}

	@Override
	public double gradient() {
		return super.softmaxPrime(getInput()) * (getOutput() - target);
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
		return super.softmax(getInput());
	}

	public double error() {
		return Math.pow(getOutput() - target, 2) / 2d;
	}

}
