package v0_2;
import java.awt.*;

/**
 * Abstract class of a Parsec enemy.
 * All enemies and enemy projectiles are based off of this
 * so I can iterate a whole lot of these and make things easy for me.
 * @author Mike Mallin
 *
 */
public abstract class Enemy
{
	private int speed, hits, points, scale, width, height, x, y;
	
	private enemyList type;
	
	public boolean visible;
	private boolean dead;
	
	protected Polygon2DList theSprite;
	
	public enum enemyList {SWOOPER, URBITE, LTF, BYNITE, UFO, ASTEROID, LAZER};
	
	/**
	 * Creates a new enemy (coordinates are bottom left centred)
	 * @param x 
	 * @param y
	 * @param w - the enemy's width
	 * @param h - the enemy's height
	 * @param speed - the enemy's speed
	 * @param hits - number of hits it can take
	 * @param points - points per hit
	 * @param scale - scale of the object
	 * @param type - the type of enemy.
	 */
	public Enemy(int x, int y, int w, int h, int speed, int hits, int points, int scale, enemyList type)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.speed = speed;
		this.hits = hits;
		this.points = points;
		this.scale = scale;
		this.visible = true;
		this.type = type;
		dead = false;
	}
	
	abstract void draw(Graphics2D g);
	
	abstract void createData();
	
	/**
	 * Returns the current x coordinate
	 * @return
	 */
	public int getX()
	{ return x; }
	
	/**
	 * Returns the current y coordinate
	 * @return
	 */
	public int getY()
	{ return y; }
	
	/**
	 * Returns the width of an enemy (# of pixels)
	 * @return
	 */
	public int getWidth()
	{ return width; }
	
	/**
	 * Returns the height of an enemy (pixels)
	 * @return
	 */
	public int getHeight()
	{ return height; }
	/**
	 * Sets the x coordinate
	 * @param x1
	 */
	public void setX(float x1)
	{ x = (int) x1; }
	
	/**
	 * Sets the y coordinate
	 * @param y1
	 */
	public void setY(float y1)
	{ y = (int) y1; }
	
	/**
	 * Sets whether the enemy is visible or not.
	 * @param v
	 */
	public void setVisible(boolean v)
	{ visible = v; }
	
	/**
	 * Returns whether the enemy is visible or not.
	 * @return
	 */
	public boolean getVisible()
	{ return visible; }
	
	/**
	 * Return the speed of the enemy.
	 * @return
	 */
	public int getSpeed()
	{ return speed; }
	
	/**
	 * Returns the enemy's type
	 * @return
	 */
	public enemyList getType()
	{ return type; }
	
	/**
	 * Returns the number of hits left.
	 * @return
	 */
	public int hitsLeft()
	{ return hits; }
	
	/**
	 * Called if the enemy is hit by the player's lazer.
	 */
	public void getHit()
	{ 	
		hits--;
		if(hits < 0)
			hits = 0;
	}
	
	/**
	 * Tells the enemy to die.
	 */
	public void die()
	{ dead = true; }
	
	/**
	 * Returns whether the enemy is dead or not.
	 * @return
	 */
	public boolean isDead()
	{ return dead; }
	
	/**
	 * Returns the number of points per hit.
	 * @return
	 */
	public int getPoints()
	{ return points; }
	
	/**
	 * Returns its scale.
	 * @return
	 */
	public int getScale()
	{ return scale; }
	
	/**
	 * Returns a Polygon2DList of the polygon data.
	 * @return
	 */
	public Polygon2DList getData()
	{ return theSprite; }
	
	/**
	 * Applies a Transform2D object to the enemy.
	 * @param t
	 */
	public void transform(Transform2D t)
	{ theSprite.applyTransform(t); }
	
	/**
	 * Updates the current location (per frame like the one in Ship.java)
	 */
	public void moveShip()
	{
		moveShipTo(getX(), getY());
	}
	
	/**
	 * Also like the one in Ship.java
	 * @param x - the x coordinate to move to
	 * @param y - the y coordinate to move to
	 */
	public void moveShipTo(float x, float y)
	{
		//1. Calc the movement vector to that point.
		//2. Transform the ship.
		Point2D newPt = new Point2D(x, y);
		Point2D oldPt;
		//Lower left point on the ship.
		if(this.type == enemyList.LAZER)
			oldPt = theSprite.getPolys().get(0).getPoints().get(0);
		else
			oldPt = theSprite.getPolys().get(2).getPoints().get(3);
		Point2D vector = newPt.sub(oldPt, newPt);

		Transform2D v = new Transform2D();
		v.setTranslation(vector);
		this.transform(v);
		setX(x);
		setY(y);
	}
	
	/**
	 * Checks whether the player's lazer has hit the enemy.
	 * @param y
	 * @param lazer
	 * @return
	 */
	public boolean checkLazerCollision(int y, boolean lazer)
	{
		return (y < getY()) && (y > getY() - getHeight()) && lazer;
	}
	
	/**
	 * Checks whether in allowable range, and adjusts accordingly.
	 * @param width
	 * @param height
	 */
	public void checkBounds(int width, int height)
	{
		if(getX() < 0)
			setX(width);
		if(getX() > width)
			setX(width);
		if(getY() < this.height)
			setY(this.height + 50);
		if(getY() > height)
			setY(height - this.height);
	}
}