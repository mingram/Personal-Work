package v0_2;
import java.util.*;
import java.text.*;


/**
 * Maintains an open locus of Point2D objects intended to be manipulated by Transform2D objects
 * @author C. D'Arcy
 */
public class Point2DList {
	
  ArrayList<Point2D> points = new ArrayList<Point2D>();
  DecimalFormat df = new DecimalFormat("0.00");
	
  /**
   * Only constructor leaves the instance field as an empty ArrayList of points
   */
  public Point2DList() {}
	
  /**
   * Modifier methods clears the ArrayList and adds the Point2D objects (0,0), (1,0), (1,1), and (0,1)
   */ 
  public void createUnitSquare() {
    points = new ArrayList<Point2D>();
    addPoint(new Point2D(0.0, 0.0));
    addPoint(new Point2D(1.0, 0.0));
    addPoint(new Point2D(1.0, 1.0));
    addPoint(new Point2D(0.0, 1.0));
  } 	 

  /**
   * Modifier method appends a Point2D object onto the end of the ArrayList of Point2D objects
   */
  public void addPoint(Point2D p) {
    points.add(p);
	
  }

  /**
   * Modifier methods clears the ArrayList and adds the Point2D objects representing 
   * the vertices of a regular <i>n</i>-gon of 'radius' <i>r</i> centered at the origin.
   * @param n the number of vertices (sides) of the regular polygon
   * @param r the 'radius' of the polygon
   */ 
  public void createPolygon(int n, double r) {
    points = new ArrayList<Point2D>();
    double delta = 2 * Math.PI / n;
    double a = 0.0;

    for (int i = 0; i < n; i++) {
      addPoint(new Point2D(r * Math.cos(a), r * Math.sin(a)));
      a += delta;
    }
		
  }

  /**
   * Modifier method applies the Transform2D matrix supplied by the parameter to each of the Point2D objects matained in the ArrayList. The 
   * method should not rewrite the code to undertake matrix multiplication, but instead 
   * make use of the public mul method available in the Matrix2D class
   * @param transform the transform to be applied
   */
  public void applyTransform(Transform2D transform) {
		
    double[][] pts = new double[3][points.size()];

    for (int i = 0; i < points.size(); i++) {
      Point2D p = points.get(i);

      pts[0][i] = p.getCoordinate(0);
      pts[1][i] = p.getCoordinate(1);
      pts[2][i] = 1;
    }
    pts = transform.getTransform2D().mul(pts);
    for (int i = 0; i < points.size(); i++) {
      points.set(i, new Point2D(pts[0][i], pts[1][i]));
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
  public ArrayList<Point2D> getPoints() {
    return points;
  }

  /**
   * Returns a String developed, in part, by calling upon Point2D's toString() method
   */
  public String toString() {
    String output = "";

    for (int i = 0; i < points.size(); i++) {
      Point2D mp = points.get(i);

      output += "Point2D " + i + ":\t(" + df.format(mp.getCoordinate(0)) + ",\t"
          + df.format(mp.getCoordinate(1)) + ")\n";
																
    }
			
    return output;	
  }
	
}
