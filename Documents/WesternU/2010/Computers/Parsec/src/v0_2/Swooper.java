package v0_2;
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Models a SWOOPER enemy from Parsec. Extends from Enemy.java
 * @author mikemallin
 *
 */
public class Swooper extends Enemy
{
	private boolean flyingIn;
	
	private long flyingTime;
	
	public static int height = 21;
	
	public Swooper(int x, int y, int speed, int hits)
	{
		super(x, y, 14*3, 7*3, speed, hits, 100, 3, enemyList.SWOOPER);
		flyingIn = true;
	}
	
	public void createData()
	{
		theSprite = new Polygon2DList();
		
		Point2DList p1 = new Point2DList();
		p1.addPoint(new Point2D(0.0, 2.0));
		p1.addPoint(new Point2D(3.0, 3.0));
		p1.addPoint(new Point2D(8.0, 3.0));
		p1.addPoint(new Point2D(5.0, 2.0));
		p1.addPoint(new Point2D(0.0, 2.0));
		
		Point2DList p2 = new Point2DList();
		p2.addPoint(new Point2D(6.0, 3.0));
		p2.addPoint(new Point2D(9.0, 7.0));
		p2.addPoint(new Point2D(14.0, 7.0));
		p2.addPoint(new Point2D(11.0, 4.0));
		p2.addPoint(new Point2D(6.0, 3.0));
		
		Point2DList p3 = new Point2DList();
		p3.addPoint(new Point2D(8.0, 3.0));
		p3.addPoint(new Point2D(9.0, 4.0));
		p3.addPoint(new Point2D(14.0, 4.0));
		p3.addPoint(new Point2D(10.0, 0.0));
		p3.addPoint(new Point2D(4.0, 0.0));
		p3.addPoint(new Point2D(6.0, 2.0));
		
		theSprite.addPoly(p1);
		theSprite.addPoly(p2);
		theSprite.addPoly(p3);
		
		Transform2D invertShip = new Transform2D(Transform2D.Type.REFX);
		this.transform(invertShip);
		
		Transform2D shipScale = new Transform2D();
		shipScale.setScale(getScale());
		this.transform(shipScale);
		
		//Moves to origin.
		Transform2D moveShip = new Transform2D();
		moveShip.setTranslation(new Point2D(0.0, 7.0 * getScale()));
		this.transform(moveShip);
		
		moveShipTo(getX(), getY());
	}
	
	public void draw(Graphics2D g)
	{
		if(getVisible())
		{
			switch(hitsLeft())
			{
				case 1:
					g.setColor(Color.WHITE);
					break;
				case 2:
					g.setColor(Color.GRAY);
					break;
				case 3:
					g.setColor(Color.BLUE);
					break;
					
			}
			
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
	}
	
	public void updateRoute(float curX)
	{
		setY((10000.0f/(curX - 500)));
	}
	
	public void setFlyingIn(boolean b)
	{ flyingIn = b; }
	
	public void setFlyingTime(long l)
	{ flyingTime = l; }
	
	public long getFlyingTime()
	{ return flyingTime; }
	
	public boolean isFlyingIn()
	{ return flyingIn; }
	
	public String toString()
	{
		return "Swooper - X: " + getX() + " Y: " + getY();
	}
}