
/**
 * <b>Purpose:</b> To create and implement a <code>PlayList</code> class that stores the playlist's name, and
 * any <code>Song</code> the user desires.
 * <p>
 * To store Songs, the PlayList contains a private array with a default
 * capacity of 1000 Songs. When the array is full, since it cannot be extended, a new array with twice the length
 * is constructed. The Songs are then pasted into the new array, and the reference is switched accordingly. The
 * class offers three different constructors for three cases. If the user only wants a new playlist, the default
 * name and capacity are assigned to it. If the user only wants to name the playlist, an inputed name is assigned
 * to it, and so is the default capacity. Finally, the last constructor offers the option of setting a desired
 * name and capacity.
 * <p>
 * The class also offers the add(String s) and sort() methods. The add(String s) method takes in a line of
 * text and passes it to a Song object, which separates it into various instance fields. It then adds the new
 * Song to the array "list". If there is no open space in the array, it constructs a new array with a length
 * of twice the old array, and copies the contents into it. Finally, it assigns the reference to the new array
 * to the old array reference, "list".
 * <p>
 * The sort() method uses Insertion Sort to sort the data by any instance field. It can be also sorted in ascending
 * or descending order by switching the condition in the while() statement from > to < or vice versa.
 * <p>
 * Finally, the PlayList allows the user to search for a particular song using the linearSearchByName(String n) and
 * binarySearchByName(String n) methods. The toString() method prints out the PlayList.
 * <p>
 * <b>Date:</b> Mar. 14/09
 * <p>
 * @author M. Kryshtalskyj
 */

public class PlayList {

	private String name;
	private Song[] list;
	private int index;
	private static final String DEFAULTNAME = "New_PlayList";
	private static final int DEFAULTCAPACITY = 23;
	
	/**
	 * Constructs a new PlayList object of default name "New_PlayList" and default capacity 1000.
	 * It completes this by using the PlayList(String n, int initialCapacity) constructor within itself.
	 */
	public PlayList() {
		this(DEFAULTNAME, DEFAULTCAPACITY);
	}
	
	/**
	 * Constructs a new PlayList object of any inputed name and default capacity 1000.
	 * It completes this by using the PlayList(String n, int initialCapacity) constructor within itself, as in the previous constructor.
	 * @param n The name of the PlayList.
	 */
	public PlayList(String n) {
		this(n, DEFAULTCAPACITY);
	}
	
	/**
	 * Constructs a new PlayList object of any inputed name and any inputed default capacity.
	 * The constructor also instantiates the class' instance fields and sets the <code>index</code> variable
	 * used in cooperation with the array <code>list</code> to zero.
	 * @param n The name of the PlayList.
	 */
	public PlayList(String n, int initialCapacity) {
		name = n;
		list = new Song[initialCapacity];
		index = 0;
	}
	
	/**
	 * Takes in a line of text and passes it to a Song object, which separates it 
	 * into various instance fields. It then adds the new Song to the array <code>list</code>.
	 * It then checks if the array is full, and if so, constructs a new array with a length of twice the old array,
	 * and copies the contents into it. Finally, it assigns the reference to the new array to the old array reference,
	 * <code>list</code>.
	 * @param s A line of text that contains information regarding a single Song.
	 */
	public void add(String s) {
		Song newSong = new Song(s);
		
		// if the last index is full, construct a larger array twice the capacity of the old one...
		if(list[list.length-1]!=null) {
			Song[] temp = new Song[list.length*2];
			System.arraycopy(list, 0, temp, 0, list.length);
			list = temp;
		}
		
		// otherwise, just add a new Song...
		list[index] = newSong;
		index++;
	}
	
	/**
	 * The sort() method uses Insertion Sort to sort the data by any instance field, which can be changed
	 * by going into the compareTo(Song other) method of the Song class. It can be also sorted in ascending
	 * or descending order by switching the condition in the while() statement from > to < or vice versa.
	 * Insertion Sort takes the first element of the array and compares it to the next one. If it is smaller, it switches
	 * them. It then takes the next element in the array as the minimum and compares it to the previous ones. It keeps going through
	 * this cycle until the whole array is sorted.
	 */
	public void sort() {
		 for(int i=1; i<index; i++) {
			 	Song next = list[i];
				int n = i;
				
				// determine whether p[n] and p[n-1] need to be swapped...
				while(n > 0 && list[n-1].compareTo(next) > 0) {
					list[n] = list[n-1];
					n--;
				}
				
				// inserts the element in its proper index...
				list[n] = next;
			}
	}
	
	/**
	 * Determines if a desired song exists within the PlayList, by using the getName() method of the Song class, and
	 * the String.equals(String n) method. If the Strings are equal, then the song is found in the PlayList. The linear
	 * search starts at index 0 in the <code>list</code> array and keeps searching through the array until it finds
	 * the Song, or until it has passed through all indexes in the array.
	 * @param n The name of the song to search for.
	 * @return A positive integer that determines the index location of the desired song if found, or -1 if not found.
	 */
	public int linearSearchByName(String n) {
		for(int i=0; i<index; i++)
			if(list[i].getName().equals(n))
				return i+1;
		return -1;
	}
	
	/**
	 * Determines if a desired song exists within the PlayList, by using the getName() method of the Song class, and
	 * the String.equals(String n) method. If the Strings are equal, then the song is found in the PlayList. The binary
	 * search starts at the middle index of the <code>list</code> array and sees if its Song name is equal to the desired song.
	 * If not, it uses the compareTo(String n) method to determine the difference between the ASCII values of a certain character
	 * in both Strings. Depending on the result, it either searches below the middle value or above, setting the new "mid" value
	 * to the midpoint of the new section.
	 * @param n The name of the song to search for.
	 * @return A positive integer that determines the index location of the desired song if found, or -1 if not found.
	 */
	public int binarySearchByName(String n) {
		int low = 0;
		int high = index;
		
		while(low <= high) {
			int mid = (low+high)/2;
			if(list[mid].getName().equals(n))
				return mid+1;
			
			if(list[mid].getName().compareTo(n) > 0)
				high = mid-1;
			else low = mid+1;
		}
		return -1;
	}
	
	/**
	 * Prints out the PlayList of Songs and their various instance fields.
	 * @return The iTunes PlayList of Songs.
	 */
	public String toString() {
		String str = getClass().getName()+": "+name+"\n";
		str += String.format("\t\t%-30s\t%-30s\t%-30s\t%-15s\t%-10s\t%-10s", "Name", "Artist", "Album", "Genre", "Size", "Time");
		for(int i=0; i<index; i++) str += "\n["+(i+1)+"]"+list[i];
		return str;
	}
}
