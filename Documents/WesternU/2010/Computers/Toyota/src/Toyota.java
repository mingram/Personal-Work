import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;
import javax.swing.*;


/**
 * <b>Purpose</b>: To develop the class <i>Toyota</i> which uses ellipses to create the Toyota logo. The logo is to be able to resize
 * according to the size of the window. This allows the logo and text to stay proportional at all times.<p>
 * <b>Date</b>: 2009 10 03
 * 
 * @author M. Ingram
 *
 */

public class Toyota extends JApplet {

	static int width = 400;
	static int height = 300;
	static int x = 100;
	static int y = 100;
		
/**
 * Creates a <code>JFrame</code> in which the content will be displayed with properties such as title and size. 
 * A <code>JApplet</code> is then created and added to the <code>JFrame</code>.
 * @param args
 * 
 */
	
	public static void main(String [] args){

		JFrame frame = new JFrame();
		frame.setTitle("Java 2D: Toyota Logo");
		frame.setPreferredSize(new Dimension(width,height));

		JApplet applet = new Toyota();
		applet.init();
		frame.getContentPane().add(applet);
		
		frame.pack();
		frame.setVisible(true);
	    
	}

/**
 * Method that simply creates a new <code>JPanel</code> and retrieves and adds content to it.
 */
	  public void init(){
		  
		  JPanel panel = new Panel2D();
		  getContentPane().add(panel);	 
	  }


/**
 * Instantiates the class <code>Panel2D</code> that extends <code>JPanel</code>.
 * @author M. Ingram
 *
 */
	public class Panel2D extends JPanel {
		
/**
 * 	Method that sets the size of the <code>Panel2D</code> and sets the background colour to white.
 */
	  public Panel2D() {
	    setPreferredSize(new Dimension(width, height));
	    setBackground(Color.white);
	  }
/**
 *  Arguably the most important method used. This <code>paintComponent</code> method delivers all the visual content that is seen.
 *  <ol>
 *  	<li>Creates a new <code>Graphics2D</code> object under the name <code>g2</code>.</li>
 *  	<li>Creates three new <code>Ellipse2D.Double</code>s and sets their location and dimension.</li>
 *  	<li>Creates a new <code>AffinTransform</code> object which is used to scale all ellipse relative to the frame width and height.</li>
 *  	<li>Creates three new <code>Shape</code>s from the three ellipses, and transforms them.</li>
 *  	<li>The <code>Graphics2D</code> object is then translated to the desired position in the frame. 
 *  	<code>setRenderingHint</code> smoothes the edges of the ellipses. </li>
 *  	<li>The three transformed shapes are drawn.</li>
 *  	<li>A new <code>Font</code> is created, with Arial font, style of 1, and the size set to <code>x</code>/6. 
 *  	Two variables are created that retrieve the width and height of the <code>String</code>.</li>
 *  	<li>Finally, the <code>String</code> is drawn using properties of <code>shape1</code> and the <code>String</code> to position it correctly.</li>
 *  </ol>
 *  	
 */
	  public void paintComponent(Graphics g) {
		 		  
		  int x = (Math.min(getSize().width,getSize().height));
		  
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D)g;
	    g2.setColor(Color.red);
	    g2.setStroke(new BasicStroke((float)x/30F));
	    
	    Ellipse2D a = new Ellipse2D.Double(0, 0, 200, 150);
	    Ellipse2D b = new Ellipse2D.Double(33, 0, 133, 75);
	    Ellipse2D c = new Ellipse2D.Double(80, 12, 40, 133);
	    
	    AffineTransform tr = new AffineTransform();	
	    if (getWidth() / ((double)width) >= getHeight() / ((double)height)) {
	    	tr.scale(getHeight() / ((double) height), getHeight() / ((double)height));
	    }
	    else {
	    	tr.scale(getWidth() / ((double)width), getWidth() / ((double)width));
	    }

	    Shape shape1 = tr.createTransformedShape(a);
	    Shape shape2 = tr.createTransformedShape(c);
	    Shape shape3 = tr.createTransformedShape(b);
	    
	    g2.translate(this.getWidth()/2-(shape1.getBounds().width/2),this.getBounds().getHeight()/16);

	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //smooth edges
	    
	    g2.draw(shape1);
	    g2.draw(shape2);
	    g2.draw(shape3);
	    
	    Font font = new Font("Arial",1,(x/6));
	    g2.setFont(font);
	    FontMetrics fm = getFontMetrics(font);
	    int stringWidth = fm.stringWidth("TOYOTA");
	    int stringHeight = fm.getHeight();
	  
	    g2.drawString("TOYOTA",(int) ((shape1.getBounds().getWidth()/2)-fm.stringWidth("TOYOTA")/2),(int) shape1.getBounds().getMaxY()+fm.getHeight());
	  }
	}
}