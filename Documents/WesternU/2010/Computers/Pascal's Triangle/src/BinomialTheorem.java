import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * <b>Purpose:</b> Develop the <code>BinomialTheorem</code> class that is very similar to the class <code>PascalIterative</code>, but instead of printing out integers, it plots a pixel for every number that is odd.
 * <p>
 * <b>Date:</b> 2009 10 31
 * <p>
 * @author M. Ingram
 *
 */

public class BinomialTheorem extends JApplet implements ActionListener{

	public static void main(String [] args) {
		JFrame frame = new JFrame();		
		frame.setTitle("Pascal: Iterative");
	    JApplet applet = new BinomialTheorem();
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
	 * Sets up a <code>JMenuBar</code> that includes an <i>Exit</i> option which closes the application.
	 */
	
	public void init() {
		JPanel panel = new PascalPanel();
		getContentPane().add(panel);

		  JMenuBar mb = new JMenuBar();
		  setJMenuBar(mb);
		  JMenu menu = new JMenu("File");
		  mb.add(menu);
		  JMenuItem mi = new JMenuItem("Exit");
		  menu.add(mi);
		  mi.addActionListener(this);
	}
	

/**
 * <b>Purpose:</b> Using an iterative approach, develop the <code>PascalPanel</code> that will paint and calculate the number of rows of Pascal's triangle, specified by the user through a <code>JOptionPane</code>.
 * <p>
 * <b>Date:</b> 2009 10 31
 * <p>
 * @author M. Ingram
 *
 */
public class PascalPanel extends JPanel {

	String input,line;
	private int y,r,x,n;

	/**
	 * Sets up the properties of the panel such as background colour, width, height, and the input of the user used for the number of rows to be displayed.
	 */
	public PascalPanel(){
		
	    setBackground(Color.white);
	    setPreferredSize(new Dimension(500,500));
	}
	
	/**
	 * Using two nested loops, the specified position of the number in Pascal's triangle is determined by adding the number directly above, and directly above to the left. 
	 * After this is done, the previous row is assigned to the current value, <code>temp</code>. A pixel is drawn for every odd number that is encountered.
	 */
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    Font courier = new Font("Courier",0,14);
	    g2.setFont(courier);
	   
	    x=0;
	   
	    
	    r=500;
	    y=15;
	    
	    int[] prev = new int [r];
	  
	    
	    for (int i = 0; i<r; i++) {
	    	
	    	x=0;
	    	
	    	int[] temp = new int [i+1];
	
	    	for (int c=0; c <= i; c++) {
   		
	    		if(c==0 || c==i){
	    			temp[c]=1;
	    			n=1;
	        		line=Integer.toString(n);
		    	}
		    	
	    		else {

	    			temp[c]=prev[c]+prev[c-1];
	    			n=temp[c];

	    		}
	    		
	    		if(n % 2 != 0){
	    			g2.drawLine(c,i,c,i);
	    			
	    		}
	    		
	    		
	    		
	    	}
	    	y+=25;
	    	prev=temp;
	    }  
	}

}
/**
 * Determines if the <i>Exit</i> option has been clicked. If it has, the application will be closed.
 */
	public void actionPerformed(ActionEvent ev) {
	String command = ev.getActionCommand();
    if ("Exit".equals(command)) {
      System.exit(0);
    }
	}
}
	


