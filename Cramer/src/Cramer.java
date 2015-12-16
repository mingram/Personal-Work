/**
 * Driver for ICS4U '0910 Cramer's Rule project. Employs methods from a
 * static NumericalMethods class that includes (among others) a method for
 * obtaining the determinant of a square matrix, recursively. 
 * @author C. D'Arcy 2010 03 25
 */

public class Cramer {

	public static void main(String[] args) {
		// The solution to the sample below is (2,-1,4)
	//	double[][] A = { { 2, 8, 6 }, { 4, 2, -2 }, { 3, -1, 1 }};
		double[][] A = { { 0, 0, 1 }, { 0, 1, 1 }, { 1, 1, 1 }};
		double[] b = { 0, 0.6, 0 };
		// does the system even have a solution?
		
		
		if (NumericalMethods.hasSolution(A)) {
			// if so, obtain them
			double[] solution = NumericalMethods.cramersRule(A, b);
			// display them..
			System.out.print("The solution is:\t");
			for (double d : NumericalMethods.cramersRule(A, b))
				System.out.printf("%6.1f\t", d);
			System.out.println();
		} else
			System.out.print("No solution exists.");

	}
}