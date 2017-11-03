
public class Weight {
	private double weight;
	private Node input,output;
	public Weight(double weight) {
		this.weight=weight;
	}
	public void setNodes(Node in,Node out) {
		input = in;
		output = out;
	}
	public double gradient() {
		return input.getOutput()*output.gradient();
	}
	public double getOutput() {
		return input.getOutput()*weight;
	}
	public Node getOutputNode() {
		return output;
	}
	public double getWeight() {
		return weight;
	}
}
