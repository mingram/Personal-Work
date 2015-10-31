package v0_2;
import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;


/**
 * PARSEC. It's a very awesome and classic game for the Texas Instruments TI 99/4a
 * Home Computer System. I'm attempting to remake it. I'm basing this off of the
 * Animation2D project by Chris D'Arcy.
 * @author Mike Mallin
 * @date 2010
 */


public class Parsec extends Canvas implements KeyListener {

	/**
	 * The width of the Canvas
	 */
	public static final int WIDTH = 800;

	/**
	 * The height of the Canvas
	 */
	public static final int HEIGHT = 600;

	/**
	 * SPEED controls the delay of the utility Thread
	 */

	public static final int SPEED = 30;

	/**
	 * Specialized class to support double buffering
	 */
	public BufferStrategy strategy;

	/**
	 * an instance of the <b>Ship</b> class to be animated
	 */

	public static Ship thePlayer;
	public static ArrayList<Enemy> theEnemies;

	private static int startX = WIDTH/4, startY = (int)(HEIGHT * 0.33);

	/**
	 *  Other required variables.
	 */

	private UI ui;
	private static BufferedImage starfield, land;
	private Random r;
	private float scrollX, ellapsed;
	private int lives, hs, score, wave, level, spawnedEnemies, destroyedEnemies;
	private long lazerKaboomTime, lastEnemyTime, lastLazerShot, lastFrameTime;
	private int groundLevel = (int)(HEIGHT * 0.57);

	/**
	 * Required text strings.
	 */
	public static String parsec = "P A R S E C";
	public static String rights = "(c) 1982		Texas Instruments";
	public static String reborn = "Reborn by Mike Mallin - 2010";
	public static String pressKey = "Press Any Key To Continue";
	public static String pause = "Game Paused. Press 'p' to continue.";
	public static String again = "Game Over. Press Fire to play again.";

	/**
	 * The game runs like a finite state machine (FSM).
	 */

	GameState theState;

	enum GameState {MENU, WAITING, PLAYING, DEAD, PAUSE, AGAIN};
	
	/**
	 * Predefined enemy speeds. Pixels/second.
	 */

	private static final int SWOOPER_SPEED = 96;
	private static final int URBITE_SPEED = 112;
	private static final int LAZER_SPEED = 256; 
	
