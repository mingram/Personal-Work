import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;
import java.lang.*;


/**
 * <b>Purpose:</b> Develop the <code>Circle</code> class that explores the circle as the limit of a regular <i>n</i>-gon as 
 * <i>n</i>&rarr; infinity. The the number of sides, <i>n</i>, of the <i>n</i>-gon increases in a linear manner, being redrawn each time. The <i>n</i>-gon
 * begins to take shape of a circle.
 * <p>
 * <b>Date:</b> 2009 10 22
 * <p>
 * @author M. Ingram
 *
 */
public class Circle extends JApplet {
	
  /**
   * Creates a <code>JFrame</code> in which the content will be displayed with properties such as title and size. 
   * A <code>JApplet</code> is then created and added to the <code>JFrame</code>.
   * @param args
   * 
   */
	
  public static void main(String s[]) {
    JFrame frame = new JFrame();

    frame.setTitle("Circle");
    JApplet applet = new Circle();

    applet.init();
    frame.getContentPane().add(applet);
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * Method that simply creates a new <code>JPanel</code> and retrieves and adds content to it.
   */
  public void init() {
    JPanel panel = new CirclePanel();

    getContentPane().add(panel);
  }

  /**
   * Instantiates the class <code>CirclePanel</code> that extends <code>JPanel</code>.
   * @author M. Ingram
   */
  
  public class CirclePanel extends JPanel implements Runnable {
    Polygon p;

    /**
     * Number of sides of the <i>n</i>-gon.
     */
    public int n = 3;

    /**
     * Area of the <i>n</i>-gon.
     */
    public double a;

    /**
     * Perimeter of the <i>n</i>-gon.
     */
    public double per;

    /**
     * Side length of each side of the <i>n</i>-gon.
     */
    public double s;

    /**
     * Radius of the <i>n</i>-gon.
     */
    public double r = 100;
    public int x;
    public int y;
  
    /**
     * Sets the size and background colour of the <code>CirclePanel</code>, as well as starting the <code>thread</code>. <br>
     * Starting the thread calls the <code>run</code> method.
     */
    public CirclePanel() {
	  
      setPreferredSize(new Dimension(640, 480));
      setBackground(Color.white);
      Thread thread = new Thread(this);
      thread.start();

    }

    /**
     *   Method that paints the <i>n</i>-gon. Using a <code>for</code> loop, <code>x</code> and <code>y</code> coordinates are set
     *   by assigning each to the position of <code>i</code> in the <code>pts</code> array. The points (x,y) are then added to the 
     *   polygon <code>p</code>. <p>
     *   Relating variables <code>r</code> and <code>n</code>, side length of the <i>n</i>-gon is calculated with the equation: <p><img src="http://mail.rsgc.on.ca/~mingram/sideLength.png"><p>
     *   Relating variables <code>n</code> and <code>s</code>, area of the <i>n</i>-gon is calculated with the equation: <p><img src="http://mail.rsgc.on.ca/~mingram/area.png">
     */
    public void paintComponent(Graphics g) {
	  
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
    
      g.translate(this.getWidth() / 2, this.getHeight() / 2);
      g2.setColor(Color.red);
      g2.drawOval(-100, -100, 200, 200);
      g2.setColor(Color.blue);
    
      g2.drawString("Area:", -150, -200);
      g2.drawString("Radius:", -165, -140);
      g2.drawString("Perimeter:", -180, -170);
  
      g2.draw(p);

      g2.drawString(String.format("%10.3f", a), -100, -200);
      g2.drawString(String.format("%10.3f", per), -100, -170);
      g2.drawString(String.format("%10.0f", r), -100, -140);
     
    }

    /**
     *  Using a <code>for</code> loops, points are generated that will be used by <code>paintComponent</code>.
     */
    public void run() {
      double deltat;

      while (n < 100) {
        deltat = Math.PI * 2 / n;
        p = new Polygon();
        for (int i = 0; i < n; i++) {
          p.addPoint((int) (r * Math.cos(i * deltat)),
              (int) (r * Math.sin(i * deltat)));
        }
        s = (2 * r * Math.sin(Math.PI / n));
        a = ((n / 4) * (s * s)) * Math.tan(Math.PI / n);
        per = n * s;
      
        repaint();
        n+=2;
   
        try {
          Thread.sleep(500);
        } catch (InterruptedException ex) {}	
    
      }
   
    }
  }
}
	
