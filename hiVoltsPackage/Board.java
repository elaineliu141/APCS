package hiVoltsPackage;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;

public class Board extends JComponent {
	
	public int cellSize;
	public Frame frame;
	
	//Number of mhos and fences.
	public int originalMhos;
	public int mhos;
	public int fences;
	public int borderFences;
	
	//Uses an array to create occupied or unoccupied spots for the random fences
	public boolean[][] occupied = new boolean[12][12];
	public Random gen = new Random();
	public boolean userCanMove;
	public Font theFont;
	public String yourTurn = "Your turn.";
	
	//Creates the ArrayLists for the random assignment
	public Background bckgrnd;
	public ArrayList<Mho> mhoList;
	public ArrayList<Fence> fenceList;
	public ArrayList<Fence> borderFenceList = new ArrayList<Fence>();
	public Player you;
	
	//Movement for the KeyListener
	public enum Movement {Left, Right, Up, Down, UpLeft, UpRight, DownLeft, DownRight, Nowhere, Jump};
	
	public static final long serialVersionUID = 823;
	
	//empty constructor
	public Board() {
		
	}
	
	/**
	 * Create all objects for the components on the board.
	 * @param size is cell size
	 * @param mhonum is number or Mhos
	 * @param fencenum is number of faces
	 * @param Frame is the frame of the game
	 */

	public Board(int size, int mhonum, int fencenum, Frame tehFrame) {

		
		cellSize = size;
		theFont = new Font("theFont", 1, (cellSize / 2));
		frame = tehFrame;
		
		//Creates background.
		bckgrnd = new Background(cellSize, this);
		
		//Creates lists of inner fences & mhos.
		mhoList = new ArrayList<Mho>();
		fenceList = new ArrayList<Fence>();
		
		//Saves values for number of mhos and number of fences.
		originalMhos = mhonum;
		fences = fencenum;
		
		resetBoard();
		
	}
	
	/**
	 * Resets the board during start of game.
	 */
	public void resetBoard() {
		
		bckgrnd.reset.setVisible(false);
		bckgrnd.quit.setVisible(false);
		this.remove(bckgrnd.reset);
		this.remove(bckgrnd.quit);
		frame.resetPlayerMover();
		
		mhos = originalMhos;
		
		mhoList.clear();
		fenceList.clear();
		borderFenceList.clear();
		
		//Sets cells to unoccupied.
		setAllUnoccupied();
		
		//Gives mhos locations & sets locations to occupied.
		for (int mhoIndex = 0; mhoIndex <= (mhos-1); mhoIndex++) {
			Point mhoPoint = nextEmptyRandom();
			Mho mho = new Mho(mhoPoint, cellSize);
			mhoList.add(mho);
			occupied[mho.getX()][mho.getY()] = true;
		}
		
		//Gives inner fences locations & sets locations to occupied.
		for (int fenceIndex = 0; fenceIndex <= (fences - 1); fenceIndex++) {
			Point fencePoint = nextEmptyRandom();
			Fence fence = new Fence(fencePoint, cellSize);
			fenceList.add(fence);
			occupied[fence.getX()][fence.getY()] = true;
		}
		
		//Gives border fences locations and sets locations to occupied.
		int borderFenceIndex = 0;
		Point borderFencePoint;
		
		//Top & Bottom Fences
		for (int xIndex = 0; xIndex <= 11; xIndex++) {
			borderFencePoint = new Point(xIndex, 0);
			Fence fence = new Fence(borderFencePoint, cellSize);
			borderFenceList.add(borderFenceIndex, fence);
			occupied[fence.getX()][fence.getY()] = true;
			borderFenceIndex++;
			
			borderFencePoint = new Point(xIndex, 11);
			fence = new Fence(borderFencePoint, cellSize);
			borderFenceList.add(fence);
			occupied[fence.getX()][fence.getY()] = true;
			borderFenceIndex++;
		}
		
		//Left & Right Fences
		for (int yIndex = 1; yIndex <= 10; yIndex++) {
			borderFencePoint = new Point(0, yIndex);
			Fence fence = new Fence(borderFencePoint, cellSize);
			borderFenceList.add(borderFenceIndex, fence);
			occupied[fence.getX()][fence.getY()] = true;
			borderFenceIndex++;
			
			borderFencePoint = new Point(11, yIndex);
			fence = new Fence(borderFencePoint, cellSize);
			borderFenceList.add(borderFenceIndex, fence);
			occupied[fence.getX()][fence.getY()] = true;
			borderFenceIndex++;
		}
		
		//Creates player and gives player a location.
		Point playerPoint = nextEmptyRandom();
		you = new Player(playerPoint, cellSize);	
		userCanMove = true;	
		repaint();
		
	}
	
