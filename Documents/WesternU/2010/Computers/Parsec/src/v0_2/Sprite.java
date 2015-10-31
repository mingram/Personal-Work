package v0_2;
/**
 * The sprite class is a Point2DList of Point2Ds to enclose a sprite.
 * @author Mike
 *
 */ 
public class Sprite
 {
	 private Point2DList thePoints;
	 
	 /**
	  * Constructs a new, null, sprite.
	  */
	 public Sprite()
	 {
		 thePoints = new Point2DList();
	 }
	 
	 /**
	  * Fills in all the Point2D data.
	  */
	 public void createData()
	 {
		 
		 thePoints.addPoint(new Point2D(-1,-1));
		 thePoints.addPoint(new Point2D(0, 2));
		 thePoints.addPoint(new Point2D(1, -1));
		 thePoints.addPoint(new Point2D(0, 0));
		// thePoints.createUnitSquare();
	 }
	 
	 /**
	  * Returns a Point2DList of the Point2Ds enclosing the sprite.
	  * @return a Point2DList of the Points.
	  */
	 public Point2DList getData()
	 {
		 return thePoints;
	 }
	 
	 /**
	  * Applies a Transform2D on the sprite.
	  * @param t the desired Transform2D
	  */
	 public void transform(Transform2D t)
	 {
		 thePoints.applyTransform(t);
	 }
 }