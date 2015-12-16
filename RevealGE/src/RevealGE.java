import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 	* This example demonstrates a technique for determining
 	* the graphics characteristics of the computing platform on which it is running.
 	* @author C. D'Arcy 2009 07 02
*/
public class RevealGE { 
	
	public static void main(String[] args) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		
		for (int j = 0; j < gs.length; j++) {
			GraphicsDevice gd = gs[j];
			GraphicsConfiguration[] gc = gd.getConfigurations();
			System.out.println("DEVICE CONFIGURATION #" + (j+1));
			System.out.println("Device:\t\t"+gc[j].getDevice());
			System.out.println("Bounds:\t\t"+gc[j].getBounds());
			System.out.println("ColorModel:\t"+gc[j].getColorModel());
			
		}
	}
}