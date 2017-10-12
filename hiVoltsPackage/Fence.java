package hiVoltsPackage;
import java.awt.Point;


public class Fence extends GamePiece {
	/**
	 * constructor for fence, calls the superclass "GamePiece".
	 */
	public Fence() {
		pType = PieceType.Mho;
		setType();
	}
	
	/**
	 * constructor that uses the superclass "GamePiece"'s method to create the fences
	 * @param p point
	 * @param diam diameter
	 */
	public Fence(Point p, int diam) {
		pType = PieceType.Fence;
		setType();
		
		cellSize = diam; //sets scale to diameter
		xGrid = (int) p.getX();
		yGrid = (int) p.getY();
		xCoord = cellSize * xGrid;
		yCoord = cellSize * yGrid;
	}
	
}
