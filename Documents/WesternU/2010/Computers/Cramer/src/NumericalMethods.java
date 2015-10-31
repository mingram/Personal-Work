/**
 * <b>Purpose:</b> Develop the NumericalMethods class that has the ability to find the determinant of a matrix. <p>
 * <b>Date:</b> 2010 03 27
 * @author M. Ingram
 *
 */
public class NumericalMethods {

/**
 * Uses the determinants of a matrix and inserts a column vector into that matrix to produce the solutions to the system of equations.
 * @param a the matrix
 * @param b the vector
 * @return
 */
	public static double[] cramersRule(double[][] a, double[] b) {
		
		double [][] m = new double[a.length][a.length];
		double k = determinant(a);
		double[] d = new double[a.length];
		
	// traversing columns		
		for(int c=0; c<a.length; c++){

	// populate the m array with the original a array.			
			for(int i=0; i<a.length; i++){
				for(int j=0; j<a.length; j++){
					m[i][j] = a[i][j];
				}
			}
	// fills the specified column with the b array.		
			for(int r=0; r<a.length; r++){
				m[r][c] = b[r];
			}
				d[c]=determinant(m)/k;	
		}
		
		return d;	
	}
	
	public static double determinant(double[][] m){
		
		//base case: if 2x2, return determinant.
		if(m.length==2){
			return m[0][0]*m[1][1] - m[0][1] * m[1][0];
		}
		
		double d=0;
		
		for(int i=0; i<m.length; i++){
			d+=Math.pow(-1, 2+i)*m[0][i]*determinant(subMat(m,i));
		}
		
		return d;
	}

	/**
	 * Method that returns the smaller matrix within the original, by removing all elements in the same row and/or column as 
	 * the specified value, int i.
	 * @param m the matrix to be used
	 * @param i the position of the matrix.
	 * @return
	 */
	public static double[][] subMat(double[][] m, int i){
	
	double [][] mat = new double[m.length-1][m.length-1];
	
		for(int r=1; r<m.length; r++){
			
			int row = r-1;

			for(int c=0; c<m.length; c++){
				
				int col = c-1;
				
				if(c!=i){
					
					if(c>i)
						mat[row][col] = m[r][c];
					
					else
						mat[row][c] = m[r][c];
				}
			}
		}
		
		return mat;
		
	}

/**
 * Simple boolean method that checks if there is a solution to the determinant, which is any value other than 0.
 * @param a the matrix
 * @return
 */
	public static boolean hasSolution(double[][] a) {
		
		if(determinant(a)!=0)
			return true;
		
		else
			return false;
	}

}
