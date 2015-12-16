import java.text.DecimalFormat;

 /**
  	The <b>Transform2DTest</b> driver class exercises the <b>Transform2D</b> and <b>Point2DList</b> classes.
 * @author C. D'Arcy
 * @version 1.06.a
 */
 
 
public class Transform2DTest {
	
  public static void main(String[] args) {
    // construction options....
    Transform2D t1 = new Transform2D();
    Transform2D t2 = new Transform2D(Transform2D.Type.IDENTITY);
    Transform2D t3 = new Transform2D(Transform2D.Type.REFX); // creates a reflection in the x axis...
    Transform2D t4 = new Transform2D(Transform2D.Type.REFY); // creates a reflection in the y axis...
    Transform2D t5 = new Transform2D(Transform2D.Type.REFORIGIN); // creates a reflection in the origin...
    Transform2D t6 = new Transform2D(t3);
    Transform2D t7 = new Transform2D(new Matrix2D());
	
    double[] a = { 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0};
    Matrix2D m1 = new Matrix2D(a);
    Transform2D t8 = new Transform2D(m1);

    // take a look...
    System.out.println("Before...");
    System.out.println("t1(ZERO): " + t1);
    System.out.println("t2(IDENTITY): " + t2);
    System.out.println("t3(REFX): " + t3);
    System.out.println("t4(REFY): " + t4);
    System.out.println("t5(REFORIGIN): " + t5);
    System.out.println("t6(t3): " + t6);
    System.out.println("t7(new): " + t7);
    System.out.println("t8(from array): " + t8);
		
    // some further transformations...
    Point2D p = new Point2D(4.0, 3.0);

    t6.setTranslation(p); // creates a translation transformation...
    t7.setScale(2.0); // creates a scale (dilation) transformation...
    t8.setRotation(Math.PI / 4); // creates a rotation transformation around the origin...
		
    // take another look...
    System.out.println("\nAfter...");
    System.out.println("t6(translation): " + t6);
    System.out.println("t7(scale): " + t7);
    System.out.println("t8(rotation 45 degrees): " + t8);

    // Showcase the multiply (mul) method...
    Transform2D t9 = t8.mul(t6);

    System.out.println("t9(translation followed by rotation 45): " + t9);
		
    // prepare for a locus of points
    Point2DList quad = new Point2DList();
	
    // Example 1. Unit Square________________
    quad.createUnitSquare();
    // publish BEFORE transforming...
    System.out.println("BEFORE:\n" + quad);
    // transform the locus...
    quad.applyTransform(t9);
    // publish AFTER transforming...
    System.out.println("AFTER:\n" + quad);

    // Example 2. Regular n-gon______________
    Point2DList ngon = new Point2DList();

    ngon.createPolygon(5, 1.0);
    // publish BEFORE transforming...
    System.out.println("BEFORE:\n" + ngon);
    // transform the locus (Reflect in y-axis) ...
    ngon.applyTransform(t4);
    // publish AFTER transforming...
    System.out.println("AFTER:\n" + ngon);

  }
}