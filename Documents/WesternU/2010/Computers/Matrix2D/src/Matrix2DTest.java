/**
 * Driver to test Matrix2D
 * @author C. D'Arcy
 *
 */
public class Matrix2DTest {

  public static void main(String[] args) {

    // --------INSTANTIATIONS-----------------------------
    // the ZERO matrix
    Matrix2D m1 = new Matrix2D();

    System.out.println("m1:" + m1);

    // the IDENTITY matrix
    double[][] v2 = { { 1, 0, 0}, { 0, 1, 0}, { 0, 0, 1}};
    Matrix2D m2 = new Matrix2D(v2);

    System.out.println("m2:" + m2);

    // from an array...
    double[] v3 = { -1, 0, 0, 0, -1, 0, 0, 0, 1};
    Matrix2D m3 = new Matrix2D(v3);

    System.out.println("m3:" + m3);

    // from nine distinct elements...
    Matrix2D m4 = new Matrix2D(0, 2, 3, 3, 2, 0, 0, 0, 0);

    System.out.println("m4:" + m4);
		
    // clone another matrix...
    Matrix2D m5 = new Matrix2D(m4);

    System.out.println("m5:" + m5);

    // exercise the methods...
    Matrix2D r1 = m1.add(m3);

    System.out.println("r1:" + r1);

    Matrix2D r2 = m4.sub(m4);

    System.out.println("r2:" + r2);

    Matrix2D r3 = m3.mul(m4);

    System.out.println("r3:" + r3);

  }
}