	/**
	 * Set the array to all unoccupied (empty spaces that can be taken up).
	 */
	private void setAllUnoccupied() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				occupied[i][j] = false;
			}
		}
	}
	
	/**
	 * Set the array for inside fences to all unoccupied (empty spaces that can be taken up).
	 */
	private void setInternalUnoccupied() {
		for (int i = 1; i < 11; i++) {
			for (int j = 1; j < 11; j++) {
				occupied[i][j] = false;
			}
		}
	}
	
	/**
	 * Changing the unoccupied to occupied when randomly distributing fences.
	 */
	private void updateOccupied() {
		setInternalUnoccupied();
		
		for (Mho m : mhoList) {
			occupied[m.getX()][m.getY()] = true;
		} for (Fence f : fenceList) {
			occupied[f.getX()][f.getY()] = true;
		}
		occupied[you.getX()][you.getY()] = true;
	}
	
	/**
	 * Checks for a spot being occupied or not.
	 * @param p is position
	 * @return
	 */
	private boolean isOccupied(Point p) {
		return isOccupied((int) p.getX(), (int) p.getY());
	}
	
	private boolean isOccupied(int x, int y) {
		return occupied[x][y];
	}
	
	/**
	 * Determines if a game piece is a non player piece (fences, mhos).
	 * @param w is position of the nonplayer piece
	 * @return
	 */
	private boolean isNonPlayerGamePiece(Point w) {
		return isNonPlayerGamePiece((int) w.getX(), (int) w.getY());
	}
	
	/**
	 * prevents repeated randomized locations
	 * @param x is x coord
	 * @param y is y coord
	 * @return
	 */
	private boolean isNonPlayerGamePiece(int x, int y) {
		boolean retval = false;		
		Point p = new Point(x,y);
		
		if (isOccupied(p) == true && (you.getLocation().equals(p) == false)) {
			retval = true;
		} return retval;
	}
	
	/**
	 * Generates random point.
	 * @return
	 */
	public Point randomPoint() {
		int nextX = gen.nextInt(10) + 1;
		int nextY = gen.nextInt(10) + 1;
		
		Point nextPoint = new Point(nextX, nextY);
		return nextPoint;
	}
	
	/**
	 * Moves onto assigning a different random piece.
	 * @return
	 */
	public Point nextEmptyRandom() {
		Point p = randomPoint();
		
		int nextX = (int) p.getX();
		int nextY = (int) p.getY();
		
		if (occupied[nextX][nextY] == true) {
			return nextEmptyRandom();
		} else {
			return p;
		}
	}
	
	/**
	 * Movement for a random jump.
	 * @return
	 */
	public Point randomJump() {
		Point p = randomPoint();
		
		int nextX = (int) p.getX();
		int nextY = (int) p.getY();
		
		if (notFence(nextX, nextY) == true) {
			return p;
		} else {
			return randomJump();
		}
	}
	
	/**
	 * Checks if the point assigned is already a fence.
	 * @param p is position
	 * @return
	 */
	public boolean fence(Point p) {
		return (!notFence(p));
	}

	public boolean fence(int x, int y) {
		return (!notFence(x, y));
	}
	
	/**
	 * Checks if the point assigned is not a fence.
	 * @param p is position
	 * @return
	 */
	public boolean notFence(Point p) {
		return notFence((int)p.getX(), (int) p.getY());
	}
	
	/**
	 * checks if a coordinate is not a fence
	 * @param x coord
	 * @param y coord
	 * @return
	 */
	public boolean notFence(int x, int y) {
		boolean retval = true;
		
		for(Fence f : fenceList) {
			if ((f.getX() == x) == true && (f.getY() == y) == true) {
				retval = false;
				break;
			}
		} return retval;
	}
	
	/**
	 * Draws the mhos and fences.
	 * @param g allows for graphics
	 */
	public void paintComponent(Graphics g) {	
		Graphics2D g2 = (Graphics2D) g;
		
		bckgrnd.draw(g2);
		
		for (int i = 0; i <= (mhos - 1); i++) {
			Mho mo = mhoList.get(i);
			mo.draw(g2);
		}
		
		for (int j = 0; j <= (fences - 1); j++) {
			Fence fence = fenceList.get(j);
			fence.draw(g2);
		}
		
		for (int k = 0; k <= 43; k++) {
			Fence fence = borderFenceList.get(k);
			fence.draw(g2);
		}
		
		you.draw(g2);
		
		if (userCanMove == true) {
			g2.setFont(theFont);
			g2.drawString(yourTurn, (int) (12.5 * cellSize), (2 * cellSize));
		}
		
		if (bckgrnd.getGameOver() == true) {
			add(bckgrnd.reset);
			add(bckgrnd.quit);
			bckgrnd.draw(g2);
		}
		
	}
	
	/**
	 * Determine the input of the key (KeyListener).
	 * @param move is the direction moved
	 */
	public void keyInput(Movement move) {
		if (userCanMove == true) {
			if (move != Movement.Jump) {
				userCanMove = false;
			} doKeyInput(move);
		}	
	}
	
	/**
	 * Uses if-else statements to determine the output of the faces.
	 * @param move is the direction moved
	 */
	private void doKeyInput(Movement move) {
		Point oldLocation = you.getLocation();
		int oldX = (int) oldLocation.getX();
		int oldY = (int) oldLocation.getY();
		int newX = oldX;
		int newY = oldY;
		Point newLocation = oldLocation;
		
		if (move == Movement.Left) {
			newX = (oldX - 1);
			newY = oldY;
			newLocation.setLocation(newX, newY);
		} else if (move == Movement.Right) {
			newX = (oldX + 1);
			newY = oldY;
			newLocation.setLocation(newX, newY);
		} else if (move == Movement.Up) {
			newX = oldX;
			newY = (oldY - 1);
			newLocation.setLocation(newX, newY);
		} else if (move == Movement.Down) {
			newX = oldX;
			newY = (oldY + 1);
			newLocation.setLocation(newX, newY);
		} else if (move == Movement.UpLeft) {
			newX = oldX - 1;
			newY = oldY - 1;
			newLocation.setLocation(newX, newY);
		} else if (move == Movement.UpRight) {
			newX = oldX + 1;
			newY = oldY - 1;
			newLocation.setLocation(newX, newY);
		} else if (move == Movement.DownLeft) {
			newX = oldX - 1;
			newY = oldY + 1;
			newLocation.setLocation(newX, newY);
		} else if (move == Movement.DownRight) {
			newX = oldX + 1;
			newY = oldY + 1;
			newLocation.setLocation(newX, newY);
		} else if (move == Movement.Jump) {
			newLocation = randomJump();
			newX = (int) newLocation.getX();
			newY = (int) newLocation.getY();
		}
		
		if (move != Movement.Nowhere) {
			occupied[oldX][oldY] = false;
		}
		
		you.setLocation(newLocation);
		repaint();
		
		if (occupied[newX][newY] == true && (move != Movement.Nowhere)) {
			bckgrnd.setGameOver(true);
		} else {
			occupied[newX][newY] = true;
			repaint();
			
			if (move != Movement.Jump) {
				nextTurn();
			}
		}
	}
	
	/**
	 * Determines the motion for the mhos (adding and removing) after each turn. 
	 */
	private void nextTurn() {	
		ArrayList<Mho> newMhoList = new ArrayList<Mho>(); 
		
		if (bckgrnd.getGameOver() == false) {
			for (Mho y : mhoList) {
				Mho newMho = y;
				moveMho(newMho);
				newMhoList.add(newMho);
				
				if (fence(newMho.getLocation()) == true) {
					newMhoList.remove(newMho);
				}
			}
		}
		mhoList = newMhoList;
		mhos = mhoList.size();
		userCanMove = true;
		updateOccupied();
		
		if (mhos == 0) {
			bckgrnd.setWin(true);
			bckgrnd.setGameOver(true);
			repaint();
		}
	}
	
	/**
	 * Determines the movement for the Mhos.
	 * @param w is position of mho
	 */
	private void moveMho(Mho w) {
		//Same row or column move.
		boolean sameRow = sameRow(w, you);
		boolean sameColumn = sameColumn(w, you);
	
		if (sameRow == true || sameColumn == true) {
			directMove(w, you, sameRow, sameColumn);
		}
		
		//Diagonal move.
		else if (diagonal(w, you) == true && (isNonPlayerGamePiece(diagonalMoveLocation(w, you)) == false)) {
			w.setLocation(diagonalMoveLocation(w, you));		
		}
		
		//Horizontal move.
		else if ((horizGreaterThanVert(w, you) == true) && (isNonPlayerGamePiece(horizMoveLocation(w, you)) == false)) {
			w.setLocation(horizMoveLocation(w, you));
		}
		
		//Vertical move.
		else if ((horizGreaterThanVert(w, you) == false) && (isNonPlayerGamePiece(horizMoveLocation(w, you)) == false)) {
			w.setLocation(vertMoveLocation(w, you));
		}
		
		//Diagonal move to fence.
		else if (diagonal(w, you) == true && (notFence(diagonalMoveLocation(w, you)) == false)) {
			w.setLocation(diagonalMoveLocation(w, you));	
		}
		
		//Horizontal move to fence.
		else if (horizGreaterThanVert(w, you) == true && notFence(horizMoveLocation(w, you)) == false) {
			w.setLocation(horizMoveLocation(w, you));
		}
		
		//Vertical move to fence.
		else if (horizGreaterThanVert(w, you) == false && notFence(horizMoveLocation(w, you)) == false) {
			w.setLocation(vertMoveLocation(w, you));
		}
		
		Point newLocation = w.getLocation();
		
		if (newLocation.equals(you.getLocation())) {
			bckgrnd.setGameOver(true);
			repaint();
		}
	}
	
	/**
	 * determines if two game pieces are in the same row
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean sameRow(GamePiece a, GamePiece b) {
		return (a.getY() == b.getY());
	}
	
	/**
	 * determines if two game pieces are in the same column
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean sameColumn(GamePiece a, GamePiece b) {
		return (a.getX() == b.getX());
	}
	
	/**
	 * Movement for the mhos and faces.
	 * @param pieceThatWillMove is the mho that will move
	 * @param referencePiece is a piece that will not move, such as a fence
	 * @param row
	 * @param column
	 */
	private void directMove(GamePiece pieceThatWillMove, GamePiece referencePiece, boolean row, boolean column) {
		//If same row, change x & change column number.
		//If same column, change y & change row number.
		
		Point r = referencePiece.getLocation();		
		Point p = pieceThatWillMove.getLocation();
		
		int newColumn = (int) p.getX();
		int newRow = (int) p.getY();
		
		if (row == true) {
			newColumn = moveNumCloser(newColumn, (int) r.getX());
		} if (column == true) {
			newRow = moveNumCloser(newRow, (int) r.getY());
		}
		
		p = new Point(newColumn, newRow);
		pieceThatWillMove.setLocation(p);
	}
	
	/**
	 * Diagonal distance for the mhos.
	 * @param thatMoves
	 * @param reference
	 * @return
	 */
	private boolean diagonal(GamePiece a, GamePiece b) {
		int dist1 = Math.abs((a.getX() - b.getX()));
		int dist2 = Math.abs((a.getY() - b.getY()));
		return (dist1 == dist2);
	}
	
	/**
	 * Diagonal Movement for the mhos.
	 * @param thatMoves
	 * @param reference
	 * @return
	 */
	private Point diagonalMoveLocation(GamePiece thatMoves, GamePiece reference) {
		int oldX = thatMoves.getX();
		int oldY = thatMoves.getY();
		
		int refX = reference.getX();
		int refY = reference.getY();
		
		int newX = moveNumCloser(oldX, refX);
		int newY = moveNumCloser(oldY, refY);
		
		Point igger = new Point(newX, newY);
		return igger;
	}
	
	/**
	 * determines if the horizontal distance between the player and the mho is greater than the vertical
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean horizGreaterThanVert(GamePiece a, GamePiece b) {
		boolean retval = false;
		int horizontal = Math.abs((a.getX() - b.getX()));
		int vertical = Math.abs((a.getY() - b.getY()));
		
		if (horizontal > vertical) {
			retval = true;
		} return retval;
	}

	/**
	 * Determines the horizontal Location and movement for the mhos
	 * @param thatMoves is the moving piece
	 * @param reference does not move
	 * @return
	 */
	private Point horizMoveLocation(GamePiece thatMoves, GamePiece reference) {
		int moveX = thatMoves.getX();
		int refrenceX = reference.getX();
		int newX = moveNumCloser(moveX, refrenceX);
		
		Point p = new Point(newX, thatMoves.getY());
		return p;
	}
	
	/**
	 * Determines the vertical Location and movement for the mhos
	 * @param thatMoves is the moving piece
	 * @param reference does not move
	 * @return
	 */
	private Point vertMoveLocation(GamePiece thatMoves, GamePiece reference) {
		int moveY = thatMoves.getY();
		int refrenceY = reference.getY();
		int newY = moveNumCloser(moveY, refrenceY);
		
		Point p = new Point(thatMoves.getX(), newY);
		return p;
	}
	
	/**
	 * Moves the mhos closer to the face, the player.
	 * @param numThatWillMove is spaces the mho moves
	 * @param otherNum is distance from the player
	 * @return
	 */
	private int moveNumCloser(int numThatWillMove, int otherNum) {
		if (otherNum > numThatWillMove) {
			return numThatWillMove +1;
		} if (otherNum < numThatWillMove) {
			return numThatWillMove -1;
		} else {
			return otherNum;
		}
	}
	
	/**
	 * Quits the frame.
	 */
	public void quit() {
		frame.dispose();
	}
	
}
