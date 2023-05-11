import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.Scanner;

public class tester {
		
	public static void main (String[] args) {
		//Change window dimensions if your monitor resolution is too low
		int width = 1000;
		int height = 1000;
		boolean useTest = false;
		boolean useBres = false;
		System.out.println("Use Bresenham or Simple? (type b or s)");
		Scanner s = new Scanner(System.in);
		String alg = s.nextLine();
		if (alg.equals("b")) {
			useBres = true;
		}
		//Test Coordinates to show functionality of printing all different line types
		if (useTest) {
			int x1[] = {100,100,200,200};
			int x2[] = {200,200,200,250};
			int y1[] = {100,200,200,200};
			int y2[] = {200,200,100,100};

			Frame frame = new Frame(width, height, x1, x2, y1, y2, useBres);
			frame.setUpGUI();
		} else {
		System.out.println("How many lines?");
		int lines = s.nextInt();
		int x1[] = new int[lines];
		int x2[] = new int[lines];
		int y1[] = new int[lines];
		int y2[] = new int[lines];
		//Randomly Generated Coordinates based on size of window
		for (int i = 0; i < lines; i++) {
			x1[i] = (int)(Math.random() * width);
			x2[i] = (int)(Math.random() * width);
			y1[i] = (int)(Math.random() * height);
			y2[i] = (int)(Math.random() * height);
		}
		Frame frame = new Frame(width, height, x1, x2, y1, y2, useBres);
		frame.setUpGUI();
		}

	}
}
