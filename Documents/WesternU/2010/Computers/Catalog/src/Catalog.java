import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

/**
 * <b>Purpose:</b> Develop the <code>Catalog</code> class that displays multiple parametric curves based on their parametric equations.
 * <p>
 * <b>Date:</b> 2009 10 17
 * <p>
 * @author M. Ingram
 *
 */

public class Catalog extends JApplet implements ActionListener {

	  SpiroPanel panel = null;
/**
 * Sets up the <code>frame</code> and adds the <code>applet</code> to the frame.
 * @param s
 */
  public static void main(String s[]) {
    JFrame frame = new JFrame();
    frame.setTitle("Catalog");
	frame.setPreferredSize(new Dimension(850,600));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JApplet applet = new Catalog();
    applet.init();
    frame.getContentPane().add(applet);
    frame.pack();
    frame.setVisible(true);
  }
/**
 * Sets up the list of options for what parametric curve will be displayed, as well as the magnification.
 */
  public void init() {
	  
	setLayout(new BorderLayout());
	Panel p = new Panel();
	p.setLayout(new GridLayout(15,1,10,5));
	add(p,BorderLayout.WEST);
	p.add(new Panel());
	p.add(new Label("Select Scaling"));
	Button button = new Button("2");
	p.add(button);
	button.addActionListener(this);
	button = new Button("1.5");
	p.add(button);
	button.addActionListener(this);
	button = new Button("1");
	p.add(button);
	button.addActionListener(this);
	button = new Button("1/2");
	p.add(button);
	button.addActionListener(this);
	button = new Button("1/3");
	p.add(button);
	button.addActionListener(this);
	
	p.add(new Panel());
	p.add(new Label("Select Parametric"));
	button = new Button("Bowditch");
	p.add(button);	
	button.addActionListener(this);
	button = new Button("Hypotrochoid");
	p.add(button);
	button.addActionListener(this);
	button = new Button("Hypocycloid");
	p.add(button);
	button.addActionListener(this);
	button = new Button("Tricuspoid (Deltoid)");
	p.add(button);
	button.addActionListener(this);
	button = new Button("Nephroid");
	p.add(button);
	button.addActionListener(this);
	button = new Button("Cardioid");
	p.add(button);
	button.addActionListener(this);
    panel = new SpiroPanel();
    getContentPane().add(panel);
  }
  /**
   * Determines what button was pressed, if any. When a button has been pressed, it assigns the <code>shapeType</code> to the
   * parametric curve corresponding to the button. <br>It then repaints the canvas. Repainting will simply remove any previous curves being displayed.
   * 
   */
  public void actionPerformed(ActionEvent ev) {
	    String command = ev.getActionCommand();
	    if ("Bowditch".equals(command)) {
	      panel.shapeType = panel.BOWDITCH;
	      repaint();
	    } else if ("Hypotrochoid".equals(command)) {
	      panel.shapeType = panel.HYPOTROCHOID;
	      repaint();
	    } else if ("Hypocycloid".equals(command)) {
	      panel.shapeType = panel.HYPOCYCLOID;
	      repaint();
	    } else if ("Tricuspoid (Deltoid)".equals(command)) {
	      panel.shapeType = panel.TRICUSPOID;
	      repaint();
	    } else if ("Nephroid".equals(command)) {
	      panel.shapeType = panel.NEPHROID;
	      repaint();
	    } else if ("Cardioid".equals(command)) {
	      panel.shapeType = panel.CARDIOID;
	      repaint();
	    } else if("2".equals(command)){
	      panel.sf=2.0;
	      repaint();
	    } else if("1.5".equals(command)){
	      panel.sf=1.5;
	      repaint();
	    } else if("1".equals(command)){
	      panel.sf=1;
	      repaint();
	    } else if("1/2".equals(command)){
	      panel.sf=0.5;
	      repaint();
	    } else if("1/3".equals(command)){
	      panel.sf=0.3;
	      repaint();
	    }
  }
}
/**
 * <b>Purpose:</b> Develop <code>SpiroPanel</code>, which extends <code>JPanel</code>, to display the six parametric curves.
 * <p>
 * <b>Date:</b> 2009 10 17
 * <p>
 * @author M. Ingram
 *
 */
