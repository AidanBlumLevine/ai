
public class BiasNode extends Node{
	public double getOutput() {
		return 1;
	}

	public double gradient() {return (Double) null;}
	public double getInput() {return (Double) null;}

	@Override
	public String getEquation() {
		return "1";
	}
}
