package v0_2;
import java.awt.*;
import java.text.*;

/**
 * User interface for Parsec.
 * @author Mike Mallin
 *
 */
public class UI
{
	private int x, y, width, height;
	
	/**
	 * Creates a new user interface.
	 * @param x - Upper left x coordinate (onscreen)
	 * @param y - Upper left y coordinate (onscreen)
	 * @param w - Width of the UI element
	 * @param h - Height of the UI element
	 */
	public UI(int x, int y, int w, int h)
	{
		setX(x);
		setY(y);
		setWidth(w);
		setHeight(h);
	}
	
	/**
	 * Draws the Parsec UI.
	 * @param g - The Graphics2D canvas
	 * @param score - Current player score
	 * @param fuel - current player fuel (0 - 10000)
	 * @param lives - Current numebr of player lives
	 * @param high - Current high score
	 * @param lift - Current speed of the ship
	 */
	public void drawUI(Graphics2D g, int score, float fuel, int lives, int high, int lift)
	{
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		g.setColor(Color.WHITE);
		//Draw the lives
		if(lives > 4)
			lives = 4;
		for(int i = 0; i < lives; i++)
		{
			Ship s = new Ship(15 * i * 4 + 20, y + (int)(getHeight() * 0.65), 4);
			s.createData();
			s.drawPlayer(g);
		}
		//Draw the fuel bar.
		g.drawString("FUEL", x + 10, y + 40);
		g.drawRect(x + 50, y + 20, (int)(width * 0.9), height/6);
		g.setColor(Color.ORANGE);
		g.fillRect(x + 51, y + 21, (int)(width * 0.9 * (fuel/10000))-1, (height/6) - 1);
		g.setColor(Color.WHITE);
		//Add the texts.
		DecimalFormat d = new DecimalFormat("00000000");
		g.drawString("LIFT: " + lift, x + (int)(width * 0.45), y + (int)(height * 0.7) - 15);
		g.drawString("SCORE: " + d.format(score), x + (int)(width * 0.6), y + (int)(height * 0.7) - 15);
		g.drawString("HIGH: " + d.format(high), x + (int)(width * 0.8), y + (int)(height * 0.7) - 15);
	}
	
	private void setX(int x1)
	{ x = x1; }
	
	private void setY(int y1)
	{ y = y1; }
	
	private void setWidth(int w)
	{ width = w; }
	
	private void setHeight(int h)
	{ height = h; }
	
	public int getWidth()
	{ return width; }
	
	public int getHeight()
	{ return height; }
}
