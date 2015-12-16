

public class ComplexNumberTest {
	
  public static void main(String[] args) {
    // Constructor Tests
    ComplexNumber z0 = new ComplexNumber(1.0, 2.0);
    ComplexNumber z1 = new ComplexNumber(0.5, -1.0);
    ComplexNumber z2 = new ComplexNumber();
    ComplexNumber z3 = new ComplexNumber(3);

    // toString
    System.out.println("z0 = " + z0);
    System.out.println("z1 = " + z1);
    System.out.println("z2 = " + z2);
    System.out.println("z3 = " + z3 + "\n");

    // Accessors
    System.out.println("Re(z0) =\t" + z0.getReal());
    System.out.println("Im(z0) =\t" + z0.getImaginary() + "\n");
		
    // Arithmetic
    System.out.println("z0+z1 =\t" + z0.add(z1));
    System.out.println("z0-z1 =\t" + z0.subtract(z1));
    System.out.println("z0*z1 =\t" + z0.multiply(z1));
    System.out.println("z0/z1 =\t" + z0.divide(z1) + "\n");

		// Combinational
		System.out.println("z0+z1^2 =\t" + z0.add(z1.square()));
		System.out.println("z3*z0/z1 =\t" + z3.multiply(z0.divide(z1)) + "\n");

    // Distance
    System.out.println("|z| =\t" + z0.magnitude());
    System.out.println("|z|^2 =\t" + z0.magnitudeSquared() + "\n");
				
    // Miscellaneous
    System.out.println("z0^2 =\t" + z0.square());
    System.out.println("z0bar =\t" + z0.conjugate() + "\n");
  
  }
  

}

