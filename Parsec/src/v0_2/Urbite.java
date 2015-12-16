package v0_2;
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class Urbite extends Enemy
{
	private int xspeed, yspeed;
	private long lastLazerTime;
	
	private boolean shootingLazer, wasPlayerAbove, lastPlayerPosn;
	
	public Urbite(int x, int y, int speed, int hits)
	{
		super(x, y, 14*3, 7*3, speed, hits, 100, 3, enemyList.URBITE);
		setXSpeed(speed);
		setYSpeed(speed);
		shootingLazer = false;
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
					g.setColor(Color.YELLOW);
					break;
				case 2:
					g.setColor(Color.YELLOW);
					break;
				case 3:
					g.setColor(Color.YELLOW);
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
	
	public void trackPlayer(float playerX, float playerY, float time)
	{
		int px = (int)playerX, py = (int)playerY;
		
		if(py > (int)getY())
		{
			lastPlayerPosn = wasPlayerAbove;
			setY(getY() + (getYSpeed() * time));
			wasPlayerAbove = true;
		}
		
		else if(py < (int)getY())
		{
			lastPlayerPosn = wasPlayerAbove;
			setY(getY() - (getYSpeed() * time));
			wasPlayerAbove = false;
		}
		
		if((px < (int)getX()) && !(wasPlayerAbove == lastPlayerPosn))
		{
			setX(getX() - (getXSpeed() * time));
		}
		
		else if(px >= (int)getX())
			setX(playerX);
		moveShip();
		
	}
	
	public long lazerShotTime()
	{ return lastLazerTime; }
	
	public void shootLazer(float playerY, int w)
	{
		if(!(getY() < playerY || getY() > playerY + w) && !shootingLazer)
		{
			shootingLazer = true;
			lastLazerTime = System.currentTimeMillis();
		}
	}
	
	public void setXSpeed(int s)
	{ xspeed = s; }
	
	public void setYSpeed(int s)
	{ yspeed = s; }
	
	public int getXSpeed()
	{ return xspeed; }
	
	public int getYSpeed()
	{ return yspeed; }
	
	public void shotLazer()
	{ shootingLazer = false; }
	
	public boolean shootingLazer()
	{ return shootingLazer; }
}