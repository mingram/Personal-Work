package v0_2;
public class Transform2D
{
	public static enum Type { IDENTITY, REFORIGIN, REFX, REFY, ZERO };
	private Matrix2D matrix;
	
	/**
	 * Creates a new Transform2D object encapsulating the ZERO matrix.
	 */
	public Transform2D()
	{
		setTransform2D(new Matrix2D());
	}
	
	/**
	 * Creates a new Transform2D from an existing Matrix2D.
	 * @param matrix2d
	 */
	public Transform2D(Matrix2D matrix2d)
	{
		setTransform2D(matrix2d);
	}
	
	/**
	 * Creates a new Transform 2D using a type of Transform2D. Acceptible types are:
	 * IDENTITY, REFORIGIN, REFX, REFY, ZERO. If a type is inputted that does not match the previous types
	 * then a ZERO matrix is specified by default.
	 * @param kind
	 */
	public Transform2D(Transform2D.Type kind)
	{
		switch(kind)
		{
		case IDENTITY:
			setTransform2D(new Matrix2D(1,0,0,0,1,0,0,0,1)); break;
		case REFORIGIN:
			setTransform2D(new Matrix2D(-1,0,0,0,-1,0,0,0,1)); break;
		case REFX:
			setTransform2D(new Matrix2D(1,0,0,0,-1,0,0,0,1)); break;
		case REFY:
			setTransform2D(new Matrix2D(-1,0,0,0,1,0,0,0,1)); break;
		default:
			setTransform2D(new Matrix2D()); break;
		}
	}
	
	/**
	 * Creates a new Transform2D from an existing Transform2D
	 * @param transform2d
	 */
	public Transform2D(Transform2D transform2d)
	{
		setTransform2D(transform2d.getTransform2D()); 
	}
	
	/**
	 * Adds two Transform2Ds together.
	 * @param other
	 * @return the resultant Transform2D.
	 */
	public Transform2D add(Transform2D other)
	{
		return new Transform2D(getTransform2D().add(other.getTransform2D()));
	}
	
	/**
	 * This returns the Matrix2D encapsulated by Transform2D.
	 * @return the Matrix2D encapsulated by the Transform2D.
	 */
	public Matrix2D getTransform2D()
	{
		return matrix;
	}
	
	/**
	 * Multiplies two Transform2Ds together.
	 * @param other
	 * @return the resultant Transform2D.
	 */
	public Transform2D mul(Transform2D other)
	{
		return new Transform2D(getTransform2D().mul(other.getTransform2D()));
	}
	
	/**
	 * Translates the point in a Transform2D along a vector (point) p.
	 * @param p - the vector to be transformed along.
	 */
	public void setTranslation(Point2D p)
	{
		matrix = new Matrix2D(1, 0, p.getCoordinate(0), 0, 1, p.getCoordinate(1), 0, 0, 1);
	}
	
	/**
	 * Rotates the Transform2D around the origin a radians.
	 * @param a - the radians to rotate the Transform2D.
	 */
	public void setRotation(double a)
	{
		matrix = new Matrix2D(Math.cos(a), -Math.sin(a), 0,
				Math.sin(a), Math.cos(a), 0, 0, 0, 1);
	}
	
	/**
	 * Scales the Transform2D s times.
	 * @param s - the scaling factor.
	 */
	public void setScale(double s)
	{
		matrix = new Matrix2D(s, 0, 0, 0, s, 0, 0, 0, 1);
	}
	
	/**
	 * Sets the Transform2D to another Matrix2D
	 * @param matrix2d
	 */
	public void setTransform2D(Matrix2D matrix2d)
	{
		matrix = matrix2d;
	}
	
	/**
	 * Returns a string of the elements in the contained Matrix2D.
	 * @return a nice String of the elements in the Matrix2D encapsulated by the Transform2D.
	 */
	public String toString()
	{
		return "Transform2D: " + matrix.toString();
	}
}