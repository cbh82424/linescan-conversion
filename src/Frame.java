import java.awt.Graphics;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private int width;
	private int height;
	int lines;
	int x1Coords[];
	int x2Coords[];
	int y1Coords[];
	int y2Coords[];
	boolean useBres;
	
	
	/**
	 * Constructs a frame to display the generated lines. Coordinates are provided from arrays
	 * for testing purposes. Coordinates are randomly generated in the tester class. 
	 * @param w : Width of the Frame
	 * @param h : Height of the Frame
	 * @param x1 : x1 Coordinates for lines
	 * @param x2 : x2 Coordinates for lines
	 * @param y1 : y1 Coordinates for lines
	 * @param y2 : y2 Coordinates for lines
	 * @param useB : Selects which algorithm to use to draw lines
	 */
	public Frame(int w, int h, int[] x1, int[] x2, int[] y1, int[] y2, boolean useB) {
		width = w;
		height = h;
		x1Coords = x1;
		x2Coords = x2;
		y1Coords = y1;
		y2Coords = y2;
		useBres = useB;
	}
	
	/** Checks whether the user selected to the simple algorithm
	 * or Bresenham's then updates the frame with the corresponding 
	 * algorithm. 
	 * */
	public void paint(Graphics g) {
		if (useBres) {
			bres(g);
		} else {
			simple(g);
		}
	}
	
	
	/**
	 * Sets up the JFrame to display the generated lines.
	 */
	public void setUpGUI() {
		setSize(width, height);
		setTitle("Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Implementation of the Simple line drawing algorithm that uses the provided
	 * randomly generated coordinates from when the frame was constructed to draw the number
	 * of lines specified by the user. 
	 */
	public void simple(Graphics g) {
		int x1 = 0;
		int x2 = 0;
		int y1 = 0;
		int y2 = 0;

		for (int i = 0; i < x1Coords.length; i++) {

			x1 = x1Coords[i];
			x2 = x2Coords[i];
			y1 = y1Coords[i];
			y2 = y2Coords[i];
			boolean swap = false;
						
			double dx = Math.abs(x2 - x1);
	        double dy = Math.abs(y2 - y1);
	        
	        
	        //To avoid dotted lines when dy>dx, the x and y values are swapped before calculation then swapped back at the end
	        if (dy > dx) {
	        	swap = true;
	        	int temp = x1;
	        	x1 = y1;
	        	y1 = temp;
	        	temp = x2;
	        	x2 = y2;
	        	y2 = temp;
	        	double tempDouble = dx;
	        	dx = dy;
	        	dy = tempDouble;
	        }
	        
	        
	        //Special case for vertical lines
	        if (dx == 0) {
	        	for (int j = 0; j <= (dy-1); j++) {
					int y = y1 + j;
					g.drawRect(x1,y,1,1);
				}
	        } else {
	        double m = dy/dx;
	        if (x1 > x2 && y1<= y2) {
	        	int temp = x1;
	        	x1 = x2;
	        	x2 = temp;
	        	temp = y1;
	        	y1 = y2;
	        	y2 = temp;
	        }
	        //Main loop for normal cases, increments dx times and increments y by the slope*j
			for (int j = 0; j <= (dx-1); j++) {
				int x = x1 + j;
				double y;
				if (y2 < y1) {
					y = y1 - m*j;
				} else if (y2 > y1) {
					y = m*j + y1;
				} else {
					y = y1;
				}
				if (swap) {
					g.drawRect((int)(y + 0.5),x,1,1);
				} else {
				g.drawRect(x,(int)(y + 0.5),1,1);
				}
				
			}
		}
		}
	}
	
	
	/**
	 * Implementation of the Bresenham Line Drawing Algorithm that uses the provided
	 * randomly generated coordinates from when the frame was constructed to draw the 
	 * number of lines specified by the user. 
	 */
	public void bres(Graphics g)
	{
	// pk is initial decision making parameter
	// Note:x1&y1,x2&y2, dx&dy values are interchanged
	// and passed in plotPixel function so
	// it can handle both cases when m>1 & m<1
		int decide;
		int x1 = 0;
		int x2 = 0;
		int y1 = 0;
		int y2 = 0;
		
		
		for (int i = 0; i < x1Coords.length; i++) {
			x1 = x1Coords[i];
			x2 = x2Coords[i];
			y1 = y1Coords[i];
			y2 = y2Coords[i];
		
		int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        if (dx > dy) {
        	decide = 0;
        } else {
        	decide = 1;
        	int temp = x1;
        	x1 = y1;
        	y1 = temp;
        	temp = x2;
        	x2 = y2;
        	y2 = temp;
        	temp = dx;
        	dx = dy;
        	dy = temp;
        }
		int pk = 2 * dy - dx;
		
		//Main loop
		for (int j = 0; j <= dx-1; j++) {
	        // checking either to decrement or increment the
	        // value if we have to plot from (0,100) to (100,0)
	        if (x1 < x2) {
	        	x1++;
	        } else {
	        	x1--;
	        }
	        if (pk < 0) {
	            // decision value will decide to plot
	            // either  x1 or y1 in x's position
	            if (decide == 0) {
	                g.drawRect(x1, y1,1,1);
	                pk = pk + 2 * dy;
	            }
	            else {
	                //(y1,x1) is passed in xt
	                g.drawRect(y1,x1,1,1);
	                pk = pk + 2 * dy;
	            }
	        }
	        else {
	        	if (y1 < y2) {
		        	y1++;
		        } else {
		        	y1--;
		        }
	            if (decide == 0) {
	 
	            	g.drawRect(x1, y1,1,1);
	            }
	            else {
	            	g.drawRect(y1,x1,1,1);
	            }
	            pk = pk + 2 * dy - 2 * dx;
	        }
	    }
		}


}
}
