import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.awt.geom.GeneralPath;
import java.awt.Polygon;

public class KochComponent extends JComponent
{
   public KochComponent(int iters, int aLength)
   {
      numIterations = iters;
      length = aLength;
   }
   
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;

      KochLine koch = new KochLine();
      int x1 = 10;
      int y1 = length / 2;
      int x2 = x1 + length;
      int y2 = y1;
      int x3 = x1 + (length / 2);
      int y3 = (y1 +length);
      koch.draw(g2, numIterations, x1, y1, x2, y2);
      koch.draw(g2, numIterations, x2, y2, x3, y3);
      koch.draw(g2, numIterations, x3, y3, x1, y1);
   }
   
   private int numIterations;
   private int length;
}