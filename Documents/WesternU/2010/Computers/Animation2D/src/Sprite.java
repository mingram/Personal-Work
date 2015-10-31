/**
 * <b>Purpose:</b> Serves as a wrapper class for a <code>Point2DList</code> object. <p>
 * <b>Date:</b> 2010 02 19 <p>
 * @author M. Ingram
 *
 */

public class Sprite {
	
	private Point2DList pl = new Point2DList();
	
	/**
	 * Constructs an empty Point2DList objects to <code>null</code>.
	 */
	
	public Sprite(){
	
	}
	
	/**
	 * Calls <code>Point2DList</code>'s <code>createPolygon</code> method to create a polygon from the local <code>Point2DList</code>.
	 */
	
	public void createData(){
		pl.createPolygon(4, 40);
	}
	
	/**
	 * Accessor method that returns the current <code>Point2DList</code>.
	 * @return the current <code>Point2DList</code>.
	 */
	public Point2DList getData(){
		return pl;
	}
	
	
	/**
	 * Applies the transform passed by the parameter.
	 * @param t Transform2D object.
	 */
	public void transform(Transform2D t){
		pl.applyTransform(t);
	
	}

}
