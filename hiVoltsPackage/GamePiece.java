package hiVoltsPackage;

import java.awt.Graphics2D;
import java.awt.Point;

public class GamePiece extends Character {
	public enum PieceType {Player, Mho, Fence};
	public PieceType pType;
	public int xGrid; //x coordinate of cell on grid
	public int yGrid; // y coordinate of cell on grid
	
	/**
	 * GamePiece empty constructor used for inheritance.
	 */
	public GamePiece() {
		
	}
	
	/**
	 * SetType method (inherited by other subclasses)
	 */
	public void setType() {
		if (pType == PieceType.Mho) {
			fType = FaceType.Sad;
		} if (pType == PieceType.Player) {
			fType = FaceType.Happy;
		} if (pType == PieceType.Fence) {
			fType = FaceType.Fence;
		}
	}
	
	/**
	 * SetLocation a method that determines the location of the faces.
	 * @param p is position
	 */
	public void setLocation(Point p) {
		xGrid = (int) p.getX();
		yGrid = (int) p.getY();
		
		xCoord = xGrid * cellSize;
		yCoord = yGrid * cellSize;
	}
	
	/**
	 * SetLocation with a determined x and y.
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y) {
		Point p = new Point(x, y);
		setLocation(p);
	}
	
	/**
	 * Returns the location of the faces.
	 * @return
	 */
	public Point getLocation() {
		Point p = new Point(xGrid, yGrid);
		return p;
	}
	
	/**
	 * Returns the X value of the location of the faces.
	 * @return
	 */
	public int getX() {
		return xGrid;
	}
	
	/**
	 * Returns the Y value of the location of the faces.
	 * @return
	 */
	public int getY() {
		return yGrid;
	}
	
	/**
	 * Sets the size of the faces.
	 * @param size is the size of the cell
	 */
	public void setSize(int size) {
		cellSize = size;
		diameter = cellSize;
		radius = cellSize / 2;
	}
	
	/**
	 * Activates the paint class.
	 * @param g2 calls 2D graphics
	 */
	public void draw(Graphics2D g2) {
		paint(g2);
	}
	
}
