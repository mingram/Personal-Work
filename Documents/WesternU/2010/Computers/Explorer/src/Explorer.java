import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

/**
 * <b>Purpose:</b> Develop the class Explorer as a simple, efficient extension of JFrame that can be used to display the contents of a folder.
 * <b>Date:</b> 2010 04 10
 * @author M. Ingram
 *
 */

public class Explorer extends JFrame implements ActionListener {

    private JLabel label;
    private JTextField enter;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    
/**
 * Instantiate a new <code>Explorer</code> object.
 * @param args
 */
    
    public static void main(String args[]) {
        new Explorer().setVisible(true);
    }
 
/**
 *  <code>Explorer</code> constructor which initializes the object.
 */
    
    public Explorer() {
        init();
        pack();
    }

/**
 *   Creates the interface for the program. Using <code>Swing</code> components, an appropriate search box is made where
 *   the user will enter the desired path.
 */
    
    private void init() {
    	
        setTitle("ICS4U Folder Explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel input = new JPanel();
        FlowLayout flow = new FlowLayout();
        
        enter = new JTextField();
        textArea = new JTextArea();
        label = new JLabel();
        label.setText("Enter path:");
       
        enter.setText("/Users/student/Desktop/Subjects/Computers");
        enter.addActionListener(this);
        enter.setColumns(40);
        
        textArea.setColumns(50);
        textArea.setRows(30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        
        scrollPane = new JScrollPane(textArea);

        flow.setAlignment(3);
       
        input.setLayout(flow);
        input.add(label);
        input.add(enter);
        
        setLayout(new BorderLayout());
        getContentPane().add(input, "North");
        getContentPane().add(scrollPane, "Center");
        
    }

/**
 *  Creates a new <code>File</code> from the text found in the entry box. First a file will be created from the address in the text box.
 *  It then performs the <code>getFolder</code> method which will take text entry, the list of files, and a number for tab stops as parameters.
 *  
 */
    
	public void actionPerformed(ActionEvent arg0) {
		
		textArea.setText("");
		File f = new File(enter.getText());
		textArea.append((new StringBuilder("Folder contents for")).append(enter.getText()).append("...\n").
				append(navigate(enter.getText(), f.list(), 0)).toString());
	}

/**
 * Recursive method for navigating folder contents. If the file being examined is a folder, the method calls itself to dig deeper into the structure.
 * If not, it simply prints out the file. Tab stops will increase if the file is inside a folder, giving it a visually structural design.
 * @param path The path being explored.
 * @param list The list of items inside that path.
 * @param tabs Tab stops.
 * @return a String with all folder and file names, formatted appropriately.
 */
	
	private String navigate(String path, String list[], int tabs) {
		
		String str = "";
		int l = list.length;

		for(int i = 0; i<l; i++){

			File f = new File(path+File.separator+list[i].toString());
			
			for(int x=0; x<tabs; x++)
				str = str+"   ".toString();
			
			if(f.isDirectory()) {
			//	textArea.setFont(new Font("Sanserif",Font.BOLD,12));
				str = str+f.getName()+"\n"+navigate((path+File.separator+list[i].toString()),f.list(),tabs+1).toString();
				
			}
			
			else {
			//	textArea.setFont(new Font("Sanserif",Font.PLAIN,12));
				str = str+f.getName()+"\n".toString();

				
			}
			
		
		}

		return str;
		
	}

	
}