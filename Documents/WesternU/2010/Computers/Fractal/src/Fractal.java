/**
 * <b>Purpose:</b> Develop the <code>Fractal</code> class that sets the bounds, the screen width and height, and returns the real and imaginary value of the <code>Mandelbrot</code>.
 * <p>
 * <b>Date:</b> 2009 05 14
 * @author M. Ingram
 *
 */

public class Fractal {
	
	private double leftBound, rightBound, bottomBound, topBound;
	private double domain, range;
	private int screenHeight, screenWidth;
	private double real,imaginary;
	private double videoCentreX,videoCentreY,videoDomain,videoZoomFactor;

	
	
/**
 * Creates a <code>Fractal</code> with the parameters w, h which represent the <code>screenWidth</code> and <code>screenHeight</code>, respectively.
 * @param w The <code>screenWidth</code>.
 * @param h The <code>screenHeight</code>.
 */
	
	public Fractal(int w, int h){
		
		screenWidth=w;
		screenHeight=h;
		
	}
	
/**
 * Creates an <code>ArrayList</code> of the 4 bounds of the <code>Fractal</code>.
 * @param b The <code>ArrayList</code>.
 */
	
	public void setBounds(double[]b){
		
		 leftBound = b[0];
		 rightBound = b[1];
		 bottomBound = b[2];
		 topBound = b[3];
		
	}
	
/**
 * Returns the real value of the orbit.
 * @param column The column that is being used to calculate the real value.
 * @return Returns the value of real.
 */
	
	public double getReal(int column){
		
		domain = rightBound-leftBound;
		real = (double)column/screenWidth*domain+leftBound;
		return real;
	
		
	}
	
/**
 * Returns the imaginary value of the orbit.
 * @param row The row that is being used to calculate the imaginary value.
 * @return Return the value of imaginary.
 */
	
	public double getImaginary(int row){
		
		range = topBound-bottomBound;
		imaginary = (double)row/screenHeight*range-topBound;
		
		if(imaginary==0)
			return imaginary;
		else
			return -imaginary;
		
	}
/**
 * Sets the video properties prior to recording.
 * @param real The x intercept of the zooming location.
 * @param imaginary The y intercept of the zooming location.
 * @param videoDomain The domain of the video.
 * @param zoomFactor The rate at which it zooms.
 * @param frames Total number of frames to be taken.
 */
	public void setVideoProperties(double real, double imaginary, double videoDomain, double zoomFactor, double frames){
		
		
		videoCentreX=real;
		videoCentreY=imaginary;
		videoZoomFactor=zoomFactor;
		this.videoDomain=videoDomain;
	//	System.out.println(videoDomain);
		
		
	}
	
/**
 * Similar method to <code>modifyBounds</code>, simply re-assigns the four <code>bounds</code> to the new x and y points and adds/subtracts
 * half the domain.
 */
	public void nextFrame(){
		
	//	System.out.println(videoDomain);
	//	
		videoDomain*=videoZoomFactor;

		leftBound = videoCentreX-(videoDomain/2);
		rightBound = videoCentreX+(videoDomain/2);
		bottomBound = videoCentreY-(videoDomain/2);
		topBound = videoCentreY+(videoDomain/2);
		
		
	}
	
	
}
