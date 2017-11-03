
public abstract class Node {
	public abstract double gradient();
	public abstract double getInput();
	public abstract double getOutput();
	public double f(double in) {
		return 1/(1+Math.pow(Math.E,-1*in));
	}
	public double fPrime(double in) {
		return f(in)*(1-f(in));
	}
}
