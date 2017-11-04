import java.util.ArrayList;

public class HiddenNode extends Node{
	private ArrayList<Weight> outputs = new ArrayList<>(),inputs = new ArrayList<>();
	public void addInput(Weight input) {
		inputs.add(input);
	}
	public void addOutput(Weight output) {
		outputs.add(output);
	}
	public double gradient() {
		double gradient=0;
		for(Weight w:outputs) {
			gradient+=w.getWeight()*w.getOutputNode().gradient();
		}
		return gradient*super.fPrime(getInput());
	}
	public double getInput() {
		double output=0;
		for(Weight w:inputs) {
			output+=w.getOutput();
		}
		return output;
	}
	public double getOutput() {
		return super.f(getInput());
	}
}
