
package chapter1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.java.games.jogl.*;

public class JOGLDemo {
  
  public static void main(String[] args) {
    Frame frame = new Frame("JOGL Demo");
    GLCapabilities cap = new GLCapabilities();
    GLCanvas canvas = GLDrawableFactory.getFactory().createGLCanvas(cap);
    canvas.setSize(300, 300);
    canvas.addGLEventListener(new Renderer());
    frame.add(canvas);
    frame.pack();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.show();
  }
  
  static class Renderer implements GLEventListener {
    private GL gl;
    private GLU glu;
    private GLDrawable gldrawable;
    
    public void init(GLDrawable drawable) {
      gl = drawable.getGL();
      glu = drawable.getGLU();
      this.gldrawable = drawable;
      gl.glMatrixMode(GL.GL_PROJECTION);
      gl.glLoadIdentity();
      glu.gluOrtho2D(-1.2, 1.2, -1.2, 1.2);
      gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
    }    
    
    public void display(GLDrawable drawable) {
      int i;
      int n = 80;
      float a = (float)(2*3.1415926535/n);
      float x;
      float y;
      
      gl.glClear(GL.GL_COLOR_BUFFER_BIT);
      gl.glColor3f(1.0f,0,0);
      gl.glBegin(GL.GL_LINE_LOOP);
      for (i = 0; i < n; i++) {
        x = (float)Math.cos(i*a);
        y = (float)Math.sin(i*a);
        gl.glVertex2f(x, y);
      }
      gl.glEnd();
      gl.glFlush();
    }
    
    public void reshape(GLDrawable drawable, int x, int y, int width,
      int height) {}
    public void displayChanged(GLDrawable drawable, boolean modeChanged,
      boolean deviceChanged) {}
  }
}

