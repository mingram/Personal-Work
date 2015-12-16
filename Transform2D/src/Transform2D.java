import java.text.DecimalFormat;

/**
 * <a href="http://darcy.rsgc.on.ca/ICS4M/Modeling2D/Transform2DJavadoc/index.html">Documentation</a> adapted from C. D'Arcy.
 * @author M. Ingram
 *
 */
public class Transform2D {	
	
	private Matrix2D matrix = new Matrix2D();

 
	public enum Type {
		
		ZERO {
			
			public Matrix2D setMatrix() { 
				return new Matrix2D(); 
			}
		},
		
		IDENTITY {
			
			public Matrix2D setMatrix(){
				return new Matrix2D(1, 0, 0, 0, 1, 0, 0, 0, 1);
			}
		},
		
		REFX {
			
			public Matrix2D setMatrix(){
				return new Matrix2D(1, 0, 0, 0, -1, 0, 0, 0, 1);
			}
		},
		
		REFY {
			
			public Matrix2D setMatrix(){
				return new Matrix2D(-1, 0, 0, 0, 1, 0, 0, 0, 1);
			}
		},
		
		REFORIGIN {
			
			public Matrix2D setMatrix(){
				return new Matrix2D(-1, 0, 0, 0, -1, 0, 0, 0, 1);
			}
		};
		
		
		public abstract Matrix2D setMatrix();

	}
	

	Transform2D(){
		setTransform2D(new Matrix2D());
	}
	
	Transform2D(Matrix2D matrix2D){
		setTransform2D(matrix2D);
	}
	
	
	Transform2D(Transform2D.Type kind){
		setTransform2D(kind.setMatrix());
	}
	
	Transform2D(Transform2D transform2D){
		setTransform2D(transform2D.getTransform2D());
	}
	
	public Transform2D add(Transform2D other){
		return new Transform2D(this.getTransform2D().add(other.getTransform2D()));	
	
	}
	
	public Matrix2D getTransform2D(){
		return matrix;
	}
	
	public Transform2D mul(Transform2D other){
		return new Transform2D(this.getTransform2D().mul(other.getTransform2D()));	
	}
	
	public void setRotation(double a){
		setTransform2D(new Matrix2D(Math.cos(a),-Math.sin(a),0,Math.sin(a),Math.cos(a),0,0,0,1));
	}
	
	public void setScale(double s){
		setTransform2D(new Matrix2D(s,0,0,0,s,0,0,0,1));
	}
	
	public void setTransform2D(Matrix2D matrix2D){
		matrix = matrix2D;
	}
	
	public void setTranslation(Point2D p){
		setTransform2D(new Matrix2D(1,0,p.x,0,1,p.y,0,0,1));
	}
	
	public Transform2D sub(Transform2D other){
		return new Transform2D(this.getTransform2D().sub(other.getTransform2D()));	
	}
	
	public String toString(){
		
		return this.getTransform2D().toString();
		
	}
}
