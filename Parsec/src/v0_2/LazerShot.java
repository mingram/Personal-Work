package v0_2;
import java.awt.*;

public class LazerShot extends Enemy
{
	public LazerShot(int x, int y, int speed)
	{
		super(x, y, 10, 5, speed, 1000, 100, 3, enemyList.LAZER);
	}
	
	public void createData()
	{
theSprite = new Polygon2DList();
		
		Point2DList p1 = new Point2DList();
		p1.addPoint(new Point2D(0.0, 0.0));
		p1.addPoint(new Point2D(0.0, 2.0));
		p1.addPoint(new Point2D(10.0, 2.0));
		p1.addPoint(new Point2D(10.0, 0.0));
		p1.addPoint(new Point2D(0.0, 0.0));
		
		theSprite.addPoly(p1);
		
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
		g.setColor(Color.YELLOW);
		g.fillRect((int)getX(), (int)getY(), 30, 5);
	}
	
	public void checkBounds(int width, int height)
	{
		//Don't need to worry about going up/down or coming back around.
		if(getX() < 0)
			die();
	}
}