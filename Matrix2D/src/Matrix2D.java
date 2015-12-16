import java.text.DecimalFormat;

/**
 * <b>Purpose:</b>
 * <p>
 * <b>Date:</b> 2010 02 13 <p>
 * @author M. Ingram
 *
 */

public class Matrix2D {
	
	private double [][] matrix = new double[3][3];
		
	
	public Matrix2D() {
		this.setElements(matrix);
	}
	
	public Matrix2D(double[] e) {
		
		for(int i=0; i<matrix.length; i++){
			
			for(int j=0; j<matrix[i].length; j++){
				matrix[i][j]=e[i*3+j];
			}
		}
		
		setElements(matrix);
	}

	public Matrix2D(double[][] matrix) {
		
		this.setElements(matrix);
		
	}

	public Matrix2D(double m00, double m01, double m02, double m10, 
			double m11, double m12, double m20, double m21, double m22) {
		
		double [][] temp = { { m00, m01, m02}, { m10, m11, m12}, { m20, m21, m22}};

		this.setElements(temp);
	}

	public Matrix2D(Matrix2D other) {
		
		matrix = other.getElements();
	}

	public Matrix2D add(Matrix2D other) {
	
		double [][] temp = new double[3][3];
		
		for(int i=0; i<matrix.length; i++){
			
			for(int j=0;j<matrix[i].length;j++) 
				temp[i][j]=this.getElements()[i][j]+other.getElements()[i][j];
			
		}
		
		return new Matrix2D(temp);
	}

	public Matrix2D sub(Matrix2D other) {

		double [][] temp = new double[3][3];
		
		for(int i=0; i<matrix.length; i++){
			for(int j=0;j<matrix[i].length;j++) {
				temp[i][j]=this.getElements()[i][j]-other.getElements()[i][j];
			}
		}
		
		return new Matrix2D(temp);

	}

	public Matrix2D mul(Matrix2D other) {
		
		double [][] temp = new double [3][3];
		
		for (int i = 0; i < 3; i++) { 
				
		  for (int j = 0; j < 3; j++) {

		    for (int k = 0; k < 3; k++) {
		    	
		      temp[i][j]+=this.getElements()[i][k]*other.getElements()[k][j];
		      
		    }
		  }
		}
			
		return new Matrix2D(temp);
	}
	
	public Matrix2D mul(double [][] other) {
		
		double [][] temp = new double [3][other[0].length];
		
		for (int i = 0; i < this.getElements().length; i++) { 
				
		  for (int j = 0; j < other[0].length; j++) {
			  
			   temp[i][j] = 0.0;
			   
		    for (int k = 0; k < this.getElements()[0].length; k++) {
		    	
		      temp[i][j]+=this.getElements()[i][k]*other[k][j];
		      
		    }
		  }
		}
			
		return new Matrix2D(temp);
	}
	
	public double [][] getElements(){
		
		return matrix;
	}
	
	public void setElements(double [][] e){
		
		for(int i=0; i<3; i++){
			matrix[i]=e[i];
		}
	}
	
	public String toString(){
		
	 DecimalFormat df = new DecimalFormat("0.00");	
	 String str="";
		
			str+="Matrix2D = {\n";
	
		for(int i=0;i<matrix.length;i++) {
			
			for(int j=0;j<matrix[i].length;j++) {
			
				str+=df.format(matrix[i][j])+"\t";
			
				if(j==2 && i!=2)
					str+="\n";
			}
		}

		str+="}\n";

		return str;
		
	}

}
