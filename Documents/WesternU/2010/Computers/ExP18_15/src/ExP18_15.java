import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExP18_15
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      final int FRAME_WIDTH = 360;
      final int FRAME_HEIGHT = 500;

      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setTitle("ExP18_15");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      String input = JOptionPane.showInputDialog(null, "Number of iterations");
      if (input == null)
         System.exit(0);
      int iterations = Integer.parseInt(input);
      KochComponent component = new KochComponent(iterations, 300);
      frame.add(component);

      frame.setVisible(true);
   }
}