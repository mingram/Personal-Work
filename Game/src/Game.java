import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;

/**
 * <b>Purpose</b>: Develop the <code>Game</code> class that extends <code>JApplet</code> that will be used to display points that create an image.
 * This image will exhibit properties similar to Coxeter's/Escher's <i>Circle Limit</i>, found <a href="http://darcy.rsgc.on.ca/ACES/ICS4U/images/CircleLimitIII.jpg">here</a>.
 * <p>
 * <b>Date:</b> 2009 10 22
 * <p>
 * @author M. Ingram
 *
 */
public class Game extends JApplet {

	/**
	 * Creates a <code>JFrame</code> in which the content will be displayed with properties such as title and size. 
	 * A <code>JApplet</code> is then created and added to the <code>JFrame</code>.
	 * @param args
	 * 
	 */
	
	public static void main(String [] args){
		
		JFrame frame = new JFrame();
		frame.setTitle("Game");
		JApplet applet = new Game();
		applet.init();
		frame.getContentPane().add(applet);
		frame.pack();
	    frame.setVisible(true);
		
	}
	
	/**
	 * Method that simply creates a new <code>JPanel</code> and retrieves and adds content to it.
	 */
	
	public void init(){
		JPanel panel = new GamePanel();
	    getContentPane().add(panel);
		
	}

/**
 * Instantiates the class <code>GamePanel</code> that extends <code>JPanel</code>.
 * @author M. Ingram
 *
 */
 public class GamePanel extends JPanel{
	
	public int limit=1000000;
	public int x=300;
	public int y=300;
	public int num;
/**
 * 	Sets the size and background colour of the <code>GamePanel</code>.
 */
	public GamePanel(){
		 setPreferredSize(new Dimension(640, 480));
		 setBackground(Color.white);
	}
/**
 * 	Sets three vertices, top left, bottom left, and bottom right as fixed points. 10<sup>6</sup> points are generated using a <code>for</code> loop.<br> 
 * 	On each iteration, a random integer in the range from 0-2 is generated, and a new point, 'P', is made.
 * 	That point is equidistant from P to the respective vertex. 
 * 	This process is repeated throughout the loop.
 */
	public void paintComponent(Graphics g){
		
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D)g;
		 
		 int height=this.getHeight();
		 int width=this.getWidth();

		 Point2D.Double pointA=new Point2D.Double(0,0);
		 Point2D.Double pointB=new Point2D.Double(0,height);
		 Point2D.Double pointC=new Point2D.Double(width,height);

		 for (int n=0; n<limit; n++){ 
			 
			 num = (int)(Math.random()*3);
			 
		 if(num==0){
			 g2.setColor(Color.red);
			 x=(int)((x+pointA.getX())/2);
			 y=(int)((y+pointA.getY())/2);	 
		 }
		 
		 else if(num==1){
			 g2.setColor(Color.blue);
			 x=(int)((x+pointB.getX())/2);
			 y=(int)((y+pointB.getY())/2);	 
			 
		 }
		 
		 else if(num==2){
			 g2.setColor(Color.green);
			 x=(int)((x+pointC.getX())/2);
			 y=(int)((y+pointC.getY())/2);	
			 
		 }
			g2.drawLine(x,y,x,y);
		 }
		 
	}
  }
}
