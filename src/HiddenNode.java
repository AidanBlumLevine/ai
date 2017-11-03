
public class HiddenNode extends Node{
	private Weight[] outputs,inputs;
	public HiddenNode(Weight[] inputs,Weight[] outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
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
