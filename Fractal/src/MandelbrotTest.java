public class MandelbrotTest {
	
	private static ComplexNumber c;
	
	public static void main(String[] args) {
		
		Mandelbrot mandelbrot = new Mandelbrot(400,400);
		
		mandelbrot.setC(300,200);
		if (mandelbrot.isInSet())
			System.out.println(mandelbrot.getC()+" is in the Mandelbrot Set");
		else
			System.out.println(mandelbrot.getC()+" is not in the Set");
		
	}
}