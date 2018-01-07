import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import graph.Line;

public class ImageNetwork {
	private static DeepNeuralNetwork network;
	private static ArrayList<String> images1 = new ArrayList<String>();
	private static ArrayList<String> images2 = new ArrayList<String>();
	private static ArrayList<String> images3 = new ArrayList<String>();
	private static ArrayList<String> images4 = new ArrayList<String>();
	private static int[][] images;
	public static void main(String args[]) throws FileNotFoundException{
		Scanner data = new Scanner(new File("Images1.txt")).useDelimiter("\n");
		while (data.hasNext()) {
		      images1.add(data.next());
		    }
		data.close();
		Scanner data2 = new Scanner(new File("Images2.txt")).useDelimiter("\n");
		while (data2.hasNext()) {
		      images2.add(data2.next());
		    }
		data2.close();
		Scanner data3 = new Scanner(new File("Images3.txt")).useDelimiter("\n");
		while (data3.hasNext()) {
		      images3.add(data3.next());
		    }
		data3.close();
		Scanner data4 = new Scanner(new File("Images4.txt")).useDelimiter("\n");
		while (data4.hasNext()) {
		      images4.add(data4.next());
		    }
		data4.close();
		shuffleList(images1);
		shuffleList(images2);
		shuffleList(images3);
		shuffleList(images4);
		images = mix(images1,images2,images3,images4);
		network = new DeepNeuralNetwork(16,new int[] {20},4,"sigmoid","sigmoid");
		for(int i=0;i<2000;i++) {
			for(int r=0;r<images.length;r++) {
				double[] pixels = new double[16];
				double[] outputs = new double[4];
				for(int j=0;j<17;j++) {
					if(j==16) {
						if(images[r][16]==1) {
							outputs[0]=1;
							outputs[1]=0;
							outputs[2]=0;
							outputs[3]=0;
						} else if(images[r][16]==2){
							outputs[0]=0;
							outputs[1]=1;
							outputs[2]=0;
							outputs[3]=0;
						}else if(images[r][16]==3){
							outputs[0]=0;
							outputs[1]=0;
							outputs[2]=1;
							outputs[3]=0;
						}else {
							outputs[0]=0;
							outputs[1]=0;
							outputs[2]=0;
							outputs[3]=1;
						}
					} else {
						pixels[j] = images[r][j];
					}
				}
				network.train(pixels,outputs, .5);
			}
		}
		
		//double[] testImage = new double[] {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0};
		//System.out.println(Arrays.toString(network.run(testImage)));
		//testImage = new double[] {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1};
		//System.out.println(Arrays.toString(network.run(testImage)));
		network.save("NetworkSave1.txt");
	}
	
	private static int[][] mix(ArrayList<String> first,ArrayList<String> second){
		ArrayList<int[]> list = new ArrayList<int[]>();
		for(int i=0;i<Math.max(first.size(), second.size());i++) {
			if(images1.size()>i) {
			int[] image1 = new int[17];
			for(int n=0;n<16;n++) {
				image1[n] = Integer.valueOf(images1.get(i).split(",")[n]);
			}
			image1[16] = 1;
			list.add(image1);
			}
			if(images2.size()>i) {
			int[] image2 = new int[17];
			for(int n=0;n<16;n++) {
				image2[n] = Integer.valueOf(images2.get(i).split(",")[n]);
			}
			image2[16] = 2;
			list.add(image2);
			}
		}
		
		int[][] fixedList = new int[list.size()][16];
		for(int r=0;r<list.size();r++) {
			fixedList[r] = list.get(r);
		}
		return fixedList;
		
	}
	
	private static int[][] mix(ArrayList<String> first,ArrayList<String> second,ArrayList<String> third,ArrayList<String> fourth){
		ArrayList<int[]> list = new ArrayList<int[]>();
		for(int i=0;i<Math.max(first.size(), second.size());i++) {
			if(images1.size()>i) {
			int[] image1 = new int[17];
			for(int n=0;n<16;n++) {
				image1[n] = Integer.valueOf(images1.get(i).split(",")[n]);
			}
			image1[16] = 1;
			list.add(image1);
			}
			if(images2.size()>i) {
			int[] image2 = new int[17];
			for(int n=0;n<16;n++) {
				image2[n] = Integer.valueOf(images2.get(i).split(",")[n]);
			}
			image2[16] = 2;
			list.add(image2);
			}
			if(images3.size()>i) {
				int[] image3 = new int[17];
				for(int n=0;n<16;n++) {
					image3[n] = Integer.valueOf(images3.get(i).split(",")[n]);
				}
				image3[16] = 3;
				list.add(image3);
			}
			if(images4.size()>i) {
				int[] image4 = new int[17];
				for(int n=0;n<16;n++) {
					image4[n] = Integer.valueOf(images4.get(i).split(",")[n]);
				}
				image4[16] = 4;
				list.add(image4);
			}
		}
		
		int[][] fixedList = new int[list.size()][16];
		for(int r=0;r<list.size();r++) {
			fixedList[r] = list.get(r);
		}
		return fixedList;
		
	}
	
	public static void shuffleList(ArrayList<String> a) {
        int n = a.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    private static void swap(ArrayList<String> a, int i, int change) {
        String helper = a.get(i);
        a.set(i, a.get(change));
        a.set(change, helper);
    }
}
