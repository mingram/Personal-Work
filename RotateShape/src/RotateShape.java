import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.font.*;
import java.awt.geom.*;

public class RotateShape extends JApplet {
  public static void main(String s[]) {
    JFrame frame = new JFrame();
    frame.setTitle("Rotate Shape");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JApplet applet = new RotateShape();
    applet.init();
    frame.getContentPane().add(applet);
    frame.pack();
    frame.setVisible(true);
  }
  
  public void init() {
    JPanel panel = new Panel2D();
    getContentPane().add(panel);
  }
}

class Panel2D extends JPanel{
  public Panel2D() {
    setPreferredSize(new Dimension(500, 400));
    setBackground(Color.white);
  }
  
  public void paintComponent(Graphics g) {

	int a = 0;
	int b = 0;
	int c = 15;
	  
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.red);

    int minDim = Math.min(getWidth(),getHeight())-2;
    Shape rect = new Rectangle2D.Double(-minDim/2, -minDim/2, minDim, minDim);
	GradientPaint paint = new GradientPaint(a,a, Color.white, b, b, Color.gray);


    AffineTransform tr = new AffineTransform();	
   
    tr.scale(0.8631,0.8631);
    tr.rotate(Math.PI/18);
    g2.translate(getWidth()/2, getHeight()/2);

    
    
    for(int x = 0; x<41; x++){
       
    	Color color = new Color(0,c,c);
   // 	paint = new GradientPaint((float) (rect.getBounds().getMaxX()-rect.getBounds().getMinX()),(float) (rect.getBounds().getMaxY()-rect.getBounds().getMinY()),color,(float) (rect.getBounds().getMaxX()-rect.getBounds().getMinX()),(float) (rect.getBounds().getMaxY()-rect.getBounds().getMinY()),color);
    	paint = new GradientPaint(a,a,color,b,b,color);
        g2.setPaint(paint);
        g2.fill(rect);
        c=c+6;
        g2.draw(rect);
    	rect = tr.createTransformedShape(rect);
    }
  }
  
}
