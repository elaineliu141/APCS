package hiVoltsPackage;

import java.awt.*;

public class Mho extends GamePiece
{
	/**
	 * Determines the type of gamepiece, a parent class (mho).
	 */
	public Mho()
	{
		pType = PieceType.Mho;
		setType();
	}
	
	/**
	 * Positions the face according to the scale (cell size).
	 * @param p
	 * @param size
	 */
	public Mho(Point p, int size)
	{
		pType = PieceType.Mho;
		setType();
		
		cellSize = size;
		diameter = cellSize;
		radius = diameter / 2;
		
		//X position, Y position
		xGrid = (int) p.getX();
		yGrid = (int) p.getY();
		
		xCoord = xGrid * cellSize;
		yCoord = yGrid * cellSize;
		
	}
}
