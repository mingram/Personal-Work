import java.io.FileInputStream;
import java.util.*;
import java.io.*;

/**
 * <b>Purpose:</b> Create a class that creates a network of friends, and test the relationship between random students.<p>
 * <b>Date:</b> 2010 01 31 <p>
 * @author M. Ingram
 *
 */
public class Network {

	private int n; //number of students
	private int lines;
	private String x,y,str;
	private String result="";
	TreeMap<Integer,Integer> map = new TreeMap<Integer, Integer>();

/**
 * Constructor that calls the <code>run</code method.	
 */
	public Network(){
		run();
	}

/**
 * Creates a new <code>Network</code>.	
 * @param args
 */
	public static void main(String [] args){
		new Network();
	}

/**
 * Retrieves input from a text file, and creates and tests friendship between students.	
 */
	public void run(){
		
		System.out.println("Input: \n");

		 try{
			    // Open the file that is the first 
			    // command line parameter
			    FileInputStream fstream = new FileInputStream("Input.txt");
			    // Get the object of DataInputStream
			    DataInputStream in = new DataInputStream(fstream);
			        BufferedReader br = new BufferedReader(new InputStreamReader(in));
			       
			    //Read File Line By Line
			    while ((str = br.readLine()) != null)   {
			      // Print the content on the console
			    	System.out.println (str);
			    	
			    if(lines==0)
			    	n=Integer.parseInt(str);
			    
			    else  {
			    
			    String[] fields = str.split(" ");
		    		x=fields[0].trim();
		    		y=fields[1].trim();
				
			    if(lines<=n)
					map.put(Integer.parseInt(x),Integer.parseInt(y));
					
				if(lines>n && Integer.parseInt(x)!=0)   
					areFriends(map.get(Integer.parseInt(x)),map.get(Integer.parseInt(y)));
					    
			    }
			    	lines++;	
			    }

			    System.out.println("\nOutput:\n"+result);
		
			    //Close the input stream
			    in.close();
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
	}
/**
 * Checks to see if two students are friends, and by what degree of separation.
 * @param xVal First student.
 * @param yVal Second student.
 */
	public void areFriends(int xVal, int yVal){
	
	int x = xVal;
	int y = 0;
	int separation = -1;
	
		for(int i=0; map.get(x)!=xVal && i<n;i++){
			y=map.get(x);
			
			if(y==yVal)
				separation=i;
			
			x=y;
		}

		if(map.get(x)==xVal && separation > -1)
			result+="Yes "+separation+"\n";
		else
			result+="No\n";

		
	}

}
