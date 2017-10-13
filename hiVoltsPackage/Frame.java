package hiVoltsPackage;
import javax.swing.JFrame;

public class Frame extends JFrame {
	
	//implementation of MoveListener
	private static final long serialVersionUID = 1L;
	public MoveListener playerMover = new MoveListener();
	public Board compo;
	
	//scale of the display
	public static int cellSize = 56;
	
	//width and height for the frame
	public static int FRAME_WIDTH = cellSize*16;
	public static int FRAME_HEIGHT = cellSize*13;
	
	/**
	 * Sets up the Background frame for the display
	 * @param mhoNum is number of Mhos
	 * @param fenceNum is number of fences
	 */
	public void setUp(int mhoNum, int fenceNum) {	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		compo = new Board(cellSize, mhoNum, fenceNum, this);
		
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("HiVolts!");
		this.setLayout(null);
		
		//proportion for the scaling of display (how big it shows on the screen)
		compo.setLocation(0,0);
		compo.setSize(cellSize * 16, cellSize * 13);
		this.add(compo);
		
		playerMover.addHiVoltsBoardComponent(compo);
		
		//adds KeyListener!
		this.addKeyListener(playerMover); 
	}
	
	/**
	 * Resets the Movement of the player at start.
	 */
	public void resetPlayerMover() {
		this.setVisible(false);
		this.setVisible(true);
		this.addKeyListener(playerMover);
	}
	
}
