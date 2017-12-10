import java.awt.EventQueue;
import javax.swing.JFrame;
/**
 * Inspired by the SteamWorks robotics competition, this game involves the player consuming wiffle balls in the maze, while avoiding opponent bots.
 * If the player runs into the opponent robot, the player dies and reduces a life out of the three.
 * The wiffle balls accumulate into the total score. 
 * @author mickey
 *
 */
public class SteamWorks extends JFrame {

	/**
	 * Starts the SteamWorks game.
	 */
    public SteamWorks() {
        
        initUI();
    }
    
    /**
     * Draws the Board (JPanel).
     */
    private void initUI() {
        
        add(new Board());
        //JPanel features
        setTitle("SteamWorks!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
        setVisible(true);        
    }

    /**
     * Creates a new object for the game.
     * @param args
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SteamWorks ex = new SteamWorks();
            ex.setVisible(true);
        });
    }
}