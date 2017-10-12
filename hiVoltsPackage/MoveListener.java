package hiVoltsPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KeyListener for keyboard input.
class MoveListener implements KeyListener
{
	/**
	 * Adds the Components of the Board into the Listener.
	 * @param tehCompo
	 */
	public void addHiVoltsBoardComponent(HiVoltsBoardComponent tehCompo)
	{
		compo = tehCompo;
	}
	
	/**
	 * Determines the input of the KeyListener.
	 */
	public void keyPressed(KeyEvent event)
	{
		if (released)
		{
			code = event.getKeyCode();
			compo.keyInput(direction[code]);
			released = false;
		}
	}
	
	public void keyReleased(KeyEvent event)
	{	
		if ((event.getKeyCode() == code) && (code < direction.length))
		{	
			released = true;
		}
	}
	
	/**
	 * Determines the key typed and produces the output.
	 * @param event
	 */
	public void keyTyped(KeyEvent event)
	{
		
	}

	public int code;
	public boolean released = true;
	public HiVoltsBoardComponent compo;
	public HiVoltsBoardComponent.Movement[] direction = new HiVoltsBoardComponent.Movement[156];
	
	// Sets up array with movements, etc.
	{	
		//Arrow keys (Up, Down, Left, Right).
		direction[37] = HiVoltsBoardComponent.Movement.Left;
		direction[39] = HiVoltsBoardComponent.Movement.Right;
		direction[38] = HiVoltsBoardComponent.Movement.Up;
		direction[40] = HiVoltsBoardComponent.Movement.Down;
		
		//Letters for movement.
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