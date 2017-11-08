
public abstract class Node {
	public abstract double gradient();

	public abstract double getInput();

	public abstract double getOutput();
	
	public abstract String getEquation();

	 public double sigmoid(double in) {
	 return 1/(1+Math.pow(Math.E,-1*in));
	 }
	 public double sigmoidPrime(double in) {
	 return sigmoid(in)*(1-sigmoid(in));
	 }
	public double relu(double in) {
		if (in > 0) {
			return in;
		} else {
			return in / 100;
		}
	}

	public double reluPrime(double in) {
		if (in > 0) {
			return 1;
		} else {
			return .01;
		}
	}
	public double step(double in) {
		if(in<-.1) {
			return -1;
		} else if(in>.1) {
			return 1;
		} else {
			return in*10;
		}
	}
	public double stepPrime(double in) {
		if(in>=-.1 && in<=.1) {
			return 10;
		} else {
			return 0;
		}
	}
	
	
	public double linear(double in) {
		return in;
	}

	public double linearPrime(double in) {
		return 1;
	}
	
	public double softmax(double in) {
		return in;
	}

	public double softmaxPrime(double in) {
		return 1;
	}
}