	/**
	 * Single constructor (supplied)
	 */
	public Parsec() {
		JFrame frame = new JFrame("Parsec");
		JPanel panel = (JPanel) frame.getContentPane();

		setBounds(0, 0, WIDTH, HEIGHT);

		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		panel.add(this);
		addKeyListener(this);
		frame.setBounds(0, 0, WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setResizable(false);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		requestFocus();
		//Init all the other variables
		r = new Random();
		//New user interface (HEIGHT = HEIGHT * 0.7) aka 420px. 2nd par + 4th par = 600.
		ui = new UI(0, 4 * HEIGHT / 5, WIDTH, 140);
		//Starfield
		starfield = new BufferedImage(WIDTH * 3, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		drawStars((Graphics2D)starfield.getGraphics());
		land = new BufferedImage(WIDTH * 3, (int)(HEIGHT * 0.2), BufferedImage.TYPE_INT_RGB);
		drawLand((Graphics2D)land.getGraphics());
		scrollX = WIDTH;
		theState = GameState.MENU;
		lazerKaboomTime = 5000l; //5 seconds till overheat.
		resetGame();
	}

	/**
	 * Draws a starfield over a Graphics2D object. Draws it 3x as wide as the screen, to support smooth scrolling
	 * @param g - The Graphics2D object that holds the screen canvas.
	 */
	public void drawStars(Graphics2D g)
	{
		for(int i = 0; i < WIDTH * 3; i++)
		{
			for(int j = 0; j < HEIGHT; j++)
			{
				if(r.nextInt(512) == 15)
				{
					g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
					g.drawLine(i, j, i, j);
				}

			}
		}
	}
	
	/**
	 * Randomly Generates a land over a graphics2D object. Like drawStars, 3*WIDTH for smooth scrolling
	 * @param g - Land canvas
	 */
	public void drawLand(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		int oldY = r.nextInt(HEIGHT / 5), nextY = r.nextInt(HEIGHT / 5);
		for(int i = 0; i < (WIDTH * 3) - 10; i+= 10)
		{
			g.drawLine(i, oldY, i+10, nextY);
			oldY = nextY;
			nextY = r.nextInt(HEIGHT / 5);
		}
	}
	/**
	 * Draws all the enemies
	 * @param g - Graphics2D object with the screen canvas
	 */
	public void drawEnemies(Graphics2D g)
	{
		ListIterator<Enemy> i = theEnemies.listIterator();
		Enemy e;
		while(i.hasNext())	
		{
			e = i.next();
			e.draw(g);
		}
	}
	
	/**
	 * Spawns the enemies. 7 enemies per wave. 6(?) waves per level. One of each type of enemy.
	 * An enemy spawns every 2500ms (2.5 seconds). URBITES and other boss enemies spawn one at
	 * a time.
	 */
	public void spawnEnemies()
	{
		//Can we spawn one?
		int numHits = level;
		if(numHits > 3)
			numHits = 3;
		if(spawnedEnemies == 8 && theEnemies.size() == 0)
		{
			wave++;
			spawnedEnemies = 0;
			destroyedEnemies = 0;
		}
		if(wave == 2)
		{
			wave = 0;
			spawnedEnemies = 0;
			destroyedEnemies = 0;
			level++;
		}
		//2.5s between enemy spawns
		if(System.currentTimeMillis() - lastEnemyTime > 2500)
		{
			Enemy e;
			if(wave == 0 && spawnedEnemies <= 7)
			{	
				e = new Swooper((int)(WIDTH * 0.75), Swooper.height, SWOOPER_SPEED + level, numHits);
				e.createData();
				((Swooper)e).setFlyingTime(System.currentTimeMillis());
				theEnemies.add(e);
				spawnedEnemies++;
			}
			if(wave == 1 && theEnemies.size() == 0)
			{
				//change to urbite
				e = new Urbite((int)(WIDTH * 0.9), r.nextInt(2*HEIGHT/3) - 20, URBITE_SPEED + level, numHits);
				e.createData();
				theEnemies.add(e);
				spawnedEnemies++;
			}
			
			lastEnemyTime = System.currentTimeMillis();
		}
	}
	
	/**
	 * Moves all the enemies to their next locations.
	 */
	public void moveEnemies()
	{
		ListIterator<Enemy> i = theEnemies.listIterator();
		Enemy e;
		
		while(i.hasNext())
		{
			e = i.next();
			switch(e.getType())
			{
				case SWOOPER:
					if(((Swooper)e).isFlyingIn())
						((Swooper)e).updateRoute(e.getX());
					//2272 because 1.1x = 2500, x = 2272. This provides between 227ms and 2500ms of flying in for the swoopers
					if(System.currentTimeMillis() - ((Swooper)e).getFlyingTime() > ((r.nextDouble() + 0.1) * 2272))
						((Swooper)e).setFlyingIn(false);
				default:
					e.setX(e.getX() - (e.getSpeed() * ellapsed));
					e.moveShip();
					break;
				case URBITE:
					((Urbite) e).trackPlayer(thePlayer.getX(), thePlayer.getY(), ellapsed);
					break;
			}
			e.checkBounds(WIDTH, (int)(3 * HEIGHT / 5));
		}
	}

	/**
	 * This is to trigger a boss to attack the player.
	 */
	public void enemyAttack()
	{
		Enemy e;
		for(int i = 0; i < theEnemies.size(); i++)
		{
			e = theEnemies.get(i);
			switch(e.getType())
			{
				//30px width between shots (vertically)
				case URBITE:
					((Urbite) e).shootLazer(thePlayer.getY(), thePlayer.height);
					if(((Urbite) e).shootingLazer() && System.currentTimeMillis() - ((Urbite) e).lazerShotTime() > 600)
					{
						LazerShot one = new LazerShot(e.getX() + 30, e.getY() - 30, LAZER_SPEED + level);
						LazerShot two = new LazerShot(e.getX() + 30, e.getY(), LAZER_SPEED + level);
						one.createData();
						two.createData();
						theEnemies.add(one);
						theEnemies.add(two);
						((Urbite) e).shotLazer();
					}
					break;
				default:
					break;
			}
			e.checkBounds(WIDTH, (int)(HEIGHT * 0.66));
		}
	}
	
	/**
	 * This checks collisions between the player's laser and the enemies.
	 */
	public void checkLazerCollisions()
	{
		ListIterator<Enemy> i = theEnemies.listIterator();
		Enemy e;
		
		while(i.hasNext())
		{
			e = i.next();
			//180ms before hit register.
			if(e.checkLazerCollision(thePlayer.getLazerY(), thePlayer.isFiringLazer()) 
					&& (System.currentTimeMillis() - lastLazerShot > 180) && e.getX() > thePlayer.getX())
			{
				e.getHit();
				score += e.getPoints();
				lastLazerShot = System.currentTimeMillis();
				if(e.hitsLeft() == 0)
				{
					e.setVisible(false);
					e.die();
					destroyedEnemies++;
				}
			}
			
		}
	}
	
	/**
	 * This checks whether the player has hit an enemy.
	 */
	public void checkEnemyCollisions()
	{
		ListIterator<Enemy> i = theEnemies.listIterator();
		Enemy e;
		
		while(i.hasNext())
		{
			e = i.next();
			if(thePlayer.checkEnemyCollisions(e))
			{
				theState = GameState.DEAD;
			}
			
		}
	}
	
	/**
	 * Cleans up all enemies that have died in the last frame.
	 */
	public void removeDeadEnemies()
	{
		for(int i = 0; i < theEnemies.size(); i++)
		{
			if(theEnemies.get(i).isDead())
			{
				theEnemies.remove(i);
				i--;
			}
		}
	}

	/**
	 * This is the main calculation loop of the game. It moves/spawns enemies,
	 * checks collisions, and does other things that need to be done on a per-frame basis.
	 */

	public void updateScene()
	{
		switch(theState)
		{
		case PLAYING:
			moveEnemies();
			spawnEnemies();
			enemyAttack();
			checkEnemyCollisions();
			checkLazerCollisions();
			removeDeadEnemies();
			thePlayer.useFuel();
			if(thePlayer.getFuel() == 0)
				theState = GameState.DEAD;
		case WAITING:
			thePlayer.checkBounds(WIDTH, (int)(HEIGHT * 0.66));
			thePlayer.moveShip();
			if(thePlayer.checkGroundCollision(groundLevel))
				theState = GameState.DEAD;
			if(thePlayer.checkLazerOverheat(lazerKaboomTime))
			{
				theState = GameState.DEAD;
				thePlayer.fireLazer(false);
			}
			
			break;
		case DEAD:
			if(!thePlayer.isExploding())
				thePlayer.explode(true);
			else
			{
				if(System.currentTimeMillis() - thePlayer.explosionTime() < 3000)
					thePlayer.calculateExplosionVectors();
				else
				{
					lives--;
					theEnemies.removeAll(theEnemies);
					if(lives < 0)
						theState = GameState.AGAIN;
					else
					{
						lastEnemyTime = System.currentTimeMillis();
						spawnedEnemies -= destroyedEnemies;
						thePlayer.createData();
						thePlayer.setFuel(thePlayer.MAX_FUEL);
						thePlayer.moveShipTo(startX, startY);
						theState = GameState.PLAYING;
						thePlayer.explode(false);
					}
				}
			}
			
		case PAUSE:
			lastEnemyTime = System.currentTimeMillis();
			break;
		case AGAIN:
			break;
		}
	}

	/**
	 * KeyListener Methods
	 */

	public void keyPressed(KeyEvent e)
	{
		//ESC Quits
		if(e.getKeyCode() == e.VK_ESCAPE)
			System.exit(0);
		switch(theState)
		{
		case MENU:
			if(e != null)
				theState = GameState.WAITING;
			break;
		case WAITING:
			if(e.getKeyChar() == ' ')
				theState = GameState.PLAYING;
		case PLAYING:
			if(e.getKeyChar() == 'w' || e.getKeyCode() == e.VK_UP)
				thePlayer.setY(thePlayer.getY() - (int)(thePlayer.getSpeed() * ellapsed));
			if(e.getKeyChar() == 's' || e.getKeyCode() == e.VK_DOWN)
				thePlayer.setY(thePlayer.getY() + (int)(thePlayer.getSpeed() * ellapsed));
			if(e.getKeyChar() == 'a' || e.getKeyCode() == e.VK_LEFT)
				thePlayer.setX(thePlayer.getX() - (int)(thePlayer.getSpeed() * ellapsed));
			if(e.getKeyChar() == 'd' || e.getKeyCode() == e.VK_RIGHT)
				thePlayer.setX(thePlayer.getX() + (int)(thePlayer.getSpeed() * ellapsed));
			if(e.getKeyChar() == '1')
				thePlayer.setSpeed(32);
			if(e.getKeyChar() == '2')
				thePlayer.setSpeed(64);
			if(e.getKeyChar() == '3')
				thePlayer.setSpeed(128);
			if(e.getKeyChar() == ' ')
			{
				thePlayer.fireLazer(true);
				lastLazerShot = System.currentTimeMillis();
			}
			if(e.getKeyChar() == 'p')
				theState = GameState.PAUSE;
			break;
		case PAUSE:
			if(e.getKeyChar() == 'p')
				theState = GameState.PLAYING;
			break;
		case AGAIN:
			if(e.getKeyChar() == ' ')
			{
				resetGame();
				theState = GameState.WAITING;
			}
			break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyChar() == ' ')
		{
			thePlayer.fireLazer(false);
			lastLazerShot = 0;
		}
	}

	public void keyTyped(KeyEvent e)
	{}

	/**
	 * Uses a bufferedImage to draw to the panel. Calls other helper methods to draw the
	 * different parts of the screen.
	 */
	public void paintPanel() {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = (Graphics2D) image.getGraphics();

		// Setup the blank canvas
		g2D.setColor(Color.black);
		g2D.fillRect(0, 0, getWidth(), getHeight());
		g2D.setColor(Color.WHITE);
		
		//Draw the starfield. 
		if((int)scrollX == WIDTH * 3)
		{
			drawStars((Graphics2D)starfield.getGraphics());
			scrollX = WIDTH;
		}
		else
		{
			g2D.drawImage(starfield.getSubimage((int)(scrollX - WIDTH), 0, WIDTH, HEIGHT), 0, 0, this);
		}
		
		switch(theState)
		{
			case MENU:
				g2D.drawString(parsec, getCentre(g2D, parsec), (int)(HEIGHT * 0.2));
				g2D.drawString(pressKey, getCentre(g2D, pressKey), (int)(HEIGHT * 0.5));
				g2D.drawString(rights, getCentre(g2D, rights), (int)(HEIGHT * 0.8));
				g2D.drawString(reborn, getCentre(g2D, reborn), (int)(HEIGHT * 0.83));
				break;
			case PAUSE:
				g2D.drawString(pause, getCentre(g2D, pause), (int)(HEIGHT * 0.5));
				scrollX -= 0.33;
			case PLAYING:
				drawEnemies(g2D);
			case WAITING:
				//Draw the land
				if((int)scrollX == WIDTH * 3)
				{
					drawLand((Graphics2D)land.getGraphics());
					scrollX = WIDTH;
				}
				else
				{
					//I want the Land to be 1/5 of the height, + ui height = 0.43 of height used by non-playable area. From the bottom
					//gives us a height of 0.57 to place the land.
					g2D.drawImage(land.getSubimage((int)(scrollX - WIDTH), 0, WIDTH, HEIGHT / 5), 0, (int)(3.01 * HEIGHT / 5), this);
					scrollX += 0.33;
				}
				g2D.setColor(Color.WHITE);
				thePlayer.drawPlayer(g2D);
				ui.drawUI(g2D, score, thePlayer.getFuel(), lives, hs, thePlayer.getSpeed());
				if(theState == GameState.WAITING)
				{
					g2D.drawString("PRESS FIRE TO BEGIN", WIDTH / 2, (int)(HEIGHT * 0.91));
					lastEnemyTime = System.currentTimeMillis();
				}
				//Draw the possible lazer.
				if(thePlayer.isFiringLazer())
					thePlayer.drawLazer(g2D, WIDTH);
				if(System.currentTimeMillis() - thePlayer.getLazerTime() > 3000 && thePlayer.isFiringLazer())
					g2D.drawString("LAZER IS OVERHEATING", WIDTH / 2, (int)(HEIGHT * 0.91));
				break;
			case DEAD:
				thePlayer.drawPlayer(g2D);
				ui.drawUI(g2D, score, thePlayer.getFuel(), lives, hs, thePlayer.getSpeed());
				break;
			case AGAIN:
				ui.drawUI(g2D, score, thePlayer.getFuel(), lives, hs, thePlayer.getSpeed());
				g2D.drawString(again, getCentre(g2D, again), (int)(HEIGHT * 0.5));
		}
		
		Graphics g = strategy.getDrawGraphics();
		g.drawImage(image, 0, 0, this);
		strategy.show();
	}

	/**
	 * Sequencer to organize calls and prove an animation speed.
	 */
	public void run() {
		while (isVisible()) {

			if (lastFrameTime == 0)
		    {
		        lastFrameTime = System.currentTimeMillis();
		    }
			
			long now = System.currentTimeMillis();
		    int elapsedMilliseconds = (int)(now - lastFrameTime);
		    ellapsed = elapsedMilliseconds / 1000.0f;
		    lastFrameTime = now;
			updateScene();
			paintPanel();
			try { 
				Thread.sleep(SPEED);    
			} catch (InterruptedException e) {}
		}

	}

	/**
	 * Instantiates an animation object,a sprite with some meaningful set of points and then calls the run method.
	 */
	public static void main(String[] args) {
		Parsec animation = new Parsec();
		animation.run();
	}

	//Helper method to centre text on the screen.
	private int getCentre(Graphics2D g, String s)
	{
		FontMetrics fm = g.getFontMetrics(new Font(s, Font.PLAIN, 12));

		java.awt.geom.Rectangle2D rect = fm.getStringBounds(s, g);

		int textWidth  = (int)(rect.getWidth());
		int panelWidth = this.getWidth();

		// Center text horizontally and vertically
		return (panelWidth  - textWidth)  / 2;
	}
	
	private void resetGame()
	{
		thePlayer = new Ship(startX, startY, 3);
		thePlayer.createData();
		theEnemies = new ArrayList<Enemy>();
		lives = 4;
		level = 1; //First Level
		wave = 0; //Start at SWOOPERS
		spawnedEnemies = 0;
		hs = score;
		score = 0;
	}

}
