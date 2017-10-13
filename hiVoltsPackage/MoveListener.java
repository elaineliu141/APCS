package hiVoltsPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KeyListener for keyboard input
class MoveListener implements KeyListener
{
	public int code;
	public boolean released = true;
	public Board compo;
	
	//Different Keys for Movement (implementation)
	public Board.Movement[] direction = new Board.Movement[156];
	
	/**
	 * Adds the Components of the Board into the Listener.
	 * @param tehCompo object created from HivoltsBoardComponent
	 */
	public void addHiVoltsBoardComponent(Board tehCompo)
	{
		compo = tehCompo;
	}
	
	/**
	 * Determines the input of the KeyListener.
	 * @param event which key was pressed
	 */
	public void keyPressed(KeyEvent event)
	{
		//Which key was pressed
		if (released)
		{
			code = event.getKeyCode();
			compo.keyInput(direction[code]);
			released = false;
		}
	}
	
	/**
	 * Determines the key typed and produces the output.
	 * @param event which key pressed and code output
	 */
	public void keyReleased(KeyEvent event)
	{	
		if ((event.getKeyCode() == code) && (code < direction.length))
		{	
			released = true;
		}
	}
	
	/**
	 * Determines the key typed and produces the output.
	 * @param event which key was typed
	 */
	public void keyTyped(KeyEvent event)
	{
		
	}
	// Sets up array with movements, etc.
	{	
		//Arrow keys (Up, Down, Left, Right).
		direction[37] = Board.Movement.Left;
		direction[39] = Board.Movement.Right;
		direction[38] = Board.Movement.Up;
		direction[40] = Board.Movement.Down;
		
		//Letters for movement (J etc).
		direction[81] = Board.Movement.UpLeft;
		direction[87] = Board.Movement.Up;
		direction[69] = Board.Movement.UpRight;
		direction[65] = Board.Movement.Left;
		direction[83] = Board.Movement.Nowhere;
		direction[68] = Board.Movement.Right;
		direction[90] = Board.Movement.DownLeft;
		direction[88] = Board.Movement.Down;
		direction[67] = Board.Movement.DownRight;
		direction[74] = Board.Movement.Jump;
		
	}
	
}