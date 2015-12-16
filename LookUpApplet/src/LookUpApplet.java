import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This driver provides an framework for our Trig LookUp Table applet assignment. Much of it is
 * adapted from  http://java.sun.com/docs/books/tutorial/uiswing/components/menu.html 
 * @author C. D'Arcy. 2009.
 */

public class LookUpApplet extends JApplet implements ActionListener {

  int width, height;
  JTextArea output;
  JScrollPane scrollPane;
  
  public void init() {
    setInitialConstraints();
    createGUI();
    setVisible(true);
    setFocusable(true);
  }

  public void setInitialConstraints() {
    width = getWidth();
    height = getHeight();
  }
 
  public void createPalette() {}

  public void createGUI() {
    setLayout(null);
    setJMenuBar(createJMenuBar());
    setContentPane(createContentPane());
  }
 
  public JMenuBar createJMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu;
    JMenuItem menuItem;
    JRadioButtonMenuItem rbMenuItem;

    // Build the first menu
    menu = new JMenu("File");
    menuBar.add(menu);

    menuItem = new JMenuItem("Clear");
    menuItem.setActionCommand("clear");
    menuItem.addActionListener(this);

    menu.add(menuItem);

    // Build the second menu
    menu = new JMenu("Trigonometric");
    menuBar.add(menu);
    
    // a group of radio button menu items
    ButtonGroup group = new ButtonGroup();

    rbMenuItem = new JRadioButtonMenuItem("sin");
    rbMenuItem.setActionCommand("sin");
    rbMenuItem.addActionListener(this);
    rbMenuItem.setSelected(true);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);

    rbMenuItem = new JRadioButtonMenuItem("cos");
    rbMenuItem.setActionCommand("cos");
    rbMenuItem.addActionListener(this);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);
    rbMenuItem = new JRadioButtonMenuItem("tan");
    rbMenuItem.setActionCommand("tan");
    rbMenuItem.addActionListener(this);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);

    menu.addSeparator();
    
    rbMenuItem = new JRadioButtonMenuItem("csc");
    rbMenuItem.setActionCommand("csc");
    rbMenuItem.addActionListener(this);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);
    rbMenuItem = new JRadioButtonMenuItem("sec");
    rbMenuItem.setActionCommand("sec");
    rbMenuItem.addActionListener(this);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);
    rbMenuItem = new JRadioButtonMenuItem("cot");
    rbMenuItem.setActionCommand("cot");
    rbMenuItem.addActionListener(this);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);
   
    return menuBar;
  }
 
  public JPanel createContentPane() {
    // Create the content-pane-to-be.
    JPanel contentPane = new JPanel(new BorderLayout());

    contentPane.setOpaque(false);
     
    // Create a scrolled text area.
    output = new JTextArea(5, 10);
    output.setFont(new Font("Courier New", Font.PLAIN, 12));
    output.setEditable(false);
    scrollPane = new JScrollPane(output);

    // Add the text area to the content pane.
    contentPane.add(scrollPane, BorderLayout.CENTER);

    return contentPane;
  }

  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();

    if ("clear".equals(action)) {
      output.setEditable(true);
      output.selectAll();
      output.cut();
      output.setEditable(true);
      output.repaint();
    } else {
    
      String message = "Table of Values for " + action + "(x):\n";
      int which = -1;

      if ("sin".equals(action)) {
        which = 0;
      } else if ("cos".equals(action)) {
        which = 1;
      } else if ("tan".equals(action)) {
        which = 2;
      } else if ("csc".equals(action)) {
        which = 3;
      } else if ("sec".equals(action)) {
        which = 4;
      } else if ("cot".equals(action)) {
        which = 5;
      }
	
      output.append("\t"+message + Trig.getTable(which) + "\n");
      output.setCaretPosition(output.getDocument().getLength());
    }
  }
}