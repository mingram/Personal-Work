/**
 * <a href="http://darcy.rsgc.on.ca/ICS4M/Modeling2D/Transform2DJavadoc/index.html">Documentation</a> adapted from C. D'Arcy.
 * @author M. Ingram
 *
 */

public class Point2D {
	
	double x;
	double y;
	double [] pts = {x,y};
	

	public Point2D(){
		initialize(0,0);
	}
	
	public Point2D(double x, double y){
		initialize(x,y);
	}
	
	public Point2D(double [] c){
		initialize(c[0],c[1]);
	}
	
	private void initialize(double x, double y) {
		this.x = x;
		this.y = y;
		pts[0] = x;
		pts[1] = y;
	}
	
	public double getCoordinate(int i){
		return pts[i];
	}
	
	public double [] getCoordinates(){
		return pts;
	}
	
	public void setCoordinates(int i, double value){
		pts[i] = value;
	}
	
	public void setCoordinates(double [] c){
		pts = c;
	}
	
	public void setCoordinates(Point2D other){
		pts = other.pts;
	}
	
	public void setLocation(double x, double y){
		initialize(x,y);
	}
	
	public String toString(){
		String str="";
		
		str+="("+x+",\t"+y+")\n";
		
		return str;
	}

}
