package v0_2;
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.*;

/**
 * This ship class is what the player uses as their ship.
 * @author Mike Mallin
 * @date 2010
 */
public class Ship
{
	public static final int MAX_FUEL = 10000;
	public static int width, height;
	
	public enum shipStates {FLYING, DEAD};
	
	private int currentX, currentY;
	private float fuel;
	private static int scale, speed;
	
	private long lazerTime, explosionTime;
	
	private boolean isFiringLazer, exploding;
	
	private Polygon2DList theSprite;
	
	Random r;
	
	/**
	 * Creates a new ship. Lower left of the ship are the base coordinates
	 * @param x - x position of the ship (int)
	 * @param y - y position of the ship (int)
	 * @param s - movement speed of the ship (int)
	 */
	public Ship(int x, int y, int s)
	{
		setX(x);
		setY(y);
		setSpeed(128);
		scale = s;
		r = new Random();
		fuel = MAX_FUEL;
		width = scale * 15;
		height = scale * 7;
	}
	
	/**
	 * Generates the polygon data for the ship.
	 */
	public void createData()
	{
		theSprite = new Polygon2DList();
		
		Point2DList p1 = new Point2DList();
		p1.addPoint(new Point2D(2.0, 7.0));
		p1.addPoint(new Point2D(6.0, 7.0));
		p1.addPoint(new Point2D(8.0, 5.0));
		p1.addPoint(new Point2D(4.0, 5.0));
		p1.addPoint(new Point2D(2.0, 7.0));
		
		Point2DList p2 = new Point2DList();
		p2.addPoint(new Point2D(0.0, 5.0));
		p2.addPoint(new Point2D(12.0, 5.0));
		p2.addPoint(new Point2D(15.0, 2.0));
		p2.addPoint(new Point2D(3.0, 2.0));
		p2.addPoint(new Point2D(0.0, 5.0));
		
		Point2DList p3 = new Point2DList();
		p3.addPoint(new Point2D(3.0, 2.0));
		p3.addPoint(new Point2D(9.0, 2.0));
		p3.addPoint(new Point2D(6.0, 0.0));
		p3.addPoint(new Point2D(0.0, 0.0));
		p3.addPoint(new Point2D(3.0, 2.0));
		
		
		theSprite.addPoly(p1);
		theSprite.addPoly(p2);
		theSprite.addPoly(p3);
		
		Transform2D invertShip = new Transform2D(Transform2D.Type.REFX);
		this.transform(invertShip);
		
		Transform2D shipScale = new Transform2D();
		shipScale.setScale(scale);
		this.transform(shipScale);
		
		//Moves to origin.
		Transform2D moveShip = new Transform2D();
		moveShip.setTranslation(new Point2D(0.0, 7.0 * scale));
		this.transform(moveShip);
		
		moveShipTo(getX(), getY());
	}
	
	/**
	 * Draws the ship of the provided canvas.
	 * @param g - Graphics2D of the drawing canvas.
	 */
	public void drawPlayer(Graphics2D g)
	{	
		ArrayList<Point2DList> p = this.getData().getPolys();
	    ListIterator<Point2DList> thePolyIter = p.listIterator();
	    ListIterator<Point2D> theIter;
	    Polygon poly = new Polygon();
	    
	    Point2DList p2dl;
	    Point2D pt;
	    
	    //Translate them to screen coordinates (pixels).
	    while(thePolyIter.hasNext())
	    {
	    	p2dl = thePolyIter.next();
	    	theIter = p2dl.getPoints().listIterator();
	    	while(theIter.hasNext())
	    	{
		    	pt = theIter.next();
		    	poly.addPoint((int)pt.getCoordinate(0), (int)pt.getCoordinate(1));
	    	}
	    	g.drawPolygon(poly);
	    	poly = new Polygon();
	    }
	}
	
	/**
	 * Moves the ship to the provided coordinates.
	 * @param x
	 * @param y
	 */
	public void moveShipTo(int x, int y)
	{
		//1. Calc the movement vector to that point.
		//2. Transform the ship.
		Point2D newPt = new Point2D(x, y);
		//Lower left point on the ship.
		Point2D oldPt = theSprite.getPolys().get(2).getPoints().get(3);
		Point2D vector = newPt.sub(oldPt, newPt);

		Transform2D v = new Transform2D();
		v.setTranslation(vector);
		this.transform(v);
		setX(x);
		setY(y);
	}
	
	public void calculateExplosionVectors()
	{
		ListIterator<Point2DList> i = theSprite.getPolys().listIterator();
		Point2DList ptlist;
		
		while(i.hasNext())
		{
			ptlist = i.next();
			
			int prevX = getX(), prevY = getY();
			
			Transform2D backOrig = new Transform2D();
			backOrig.setTranslation(new Point2D(-prevX, -prevY));
			ptlist.applyTransform(backOrig);
			
			Transform2D rotation = new Transform2D();
			rotation.setRotation(r.nextDouble());
			ptlist.applyTransform(rotation);
			
			Transform2D backOut = new Transform2D();
			backOut.setTranslation(new Point2D(prevX, prevY));
			ptlist.applyTransform(backOut);
			
		}
	}
	
