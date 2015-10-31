import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * <b>Purpose:</b> To simulate the iTunes application, which holds <code>PlayList</code>s of <code>Song</code>s.
 * This driver first imports an exported list of songs from iTunes, and adds them to a new <code>PlayList</code>.
 * Each line in the text file contains the <code>Song</code>'s name, artist, album, genre, size and time.
 * iTunes then allows the user to sort the <code>PlayList</code> in ascending or descending order by any field
 * desired. iTunes also includes the feature of calling the linearSearchByName(String n) or binarySearchByName(String n)
 * methods of the <code>PlayList</code> class to search for a particular song in the list.
 * <p>
 * <b>Date:</b> Mar. 14/09
 * <p>
 * @author M. Kryshtalskyj
 * <p>
 * ***************************************************
 * Driver class for ICS3M iTunes Project.<br>
 * 2009 03 09
 * @author C. D'Arcy
 */

public class iTunes { 

  // Text File of PlayList exported from iTunes 
  static String filename = "Music.txt";
  
  // Encapsulates an array of Songs 
  static PlayList playList;
   
  public static void main(String[] args) throws FileNotFoundException {
		
    // Create a PlayList Object to hold the Songs
	playList = new PlayList("Mark's Playlist", 100);
    
	// Open the input channel 
	FileReader reader = new FileReader(filename);
    Scanner in = new Scanner(reader); 

    // Read in the lines, adding each one to the PlayList
    while (in.hasNext()) {
      String line = in.nextLine();

      playList.add(line);
    }
    
    playList.add("Sinking Like A Sunset\tTom Cochrane\tSS\tRock\t938382\t922");
    
    // Sort the songs by whatever field you choose
      playList.sort();
    
    // confirm sorted list 
    System.out.println(playList);
    
    Scanner kbd = new Scanner(System.in);
    
    System.out.print("Enter a song to search for: ");
    String name = kbd.nextLine();
    
    int position = playList.binarySearchByName(name);
    
    if(position < 0)
    	System.out.println("Song: "+name+" was not found");
    else System.out.println("Song: "+name+" is located at index: "+position);
  }
	
}