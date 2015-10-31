/**
 * <b>Purpose:</b> The purpose of this project was to explore complex numbers and to develop methods that could manipulate those numbers. 
 * <b>Date:</b> 2009 05 08 
 * @author M. Ingram
 *
 */

public class ComplexNumber {
	
	private double real, imaginary;

	
/**
 * Constructs a new <code>ComplexNumber</code> without any parameters.
 * 	
 */
	public ComplexNumber(){
		
		
		
	}

/**
 * Constructs a new <code>ComplexNumber</code> with a real number as a parameter.
 * @param r A real number.
 */
	
	public ComplexNumber(double r){
		
		real = r;
		
	}

/**
 * Constructs a new <code>ComplexNumber</code> with two parameters, real and imaginary numbers.
 * @param r A real number.
 * @param i An imaginary number.
 */
	
	public ComplexNumber(double r, double i){
		
		real = r;
		imaginary = i;
		
	}

/**
 * Method that adds two <code>ComplexNumber</code>s together and returns the result.	
 * @param other The other <code>ComplexNumber</code> that is being added.
 * @return Sum of the two<code>ComplexNumber</code>s.
 */
	
	public ComplexNumber add(ComplexNumber other){
		
		return new ComplexNumber(this.real+other.real, this.imaginary+other.imaginary);
	
		
	}
	
/**
 * Method that subtracts two <code>ComplexNumber</code>s together and returns the result.	
 * @param other The other <code>ComplexNumber</code> that is being subtracted.
 * @return Difference of the two <code>ComplexNumber</code>s.
 */
	
	public ComplexNumber subtract(ComplexNumber other){
		
		return new ComplexNumber(this.real-other.real, this.imaginary-other.imaginary);
	
	}
	
/**
 * Method that multiplies two <code>ComplexNumber</code>s together and returns the result.	
 * @param other The other <code>ComplexNumber</code> that is being multiplied.
 * @return Product of the two <code>ComplexNumber</code>s.
 */
	
	public ComplexNumber multiply(ComplexNumber other){
	
		
		
		return new ComplexNumber((this.real*other.real)-(this.imaginary*other.imaginary), (this.real*other.imaginary)+(this.imaginary*other.real));
	
		
	}
	
/**
 * Method that divides two <code>ComplexNumber</code>s together and returns the result.	
 * @param other The other <code>ComplexNumber</code> that is being divided.
 * @return Quotient of the two <code>ComplexNumber</code>s.
 */
	
	public ComplexNumber divide(ComplexNumber other){
		
		double c = other.real;
		double d = other.imaginary;
		
		return new ComplexNumber(((this.real*other.real)+(this.imaginary*other.imaginary))/((c*c)+(d*d)), ((this.imaginary*other.real)-(this.real*other.imaginary))/((c*c)+(d*d)));
		
		
	}
	
/**
 * Method that squares a <code>ComplexNumber</code>s and returns the result.	
 * @return Square of the two <code>ComplexNumber</code>s.
 */
	
	public ComplexNumber square(){
		
		return this.multiply(this);
		
	}
	
/**
 * Method that finds the magnitude of two <code>ComplexNumber</code>s.
 * @return The magnitude.
 */
	
	public double magnitude(){
		
		double a = (this.real*this.real)+(this.imaginary*this.imaginary);
		return Math.sqrt(a);
		
	}

/**
 * Method that finds the magnitude<sup>2</sup>.
 * @return Magnitude<sup>2</sup>.
 */
	
	public double magnitudeSquared(){
		
		return (this.real*this.real)+(this.imaginary*this.imaginary);
		
	}

/**
 * Method that takes a <code>ComplexNumber</code> and returns its conjugate pair.
 * @return The conjugate pair.
 */
	
	public ComplexNumber conjugate(){
		
		
		return new ComplexNumber(this.real, -this.imaginary);
		
	}
/**
 * Simple method that retrieves the value of <code>real</code>.
 * @return The value of <code>real</code>.
 */
	public double getReal(){
		
		return real;
	}

/**
 * Simple method that retrieves the value of <code>imaginary</code>.
 * @return The value of <code>imaginary</code>.
 */
	public double getImaginary(){
		
		return imaginary;
		
	}

/**
 * Returns a <code>String</code> that displays the new <code>ComplexNumber</code>.
 */
	public String toString(){
	
		if(imaginary>=0)
		return +real+"+"+imaginary+"i";
		else 
		return +real+""+imaginary+"i";
		
	}
	
	
}


	