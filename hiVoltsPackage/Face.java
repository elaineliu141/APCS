package hiVoltsPackage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;


public class Face 
{
	public enum FaceType {Happy, Sad, Fence}
	private static final ImageObserver ImageObserver = null;;
	public FaceType fType;
	Color myOrange = new Color(255, 140, 0);
	public Arc2D.Double mouth;
	public int xCoord; //Coordinates of top left corner of cell.
	public int yCoord;
	public int cellSize;
	public double diameter;
	public double radius; 
	
	//empty constructor
	public Face(){
	}
	
	/**
	 * Positions the face according to the scale (cell size).
	 * @param tehType
	 * @param x
	 * @param y
	 * @param diam
	 */
	public Face(FaceType tehType, int x, int y, int diam)
	{
		xCoord = x;
		yCoord = y;
		radius = diameter / 2;
		fType = tehType;
	}
	
	/**
	 * Uses Graphics2D to display the faces.
	 * @param g2
	 */
	public void paint(Graphics2D g2)
	{
		if (fType == FaceType.Fence)
		{
			fenceDraw(g2);
		}
		
		else
		{
			otherDraw(g2);
		}
	}
	
	/**
	 * Uses graphics 2D to paint the mhos.
	 * @param g2
	 */
	public void otherDraw(Graphics2D g2)
	{
		// Makes circle for head.
		double xCircle = xCoord + 2;
		double yCircle = yCoord + 2;
		
		Ellipse2D.Double head = new Ellipse2D.Double(xCircle, yCircle, (diameter - 4), (diameter - 4));
		
		//makes both eyes
		double xEye1 = xCircle + (diameter / 4);
		double xEye2 = xCircle + (3 * diameter / 4);
		double yEye = yCircle + (diameter / 4);
	
		//positions of the eyes
		Rectangle2D.Double eye1 = new Rectangle2D.Double((xEye1 - 1), (yEye - 1), 2, 2);
		Rectangle2D.Double eye2 = new Rectangle2D.Double((xEye2 - 1), (yEye - 1), 2, 2);
		Rectangle2D.Double nose = new Rectangle2D.Double((xCoord + radius - 1), (yCoord + radius - 1), 2, 2);

		g2.setColor(myOrange);
		
		//mouth set in a smiley face :)
		if (fType == FaceType.Happy)
		{
			mouth = new Arc2D.Double(
					(xCoord + (diameter / 4)),
					(yCoord + (3 * diameter / 8)),
					(diameter / 2),
					(diameter / 2), 180, 180, 2);
			g2.draw(head);
		}
		//else if not heppy -> sad (mouth set to unhappy face)
		else
		{
			mouth = new Arc2D.Double(
					(xCoord + (diameter / 4)),
					(yCoord + (9 * diameter / 16)),
					(diameter / 2),
					(diameter / 2), 0, 180, 2);
			g2.fill(head);
			g2.setColor(Color.BLACK);
		}
		
		//draws the eyes, nose, mouth onto the circle
		g2.fill(eye1);
		g2.fill(eye2);
		g2.fill(nose);
		g2.fill(mouth);
	}
	
	/**
	 * Draws the electric fences (Graphics2D).
	 * @param g2
	 */
	public void fenceDraw(Graphics2D g2)
	{
		//both bars on the left and right side
		int leftBarX = xCoord + 2;
		int rightBarX = xCoord + cellSize - 6;
		int barY = yCoord + 2;
		
		Rectangle leftBar = new Rectangle(leftBarX, barY, 4, cellSize - 4);
		Rectangle rightBar = new Rectangle(rightBarX, barY, 4, cellSize - 4);
		
		//3 lines in the middle of the bars
		double[] lineYCoords = new double[4];
		Line2D.Double[] horizlines = new Line2D.Double[4];
		
		//sets color
		Color myOrange = new Color(255, 140, 0);
		g2.setColor(myOrange);
		
		//creates 3 of the lines
		for(int lineCoord = 0; lineCoord <= 3; lineCoord++)
		{
			lineYCoords[lineCoord] = yCoord + (0.2 * cellSize) + (0.2 * lineCoord * cellSize);
			horizlines[lineCoord] = new Line2D.Double(leftBarX, lineYCoords[lineCoord], rightBarX, lineYCoords[lineCoord]);
			g2.draw(horizlines[lineCoord]);
		}
				
		g2.fill(leftBar);
		g2.fill(rightBar);
		
	}
	
}
