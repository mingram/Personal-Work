

public class OrbitTest {
	
	public static void main (String[] args) {
		ComplexNumber c = new ComplexNumber(0.5,0.5);
		Orbit orbit = new Orbit(c);
		System.out.println(orbit);
	}

}