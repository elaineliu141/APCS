	/**
	 * Runs on an Applet and paints a flag to scale.
	 * Draws all parts of the flag, including the blue flag of union and the 13 stripes.
	 * The stars are included in a separate helper method.
	 * 
	 * The entire flag can scale to proportion and fill the entire screen when the window is dragged.
	 * 
	 */
	import java.applet.Applet;
	import java.awt.*;
	
	import javax.swing.ImageIcon;
	
	public class flag extends Applet {
		/**
		 * Defines all the variables for the class and creates custom colors from JDateStamp.
		 */
		private double B = 1.9;
		private final Color DARKSALMON = new Color(0x8B0000);
		private final Color BLUE = new Color(0x00008B);
		private static final int STRIPES = 13;
		
		//Calls the function that sets the background and size.
		public flag () {
			init();
		}
		
		//Sets the background color and size.
		public void init() {
			/**
			 * Sets the background color to white and calls the paint method.
			 */
			setBackground(Color.WHITE);
			repaint();
		}
		
		//main paint method
		public void paint(Graphics g){
			/**
			 * Always fills the window with the flag and scales the flag to proportion 
			 * when the window is dragged.
			 */
			int height = getHeight();
			int width = getWidth();
					
			if (height * B <= width) {
		        width = (int) (height * B);
 	            height = getHeight();
	        } else {
		         height = (int) (width / B);
	        }
			
			//calls the function to paint the flag based on height and width
			Flag(g,height, width); 
					
		}
				
		public void Flag(Graphics g, int height, int width) {
			/**
			 * Draws the stripes, the blue flag of union, and the 50 stars. 
			 */
			
			/*
			 * This portion of the flag draws the red and white stripes.
			 */
			
			//draw stripes
			g.setColor(DARKSALMON);
			g.fillRect(0, 0, (int) width, (int) height);
			
			// 1/13 is the specification for the width of the stripe
			g.setColor(Color.WHITE);
			for (int j = 1; j <= 6; j++) {
				g.fillRect(0, (int) (2 * height * 1/13 * j - (height * 1/13)), (int) (width), (int) (height * 1/13));
			}
			
			/*
			 * This portion of the flag draws the blue flag of union.
			 */
			
			// 19/25 is the specification for the width of the blue flag of union, and 7/13 is the height
			g.setColor(BLUE);
			g.fillRect(0, 0, (int) (height * 19/25), (int) (height * 7/13));
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, (int) (height * 19/25), (int) (height * 7/13));
			
			
			//draws all the rows of the stars
			/*
			 * This for loop draws the stars for each of the rows.
			 * The star is scaled based on the width and height of the flag.
			 */
			
			//63/1000 is the specification for the x coordinate of the stars 
			for(int j=2; j<=5;j++){
				for(int i=0; i<11;i++){
					if(i%2==0){
						Graphics2D g2 = (Graphics2D) g;
						//This draws the start of each row
						//54/1000 is the specification for the y coordinate of the stars
						stars(g2,height*63/1000+height*63*i/1000,height*(54+108*(j-2))/1000,width,height);
					}
					else{
						Graphics2D g2 = (Graphics2D) g;
						//This draws the y coordinate of each row
						stars(g2,height*63/1000+height*63*i/1000,height*(108+108*(j-2))/1000,width,height);
					}		
				}
			}
			
			//draws the last line of stars
			
			for(int i=0; i<11;i++){
				if(i%2==0){
					Graphics2D g2 = (Graphics2D) g;
					//This draws the start of each row
					//63/1000 is the specification for the x coordinate of the stars
					//54/1000 is the specification for the y coordinate of the stars
					stars(g2,height*63/1000+height*63*i/1000,height*486/1000,width,height);
				}
			}
		}
		
	
		/*
		 * This star function uses an array of integers and stores them as 10 points for the star.
		 * The for loop uses trigonometry to determine the angles of the stars.
		 * Lastly, the color of the stars is set to white, and the polygon with 10 points is drawn.
		 */
		public void stars(Graphics g, int midX, int midY,int width,int height){
			//308/1000 is the specification for the diameter of the stars
			//154/1000 is the radius of the stars
		    int radius[] = {height*308/10000,height*154/10000,height*308/10000,height*154/10000};
		    int nPoints = 10;
		    int[] X = new int[nPoints];
		    int[] Y = new int[nPoints];
		    //outer border of the star
		    for (double current=0.0; current<nPoints; current++)
		    {
		        int i = (int) current;
		        //determines the angles needed to draw the lines of the stars
		        double x = Math.cos(current*((2*Math.PI)/10)+60)*radius[i % 4];
		        double y = Math.sin(current*((2*Math.PI)/10)+60)*radius[i % 4];

		        X[i] = (int) x+midX;
		        Y[i] = (int) y+midY;
		    }
		    
		    //sets color for the star
		    g.setColor(Color.WHITE);
		    g.fillPolygon(X, Y, 10);
		    
		}
		
	}
