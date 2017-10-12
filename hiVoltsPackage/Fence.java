package hiVoltsPackage;
import java.awt.Point;


public class Fence extends GamePiece
{
	/**
	 * Constructor with no parameters that calls the superclass "GamePiece".
	 */
	public Fence()
	{
		pType = PieceType.Mho;
		setType();
	}
	
	/**
	 * A constructor that uses the superclass "GamePiece"'s method to create the fences.
	 * @param p
	 * @param diam
	 */
	public Fence(Point p, int diam)
	{
		pType = PieceType.Fence;
		setType();
		
		cellSize = diam;
		xGrid = (int) p.getX();
		yGrid = (int) p.getY();
		xCoord = cellSize * xGrid;
		yCoord = cellSize * yGrid;
			
	}
	
}
