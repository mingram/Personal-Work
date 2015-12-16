/****************************************************************
 * PURPOSE: To serve as a platform for simple Graphics projects
 * DATE:	2005 05 13 (Adapted from Edmund Lee 2004 05 08)
 ****************************************************************/
import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;


public class Platform extends JFrame
{
	int width = 400;
	int height = 400;

	
	
	


/**
 * Pass content the parameter of this class, giving it access to <code>JFrame</code>.
 */
	public Platform() {
		super("Sample Graphics Platform");

		// add some content
		getContentPane().add(new Content(width,height,this));
		

		// setup the frame 	
		setBounds(0,0,width,height+25);
		setVisible(true);

		

		
	}

	public static void main( String[] args ) {
		Platform platform = new Platform();
		platform.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}


// inner Content class
class Content extends JPanel implements MouseListener,MouseMotionListener,KeyListener {
	final int WIDTH;
	final int HEIGHT;
	Palette palette;
	Mandelbrot mandelbrot;
	private int zoomLevel;
	public double [] orbits;
	public boolean o;
	
	private int x;
	private int y;
	public String titleName;
	
	/**
	 * Added Platform parameter that passes JFrame properties of the Platform class to the Content class.
	 * @param w 
	 * @param h
	 * @param Platform
	 */
	
	public Content(int w, int h, JFrame Platform) {
		super();
		WIDTH = w;
		HEIGHT = h;
		palette = new Palette();
		mandelbrot = new Mandelbrot(WIDTH, HEIGHT);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
			
		//titleName = "("+x+","+y+") = "+mandelbrot.getReal(x)+" "+mandelbrot.getImaginary(y)+"i. Max. Iterations:"+mandelbrot.getIterations();
		titleName="Mandelbrot Set";
		Platform.setTitle(titleName);
		
		
	//	refer Platform's access to title of JFrame
		
		
		
		
	}
	
	public void drawOrbits(int col,int row){
		
		
		if(o=true){
		Graphics g2 = (Graphics2D) getGraphics();
		g2.setXORMode(Color.WHITE);
		
		mandelbrot.setC(x, y);
		
		if(mandelbrot.isInSet())
		
			g2.drawLine(x, y, x, y);
		}
	}
	
	public void paintComponent ( Graphics g ) {
		super.paintComponent( g );
		Graphics2D g2 = (Graphics2D) g;
		
		for(int c=0; c<WIDTH; c++) {
			
			for(int r=0; r<HEIGHT; r++) {
				
				mandelbrot.setC(c, r);
				
				if(mandelbrot.isInSet())
					g2.setColor(Color.black);
				else
					g2.setColor(palette.getColor(mandelbrot.getIterations()%256));
				
				g2.drawLine(c, r, c, r);
				
				
			}
		}
	}

		//  To confirm that you can create some graphics content
		//  the next two statements will select the first color from 
		//  your palette and draw a line from the top left corner of the canvas to the bottom right.
		//  You will delete these statements and replace them with your Mandelbrot code.
		//	g2.setColor(palette.getColor(0));
		//	g2.drawLine(0,0,WIDTH,HEIGHT);


	

	/**
	 * Calls the method <code>modifyBounds</code> of the <code>mandelbrot</code> class when the system recognizes
	 * that the mouse has been clicked in the <code>Platform</code> window. It then repaints the canvas by calling
	 * the <code>repaint</code> method.
	 */
	
	public void mouseClicked(MouseEvent e) {
	
	//	System.out.println("(col,row) = ("+e.getX()+","+e.getY()+")");
		mandelbrot.modifyBounds(e.getX(),e.getY());
	
		System.out.println(mandelbrot.getReal(e.getX()));
		System.out.println(mandelbrot.getImaginary(e.getY()));
		repaint();
		
		
	}

	public void mouseEntered(MouseEvent e) {
	
		
	}

	public void mouseExited(MouseEvent e) {
		
		
	}

	public void mousePressed(MouseEvent e) {
		
		
	}

	public void mouseReleased(MouseEvent e) {
		
		
	}
	
	public void mouseDragged(MouseEvent e) {
		
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
		x=e.getX();
		y=e.getY();
		titleName = "("+x+","+y+") = "+mandelbrot.getReal(x)+" "+mandelbrot.getImaginary(y)+"i. Max. Iterations: 512";
		
		System.out.println(titleName);
	
		
		
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_S){
			screenCapture();
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_V){
			generateFrames();
		
			
		}
		/**
		 * O is set to true if it is pressed.
		 */
		if(e.getKeyCode() == KeyEvent.VK_O){
			o=true;
		
		}
		
		
	}

	public void keyReleased(KeyEvent e) {
		
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}
	
	
	public void screenCapture()	{
	
		
		  try {
		    Robot robot = new Robot();
		    BufferedImage screenShot = robot.createScreenCapture
		      (new Rectangle(3,43, WIDTH-6, HEIGHT-7));
		    File file = new File(mandelbrot.getClass().getName().toLowerCase()
		      +"_"+zoomLevel+".jpg");
		    javax.imageio.ImageIO.write(screenShot, "JPG", file);
		  } catch(Exception e) {
		    System.out.println("Exception found: " + e);
		  }
		}
		



	public void generateFrames() {
    int frames = 400;
    // Parameters: vCentreX, vCentreY, vDomain, vZoomFactor, frames
    mandelbrot.setVideoProperties(-0.16659700858629986, -1.0405545351053962, 5, 0.96, frames);

    zoomLevel = 1;
    Graphics g = this.getGraphics();

    for (int f = 0; f < frames; f++) {
      System.out.println("Drawing Frame:" + zoomLevel);
      drawMandelbrot(g);
      screenCapture();
      // Updates viewing parameters for the next frame: domain, leftBound, rightBound, bottomBound and topBound 
      mandelbrot.nextFrame();
      zoomLevel++;
      
    }
}
	
	public void drawMandelbrot ( Graphics g ) {

		Graphics2D g2 = (Graphics2D) g;
		
		for(int c=0; c<WIDTH; c++) {
			
			for(int r=0; r<HEIGHT; r++) {
				
				mandelbrot.setC(c, r);
				
				if(mandelbrot.isInSet())
					g2.setColor(Color.black);
				else {
					g2.setColor(palette.getColor(mandelbrot.getIterations()%256));
				}
				g2.drawLine(c, r, c, r);
				
				
			}
		}
	}
 
}

// inner Palette class
class Palette {

	Color[] palette;
	
	public Palette() {
		Random random = new Random();
		palette = new Color[256];
		palette[0] = Color.BLACK;
		// REPLACE THE FOLLOWING WITH A CUSTOM PALETTE

		int r = 66;
		int g = 61;
		int b = 255;
		
		for (int i=1; i<256; i++) {
	
			if(i<5){
				
				palette[i]=new Color(r,g,b);
				r=r-5;
				g=g+7;			
			}
		
			else if(i<15){
			
				palette[i] = new Color(r,g,b);
				r=r-2;
				g=g+12;
				b=b-24;
			}
			
			else if(i<35){
		
				palette[i] = new Color(r,g,b);
				r=r+10;
				b=b+6;	
			}	
			
		//	else palette[i] = new Color(255,255,255);
			
			else if(i<100){
				palette[i]=new Color(r,g,b);	
				r--;
				g--;
				b--;
				
				
			
			}
			
			
			
			
			else if(i<170)
				 palette[i]=new Color(0,i,i);
			
			else palette[i]=new Color(0,i,0);		
			
		
	}	
}
	
	// accessor method...
	public Color getColor(int i) {
		return palette[i];
	}
	
	
}