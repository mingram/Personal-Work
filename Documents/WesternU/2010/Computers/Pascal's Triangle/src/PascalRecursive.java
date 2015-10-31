import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * <b>Purpose:</b> Through recursion, the <code>PascalIterative</code> class will be utilized to display the first 20 rows of the Pascal triangle.
 * <p>
 * <b>Date:</b> 2009 10 31
 * <p>
 * @author M. Ingram
 */

public class PascalRecursive extends JApplet implements ActionListener{

	public static void main(String [] args){
		JFrame frame = new JFrame();		
		frame.setTitle("Pascal: Recursive");
	    JApplet applet = new PascalRecursive();
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
	 * <b>Purpose:</b> Using a recursive approach, develop the <code>PascalPanel</code> that will paint and calculate the number of rows of Pascal's triangle, specified by the user through a <code>JOptionPane</code>.
	 * <p>
	 * <b>Date:</b> 2009 10 31
	 * <p>
	 * @author M. Ingram
	 */
	
	public class PascalPanel extends JPanel {

		String input;
		private int y,r,x,width,height;
		
	/**
	 * Sets up the properties of the panel such as background colour, width, height, and the input of the user used for the number of rows to be displayed.	
	 */
		
		public PascalPanel(){
			
		    setBackground(Color.white); 
		    input = JOptionPane.showInputDialog("Enter number of rows (1-30)", 10);
		    height = (int)(Integer.parseInt(input)*30);
		    width =  (int)(Integer.parseInt(input)*60);
		    setPreferredSize(new Dimension(width, height));
		}
		
	/**
	 * Using a method <code>p</code>, the next row is determines by returning the integer directly above, and directly above and to the left one space.
	 */
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
		    Graphics2D g2 = (Graphics2D) g;
		    Font courier = new Font("Courier",0,14);
		    g2.setFont(courier);
		   
		    x=0;
		    r=Integer.parseInt(input)-1;
		    y=15;

		    
		    for (int i = 0; i < r+1; i++) {
		    	
		    	x=0;
		    	
		    	
		    	for (int c = 0; c <= i; c++) {
		    
		    	String line = new String(Integer.toString(p(i,c)));
	    		g2.drawString(line,x,y);
		    	x+=60;
		    		
		    	}
		    	
		    	y+=30;
		    	
		    }  
		}
		
	/**
	 * Determines the next row by returning the integer above, and adding it to the interger above and to the left one space.
	 * @param r Row
	 * @param c Column
	 * @return	integer
	 */
		public int p(int r, int c){

			//basis
			if(c==0 || c==r){
				return 1;
			}
			//induction
			return p(r-1,c-1)+p(r-1,c);
			
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