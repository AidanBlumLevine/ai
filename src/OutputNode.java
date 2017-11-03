public class OutputNode  extends Node{
	private Weight[] inputs;
	private double target;
	public OutputNode(Weight[] inputs) {
		this.inputs = inputs;
	}
	public double gradient() {
		return super.fPrime(getInput())*(getOutput()-target);
	}
	public double getInput() {
		double output=0;
		for(Weight w:inputs) {
			output+=w.getOutput();
		}
		return output;
	}
	public void setTarget(double target) {
		this.target=target;
	}
	public double getOutput() {
		return super.f(getInput());
	}
	public double error() {
		return Math.pow(getOutput()-target,2)/2d;
	}

}
