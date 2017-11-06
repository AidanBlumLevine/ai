package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MpgNetwork {
	public static void main(String args[]) throws FileNotFoundException{	
		Scanner data = new Scanner(new File("IrisData")).useDelimiter("\n");
		while (data.hasNext()) {
		      dataLines.add(data.next());
		    }
		data.close();

		trainData = new double[dataLines.size()][7];
		for(int s=0;s<dataLines.size();s++){
			//outputSetosa
			if(dataLines.get(s).split(",")[4].equals("Iris-setosa")){
				trainData[s][4]=1;
				trainData[s][5]=0;
				trainData[s][6]=0;
				//System.out.println("Iris-setsona");
			} else
			//outputVersicolor
			if(dataLines.get(s).split(",")[4].equals("Iris-versicolor")){
				trainData[s][4]=0;
				trainData[s][5]=1;
				trainData[s][6]=0;
				//System.out.println("Iris-versicolor");
			} else 
			//outputVirginica
			if(dataLines.get(s).split(",")[4].equals("Iris-virginica")){
				trainData[s][4]=0;
				trainData[s][5]=0;
				trainData[s][6]=1;
				//System.out.println("Iris-virginica");
			} else {
				System.out.println("Unrecognised target");
			}
			//input1
			trainData[s][0]=Double.valueOf(dataLines.get(s).split(",")[0])/10;		
			//input2
			trainData[s][1]=Double.valueOf(dataLines.get(s).split(",")[1])/10;
			//input3
			trainData[s][2]=Double.valueOf(dataLines.get(s).split(",")[2])/10;		
			//input4
			trainData[s][3]=Double.valueOf(dataLines.get(s).split(",")[3])/10;
		}	
		
		n.learn(trainData, 500);
		System.out.println("Setsona:"+n.getApproximation(new double[]{5.4,3.9,1.3,0.4})[0]);
		System.out.println("Versicolor:"+n.getApproximation(new double[]{5.4,3.9,1.3,0.4})[1]);
		System.out.println("Virginica:"+n.getApproximation(new double[]{5.4,3.9,1.3,0.4})[2]);

	}
}
