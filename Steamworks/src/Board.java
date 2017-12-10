
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	//Font for the intro screen.
    private Dimension d;
    private final Font smallFont = new Font("Helvetica", Font.BOLD, 14);

    //creates the wiffle balls
    Image ii;
    private final Color dotColor = new Color(192, 192, 0);
    private Color mazeColor;

    private boolean inGame = false;
    private boolean dying = false;

    //Determines the final variables for the components in the maze. (i.e. maximum number of opponents bots is 12)
    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int BOT_ANIM_DELAY = 2;
    private final int BOTS_ANIM_COUNT = 4;
    private final int MAX_OPPONENT = 12;
    private final int BOT_SPEED = 6;

    private int botAnimCount = BOT_ANIM_DELAY;
    private int botAnimDir = 1;
    private int botsAnimPos = 0;
    private int N_OPPONENT = 6;
    private int botsLeft, score;
    
    //movement of the opponent robot with cursor keys
    private int[] dx, dy;
    private int[] opponent_x, opponent_y, opponent_dx, opponent_dy, opponentSpeed;

    private Image opponentBot;
    private Image team8Bot1;

    private int team8_x, team8_y, team8d_x, team8d_y;
    private int req_dx, req_dy, view_dx, view_dy;

    //stores the x and y coordinates of the team8bot
    private final short levelData[] = {
        19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
        21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        21, 0, 0, 0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20,
        17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20,
        25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0, 21,
        1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21,
        1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0, 21,
        1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
        1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
        1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21,
        1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 21,
        1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
        9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 25, 24, 24, 24, 28
    };

    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;

    //Determines the speed of the bots
    private int currentSpeed = 3;
    private short[] screenData;
    private Timer timer;

    /**
     * Initializes the board to display the game.
     */
    public Board() {

        loadImages();
        initVariables();
        initBoard();
    }
    
    /**
     * Initializes the components of the Board.
     */
    private void initBoard() {
        
        addKeyListener(new TAdapter());

        setFocusable(true);

        setBackground(Color.black);
        setDoubleBuffered(true);        
    }

    /**
     * Initializes the Variables of the Board.
     */
    private void initVariables() {

        screenData = new short[N_BLOCKS * N_BLOCKS];
        mazeColor = new Color(5, 100, 5);
        d = new Dimension(400, 400);
        //Initializes the x, y coordinates of the opponent
        opponent_x = new int[MAX_OPPONENT];
        opponent_dx = new int[MAX_OPPONENT];
        opponent_y = new int[MAX_OPPONENT];
        opponent_dy = new int[MAX_OPPONENT];
        opponentSpeed = new int[MAX_OPPONENT];
        dx = new int[4];
        dy = new int[4];
        
        timer = new Timer(40, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        initGame();
    }

    /**
     * This method counts the lives of the bots left. (3 lives to begin with)
     */
    private void doAnim() {

        botAnimCount--;

        if (botAnimCount <= 0) {
            botsAnimPos = botsAnimPos + botAnimDir;
            if (botsAnimPos == (BOTS_ANIM_COUNT - 1) || botsAnimPos == 0) {
            	botAnimDir = -botAnimDir;
            }
        }
    }

    /**
     * This method ensures that the game continues when the player is alive and stops when the player is dead.
     * @param g2d
     */
    private void playGame(Graphics2D g2d) {

        if (dying) {

            death();

        } else {
        	//Moves the components of the board
            moveTeam8();
            drawTeam8(g2d);
            moveOpponents(g2d);
            checkMaze();
        }
    }

    /**
     * Displays the intro screen to start the game.
     * @param g2d
     */
    private void showIntroScreen(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, SCREEN_SIZE / 2 - 30, SCREEN_SIZE - 100, 50);

        //Intro message
        String s = "Press s to start.";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (SCREEN_SIZE - metr.stringWidth(s)) / 2, SCREEN_SIZE / 2);
    }

    /**
     * Displays the score on the bottom.
     * @param g
     */
    private void drawScore(Graphics2D g) {

        int i;
        String s;

        //Accumulates the score of the player.
        g.setFont(smallFont);
        g.setColor(new Color(96, 128, 255));
        s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (i = 0; i < botsLeft; i++) {
            g.drawImage(team8Bot1, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    /**
     * Checks to see if there are any wiffle balls, or "fuel" for the team8bot to eat on the board.
     */
    private void checkMaze() {

        short i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i] & 48) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {

            score += 50;

            if (N_OPPONENT < MAX_OPPONENT) {
                N_OPPONENT++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initLevel();
        }
    }

    /**
     * This method checks for when the bots die or have no more lives left.
     */
    private void death() {

    	botsLeft--;

        if (botsLeft == 0) {
            inGame = false;
        }

        //restarts a game
        continueLevel();
    }

    /**
     * A method to determine the movement for Opponents. The ghost would move one square and determine whether to change direction or not.
     * @param g2d
     */
    private void moveOpponents(Graphics2D g2d) {

        short i;
        int pos;
        int count;

        for (i = 0; i < N_OPPONENT; i++) {
        	//moved one square
            if (opponent_x[i] % BLOCK_SIZE == 0 && opponent_y[i] % BLOCK_SIZE == 0) {
                pos = opponent_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (opponent_y[i] / BLOCK_SIZE);

                count = 0;

                //determines where the opponent bot is located
                if ((screenData[pos] & 1) == 0 && opponent_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && opponent_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && opponent_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && opponent_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                //Draws the position of the opponent bot on random
                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                    	opponent_dx[i] = 0;
                    	opponent_dy[i] = 0;
                    } else {
                    	opponent_dx[i] = -opponent_dx[i];
                    	opponent_dy[i] = -opponent_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    opponent_dx[i] = dx[count];
                    opponent_dy[i] = dy[count];
                }

            }

            opponent_x[i] = opponent_x[i] + (opponent_dx[i] * opponentSpeed[i]);
            opponent_y[i] = opponent_y[i] + (opponent_dy[i] * opponentSpeed[i]);
            drawOpponent(g2d, opponent_x[i] + 1, opponent_y[i] + 1);

            //checks for the possibility of a collision between team8 and the opponent (team8 dies)
            if (team8_x > (opponent_x[i] - 12) && team8_x < (opponent_x[i] + 12)
                    && team8_y > (opponent_y[i] - 12) && team8_y < (opponent_y[i] + 12)
                    && inGame) {

                dying = true;
            }
        }
    }

    /**
     * This methods draws the opponents robot images.
     * @param g2d implements graphics2D to draw image
     * @param x determines the x coordinate
     * @param y determines the y coordinate
     */
    private void drawOpponent(Graphics2D g2d, int x, int y) {

        g2d.drawImage(opponentBot, x, y, this);
    }

    /**
     * This method moves Team8 bot based on the input of cursor keys.
     */
    private void moveTeam8() {

        int pos;
        short ch;

        if (req_dx == -team8d_x && req_dy == -team8d_y) {
        	team8d_x = req_dx;
        	team8d_y = req_dy;
            view_dx = team8d_x;
            view_dy = team8d_y;
        }

        if (team8_x % BLOCK_SIZE == 0 && team8_y % BLOCK_SIZE == 0) {
            pos = team8_x / BLOCK_SIZE + N_BLOCKS * (int) (team8_y / BLOCK_SIZE);
            ch = screenData[pos];

            //if team8 "eats" a fuel the fuel is removed from the maze (collides with it)
            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                score++;
            }
            
            //to check if team8 hit a wall (could not possibly move)
            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                	team8d_x = req_dx;
                	team8d_y = req_dy;
                    view_dx = team8d_x;
                    view_dy = team8d_y;
                }
            }

            // Check for standstill
            if ((team8d_x == -1 && team8d_y == 0 && (ch & 1) != 0)
                    || (team8d_x == 1 && team8d_y == 0 && (ch & 4) != 0)
                    || (team8d_x == 0 && team8d_y == -1 && (ch & 2) != 0)
                    || (team8d_x == 0 && team8d_y == 1 && (ch & 8) != 0)) {
            	team8d_x = 0;
            	team8d_y = 0;
            }
        }
        team8_x = team8_x + BOT_SPEED * team8d_x;
        team8_y = team8_y + BOT_SPEED * team8d_y;
    }

    /**
     * Draws the image of Team8Bot.
     * @param g2d implements graphics 2D
     */
    private void drawTeam8(Graphics2D g2d) {
        g2d.drawImage(team8Bot1, team8_x + 1, team8_y + 1, this);
          
    }

    /**
     * Draws the maze or the board with the walls.
     * @param g2d implements graphics2D
     */
    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        //Draws based on the screen size of maze
        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(2));

                if ((screenData[i] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) { 
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) { 
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) { 
                    g2d.setColor(dotColor);
                    g2d.fillRect(x + 11, y + 11, 2, 2);
                }

                i++;
            }
        }
    }

    /**
     * Initializes the game by setting the number of bots and opponent bots.
     */
    private void initGame() {

    	botsLeft = 3;
        score = 0;
        initLevel();
        N_OPPONENT = 6;
        currentSpeed = 3;
    }

    /**
     * Starts a new level.
     */
    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }

        continueLevel();
    }

    /**
     * Starts a new level when the bots have no more lives.
     */
    private void continueLevel() {

        short i;
        int dx = 1;
        int random;

        //Compares the speed of the opponent to the player. If the player is slower and is surpassed by the opponent bot, the player is counted dead.
        for (i = 0; i < N_OPPONENT; i++) {

        	opponent_y[i] = 4 * BLOCK_SIZE;
        	opponent_x[i] = 4 * BLOCK_SIZE;
        	opponent_dy[i] = 0;
        	opponent_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));

            if (random > currentSpeed) {
                random = currentSpeed;
            }

            opponentSpeed[i] = validSpeeds[random];
        }

        team8_x = 7 * BLOCK_SIZE;
        team8_y = 11 * BLOCK_SIZE;
        team8d_x = 0;
        team8d_y = 0;
        req_dx = 0;
        req_dy = 0;
        view_dx = -1;
        view_dy = 0;
        dying = false;
    }

    /**
     * Contains all the images of the bot and the opponent bots.
     */
    private void loadImages() {

    	//Images of the bots
    	opponentBot = new ImageIcon("src/254Robot.png").getImage();
        team8Bot1 = new ImageIcon("src/8Robot.png").getImage();
        

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    /**
     * Draws the components in the maze.
     * @param g
     */
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        //Maze color
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);
        doAnim();

        if (inGame) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }
    
    
	/**
	 * Determines the output based on the keyboard input.
	 * @author mickey
	 *
	 */
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            //Key Events and Outputs
            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                } else if (key == KeyEvent.VK_PAUSE) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }
            } else {
                if (key == 's' || key == 'S') {
                    inGame = true;
                    initGame();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == Event.LEFT || key == Event.RIGHT
                    || key == Event.UP || key == Event.DOWN) {
                req_dx = 0;
                req_dy = 0;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}