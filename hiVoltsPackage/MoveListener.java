package hiVoltsPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KeyListener for keyboard input
class MoveListener implements KeyListener
{
	public int code;
	public boolean released = true;
	public HiVoltsBoardComponent compo;
	
	//Different Keys for Movement (implementation)
	public HiVoltsBoardComponent.Movement[] direction = new HiVoltsBoardComponent.Movement[156];
	
	/**
	 * Adds the Components of the Board into the Listener.
	 * @param tehCompo object created from HivoltsBoardComponent
	 */
	public void addHiVoltsBoardComponent(HiVoltsBoardComponent tehCompo)
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
		direction[37] = HiVoltsBoardComponent.Movement.Left;
		direction[39] = HiVoltsBoardComponent.Movement.Right;
		direction[38] = HiVoltsBoardComponent.Movement.Up;
		direction[40] = HiVoltsBoardComponent.Movement.Down;
		
		//Letters for movement (J etc).
		direction[81] = HiVoltsBoardComponent.Movement.UpLeft;
		direction[87] = HiVoltsBoardComponent.Movement.Up;
		direction[69] = HiVoltsBoardComponent.Movement.UpRight;
		direction[65] = HiVoltsBoardComponent.Movement.Left;
		direction[83] = HiVoltsBoardComponent.Movement.Nowhere;
		direction[68] = HiVoltsBoardComponent.Movement.Right;
		direction[90] = HiVoltsBoardComponent.Movement.DownLeft;
		direction[88] = HiVoltsBoardComponent.Movement.Down;
		direction[67] = HiVoltsBoardComponent.Movement.DownRight;
		direction[74] = HiVoltsBoardComponent.Movement.Jump;
		
	}
	
}