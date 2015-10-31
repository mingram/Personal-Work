package v0_2;
/**
 * The Matrix2D class. Only 3x3 matrices.
 * @author Mike
 *
 */

public class Matrix2D
{
	private double[][] matrix = new double[3][3];
	
	/**
	 * Constructs the ZERO Matrix2D
	 */
	public Matrix2D()
	{
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
			{
				matrix[i][j] = 0;
			}
		}
		matrix[2][2] = 1;
	}
	
	/**
	 * Constructs a Matrix2D from a linear array.
	 * @param e - the array to be used.
	 */
	public Matrix2D(double[] e)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
			{
				matrix[i][j] = e[i * 3 + j];
			}
		}
	}
	
	/**
	 * Constructs a Matrix2D from a 2D array.
	 * @param m - the array to be used.
	 */
	public Matrix2D(double[][] m)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
			{
				matrix[i][j] = m[i][j];
			}
		}
	}
	
	/**
	 * Constructs a Matrix2D from 9 doubles
	 * @param m00
	 * @param m01
	 * @param m02
	 * @param m10
	 * @param m11
	 * @param m12
	 * @param m20
	 * @param m21
	 * @param m22
	 */
	public Matrix2D(double m00, double m01, double m02, double m10, double m11, double m12,
			double m20, double m21, double m22)
	{
		matrix[0][0] = m00;
		matrix[0][1] = m01;
		matrix[0][2] = m02;
		matrix[1][0] = m10;
		matrix[1][1] = m11;
		matrix[1][2] = m12;
		matrix[2][0] = m20;
		matrix[2][1] = m21;
		matrix[2][2] = m22;
	}
	
	/**
	 * Constructs a Matrix2D from another Matrix2D
	 * @param other
	 */
	public Matrix2D(Matrix2D other)
	{
		double [][] o = other.getElements();
		for(int i = 0; i < o.length; i++)
		{
			for(int j = 0; j < o[0].length; j++)
			{
				matrix[i][j] = o [i][j];
			}
		}
	}
	
	/**
	 * Adds two Matrix2Ds together
	 * @param other
	 * @return a new Matrix2D with the sum
	 */
	public Matrix2D add(Matrix2D other)
	{
		double[][] elements = other.getElements();
		
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
			{
				elements[i][j] += matrix[i][j];
			}
		}
		
		return new Matrix2D(elements);
	}
	
	/**
	 * Gets the contents of a Matrix2D.
	 * @return the elements in a 2D array.
	 */
	public double[][] getElements()
	{
		double[][] elements = new double[3][3];
		
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
			{
				elements[i][j] = matrix[i][j];
			}
		}
		
		return elements;
	}
	
	/**
	 * Multiplies two matrices together
	 * @param other - the multiplier (2D Array)
	 * @return a new Matrix2D with the product
	 */
	public double[][] mul(double[][] other)
	{
		double[][] products = new double[matrix.length][other[0].length];
		
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < other[0].length; j++)
			{
				for(int k = 0; k < matrix[0].length; k++)
				{
					products[i][j] += matrix[i][k] * other[k][j];
				}
			}
		}
		
		return products;
	}
	
	/**
	 * Multiplies two matrices together
	 * @param other - the multiplier (Matrix2D)
	 * @return a new Matrix2D with the product
	 */
	public Matrix2D mul(Matrix2D other)
	{
		double[][] otherMatrix = other.getElements();
		double[][] products = new double[matrix.length][otherMatrix[0].length];
		
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix.length; j++)
			{
				products[i][j] = 0.0;
				for(int k = 0; k < matrix.length; k++)
				{
					products[i][j] += matrix[i][k] * otherMatrix[k][j];
				}
			}
		}
		
		return new Matrix2D(products);
	}
	
	/**
	 * Sets all the elements in a Matrix2D object
	 * @param e - the 2D array with the desired values.
	 */
	public void setElements(double[][] e)
	{
		for(int i = 0; i < e.length; i++)
		{
			for(int j = 0; j < e[i].length; j++)
			{
				matrix[i][j] = e[i][j];
			}
		}
	}
	
	/**
	 * Subtracts one Matrix2D with another.
	 * @param other
	 * @return a new Matrix2D with the difference
	 */
	public Matrix2D sub(Matrix2D other)
	{
		double[][] elements = other.getElements();
		
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
			{
				elements[i][j] -= matrix[i][j];
			}
		}
		
		return new Matrix2D(elements);
	}
	
	
	/**
	 * Returns a beautiful, 2-dimensional, tab-delimited String rendering an image of the Matrix2D object.
	 * @return a String representation of the Matrix2D
	 */
	public String toString()
	{
		String ret = "Matrix2D = {\n";
		
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
			{
				ret += matrix[i][j];
				if(j < matrix[i].length-1 || i < matrix.length-1)
					ret += ",\t";
				else
					ret += " ";
			}
			if(i < matrix.length-1)
				ret += "\n";
			else
				ret += "}\n";
		}
		
		return ret;
	}
}