package hiVoltsPackage;

import java.awt.*;

public class Player extends GamePiece
{

	/**
	 * Constructor with no parameters that calls the superclass "GamePiece".
	 */
	public Player()
	{
		pType = PieceType.Player;
		setType();
		
	}
	
	/**
	 * Constructor inheriting the methods of the superclass to display the player "emoji".
	 * @param p
	 * @param size
	 */
	public Player(Point p, int size)
	{
		pType = PieceType.Player;
		setType();
		
		cellSize = size;
		diameter = cellSize;
		radius = cellSize / 2;
		
		xGrid = (int) p.getX();
		yGrid = (int) p.getY();
		
		xCoord = xGrid * cellSize;
		yCoord = yGrid * cellSize;
		
	}
	
}
