import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * <b>Purpose:</b> Develop a project that recursively sorts an array.
 * <p>
 * <b>Date:</b> 2009 11 7
 * <p>
 * 
 * @author M. Ingram
 * 
 */

public class Sort extends JApplet implements ActionListener {

	private static Dimension appDimen = new Dimension(600, 400);

	public static int[] nums1 = { 15, 76, 12, 23, 32, 18, 19 };
	public static int[] nums2 = { 15, 76, 12, 23, 32, 18, 19 };
	public static int[] nums = { 15, 76, 12, 23, 32, 18, 19 };
	public static int[] numsTwo = { 15, 76, 12, 23, 32, 18, 19 };

	public static String str = "";
	private static int n = 0;
	static Content content;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setPreferredSize(appDimen);
		frame.setTitle("Graphic Depiction of Sorting Algorithms");
		JApplet applet = new Sort();
		applet.init();
		frame.getContentPane().add(applet);
		frame.pack();
		frame.setVisible(true);
	}

	public void init() {

		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		JMenu menu = new JMenu("Run");
		mb.add(menu);

		JMenuItem m1 = new JMenuItem("Selection Sort");
		menu.add(m1);
		m1.addActionListener(this);

		content = new Content();
		getContentPane().add(content);

	}

	public void actionPerformed(ActionEvent ev) {
		String command = ev.getActionCommand();
		if ("Exit".equals(command)) {
			System.exit(0);
		} else if ("Selection Sort".equals(command)) {
			content.sortType = content.SELECTION;
		}

		content.createNewImage();
		content.repaint();
	}

	public static void selectionSort(int[] x) {
		for (int i = n; i < n + 1; i++) {

			for (int j = i + 1; j < x.length; j++) {
				if (x[i] > x[j]) {
					int temp = x[i];
					x[i] = x[j];
					x[j] = temp;
				}
			}
		}
		n++;
	}

	private static int smallest(int[] x, int start, int end) {

		if (start == end)
			return nums.length - 1;

		if (x[end - 1] <= x[smallest(x, start, end - 1)])
			return end - 1;

		return smallest(x, start, end - 1);

	}

	class Content extends JPanel implements Runnable {

		private BufferedImage img;
		private Graphics2D drawImg;
		int x, y = 20;
		int m = 0;
		double y2 = appDimen.height / 2 - ((nums.length * 18) / 2);
		static final int SELECTION = 1;
		static final int INSERTION = 2;
		public int sortType;
		Thread thread;

		/**
		 * Constructor initializes dimensions, background colour, and a
		 * <code>thread</code>.
		 */
		public Content() {
			setPreferredSize(new Dimension(400, 400));
			setBackground(Color.black);
			thread = null;
		}

		/**
		 * Creates and sets up a new <code>BufferedImage</code>.
		 */
		public void createNewImage() {
			img = new BufferedImage(appDimen.width, appDimen.height,
					BufferedImage.TYPE_INT_RGB);
			drawImg = img.createGraphics();
			drawImg.clearRect(0, 0, getWidth(), getHeight());
			setPreferredSize(appDimen);
			thread = new Thread(this);
			thread.start();

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
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(getImage(), 0, 0, this);
		}

		/**
		 * Draws each stage of sorting.
		 * @param g a graphic to draw on.
		 */
		public void draw(Graphics g) {

			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			if (sortType > 0) {
				double x = appDimen.width / 2 - ((nums.length * 48) / 2);

				for (int i = 0; i < nums.length; i++) {
					g2.drawString(Integer.toString(nums[i]), (int) x,
							(int) y2 - 40);
					x += 50;
				}

				if (m < nums.length) {
					g2.drawRect((int) x - (50 * nums.length) + (m * 50) - 2,
							(int) y2 - 55, 20, 20);
					m++;
				}

				selectionSort(nums);
				y += 20;

			}

		}
		/**
		 * Displays all the stages of sorting. Sorted items appear in red. The item to be sorted next appears in green.
		 * The final sorted array appears in yellow.
		 * @param g
		 */
		public void display(Graphics g) {

			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			n = 0;

			double x = appDimen.width / 2 - ((numsTwo.length * 48) / 2);
			double y = appDimen.height / 2 - ((numsTwo.length * 18) / 2);
			int q = 0;
			int p = 0;

			if (sortType > 0) {

				g2.setColor(Color.RED);
				g2.drawString("New location of sorted item", 5, 20);
				g2.setColor(Color.GREEN);
				g2.drawString("Next item to be moved", 5, 40);
				g2.setColor(Color.YELLOW);
				g2.drawString("Sorted array", 5, 60);

				for (int t = 0; t < numsTwo.length; t++) {

					if (numsTwo[t] == numsTwo[smallest(numsTwo, p, nums.length)]) {
						g2.setColor(Color.RED);
					} else
						g2.setColor(Color.WHITE);

					g2.drawString(Integer.toString(numsTwo[t]), (int) x,
							(int) y - 20);
					x += 50;
				}

				for (int k = 0; k < numsTwo.length; k++) {

					x = appDimen.width / 2 - ((nums.length * 48) / 2);

					selectionSort(numsTwo);

					for (int i = 0; i < nums.length; i++) {

						g2.setColor(Color.WHITE);

						if (numsTwo[i] == numsTwo[smallest(numsTwo, p + 1,
								nums.length)]) {
							g2.setColor(Color.GREEN);
						}

						if (numsTwo[i] == numsTwo[smallest(numsTwo, p,
								nums.length)]) {
							g2.setColor(Color.RED);
						}

						g2.drawString(Integer.toString(numsTwo[i]), (int) x,
								(int) y);
						x += 50;
					}

					y += 20;
					q++;
					p++;
				}

				x = appDimen.width / 2 - ((numsTwo.length * 48) / 2);

				for (int u = 0; u < numsTwo.length; u++) {
					g2.setColor(Color.YELLOW);
					g2.drawString(Integer.toString(numsTwo[u]), (int) x,
							(int) y);
					x += 50;
				}

			}

		}

		/**
		 * The <code>BufferedImage</code> is drawn each time with incrasing y coordinates, with a pause of 1.5 seconds between each drawing.
		 * 
		 */
		public void run() {

			while (y < 150) {

				draw(drawImg);
				repaint();
				y2 += 20;

				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			display(drawImg);
			repaint();
		}

	}
}
