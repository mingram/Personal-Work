import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.*;

/**
 * Developed for 2009/2010 RSGC ICS4U ACES: <a
 * href="http://darcy.rsgc.on.ca/ACES/ICS4U/Tasks/0910Tasks.htm#Koch"
 * >Plane-Filling Curves Assignment</a>.<br>
 * <b>Date: 2009 11 12</b>
 * 
 * @author C. D'Arcy
 */
public class LSystems extends JApplet implements ActionListener {
	// The program's base dimensions.

	private static Dimension appDimen = new Dimension(400,400);
	// The singular panel onto which will be drawn the individual monsters
	static Content content;	
	private static boolean isKochActive = true;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setTitle("Plane-Filling Curves");
		// end program when window is closed
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		JApplet applet = new LSystems();

		applet.init();
		GraphicsConfiguration gc = frame.getGraphicsConfiguration();
		Rectangle rect = gc.getBounds();

		frame.setLocation((rect.width - appDimen.width) / 2,
				(rect.height - appDimen.height) / 2);
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
		content.createNewImage();
		content.repaint();
	}

	public LSystems() {
		
	}

	public void init() {
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		TreeMap<String,Object> IFS = new TreeMap<String,Object>();
		
		JMenu menu;

		/*
		 * menu = new JMenu("File"); mb.add(menu); addMenuItem(menu, "Exit",
		 * "Exit", this);
		 */
		
		menu = new JMenu("Koch");
		mb.add(menu);

		addMenuItem(menu, "Figure 1.7 a)", "RBK1", this);
		addMenuItem(menu, "Figure 1.9 a)", "IC1", this);
		addMenuItem(menu, "Figure 1.9 b)", "OE1", this);
		addMenuItem(menu, "Figure 1.9 c)", "MI1", this);
		addMenuItem(menu, "Figure 1.9 f)", "MK1", this);
		
		menu = new JMenu("FASS");
		mb.add(menu);

		addMenuItem(menu, "Islands (1.8)", 		"Islands",this);
		addMenuItem(menu, "Sierpinski (1.10b)", "RBK2", this);
		addMenuItem(menu, "Dragon (1.10a)", 	"IC2", this);
		addMenuItem(menu, "Hilbert (2.7b)",		"OE2", this);
		addMenuItem(menu, "Gosper (1.11a)",	 	"MI2", this);
		addMenuItem(menu, "Peano (2.7b)", 		"MK2", this);

		menu = new JMenu("Bracketed");
		mb.add(menu);
		addMenuItem(menu, "Figure 1.24 a)", "124_A", this);
		addMenuItem(menu, "Figure 1.24 b)", "124_B", this);
		addMenuItem(menu, "Figure 1.24 c)", "124_C", this);
		addMenuItem(menu, "Figure 1.24 d)", "124_D", this);
		addMenuItem(menu, "Figure 1.24 e)", "124_E", this);
		addMenuItem(menu, "Figure 1.24 f)", "124_F", this);
		
		
		menu = new JMenu("IFS");
		mb.add(menu);
	
		addMenuItem(menu, "Fern #1", "FERN1", this);
		addMenuItem(menu, "Fern #2", "FERN2", this);
		addMenuItem(menu, "Leaf", "LEAF", this);
		addMenuItem(menu, "Maple Leaf", "MAPLELEAF", this);
		addMenuItem(menu, "Tree #1", "TREE1", this);
		addMenuItem(menu, "Tree #2", "TREE2", this);
		addMenuItem(menu, "Sierpinski", "Sierpinski", this);
		addMenuItem(menu, "Twin Christmas Tree", "Christmas", this);		
		
		content = new Content();
		getContentPane().add(content);
	}

	public void start() {
		content.createNewImage();
		content.repaint();
	}

	public void addMenuItem(JMenu menu, String label, String action,
			LSystems app) {
		JMenuItem mi = new JMenuItem(label);
		mi.setActionCommand(action);
		menu.add(mi);
		mi.addActionListener(app);
	}

	public void actionPerformed(ActionEvent ev) {
		String command = ev.getActionCommand();

		if ("Exit".equals(command)) {
			System.exit(0);
		} else if ("RBK1".equals(command)) {
			content.current = content.new Koch("Figure 1.7 a)", "F-F-F-F", "F",
					new String[] { "F+FF-FF-F-F+F+FF-F-F+F+FF+FF-F" }, 0, 300,
					90, 8, 2, -90, -90);
			isKochActive=true;

		} else if ("IC1".equals(command)) {
			content.current = content.new Koch("Figure 1.9 a)", "F-F-F-F", "F",
					new String[] { "FF-F-F-F-F-F+F" }, 0, 600, 90, 4, 4, 80,
					-150);
			isKochActive=true;
			
		} else if ("OE1".equals(command)) {
			content.current = content.new Koch("Figure 1.9 b)", "F-F-F-F", "F",
					new String[] { "FF-F-F-F-FF" }, 0, 300, 90, 3, 4, -150, -150);
			isKochActive=true;
			
		} else if ("MI1".equals(command)) {
			content.current = content.new Koch("Figure 1.9 c)", "F-F-F-F", "F",
					new String[] { "FF-F+F-F-FF" }, 0, 400, 90, 3, 3, 110, 40);
			isKochActive=true;
			
		} else if ("MK1".equals(command)) {
			content.current = content.new Koch("Figure 1.9 f)", "F-F-F-F", "F",
					new String[] { "F-F+F-F-F" }, 0, 700, 90, 3, 4, -110, -110);
			isKochActive=true;
			
		} else if ("Islands".equals(command)){
			content.current = content.new Koch("Islands (1.8)", "F-F-F-F", "Ff",
					new String[] { "F+f-FF+F+FF+Ff+FF-f+FF-F-FF-Ff-FFF",
							"ffffff" }, 0, 40, 90, 3, 2, -80,-80);
			isKochActive=true;
			
		} else if ("RBK2".equals(command)){
			content.current = content.new Koch("Sierpinski (1.10b)", "X", "XY",
					new String[] { "YF-XF-Y","XF+YF+X" }, 0, 3000, 60, 3, 6, -132, 112);
			isKochActive=true;
			
		} else if ("IC2".equals(command)){
			content.current = content.new Koch("Dragon (1.10a)", "FX", "XY",
					new String[] { "X+YF","FX-Y" }, 0, 450000, 90, 3, 10, -45, 98);
			isKochActive=true;
			
		} else if ("OE2".equals(command)){
			content.current = content.new Koch("Hilbert (2.7b)", "X", "XY",
					new String[] { "-YF+XFX+FY-",
							"+XF-YFY-FX+" }, 0, 2000, 90, 3, 5, -130, -130);
			isKochActive=true;
			
		} else if ("MI2".equals(command)) {
			content.current = content.new Koch("Gosper (1.11a)", "XF", "XY",
					new String[] { "X+YF++YF-FX--FXFX-YF+",
						"-FX+YFYF++YF+FX--FX-Y" }, 0, 400, 60, 3, 4, 50, 130);
			isKochActive=true;
			
		} else if ("MK2".equals(command)) {
			content.current = content.new Koch("Peano (2.7a)", "X", "XY",
					new String[] { "XFYFX+F+YFXFY-F-XFYFX",
						"YFXFY-F-XFYFX+F+YFXFY" }, 0, 300, 90, 3, 3, -145, 145);
			isKochActive=true;
			
		} else if ("124_A".equals(command)) {
			content.current = content.new Koch("Figure 1.24 a)", "F", "F",
					new String[] { "F[+F]F[-F]F" }, -90, 1200, 25.7, 3, 5, 0, 200);
			isKochActive=true;
			
		} else if ("124_B".equals(command)) {
			content.current = content.new Koch("Figure 1.24 b)", "F", "F",
					new String[] { "F[+F]F[-F][F]" }, -90, 1500, 20, 3, 5, 0, 200);
			isKochActive=true;
			
		} else if ("124_C".equals(command)) {
			content.current = content.new Koch("Figure 1.24 c)", "F", "F",
					new String[] { "FF-[-F+F+F]+[+F-F-F]" }, -90, 565, 22.5, 3, 4, 0, 200);
			isKochActive=true;
			
		} else if ("124_D".equals(command)) {
			content.current = content.new Koch("Figure 1.24 d)", "X", "XF",
					new String[] { "F[+X]F[-X]+X", "FF" }, -90, 3500, 20, 3, 7, 0, 200);
			isKochActive=true;
			
		} else if ("124_E".equals(command)) {
			content.current = content.new Koch("Figure 1.24 e)", "X", "XF",
					new String[] { "F[+X][-X]FX", "FF" }, -90, 3500, 25.7, 3, 7, 0, 200);
			isKochActive=true;
			
		} else if ("124_F".equals(command)) {
			content.current = content.new Koch("Figure 1.24 f)", "X", "XF",
					new String[] { "F-[[X]+X]+F[+FX]-X", "FF" }, -90, 1200, 22.5, 3, 5, 0, 200);
			isKochActive=true;
			
		} else if ("FERN1".equals(command)) {
			double[][] d = {{0.75, 0.04, -0.04, 0.85, 0.0, 1.6, 0.74},
					{0.0, 0.0, 0.0, 0.16, 0.0, 0.0, 0.84},
					{0.2, -0.26, 0.23, 0.22, 0.0, 1.6, 0.92},
					{-0.15, 0.28, 0.26, 0.24, 0.0, 0.44, 1.0}};
				
			isKochActive = false;
			content.code = content.new Code(d, 5.5, 0, 5.5, -5, 1, 1);
			content.ifs = content.new IFS(new Point2D.Double(appDimen.width / 60,
					appDimen.height / 2), content.code);
		} else if ("FERN2".equals(command)) {
			double[][] d = { { 0.85, 0.04, -0.04, 0.85, 0.0, 1.6, 0.85 },
					{ 0.0, 0.0, 0.0, 0.16, 0.0, 0.0, 0.92 },
					{ 0.2, -0.26, 0.23, 0.22, 0.0, 1.6, 0.99 },
					{ -0.15, 0.28, 0.26, 0.24, 0.0, 0.44, 1.0 } };

			isKochActive = false;
			content.code = content.new Code(d, 5.5, 0, 5.5, -4, 1, 1);
			content.ifs = content.new IFS(new Point2D.Double(appDimen.width / 60,
					appDimen.height / 2), content.code);
			
		} else if ("LEAF".equals(command)) {
			double[][] d = {
					{ 0.0000, 0.2439, 0.0000, 0.3053, 0.0000, 0.0000, 0.25 },
					{ 0.7248, 0.0337, -0.0253, 0.7426, 0.2060, 0.2538, 0.50 },
					{ 0.1583, -0.1297, 0.3550, 0.3676, 0.1383, 0.1750, 0.75 },
					{ 0.3386, 0.3694, 0.2227, -0.0756, 0.0679, 0.0826, 1.0 } };

			isKochActive = false;
			content.code = content.new Code(d, 1.3, 0.4, 0.5, -0.5, 1, 1);
			content.ifs = content.new IFS(new Point2D.Double(appDimen.width / 60,
					appDimen.height / 2), content.code);
			
		} else if ("MAPLELEAF".equals(command)) {
			double[][] d = {
					{ 0.1400, 0.0100, 0.0000, 0.5100, -0.0800, -1.3100, 0.2500 },
					{ 0.4300, 0.5200, -0.4500, 0.5000, 1.4900, -0.7500, 0.5000 },
					{ 0.4500, -0.4900, 0.4700, 0.4700, -1.6200, -0.7400, 0.7500 },
					{ 0.4900, 0.0000, 0.0000, 0.5100, 0.0200, 1.6200, 1.00 } };

			isKochActive = false;
			content.code = content.new Code(d, 8, 0, 0.25, -7.5, 1, 1);
			content.ifs = content.new IFS(new Point2D.Double(appDimen.width / 60,
					appDimen.height / 2), content.code);
			
		} else if ("TREE1".equals(command)) {
			double[][] d = {
					{ 0.0100, 0.0000, 0.0000, 0.4500, 0.0000, 0.0000, 0.2500 },
					{ -0.0100, 0.0000, 0.0000, -0.4500, 0.0000, 0.4000, 0.5000 },
					{ 0.4200, -0.4200, 0.4200, 0.4200, 0.0000, 0.4000, 0.7500 },
					{ 0.4200, 0.4200, -0.4200, 0.4200, 0.0000, 0.4000, 1.00 } };

			isKochActive = false;
			content.code = content.new Code(d, 1, 0, 0.5, -0.5, 1, 1);
			content.ifs = content.new IFS(new Point2D.Double(appDimen.width / 60,
					appDimen.height / 2), content.code);
			
		} else if ("TREE2".equals(command)) {
			double[][] d = {
					
					{ 0.1950, -0.4880, 0.3440, 0.4430, 0.4431, 0.2452, 0.2000 },
					{ 0.4620, 0.4140, -0.2520, 0.3610, 0.2511, 0.5692, 0.4000 },
					{ -0.6370, 0.0000, 0.0000, 0.5010, 0.8562, 0.2512, 0.6000 },
					{ -0.0350, 0.0700, -0.4690, 0.0220, 0.4884, 0.5069, 0.8000 },
					{ -0.0580, -0.0700, 0.4530, -0.1110, 0.5976, 0.0969, 1.000 } };

			isKochActive = false;
			content.code = content.new Code(d, 1.5, 0.5, 0.5, -0.5, 1, 1);
			
			content.ifs = content.new IFS(new Point2D.Double(1, 1), content.code);
			
		} else if ("Sierpinski".equals(command)) {
			double[][] d = {
					
					{ 0.5, 0.0, 0.0, 0.5, 0.0, 0.0, 0.333 },
					{ 0.5, 0.0, 0.0, 0.5, 1.0, 2.0, 0.666 },
					{ 0.5, 0.0, 0.0, 0.5, 2.0, 0.0, 0.999 }};

			isKochActive = false;
			content.code = content.new Code(d, 1.5, 0.5, 0.5, -0.5, 1, 1);
			
			content.ifs = content.new IFS(new Point2D.Double(1,1), content.code);
			

		}  else if ("Christmas".equals(command)) {
			double[][] d = {
					
					
					{ 0.0, -0.5, 0.5, 0.0, 0.5, 0.0, 0.333 },
					{ 0.0, 0.5, -0.5, 0.0, 0.5, 0.5, 0.666 },
					{ 0.5, 0.0, 0.0, 0.5, 0.25, 0.5, 0.999 }};

			
			
			isKochActive = false;
			
			content.code = content.new Code(d, 1.5, 0.5, 0.5, -0.5, 1, 1);
			
			content.ifs = content.new IFS(new Point2D.Double(1,1), content.code);

		}	
		
	
		content.createNewImage();
		content.repaint();

	}

	/**
	 * A JPanel class used to as a container for all L-System renderings.
	 * 
	 * @author C. D'Arcy (based on Ireland Comery's earlier Parametric Equation
	 *         assignment)
	 */
	public class Content extends JPanel {

		public Code code;
		// The temporary buffered image used for drawing off screen...
		private BufferedImage img;
		// The reference to img's drawing canvas...
		private Graphics2D drawImg;
		// The active plane-filling curve...
		private Koch current;
		private IFS ifs;
		
		// Canvas colours...
		private Color backgroundColor;
		private Color curveColor;

		/**
		 * Constructor initializes
		 * <ol>
		 * <li>the Triadic Koch Snowflake as the initial Monster object</li>
		 * <li>the active Canvas colours</li>
		 * <li>the offscreen BufferedImage</li>
		 * </ol>
		 */
		public Content() {
			
			current = new Koch("Triadic Koch Snowflake", "F--F--F", "F",
					new String[] { "F+F--F+F" }, 0, 275, 60, 3, 3,
					-140,-80);	
			
		
			backgroundColor = new Color(0, 0, 128); // navy
			curveColor = Color.white;
			img = new BufferedImage(appDimen.width, appDimen.height,
					BufferedImage.TYPE_INT_RGB);

			drawImg = img.createGraphics();
			setPreferredSize(appDimen);
		
		}

		/**
		 * Creates and sets up a new <code>BufferedImage</code> with
		 * <code>this component</code>'s size, and draws the current Monster
		 * curve onto that <code>BufferedImage</code>.
		 */
		public void createNewImage() {

			try {

				img = new BufferedImage(getWidth(), getHeight(),
						BufferedImage.TYPE_INT_RGB);
				drawImg = img.createGraphics();
				drawImg.setColor(curveColor);
				drawImg.setBackground(backgroundColor);
				drawImg.clearRect(0, 0, getWidth(), getHeight());

				drawImg.translate(getWidth() / 2, getHeight() / 2);
				drawImg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				if(isKochActive)
					current.draw(drawImg);
				else {
					drawImg.scale(0.80,0.80);
					ifs.draw(drawImg);
				}


			} catch (IllegalArgumentException e) {
			}
		}

		/**
		 * Returns the main <code>BufferedImage</code> being used by the
		 * <code>paintComponent</code> method.
		 * 
		 * @return the <code>BufferedImage</code> currently being used as a
		 *         graphic.
		 */
		public BufferedImage getImage() {
			return img;
		}

		/**
		 * Overridden <code>paintComponent</code> method, to draw to
		 * <code>this</code> component.
		 * 
		 * @param g
		 *            a graphic to draw on.
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2D = (Graphics2D) g;
			g2D.drawImage(getImage(), 0, 0, this);
		}

		/**
		 * This class encapsulates the details and drawing of the Koch category
		 * of plane-filling curves.
		 */
		public class Koch {
		
			/**
			 * The name of this instance of the Koch object
			 */
			private String name;

			/** the initiator (axiom) of the fractal */
			private String axiom;

			/** holds the characters for which productions exist */
			private String keys;

			/**
			 * Holds the characters defining the fractals as a sequence of
			 * drawing commands for the Turtle
			 */
			private String definition;

			/**
			 * The array of productions (generators) that replace their
			 * respective key in each generation
			 */
			private String[] productions;

			/** The initial angle of the Turtle in degrees */
			private double heading;

			/**
			 * The distance or length of each line segment(decreases with each
			 * generation).
			 */
			private double distance;

			/** The angle the heading of the turtle is modified by at each turn */
			private double delta;

			/** the factor used to decrease the distance with each generation */
			private int factor;

			/** The number of generations to propagate. */
			private int n;

			/**
			 * The initial x position of the turtle
			 */
			private double xPos;

			/**
			 * The initial y position of the turtle
			 */
			private double yPos;

			/**
			 * Constructor for basic Koch curve: one key
			 * 
			 * @param name
			 *            the name of the Koch curve
			 * @param axiom
			 *            the initial (generation 0) curve (the initiator in
			 *            Koch parlance)
			 * @param keys
			 *            the String containing the keys. Koch curve only has
			 *            one key but keep as a String for future development
			 * @param productions
			 *            the array of productions. Koch curve only has one
			 *            production but keep as an array of String for future
			 *            development
			 * @param heading
			 *            the initial heading of the drawing pen (turtle) in
			 *            degrees. 0 degrees is east. Positive is
			 *            Counterclockwise.
			 * @param distance
			 *            the length of the line to draw in pixels when moving
			 *            the turtle forward (the F command)
			 * @param delta
			 *            the change in the turtle's heading when executing + or
			 *            - command. + is counterclockwise and - is clockwise
			 * @param factor
			 *            the reduction ratio applied to distance after each
			 *            generation
			 * @param n
			 *            the number of generations to propagate the curve
			 * @param xPos
			 *            the initial x position (column) of the turtle
			 * @param yPos
			 *            the initial y position (row) of the turtle
			 */

			public Koch(String name, String axiom, String keys,
					String[] productions, double heading, int distance,
					double delta, int factor, int n, double xPos, double yPos) {

				this.name = name;
				this.axiom = axiom;
				this.keys = keys;
				this.productions = productions;
				this.heading = Math.toRadians(heading);
				this.distance = distance;
				this.delta = delta * Math.PI / 180.0;
				this.factor = factor;
				this.n = n;
				this.xPos = xPos;
				this.yPos = yPos;
				definition = generate(n);
			}

			/**
			 * Simple recursive method that returns a <code>String</code>
			 * containing the set of instructions needed to draw the curve.
			 * 
			 * @param n
			 *            Number of generations to be produced.
			 * @return the <code>String</code> used as the
			 *         <code>definition</code>.
			 */
			public String generate(int n) {

				if (n == 0)
					return axiom;

				distance /= factor;

				return myReplace(generate(n - 1));

			}
			
			/**
			 * Goes through the <code>string</code>, checking each position.<br>
			 * If the character at the position is not a <code>key</code>, simply add it to the new <code>string</code>.<br>
			 * If the character <i>is</i> a <code>key</code>, insert its corresponding production statement into the new <code>string</code>.
			 * @param s A string.
			 * @return The new definition.
			 */
			
			public String myReplace(String s) {

				String temp = "";

				for (int i = 0; i < s.length(); i++) {

					int position = keys.indexOf(s.charAt(i));

					if (position < 0)
						temp += s.charAt(i);

					else
						temp += productions[position];
				}
				return temp;
			}

			/**
			 * This method draws the desired shape.
			 * <ol>
			 * <li>The definition is set by calling the <code>generate</code> method.</li>
			 * <li>Using a <code>for</code> loop, each position of the <code>definition</code> is examined. <p> 
			 * 
			 * <ul><li>If the character is an <i>F</i>, a line is drawn from the starting x and y coordinates,
			 * to the new coordinates, defined by: <p>
			 * 
			 * <code>
			 * 	x2 = xPos+distance*Math.cos(heading) <br>
			 * 	y2 = yPos+distance*Math.sin(heading) <p>
			 * </code>
			 * </li>
			 * 
			 * <li>If the character is an <i>f</i>, the "pen" is lifted from the canvas and moved to the new x and y coordinates.</li>
			 * 
			 * <li>If the character is a <code>-</code>, the <code>heading</code> is modified by adding to it <code>delta</code>.
			 * This will turn the direction of the new line to be drawn, clockwise.</li>
			 * 
			 * <li>If the character is a <code>+</code>, the <code>heading</code> is modified by subtracting from it <code>delta</code>.
			 * This will turn the direction of the new line to be drawn, counter-clockwise.</li>
			 * 
			 * <li>If the character is a <code>[</code>, the current drawing state (position, heading, and possibly color and line thickness) of the <code>Turtle</code> 
			 * is preserved by pushing it onto a <code>Stack</code>.</li>
			 * 
			 * <li>If the character is a <code>]</code>, the current drawing state is restored by popping it from the <code>Stack</code>, allowing it to 'return' to a node and continue drawing elsewhere.</li>
			 * 
			 * </ul>
			 * </li>
			 * </ol>
			 * 
			 * @param g2
			 *            A <code>Graphics2D</code> object.
			 */

			public void draw(Graphics2D g2) {
		
			Turtle turtle;
			Stack <Turtle> stack = new Stack<Turtle>();
			double thickness = 1.0;
			double r = 255;
			double g = 255;
			double b = 255;
			
		
			
				for (int i = 0; i < definition.length(); i++) {

					double x2 = (xPos + distance * Math.cos(heading));
					double y2 = (yPos + distance * Math.sin(heading));

					switch (definition.charAt(i)) {

					case 'F':
						g2.setStroke(new BasicStroke((float)thickness));
						g2.setColor(new Color((int)r,(int)g,(int)b));
						g2.drawLine((int)xPos, (int)yPos, (int) x2, (int) y2);
						xPos = x2;
						yPos = y2;
						break;
						
					case 'f':
						xPos = x2;
						yPos = y2;
						break;

					case '-':
						heading += delta;
						break;

					case '+':
						heading -= delta;
						break;
						
					case '[':
						stack.push(new Turtle((int)xPos,(int)yPos,heading));
						g*=0.75;
						b*=0.66;
						
						break;

					case ']':
						turtle = stack.pop();
						xPos = turtle.getXPos();
						yPos = turtle.getYPos();
						heading = turtle.getHeading();
						b*=1.25;
						g*=1.333333333333;
				
						break;
					}
				}
			}
		} // Koch
		
		public class Turtle {
		
		/**
		 * The x position of the Turtle.	
		 */
			private int xPos;
			
		/**
		 * The y position of the Turtle.	
		 */
			private int yPos;
			
		/**
		 * The heading of the Turtle.	
		 */
			private double heading;
			
		/**
		 * Constructor for the Turtle object.	
		 * @param x X position of the Turtle.
		 * @param y Y position of the Turtle.
		 * @param heading Heading of the Turtle.
		 */
			public Turtle(int x, int y, double heading){
				
				this.xPos = x;
				this.yPos = y;
				this.heading = heading;
			}
			
			/**
			 * Getter for <code>xPos</code>.
			 * @return current xPos
			 */
			
			public double getXPos(){
				return xPos;
			}
			
			/**
			 * Getter for <code>yPos</code>.
			 * @return current yPos
			 */
			
			
			public double getYPos(){
				return yPos;
			}
			
			/**
			 * Getter for <code>heading</code>.
			 * @return current heading
			 */
			
			
			public double getHeading(){
				return heading;
			}
			
			/**
			 * Sets the new <code>xPos</code>.
			 * @param x the new <code>xPos</code>
			 */
			
			public void setXPos(int x){
				xPos = x;
			}
			
			/**
			 * Sets the new <code>yPos</code>.
			 * @param y the new <code>yPos</code>
			 */
			
			public void setYPos(int y){
				yPos = y;
			}
			
			/**
			 * Sets the new <code>heading</code>.
			 * @param heading the new <code>heading</code>
			 */
			
			public void setHeading(double heading){
				this.heading = heading;
			}
			
			/**
			 * Displays the properties of the <code>Turtle</code>.
			 */

			public String toString(){
				String str = "X: "+xPos+"\t\tY: "+yPos+"\tHeading: "+heading;	
				return str;
			}
			
		} //Turtle
		
		public class Code {

			private double code[][];
			private double translationFactor, scalingFactor;
			private double domain, range, maxX, minX, maxY, minY;
			private final int WIDTH = 500;
			private final int HEIGHT = 500;
			
		
			public Code(double[][] d, double xMax, double xMin, double yMax, double yMin, double t, double s) {
				code = d;
				
				maxX = xMax;
				minX = xMin;
				maxY = yMax;
				minY = yMin;
				
				translationFactor = t;
				scalingFactor = s;
			}
			
		
			public Point2D.Double getNewCoordinates(Point2D.Double point, int set) {
				
				double x = code[set][0]*point.x + code[set][1]*point.y + code[set][4];
				double y = code[set][2]*point.x + code[set][3]*point.y + code[set][5];
						
				return new Point2D.Double(x, y);
			}

			public double getProbability(int set) {
				return code[set][6];
			
			}
		
			public int numSets() {
				return code.length;
			}
			
			
			public int screenX(double x) {
				 domain = maxX-minX;
				 return (int) (WIDTH*(x-minX)/domain);
			}
			
			
			public int screenY(double y) {
				 range = maxY-minY;
				 return (int) (HEIGHT*(maxY-y)/range);
			}
		} // Code

		
		public class IFS {

			private Code code;
			private Point2D.Double point;
			private final int NUMPOINTS = 5000000;
			
			
			public IFS(Point2D.Double p, Code c) {
				code = c;
				point = p;
			}
			
		
			public void draw(Graphics g) {
				
				Graphics2D g2 = (Graphics2D) g;
				Point2D.Double newPoint = new Point2D.Double();
				
				for(int n=0; n<NUMPOINTS; n++) {
				
					double r = Math.random();
								
					for(int i=code.numSets()-1; i>=0; i--) {
						if(r<code.getProbability(i))
							newPoint = code.getNewCoordinates(point, i);
					}
				
					int scrX = code.screenX(newPoint.x);
					int scrY = code.screenY(newPoint.y);
						
					g2.drawLine(scrX, scrY, scrX, scrY);

					point.x = newPoint.x;
					point.y = newPoint.y;
				}
			}
		} // IFS
		

		
	} // Content

	} // LSystems
	
