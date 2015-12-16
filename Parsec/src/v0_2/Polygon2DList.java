package v0_2;
import java.util.*;
import java.text.*;


/**
 * Maintains an list of Point2DList objects intended to be manipulated by Transform2D objects
 * @author Mike Mallin
 */
public class Polygon2DList {
	
  ArrayList<Point2DList> polys = new ArrayList<Point2DList>();
  DecimalFormat df = new DecimalFormat("0.00");
	
  /**
   * Only constructor leaves the instance field as an empty ArrayList of points
   */
  public Polygon2DList() {}
	
  /**
   * Modifier methods clears the ArrayList and adds the Point2DList object with points (0,0), (1,0), (1,1), and (0,1)
   */ 
  public void square() {
    polys.add(new Point2DList());
    polys.get(0).createUnitSquare();
  } 	 

  /**
   * Modifier method appends a Point2DList object onto the end of the ArrayList of Point2DList objects
   */
  public void addPoly(Point2DList p) {
    polys.add(p);
	
  }

  /**
   * Modifier method applies the Transform2D matrix supplied by the parameter to each of the Point2DList objects matained in the ArrayList. The 
   * method should not rewrite the code to undertake matrix multiplication, but instead 
   * make use of the public mul method available in the Matrix2D class
   * @param transform the transform to be applied
   */
  public void applyTransform(Transform2D transform) {
		
	for(int h = 0; h < polys.size(); h++)
	{
	    double[][] pts = new double[3][polys.get(h).getPoints().size()];
	
	    for (int i = 0; i < polys.get(h).getPoints().size(); i++) {
	      Point2D p = polys.get(h).getPoints().get(i);
	
	      pts[0][i] = p.getCoordinate(0);
	      pts[1][i] = p.getCoordinate(1);
	      pts[2][i] = 1;
	    }
	    pts = transform.getTransform2D().mul(pts);
	    for (int i = 0; i < polys.get(h).getPoints().size(); i++) {
	    	polys.get(h).getPoints().set(i, new Point2D(pts[0][i], pts[1][i]));
	    }
	}
  }
	
  private double[][] multiply(double[][] m, double[][] p) {
    double[][] pts = new double[3][p[0].length];

    for (int r = 0; r < m.length; r++) {
      for (int c = 0; c < p[0].length; c++) {
        pts[r][c] = 0.0;
        for (int k = 0; k < m[0].length; k++) {
          pts[r][c] += m[r][k] * p[k][c];
        }
      }
    }
    return pts;
  }

  /**
   * Accessor method called to retrieve the ArrayList&lt;Point2D&gt; containing the data
   * @return the ArrayList&lt;Point2D&gt; containing the data
   */
  public ArrayList<Point2DList> getPolys() {
    return polys;
  }

  /**
   * Returns a String developed, in part, by calling upon Point2D's toString() method
   */
  public String toString() {
    String output = "";

    for (int i = 0; i < polys.size(); i++) {
      Point2DList mp = polys.get(i);
      for(int j = 0; j < mp.getPoints().size(); j++)
      {
	      output += "Point2D " + i + ":\t(" + df.format(mp.getPoints().get(j).getCoordinate(0)) + ",\t"
	          + df.format(mp.getPoints().get(j).getCoordinate(1)) + ")\n";
      }
																
    }
			
    return output;	
  }
	
}
