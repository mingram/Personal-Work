import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;

import javax.swing.*;

/*******************************
 * Calculus Project: qualify() *
 *******************************/
public class Qualify extends JApplet implements ActionListener {

	static String[] list = { "tan(2*x)", "xsin(x)", "x^2ln(3x)",
			"(2x-1)(1-4x^2)", "x^(3E)", "-1.5abs(2x)", "4sqrt(5-3.1x)",
			"(3x^4-4x^3)/(2-cos(1/(2x)))", "tan(2x)/3x", "e^atan(px)", "3ePE" };

	private JLabel label,dLabel,rLabel;
	private JTextField enter,domain,range;
	private String eq;
	
	private int width,height;

	Calculus panel = null;

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setTitle("Calculus");
		frame.setPreferredSize(new Dimension(700,700));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JApplet applet = new Qualify();
		applet.init();
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);

		for (String def : list) {
			Function f = new Function(def);
		//	System.out.println(def + " qualified yields " + f);
			//System.out.println(f.f(0, 1.5));
			// System.out.println(f.reportStructure());

		}


	}

	public void init() {

		JPanel input = new JPanel();
		FlowLayout flow = new FlowLayout();
		input.setBackground(Color.white);

		enter = new JTextField();
		domain = new JTextField();
		range = new JTextField();

		label = new JLabel();
		label.setText("f(x) = ");

		enter.setText("sin(x)");
		enter.addActionListener(this);
		enter.setColumns(20);
		
		dLabel = new JLabel();
		dLabel.setText("Domain: ");
		
		domain.setText("1");
		domain.addActionListener(this);
		domain.setColumns(10);
		
		rLabel = new JLabel();
		rLabel.setText("Range: ");
		
		range.setText(domain.getText());
		range.addActionListener(this);
		range.setColumns(10);

		flow.setAlignment(3);

		input.setLayout(flow);
		input.add(label);
		input.add(enter);
		input.add(dLabel);
		input.add(domain);
		input.add(rLabel);
		input.add(range);

		setLayout(new BorderLayout());
		getContentPane().add(input, "North");
		
		panel = new Calculus();
		getContentPane().add(panel);
		getContentPane().setBackground(Color.white);

	}

	public void actionPerformed(ActionEvent arg0) {
		eq = enter.getText();
		panel.width = Integer.parseInt(domain.getText());
		panel.height = Integer.parseInt(range.getText());	
		range.setText(domain.getText());
		repaint();
	}

	class Calculus extends JPanel {
		
		Shape s;
		
		private int width;
		private int height;
		
		
		public Calculus() {
			setBackground(Color.white);
		}

		public void paintComponent(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			g2.translate(350, 350);
			
			g2.drawLine(0, -350, 0, 350);
			g2.drawLine(-350, 0, 350, 0);
			
			g2.drawString("+"+Integer.parseInt(domain.getText()),325,12);
			g2.drawString("-"+Integer.parseInt(domain.getText()),-350,12);
			g2.setColor(Color.red);
			
			int y1=0;
			int y2=0;
			
			if (eq != null) {
				
				Function f = new Function(eq);
			
				for (int i=-350; i < 350; i++) {
					y2=(int)(100*f.f(0,(i*width)));
					g2.draw(new Line2D.Double(i,y1,i,y2));
					y1=y2;
				}
			}
		}
	}
}