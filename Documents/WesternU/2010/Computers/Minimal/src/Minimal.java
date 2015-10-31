import java.awt.*;
import java.awt.event.*;

/**
 * This example demonstrates the creation of a minimal Frame
 * covering the full dimensions of the screen.
 * @author Chris D'Arcy. 2009 06 28
 *
 */


public class Minimal extends Frame {
	
	public Minimal() {
		super("Abstract Windowing Toolkit Example");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Rectangle rect = 
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
		setSize(rect.width, rect.height);
		setUndecorated(false);
		setVisible(true);
	}

	public static void main (String [] args) {
	//go through the full Graphics hierarchy if multiple devices are available	
		new Minimal();
	}
}
