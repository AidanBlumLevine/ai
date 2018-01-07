

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageCreator extends JPanel{
	private int[][] pixels = new int[4][4];
	private int mouseX,mouseY;
	private int click = 0;
	private DeepNeuralNetwork network;
	public ImageCreator() {
		network = new DeepNeuralNetwork(16,new int[] {20},4,"sigmoid","sigmoid");
		network.load("NetworkSave1.txt");
		JFrame frame = new JFrame("Frame");
		frame.setSize(500, 500);
	    frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me)
            {
            	mouseX = me.getX();
                mouseY = me.getY()-27;
            }
        });
        
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
	            if (e.getButton() == MouseEvent.NOBUTTON) {
	            } else if (e.getButton() == MouseEvent.BUTTON1) {
	            	click=1;
	            	mouseX=e.getPoint().x;
	            	mouseY=e.getPoint().y-27;
	            } else if (e.getButton() == MouseEvent.BUTTON3) { 
	            	click=-1;
	            	mouseX=e.getPoint().x;
	            	mouseY=e.getPoint().y-27;
	            } else if (e.getButton() == MouseEvent.BUTTON2) {
	            }
            }
            public void mouseReleased(MouseEvent e){
            	click=0;
            }
         });
        JButton start = new JButton("Clear");
    	start.addActionListener(new ActionListener(){  
    		public void actionPerformed(ActionEvent e){
    			for(int i=0;i<4;i++) {
    				for(int r=0;r<4;r++) {
        				pixels[i][r]=0;
        			}
    			}
            }  
        });  
    	start.setBounds(0, 0, 100, 50);
    	add(start);
    	JButton clear = new JButton("Save");
    	clear.addActionListener(new ActionListener(){  
    		public void actionPerformed(ActionEvent e){
    			try {
					BufferedWriter br = new BufferedWriter(new FileWriter("Images4.txt",true));
	                StringBuilder sb = new StringBuilder();
	                String image = "";
	                for (int[] row : pixels) {
	                	for(int i:row) {
	                		image += i+",";
	                	}
	                }
	                image = image.substring(0,image.length()-1);
	                image+="\n";
	                br.write(image);
	                br.close();
	                System.out.println("Saved");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
    			for(int i=0;i<4;i++) {
    				for(int r=0;r<4;r++) {
        				pixels[i][r]=0;
        			}
    			}
            }  
        });  
    	clear.setBounds(0, 50, 100, 50);
    	add(clear);
    	JButton test = new JButton("Test");
    	test.addActionListener(new ActionListener(){  
    		public void actionPerformed(ActionEvent e){
    			double[] fixedImage = new double[16];
    			for(int i=0;i<pixels.length;i++) {
    				for(int r=0;r<pixels[0].length;r++) {
    					fixedImage[i*4+r] = pixels[i][r];
    				}
    			}
    			System.out.println("Input: "+Arrays.toString(fixedImage));
    			System.out.println("Output: "+Arrays.toString(network.run(fixedImage)));
            }  
        });  
    	test.setBounds(0, 100, 100, 50);
    	add(test);
    	setLayout(null);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(new Color(230,230,230));
		g2d.fillRect(100, 100, 300, 300);
		g2d.setColor(Color.DARK_GRAY);
		for(double x=100;x<=400;x+=300/4.0) {
			g2d.drawLine((int)x, 100, (int)x, 400);
		}
		for(double y=100;y<=400;y+=300/4.0) {
			g2d.drawLine(100, (int)y, 400, (int)y);
		}
		
		for(int x=0;x<4;x++) {
			for(int y=0;y<4;y++) {
				if(pixels[x][y]==1) {
					g2d.setColor(Color.BLACK);
					g2d.fillRect(100+(int)((300/4.0*x)+(300/4.0-30)/2), 100+(int)((300/4.0*y)+(300/4.0-30)/2), 30, 30);
				}
			}
		}
		
		if(click==1 && (int)((mouseX-100)/(300/4.0))>=0 && (int)((mouseX-100)/(300/4.0))<=7 && (int)((mouseY-100)/(300/4.0))>=0 && (int)((mouseY-100)/(300/4.0))<=7) {
			if(pixels[(int)((mouseX-100)/(300/4.0))][(int)((mouseY-100)/(300/4.0))]==0) {
				pixels[(int)((mouseX-100)/(300/4.0))][(int)((mouseY-100)/(300/4.0))]=1;
			}
		}
		if(click==-1 && (int)((mouseX-100)/(300/4.0))>=0 && (int)((mouseX-100)/(300/4.0))<=7 && (int)((mouseY-100)/(300/4.0))>=0 && (int)((mouseY-100)/(300/4.0))<=7) {
			if(pixels[(int)((mouseX-100)/(300/4.0))][(int)((mouseY-100)/(300/4.0))]==1) {
				pixels[(int)((mouseX-100)/(300/4.0))][(int)((mouseY-100)/(300/4.0))]=0;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ImageCreator i = new ImageCreator();
		while(true) {
			Thread.sleep(10);
			i.repaint();
		}
	}
}
