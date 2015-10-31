
/**
 * <b>Purpose: </b>Develop the <code>Trig</code> class that calculates all 360 values of each trigonometric functions.
 * By providing the first 45 values of sine, every other angle can be calculated through thoughtful manipulation of the given values.
 * <p>
 * <b>Date: </b>2010 05 01
 * <p>
 * @author M. Ingram
 *
 */

public class Trig {
	
	final static double[] lookUp = { // sin ratios for degree measures in the
										// interval [0,45]
		
	0.0000, 0.0175, 0.0349, 0.0523, 0.0698, 0.0872, 0.1045, 0.1219, 0.1392,

	0.1564, 0.1736, 0.1908, 0.2079, 0.2250, 0.2419, 0.2588, 0.2756, 0.2924,

	0.3090, 0.3256, 0.3420, 0.3584, 0.3746, 0.3907, 0.4067, 0.4226, 0.4384,

	0.4540, 0.4695, 0.4848, 0.5000, 0.5150, 0.5299, 0.5446, 0.5592, 0.5736,

	0.5878, 0.6018, 0.6157, 0.6293, 0.6428, 0.6561, 0.6691, 0.6820, 0.6947,

	0.7071 };

	final static String[] functions = { "sin", "cos", "tan", "csc", "sec",
			"cot" };

	/**
	 * 
	 * Called by LookUpApplet to obtain the table of results in the form of a
	 * single String
	 * 
	 * @param w
	 *            the specific function (see functions array above)
	 * 
	 * @return the complete table as a single String
	 */

	public static String getTable(int w) {
		
		String ch = "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500";
		String str = String.format("%18s",ch+"\u252C")+String.format("%1s",ch+"\u2500\u2500\u2500\u2500\u2500")+"\n";
		
		for (int i = 0; i <= 360; i++) {
			
			str+=String.format("%13d",i)+String.format("%5s","\u2502");
			
			switch (w) {

			case 0:
				str+=String.format("%10.4f",sin(i))+"\n";
				break;

			case 1:
				str+=String.format("%10.4f",cos(i))+"\n";
				break;

			case 2:
				str+=String.format("%10.4f",tan(i))+"\n";
				break;

			case 3:
				str+=String.format("%10.4f",csc(i))+"\n";
				break;

			case 4:
				str+=String.format("%10.4f",sec(i))+"\n";
				break;

			case 5:
				str+=String.format("%10.4f",cot(i))+"\n";
				break;	
				
			}
		}
		
		return str;

	}

/**
 * Method that returns the sin value of the integer passed to it through its one parameter.
 * The first step is to mod the value of the integer by 360. 
 * This will determine what quadrant the value is in by removing all previous rotations around the unit circle. <br>
 * If the new modded value is greater than 180, its new value is the current value minus 180 to find the value in the first or second quadrant. <br>
 * If the new value is greater than 90 but less than 180, it is subtracted from 180 to get the equivalent value in the first quadrant. <br>
 * If the new value is greater than 45, you simply calculate it using the equation: <code>&radic; 1-sin(x)<sup>2</sup></code>, where <code>x</code> is
 * the value of the angle being used.
 * @param x the angle to be used
 * @return 
 */
	private static double sin(int x){
		
		int neg = 1;
		x %= 360;

		if (x > 180) {
			x-=180;
			neg = -1;
		}

		if (x > 90)
			x = 180 - x;

		if (x > 45)
			return Math.sqrt((1 - ((lookUp[90 - x]) * (lookUp[90 - x])))) * neg;

		return lookUp[x] * neg;

	}
	
	/**
	 * Calculates the cosine value of an angle by taking the square root of 1-sin<sup>2</sup> x.
	 * @param x the angle value.
	 * @return the cosine value of the angle.
	 */
	
	private static double cos(int x){
		
		return Math.sqrt(1-(sin(x)*sin(x)));
	}

	/**
	 * Calculates the tan value of an angle by dividing the sin value of the parameter by the cosine value.
	 * @param x the angle value.
	 * @return the tan value of the angle.
	 */
	
	private static double tan(int x){
		
		return sin(x)/cos(x);
	}

	/**
	 * Calculates the cosecant value of an angle by dividing 1 by the cosine value of the angle.
	 * @param x the angle value.
	 * @return the cosecant value of the angle.
	 */
	
	private static double csc(int x){
	
		return 1/sin(x);
	}
	
	/**
	 * Calculates the cosine value of an angle by dividing 1 by the cosine value of the angle.
	 * @param x the angle value.
	 * @return the secant value of the angle.
	 */
	
	private static double sec(int x){
		
		return 1/cos(x);
	}
	
	/**
	 * Calculates the cotangent value of an angle by dividing 1 by the tan value of the angle.
	 * @param x the angle value.
	 * @return the cotangent value of the angle.
	 */
	private static double cot(int x){
		
		return 1/tan(x);
	}

	
}
