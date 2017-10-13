package hiVoltsPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class Background 
{
	/**
	 * constructor for Background
	 */
	public Background() {
		
	}
	
	public int cellSize; //scale for entire board
	public Rectangle rect;
	
	//win or lose?
	public boolean gameOver;
	public boolean won;
	
	//win/lose messages
	public String lose = "GAME OVER!";
	public String win = "YOU WON!";
	public int winMessageX;
	public int winMessageY;
	public int loseMessageX;
	public int loseMessageY;
	public int resetButtonX;
	public int resetButtonY;
	public int quitButtonX;
	public int quitButtonY;
	
	//frame
	public Board compo;
	public JButton reset;
	public JButton quit;
	public resetListenerClass resetListener = new resetListenerClass();
	public quitListenerClass quitListener = new quitListenerClass();
	public Font theFont;
	
	/**
	 * sets background
	 * @param diam diameter of the smiley face
	 * @param tehCompo composition
	 */
	public Background(int diam, Board tehCompo) {
		//win/lose message size
		cellSize = diam; //sets scale to diameter
		
		loseMessageX = (int) (1.4 * cellSize);
		loseMessageY = (int)(cellSize * 6);
		winMessageX = (int) (2.8 * cellSize);
		
		winMessageY = loseMessageY; //same Y position
		resetButtonX = (int)(cellSize * 5);
		resetButtonY = (int) (cellSize * 7.5);
		quitButtonX = (int)(cellSize * 9);
		quitButtonY = resetButtonY;
		
		rect = new Rectangle(0, 0, 16 * cellSize, 14 * cellSize);
		compo = tehCompo;
		
		//reset messages for "new game" and "quit"
		reset = new JButton("New Game");
		reset.setLocation(resetButtonX, resetButtonY);
		reset.setSize(cellSize * 2, cellSize);
		reset.addActionListener(resetListener);
		
		quit = new JButton("Quit");
		quit.setLocation(quitButtonX, quitButtonY);
		quit.setSize(cellSize * 2, cellSize);
		quit.addActionListener(quitListener);
		
		
		theFont = new Font("theFont", 1, cellSize * 2);
		
	}
	
	/**
	 * determines if game is over
	 * @param setTo stores boolean value; true if game is over
	 */
	public void setGameOver(boolean setTo) {
		gameOver = setTo;
	}
	
	/**
	 * determines if won or lost
	 * @param setTo stores boolean value; true if user won
	 */
	public void setWin(boolean setTo) {
		won = true;
	}
	
	/**
	 * returns "game over"
	 * @return "game over"
	 */
	public boolean getGameOver()
	{
		return gameOver;
	}
	
	/**
	 * draws background
	 * @param g2 graphics of background
	 */
	public void draw(Graphics2D g2)
	{	
		//sets the bg color to black
		g2.setColor(Color.BLACK);
		g2.fill(rect);
		
		if (gameOver)
		{	
			//sets the font for the text
			g2.setFont(theFont);
			if (won)
			{
				//draws "you won" in green
				g2.setColor(Color.GREEN);
				g2.drawString(win, winMessageX, winMessageY);
			}
			else
			{
				//draws "you lost" in red
				g2.setColor(Color.RED);
				g2.drawString(lose, loseMessageX, loseMessageY);
			}
			reset.setVisible(true);
			quit.setVisible(true);
		}
		
	}
	
	/**
	 * resets game after winning/losing
	 * @author ashley
	 *
	 */
	class resetListenerClass implements ActionListener {	
		public void actionPerformed(ActionEvent event)
		{
			gameOver = false; //sets gameover to false
			won = false; //sets win to false
			compo.resetBoard(); //resets board
		}
	}
	
	/**
	 * if quit button is pressed, quits game
	 * @author ashley
	 *
	 */
	class quitListenerClass implements ActionListener {	
		public void actionPerformed(ActionEvent event) //if quit button pressed
		{
			compo.quit(); //quit game
		}
	}
	
	

}
