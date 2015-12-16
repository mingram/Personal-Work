
import java.util.Arrays;

/**
 * 
 * <b>Purpose</b>:<p> 
 * 
 * Develop the <code>CubeFactory</code> class, which is comprised of two methods. 
 * The method <code>formCubes</code> of type <code>int[]</code> instantiates a new array of size <code>|n|+1</code> and fills it so the <code>i</code><sup>th</sup> 
 * element contains the value <code>i*i*i</code>. It then returns the array.  
 * The second method, <code>sumArray</code>, has a local variable <code>n</code>, which is immediately set to one less the length of the 
 * parameter <code>a</code>, which is an integer array. The equation <code>n*n*(n+1)*(n+1)/4</code> is used to compute
 * the sum of the elements in the array.<p>
 * 
 * <b>Date</b> : Sep 21, 2010<p>
 * @author M. Ingram
 *
 */

public class CubeFactory {
	
	private static int [] cubes;
	
	/**
	 * Prints out the array that has been filled by the <code>formCubes</code> method, followed by the sum of the array,
	 *  which has been computed by the <code>sumArray</code> method.
	 * @param args
	 */
	
	public static void main(String [] args){
		
		System.out.println(Arrays.toString(formCubes(4)));
		System.out.println("Sum of Array = "+sumArray(cubes));
	}
	
	/**
	 * Method of type <code>int[]</code> which instantiates the <code>cube</code> array as a new integer array, of length <code>|n|+1</code>.
	 * Using a simple <code>for</code> loop, the array is filled with the cubes of each element.
	 * @param n number of cubes to compute.
	 * @return the array <code>cubes</code>, which is filled with the cubes of each number from 1 to <code>n</code>.
	 */
	
	public static int[] formCubes(int n){
		 
		cubes = new int [Math.abs(n)+1];
		
		for(int i = 1; i<cubes.length; i++)
			cubes[i] = i*i*i;
		
		return cubes;
	}
	
	/**
	 * Method that computes the sum of the cubes developed by the <code>formCubes</code> method. We use <code>n</code> to represent one less
	 * the length of the array. We can then use the equation <code> n*n*(n+1)*(n+1)/4 </code>to calculate the sum.
	 * @param a array of cubes to be added together.
	 * @return sum of the array of cubes.
	 */
	
	public static int sumArray(int [] a){

		int n = a.length-1;
	
		return n*n*(n+1)*(n+1)/4;
	}

}
