public class InputNode  extends Node{
	private double value;
	public void setValue(double value) {
		this.value=value;
	}

	public double getOutput() {
		return value;
	}

	public double gradient() {return (Double) null;}
	public double getInput() {return (Double) null;}

}
