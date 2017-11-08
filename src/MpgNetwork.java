import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MpgNetwork {
	private static ArrayList<String> dataLines = new ArrayList<String>();
	public static void main(String args[]) throws FileNotFoundException{	
		Scanner data = new Scanner(new File("CarData")).useDelimiter("\n");
		while (data.hasNext()) {
		      dataLines.add(data.next());
		    }
		data.close();

		NeuralNetwork network = new NeuralNetwork(6,6,1,"relu","linear");
		for(int n=0;n<500;n++) {
			for(int i=0;i<dataLines.size();i++) {
				double mpg = Double.valueOf(dataLines.get(i).split(" ")[0]);
				mpg = normalize(mpg, 9, 46.6);
				double cylinders = Double.valueOf(dataLines.get(i).split(" ")[1]);
				cylinders = normalize(cylinders,3,8);
				double displacement = Double.valueOf(dataLines.get(i).split(" ")[2]);
				displacement = normalize(displacement,68,455);
				double horsepower = Double.valueOf(dataLines.get(i).split(" ")[3]);
				horsepower = normalize(horsepower,46,230);
				double weight = Double.valueOf(dataLines.get(i).split(" ")[4]);
				weight = normalize(weight,1613,5140);
				double acceleration = Double.valueOf(dataLines.get(i).split(" ")[5]);
				acceleration = normalize(acceleration,8,24);
				double year = Double.valueOf(dataLines.get(i).split(" ")[6]);
				year = normalize(year,70,82);
				network.train(new double[] {cylinders,displacement,horsepower,weight,acceleration,year},new double[] {mpg},.05);
				if(i==0) {
					System.out.println(network.error(new double[] {cylinders,displacement,horsepower,weight,acceleration,year},new double[] {mpg}));
				}
			}
		}
		System.out.println(unnormalize(network.run(new double[] {normalize(4, 3, 8),normalize(97,68,455),normalize(46,46,230),normalize(1950,1613,5140),normalize(21,8,24),normalize(73,70,82)})[0],9,46.6));
		System.out.println(unnormalize(network.run(new double[] {normalize(8, 3, 8),normalize(400,68,455),normalize(150,46,230),normalize(4997,1613,5140),normalize(17,8,24),normalize(73,70,82)})[0],9,46.6));
	}
	public static double normalize(double value,double low,double high) {
		return (value-low)/(high-low);
	}
	public static double unnormalize(double value,double low,double high) {
		return value*(high-low)+low;
	}
}
