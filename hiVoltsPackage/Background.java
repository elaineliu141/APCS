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
	 * Empty Constructor.
	 */
	public Background()
	{}
	
	public int cellSize;
	public Rectangle rect;
	
	//either win or lose (true or false)
	public boolean gameOver;
	public boolean won;
	
	//win or lose messages
	public String loseMessage = "GAME OVER!";
	public String winMessage = "YOU WON!";
	public int winMessageX;
	public int winMessageY;
	public int loseMessageX;
	public int loseMessageY;
	public int resetButtonX;
	public int resetButtonY;
	public int quitButtonX;
	public int quitButtonY;
	
	//public HiVoltsFrame frame;
	public HiVoltsBoardComponent compo;
	public JButton reset;
	public JButton quit;
	public resetListenerClass resetListener = new resetListenerClass();
	public quitListenerClass quitListener = new quitListenerClass();
	public Font theFont;
	
	/**
	 * Sets the background for the hiVolts Board.
	 * @param diam
	 * @param tehCompo
	 */
	public Background(int diam, HiVoltsBoardComponent tehCompo)
	{
		/**
		 * Sizes for the win or lose messages.
		 */
		
		cellSize = diam;
		
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
		
		//Reset messages "New Game" and "Quit"
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
	 * Uses a boolean to determine win or lose.
	 * @param setTo
	 */
	public void setGameOver(boolean setTo)
	{
		gameOver = setTo;
	}
	
	/**
	 * Uses a boolean to determine win or lose.
	 * @param setTo
	 */
	public void setWin(boolean setTo)
	{
		won = true;
	}
	
	/**
	 * Returns the "Game Over!" boolean
	 * @return
	 */
	public boolean getGameOver()
	{
		return gameOver;
	}
	
	/**
	 * Draws the main background.
	 * @param g2
	 */
	public void draw(Graphics2D g2)
	{	
		//Black background
		g2.setColor(Color.BLACK);
		g2.fill(rect);
		
		if (gameOver)
		{	
			//sets the font for the text
			g2.setFont(theFont);
			if (won)
			{
				//Draws a Green string "You Won!"
				g2.setColor(Color.GREEN);
				g2.drawString(winMessage, winMessageX, winMessageY);
			}
			else
			{
				//Draws a Green string "You Lose!"
				g2.setColor(Color.RED);
				g2.drawString(loseMessage, loseMessageX, loseMessageY);
			}
			reset.setVisible(true);
			quit.setVisible(true);
		}
		
	}
	
	/**
	 * Resets the game after winning or losing.
	 * @author mickey
	 *
	 */
	class resetListenerClass implements ActionListener
	{	
		public void actionPerformed(ActionEvent event)
		{
			gameOver = false;
			won = false;
			compo.resetBoard();
		}
	}
	
	/**
	 * Quits the game from input of the "Quit" button
	 * @author mickey
	 *
	 */
	class quitListenerClass implements ActionListener
	{	
		public void actionPerformed(ActionEvent event)
		{
			compo.quit();
		}
	}
	
	

}
