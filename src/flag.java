	/**
	 *  * Runs on an applet and paints a flag to scale.
	 * 
	 * 1: How you have broken this assignment into at least three parts.
	 * 2: The status of each part! 
	 * The report need not be long, the following is ab example that shows a student learning a bit, which I like (the learning). 
	 * You should not following this student's design example -- this is meant to show what level of detail I expect in your report, not to replace your effort to make design decisions.
	 * I broke the flag project into 52 parts. I will draw each of the 50 stars (50 parts) and then I will build a module that scales my flag and then I will work on the stripes.
	 * I got all fifty stars drawn when I realized I could generalize one drawing of a star into 5 by making certain numbers that indicate position into variables. 
	 * 
	 */
	import java.applet.Applet;
	import java.awt.*;
	
	import javax.swing.ImageIcon;
	
	public class flag extends Applet {
		private final Color DARKSALMON = new Color(0x8B0000);
		private final Color BLUE = new Color(0x00008B);
		private static final int STRIPES = 13;
		private static final int SCALE = 300;
			
		public flag () {
			init();
		}
		
		//Sets the background color and size.
		public void init() {
			/**
			 * Background color and size.
			 */
			setSize(700,600);
			setBackground(Color.WHITE);
			repaint();
		}
		
		public void Flag(Graphics g) {
			/**
			 * Draws the stripes the blue flag of union. 
			 */
			//Draws the red and white stripes
			for(int i = 0; i<STRIPES; i++){
				if(i%2==0){
					//red and white stripes
					g.setColor(DARKSALMON);
					g.fillRect(SCALE*1/13, SCALE*1/13+SCALE*1/13*i, SCALE*19/10, SCALE*1/13);
					g.setColor(Color.BLACK);
					g.drawRect(SCALE*1/13, SCALE*1/13+SCALE*1/13*i, SCALE*19/10, SCALE*1/13);
				}
				else{
					g.setColor(Color.WHITE);
					g.fillRect(SCALE*1/13, SCALE*1/13+SCALE*1/13*i, SCALE*19/10, SCALE*1/13);
					g.setColor(Color.BLACK);
					g.drawRect(SCALE*1/13, SCALE*1/13+SCALE*1/13*i, SCALE*19/10, SCALE*1/13);
				}
			}
			
			//Draws the Union
			g.setColor(BLUE);
			g.fillRect(SCALE*1/13, SCALE*1/13, SCALE*19/25, SCALE*7/13);
			g.setColor(Color.BLACK);
			g.drawRect(SCALE*1/13, SCALE*1/13, SCALE*19/25, SCALE*7/13);
			
			
			//for loop to draw stars
			//draws all the rows of the stars
		
			for(int i=0; i<11;i++){
				if(i%2==0){
					Graphics2D g2 = (Graphics2D) g;
					//This draws the start of each row
					stars(g2,SCALE*1/13+SCALE*63/1000+SCALE*63*i/1000+SCALE*1/80,SCALE*1/13+SCALE*54/1000,SCALE);
					stars(g2,SCALE*1/13+SCALE*63/1000+SCALE*63*i/1000+SCALE*1/80,SCALE*1/13+SCALE*162/1000,SCALE);
					stars(g2,SCALE*1/13+SCALE*63/1000+SCALE*63*i/1000+SCALE*1/80,SCALE*1/13+SCALE*270/1000,SCALE);
					stars(g2,SCALE*1/13+SCALE*63/1000+SCALE*63*i/1000+SCALE*1/80,SCALE*1/13+SCALE*378/1000,SCALE);
					stars(g2,SCALE*1/13+SCALE*63/1000+SCALE*63*i/1000+SCALE*1/80,SCALE*1/13+SCALE*486/1000,SCALE);
					
				}
				else{
					Graphics2D g2 = (Graphics2D) g;
					//This draws the y coordinate of each row
					stars(g2,SCALE*1/13+SCALE*63/1000+SCALE*63*i/1000+SCALE*1/80,SCALE*1/13+SCALE*108/1000,SCALE);
					stars(g2,SCALE*1/13+SCALE*63/1000+SCALE*63*i/1000+SCALE*1/80,SCALE*1/13+SCALE*216/1000,SCALE);
					stars(g2,SCALE*1/13+SCALE*63/1000+SCALE*63*i/1000+SCALE*1/80,SCALE*1/13+SCALE*324/1000,SCALE);
					stars(g2,SCALE*1/13+SCALE*63/1000+SCALE*63*i/1000+SCALE*1/80,SCALE*1/13+SCALE*432/1000,SCALE);
				}
						
		}
		}
		
		//main paint method
		public void paint(Graphics g){
			Graphics2D g2 = (Graphics2D) g;
			double scale = 1;
			g2.scale(scale, scale);
			Flag(g2);
			//stars(g2,520,300,SCALE);
		}
	
		//draws the stars
		public void stars(Graphics g, int midX, int midY,int scale){
		    int radius[] = {scale*366/10000,scale*133/10000,scale*366/10000,scale*133/10000};
		    int nPoints = 10;
		    int[] X = new int[nPoints];
		    int[] Y = new int[nPoints];
		    //outer border of the star
		    for (double current=0.0; current<nPoints; current++)
		    {
		        int i = (int) current;
		        double x = Math.cos(current*((2*Math.PI)/5)+60)*radius[i % 4];
		        double y = Math.sin(current*((2*Math.PI)/5)+60)*radius[i % 4];

		        X[i] = (int) x+midX;
		        Y[i] = (int) y+midY;
		    }

		    //small pentagon inside
		    g.setColor(Color.WHITE);
		    g.fillPolygon(X, Y, nPoints);
		    int xCordSmallP[] = new int[5];
			int yCordSmallP[] = new int[5];
			for(int k = 0; k<5; k++){
				xCordSmallP[k] = (int) (scale *1/75* Math.cos(k*2*Math.PI/5+180)+midX);
				yCordSmallP[k] = (int) (scale*1/76* Math.sin(k*2*Math.PI/5+180)+midY+1);
			}		
			g.fillPolygon(xCordSmallP, yCordSmallP, 5);
		}
		
	}
