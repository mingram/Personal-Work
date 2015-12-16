import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * <b>Purpose:</b> Develop the <code>Mandelbrot</code> class that extends the <code>Fractal</code> class. 
 * Its purpose is to create a new <code>Mandelbrot</code> and to contain methods that determine if the orbits lie outside the given <code>bounds</code>.
 * <p>
 * <b>Date:</b> 2009 05 14
 * @author M. Ingram
 *
 */
public class Mandelbrot extends Fractal{

	private ComplexNumber z,c;
	private static final int ITERATIONS = 512;
	private int iterations;
	private double [] bounds = {-2.25, 0.75, -1.5, 1.5};
	private double delta = 1.5;
	private double zoomFactor = 0.4;
	
/**
 * Constructs a new <code>Mandelbrot</code> and sets the parameters <code>int w, int h</code> to the parameters of the <code>Fractal</code>.
 * @param w The width of the <code>Mandelbrot</code>.
 * @param h The height of the <code>Mandelbrot</code>.
 */
	public Mandelbrot(int w, int h){
		
		super(w,h);
		setBounds(bounds);
		
	}
	
/**
 * Creates a new orbit by constructing a new <code>ComplexNumber</code> and passes <code>getReal</code> and <code>getImaginary</code> methods from the <code>Fractal</code> class, as parameters.
 * @param column The column of the new orbit.
 * @param row The row of the new Orbit.
 */
	
	public void setC(int column, int row){
		
		c = new ComplexNumber(getReal(column), getImaginary(row));
	}

/**
 * Simply takes the value of <code>c</code> and makes it into a <code>String</code>.
 * @return Returns the <code>String</code> containing the value of <code>c</code>.
 */
	
	public String getC(){
		
		return c.toString();
	}
	
/**
 * Checks to see if the orbits of the origin have a magnitude greater than two. 
 * If not, it continues to loop through, increasing the value of <code>iterations</code> by 1 each time, until it does have a magnitude greater than two, or if the number of <code>iterations</code> is equal to the final value <code>ITERATIONS</code>.
 * @return Returns true if the magnitude never reached two, or if <code>iterations</code> reached the value of <code>ITERATIONS</code>.
 */
	
	public boolean isInSet(){
	
		z = new ComplexNumber();
		iterations = 0;
		while(z.magnitude()<2 && iterations<ITERATIONS){
			
			z=z.square().add(c);
			iterations++;
			
			if(iterations==ITERATIONS)
				return true;
		}
		
		return false;	
	}

/**
 * Modifies the bounds so that the new bounds are suited for the zoomed in image. 
 * Two local double variables <code>x</code>,<code>y</code>, are defined by <code>getReal(col)</code>, 
 * and <code>getImaginary(row)</code> The new bounds are defined as <code>x-delta,x+delta,y-delta,y+delta</code>.
 * <code>Delta</code> represents the new domain and range.
 * 
 * @param col The column of the point clicked. Defined by <code>e.getX()</code>, in the <code>Platform</code> class method, <code>mouseClicked</code>.
 * @param row The row of the point clicked. Defined by <code>e.getY()</code>, in the <code>Platform</code> class method, <code>mouseClicked</code>.
 */
	
	public void modifyBounds(int col, int row){
		
		delta*=zoomFactor;
		
		double x = getReal(col);
		double y = getImaginary(row);
		
		bounds[0] = x-delta;
		bounds[1] = x+delta;
		bounds[2] = y-delta;
		bounds[3] = y+delta;
		
		setBounds(bounds);
		

		
	
	}
	

/**
 * Simple method that returns the value of <code>iterations</code>.
 * @return Returns the value of the variable <code>iterations</code>.
 */
	
	public int getIterations(){

		return iterations;
	}
	
}
