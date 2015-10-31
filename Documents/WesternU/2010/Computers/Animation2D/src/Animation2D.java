import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;


/**
 * Animation2D uses classes developed in last week's Transform2DTest project to confirm the 
 * applicability of Matrix-defined transformations to simple animations.
 * Note: <b>Add Path</b> to <b>Transform2DTest</b> folder in <b>Classes</b> Tab of your <b>JDK Profile.</b>
 * The <i>Space Invaders Tutorial</i> at http://www.planetalia.com/cursos/Java-Invaders/JAVA-INVADERS-11.tutorial was useful.
 * @author C. D'Arcy
 */


public class Animation2D extends Canvas {

  /**
   * The width of the Canvas
   */
  public static final int WIDTH = 500;

  /**
   * The height of the Canvas
   */
  public static final int HEIGHT = 500;

  /**
   * SPEED controls the delay of the utility Thread
   */

  public static final int SPEED = 50;
  
  /**
   * Specialized class to support double buffering
   */
  public BufferStrategy strategy;

  /**
   * Cartesian parameters: domain, range, minX, maxX, minY, maxY
   */
  public static double maxX = 10.0;
  public static double minX = -10.0;
  public static double maxY = 10.0;
  public static double minY = -10.0;

  /**
   * assists in converting Cartesian coordinates to screen coordinates
   */
  public static double domain = maxX - minX;

  /**
   * assists in converting Cartesian coordinates to screen coordinates
   */
  public static double range = maxY - minY;

  // Animation details---------------------
  private static double delta = Math.PI / 180;
  private static double angle = -delta;
  private static double transX = 0.1;
  private static double transY = -0.1;

  /**
   * an instance of the <b>Sprite</b> class to be animated
   */
  
  public static Sprite sprite;

  /**
   * Single constructor (supplied)
   */
  public Animation2D() {
    JFrame frame = new JFrame("2D Animation ");
    JPanel panel = (JPanel) frame.getContentPane();

    setBounds(0, 0, WIDTH, HEIGHT);

    panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    panel.setLayout(null);
    panel.add(this);
    frame.setBounds(0, 0, WIDTH, HEIGHT);
    frame.setVisible(true);
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setResizable(false);
    createBufferStrategy(2);
    strategy = getBufferStrategy();
    requestFocus();

  }
  
  /**
   * updateScene prepares and applies the matrix transform(s) to the
   * Sprite object
   */
  
  public void updateScene() {
	  
    Transform2D t1 = new Transform2D();
    t1.setRotation(0.1);
    
    sprite.transform(t1);
  }

  /**
   * Converts Cartesian x-coordinate to relative Screen x-coordinate
   */
  public int scrX(double x) {
    int sx = (int)(x/WIDTH*domain+minX);
	// remember the Mandelbrot assignment?
	return sx;
  }
	
  /**
   * Converts Cartesian y-coordinate to relative Screen y-coordinate
   */
  public int scrY(double y) {
	int sy= (int)(y/HEIGHT*range-maxY);
	// remember the Mandelbrot assignment?
    return sy;
  }

  /**
   * Creates a BufferedImage object, obtains its Graphics2D drawing context, and
   * goes to work drawing to the image. Calls sprite's getData() method to obtain the set of
   * points (vertices) transformed, drawn and connected. Hint. Consider copying the points to a java.awt.Polygon object, and
   * using Graphics2D's drawPolygon(poly) or fillPolygon(poly) method. Once rendered, obtain the visible drawing context from the strategy 
   * object, copying the BufferedImage to the visible canvas with the drawImage() method. Finally, strategy.show().
   */
  
  public void paintPanel() {
    BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g2D = (Graphics2D) image.getGraphics();

    // Setup the blank canvas
    g2D.setColor(Color.black);
    g2D.fillRect(0, 0, getWidth(), getHeight());
    g2D.setColor(Color.CYAN);
    g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    // your stuff here
    
    Polygon p1 = new Polygon();
    Polygon p2 = new Polygon();
    
    ArrayList<Point2D> ap2D = sprite.getData().getPoints();
    
    double x,y;
    
    for(int i=0; i<ap2D.size(); i++){
    	
    	double [] point = ap2D.get(i).getCoordinates();
        	
    	x=point[0];
    	y=point[1];
    
    	p1.addPoint((int)x,(int)y);   
    	p2.addPoint((int)x+100,(int)y-50);

    }
   
    g2D.translate((WIDTH/2)-60,(HEIGHT/2)-p1.getBounds().height+60); // "rolling"
 //	g2D.translate((WIDTH/2)-60,(HEIGHT/2)); 		// " Non-rolling "
    g2D.drawPolygon(p1);
    g2D.drawPolygon(p2);
    
    double x1,y1,x2,y2;
    
    for(int i=0; i<ap2D.size(); i++){
    	
    	double [] point = ap2D.get(i).getCoordinates();
    	
    	x1=point[0];
    	y1=point[1];
    	x2=x1+100;
    	y2=y1-50;
    	
    	g2D.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
    }
    
    Graphics g = strategy.getDrawGraphics();
    g.drawImage(image, 0, 0, this);
    strategy.show();
  }

  /**
   * Sequencer to organize calls and prove an animation speed.
   */
  public void run() {
    while (isVisible()) {
      updateScene();
      paintPanel();
      try { 
        Thread.sleep(SPEED);
      } catch (InterruptedException e) {}
    }
  	
  }
 
  /**
   * Instantiates an animation object,a sprite with some meaningful set of points and then calls the run method.
   */
  public static void main(String[] args) {
    Animation2D animation = new Animation2D();

    sprite = new Sprite();
    sprite.createData();
    animation.run();
  }

}