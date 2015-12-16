import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <b>Purpose</b>: Develop the <code>Ultimate</code> class to serve as a Platform for further projects.
 * This class allows the program to run as either an Applet in a browser, or an Application as a runnable <code>jar</code>.
 * <b>Date</b>: 2009 09 26
 * @author M. Ingram
 *
 */

public class Ultimate extends JApplet implements ActionListener {

	static int width = 500;
	static int height = 500;
	static int x = 100;
	static int y = 100;
/**
 * Creates a <code>JFrame</code> in which the content will be displayed with properties such as title and size.
 * The <code>setLocation</code> method uses the <code>getGraphicsConfiguration</code> method to center the window.
 * Finally, <code>addWindowListener</code> is instantiated to allow the user to interact with the window.
 * 
 * @param args
 */
	
public static void main(String[] args) {
	
    JFrame frame = new JFrame();    
    frame.setTitle("Ultimate Application");
    frame.setSize(width, height);
  
    GraphicsConfiguration gc = frame.getGraphicsConfiguration();
    Rectangle JFrame= gc.getBounds();
    frame.setLocation((JFrame.width - width) / 2, (JFrame.height - height) / 2);

    JApplet applet = new Ultimate();
    applet.init();
    
    frame.getContentPane().add(applet);
    frame.pack();
    frame.setVisible(true);
    
    frame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent ev) {
		        System.exit(0);
		      }
		    });
    
}

/**
 * Method used to set up the <code>JPanel</code>. A <code>MouseListener</code> is added to respond to mouse clicks on the applet, 
 * with the method <code>mouseClicked</code> repainting the oval at the x and y coordinates of the mouse click.
 */

  public void init(){
	  
	  JPanel panel = new Panel2D();
	  getContentPane().add(panel);	 
	
	  JMenuBar mb = new JMenuBar();
	  setJMenuBar(mb);
	  JMenu menu = new JMenu("File");
	  mb.add(menu);
	  JMenuItem mi = new JMenuItem("Exit");
	  menu.add(mi);
	  mi.addActionListener(this);
	  
	  
  }
  
  class Panel2D extends JPanel{
	  public Panel2D() {
	   setPreferredSize(new Dimension(width, height));
	    setBackground(Color.white);
	    MouseListener mouseListener = new MouseAdapter() {
		     
	    		public void mouseClicked(MouseEvent ev) {
		        x = ev.getX();
		        y = ev.getY();
		       
		        repaint();
		      }
		    };
		    addMouseListener(mouseListener);
	  }
  /**
   * Simply paints a filled oval of the desired size.
   */
  
  public void paintComponent(Graphics g){
	  super.paintComponent(g);
	  Graphics2D g2 = (Graphics2D)g;
	  g2.fillOval(x-50, y-50, 100, 100);
	
	
  }
  
  }
  
  /**
   * When the user clicks <code>File, Exit</code> this method checks to see if exit was clicked, and performs the command.
   */
  
  public void actionPerformed(ActionEvent ev) {
	    String command = ev.getActionCommand();
	    if ("Exit".equals(command)) {
	      System.exit(0);
	    }
	  }

  }

  