class SpiroPanel extends JPanel{
  
  int nPoints = 1000;
  double r1 = 60;
  double r2 = 50;
  double p = 70;
  static final int NONE = 0;
  static final int BOWDITCH = 1;
  static final int HYPOTROCHOID= 2;
  static final int HYPOCYCLOID = 3;
  static final int TRICUSPOID = 4;
  static final int NEPHROID = 5;
  static final int CARDIOID = 6;
  public double sf=1;
  
  Shape s;
  int shapeType;

  /**
   * Simply creates a new <code>SpiroPanel</code>.
   */
  
  public SpiroPanel() {
    setPreferredSize(new Dimension(700, 500));
    setBackground(Color.white);
  }
  
/**
 * Draws all the content. This method works by using a <code>switch</code> statement, which creates a <code>shape</code> with certain parameters <br>
 * according to the active <code>shapeType</code>. An <code>if</code> statement at the end draws the <code>shape</code> that has been created.
 */
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.translate(350,300);
   
    int x1=0;
    int y1=0;
    int x2=0;
    int y2=0;

    g2.drawLine(0,-350,0,300);
    g2.drawLine(-350,0,350,0);

    for (int i=0; i<nPoints; i++) {
	    
	 double t = i*Math.PI/100;
 
	 switch(shapeType){
	
	 case BOWDITCH:
		x2=(int)(100*(Math.cos(3*t))*sf);
		y2=(int)(100*(Math.sin(4*t))*sf);
		s = new Line2D.Double(x1,y1,x2,y2);
		break;
		
	 case HYPOTROCHOID:
		 x2 = (int)((r1+r2)*Math.cos(t)+p*Math.cos((r1/r2-1)*t)*sf);
		 y2 = (int)((r1+r2)*Math.sin(t)-p*Math.sin((r1/r2-1)*t)*sf);     
		 s = new Line2D.Double(x1,y1,x2,y2);
	//	 s = new Line2D.Double(x1,y1,x2*0.5,y2*0.5);
		 break;
		 
	 case HYPOCYCLOID:
		double k = 3.8;
 		x2 = (int)(((r2*(k-1)*Math.cos(t)+r2*Math.cos((k-1)*t))*0.7)*sf);
 		y2 = (int)(((r2*(k-1)*Math.sin(t)-r2*Math.sin((k-1)*t))*0.7)*sf);
 		s = new Line2D.Double(x1,y1,x2,y2);
   	    break;
   	    
	 case TRICUSPOID:
		x2 = (int)(r1*(2*Math.cos(t)+Math.cos(2*t))*sf*0.8);
		y2 = (int)(r1*(2*Math.sin(t)-Math.sin(2*t))*sf*0.8);   
		s = new Line2D.Double(x1,y1,x2,y2);
		break;
		
	 case NEPHROID:
		x2 = (int)((r1*(3*Math.cos(t)-(Math.cos(3*t)))*0.5)*sf);
		y2 = (int)((r1*(3*Math.sin(t)-(Math.sin(3*t)))*0.5)*sf); 
		s = new Line2D.Double(x1,y1,x2,y2);
    	break;
    	  
	 case CARDIOID:	
		x2 = (int)(1.5*r2*Math.cos(t)*(1+Math.cos(t))*sf);
		y2 = (int)(1.5*r2*Math.sin(t)*(1+Math.cos(t))*sf);
		s = new Line2D.Double(x1,y1,x2,y2);
		
	    break;
	 }
	
	 if (s != null) {
	     g2.draw(s);
	     x1=x2;
	     y1=y2;
	  }
	 
    }
  }
}