	/**
	 * Checks whether the ship has collided with the specified enemy. Done using bounding boxes, not line intersection.
	 * Not pixel perfect... yet...
	 * @param e - the Enemy
	 * @return whether they have collided or not
	 */
	public boolean checkEnemyCollisions(Enemy e)
	{
		if(e.getX() <= getX() + getWidth()
				&& e.getX() + e.getWidth() > getX()
				&& e.getY() <= getY()
				&& e.getY() > getY() - getHeight())
			return true;
		return false;
	}
	
	/**
	 * Draws the lazer of the ship
	 * @param g
	 * @param width
	 */
	public void drawLazer(Graphics2D g, int width)
	{
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.drawLine((int)(getX() + (14 * scale)), (int)(getY() - 6), width, (int)(getY() - 6));
	} 
	
	/**
	 * Moves the ship (used per frame when the Parsec class updates the coordinates)
	 */
	public void moveShip()
	{
		moveShipTo(getX(), getY());
	}
	
	/**
	 * Uses a unit of fuel
	 */
	public void useFuel()
	{ fuel -= 1; }
	
	/**
	 * Checks the ship with respect to the provided screen bounds.
	 * @param width
	 * @param height
	 */
	public void checkBounds(int width, int height)
	{
		if(getX() > width / 2)
			setX(width / 2);
		if(getX() < 0)
			setX(0);
		if(getY() - getHeight() < 0)
			setY(getHeight());
		if(getY() > height)
			setY(height - getHeight());
	}
	
	/**
	 * Checks whether the ship has collided with the ground. **CURRENTLY OUT OF ORDER
	 * AS IT ONLY CHECKS HEIGHT, NOT LANDSCAPE**
	 * @param level
	 * @return
	 */
	public boolean checkGroundCollision(int level)
	{
		if(getY() > level)
			return true;
		return false;
	}
	
	/**
	 * Checks whether the lazer has overheated (current time - ship's lazer time)
	 * @param ktime
	 * @return
	 */
	public boolean checkLazerOverheat(long ktime)
	{
		if(System.currentTimeMillis() - getLazerTime() > ktime && isFiringLazer())
			return true;
		return false;
	}
	
	/**
	 * Whether to fire the lazer or not
	 * @param o - true to fire
	 */
	public void fireLazer(boolean o)
	{ 
		if(isFiringLazer != o && isFiringLazer == false)
			setLazerTime(System.currentTimeMillis());
		if(isFiringLazer != o && isFiringLazer == true)
			setLazerTime(0l);
		isFiringLazer = o; 
	}
	
	/**
	 * Is the ship currently firing?
	 * @return
	 */
	public boolean isFiringLazer()
	{ return isFiringLazer; }

	/**
	 * Tells the ship to explode.
	 * @param b
	 */
	public void explode(boolean b)
	{ 
		exploding = b;
		explosionTime = System.currentTimeMillis();
	}
	
	/**
	 * Returns the time that the ship exploded
	 * @return
	 */
	public long explosionTime()
	{ return explosionTime; }
	
	/**
	 * Is the ship currently exploding?
	 * @return
	 */
	public boolean isExploding()
	{ return exploding; }
	
	/**
	 * Returns the x position of the ship.
	 * @return
	 */
	public int getX()
	{ return currentX; }
	
	/**
	 * Returns the y position of the ship.
	 * @return
	 */
	public int getY()
	{ return currentY; }
	
	/**
	 * Returns the y positions of the ship's lazer
	 * @return
	 */
	public int getLazerY()
	{ return getY() - 6; }
	
	/**
	 * Returns the current speed of the ship.
	 * @return
	 */
	public int getSpeed()
	{ return speed; }
	
	/**
	 * Returns the ship's scale
	 * @return
	 */
	public int getScale()
	{ return scale; }
	
	/**
	 * Returns the time when the lazer was started.
	 * @return
	 */
	public long getLazerTime()
	{ return lazerTime; }
	
	/**
	 * Returns the amount of fuel left.
	 * @return
	 */
	public float getFuel()
	{ return fuel; }
	
	/**
	 * Returns the width of the ship (in pixels)
	 * @return
	 */
	public int getWidth()
	{ return width; }
	
	/**
	 * Returns the height of the ship (in pixels)
	 * @return
	 */
	public int getHeight()
	{ return height; }
	
	/**
	 * Sets the ship's new x coordinate.
	 * @param x
	 */
	public void setX(int x)
	{ currentX = x; }
	
	/**
	 * Sets the ship's new y coordinate.
	 * @param y
	 */
	public void setY(int y)
	{ currentY = y; }
	
	/**
	 * Sets the ship's speed.
	 * @param s
	 */
	public void setSpeed(int s)
	{ speed = s; }
	
	/**
	 * Sets when the lazer was first fired.
	 * @param t
	 */
	public void setLazerTime(long t)
	{ lazerTime = t; }
	
	/**
	 * Sets the amount of fuel in the ship.
	 * @param f
	 */
	public void setFuel(int f)
	{ fuel = f; }
	
	/**
	  * Returns a Polygon2DList of the polygons forming the ship.
	  * @return a Polygon2DList of the polygons.
	  */
	 public Polygon2DList getData()
	 {
		 return theSprite;
	 }
	
	/**
	  * Applies a Transform2D on the ship.
	  * @param t the desired Transform2D
	  */
	 public void transform(Transform2D t)
	 {
		 theSprite.applyTransform(t);
	 }
	
	
}
