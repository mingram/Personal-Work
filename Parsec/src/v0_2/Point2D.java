package v0_2;
public class Point2D
{
	private double x, y;
	
	/**
	 * Creates a new Point2D with the coordinates (0,0).
	 */
	public Point2D()
	{
		setLocation(0, 0);
	}
	
	/**
	 * Creates a Point2D using the values in a 2 element array.
	 * c[0] is the x coordinate, c[1] is the y coordinate.
	 * @param c
	 */
	public Point2D(double[] c)
	{
		setLocation(c[0], c[1]);
	}
	
	/**
	 * Creates a new Point2D using the provided doubles
	 * @param x1
	 * @param y1
	 */
	public Point2D(double x1, double y1)
	{
		setLocation(x1, y1);
	}
	
	/**
	 * Gets the coordinate in the Point2D. Input 0 for x value, and 1 for y value.
	 * @param i
	 * @return
	 */
	public double getCoordinate(int i)
	{
		switch(i)
		{
		//x coordinate
		case(0):
		{
			return x;
		}
		case(1):
		{
			return y;
		}
		default:
			return 0.0;
		}
	}
	
	/**
	 * Returns a 2 element array with the point values in it.
	 * @return
	 */
	public double[] getCoordinates()
	{
		return new double[] {x, y};
	}
	
	/**
	 * Sets a desired x or y coordinate to a desired value. 0 for x, 1 for y.
	 * @param i
	 * @param value
	 */
	public void setCoordinate(int i, double value)
	{
		switch(i)
		{
		//x coordinate
		case(0):
		{
			setLocation(value, getCoordinate(1));
		}
		case(1):
		{
			setLocation(getCoordinate(0), value);
		}
		default:
		}
	}
	
	/**
	 * Sets the coordinates using a provided array. c[0] is the x coordinate,
	 * c[1] is the y value.
	 * @param c
	 */
	public void setCoordinates(double[] c)
	{
		setLocation(c[0], c[1]);
	}
	
	/**
	 * Sets the coordinate using another Point2D.
	 * @param other
	 */
	public void setCoordinates(Point2D other)
	{
		setLocation(other.getCoordinate(0), other.getCoordinate(1));
	}
	
	/**
	 * Sets the individual coordinate values.
	 * @param x1
	 * @param y1
	 */
	public void setLocation(double x1, double y1)
	{
		x = x1;
		y = y1;
	}
	
	/**
	 * Creates a new Point2D from [two] - [one]
	 * @param one
	 * @param two
	 */
	public Point2D sub(Point2D one, Point2D two)
	{
		return new Point2D(two.getCoordinate(0) - one.getCoordinate(0), two.getCoordinate(1) - one.getCoordinate(1));
	}
	
	
	/**
	 * Returns a beautifully formatted string of the Point2D object.
	 * @return the String of the Point in the form: (x,y).
	 */
	public String toString()
	{
		return "(" + getCoordinate(0) + "," + getCoordinate(1) + ")";
	}
	
}