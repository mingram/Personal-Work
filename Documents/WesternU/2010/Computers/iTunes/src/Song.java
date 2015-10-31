
/**
 * <b>Purpose:</b> To hold the important instance fields of a song (name, artist, album, genre, size, and time).
 * Each song is held in one index of an array in the PlayList class, and is comparable with other objects of type
 * <code>Song</code>. Since it is implemented with the Comparable<T> interface, the user can compare any two instance
 * fields of two Songs. The constructor instantiates each instance field by taking the original String passed to it (one line
 * from the text file), and cutting it up using the <code>split(String args)</code> method.
 * <p>
 * <b>Date:</b> Mar. 14/09
 * <p>
 * @author M. Kryshtalskyj
 */

public class Song implements Comparable<Song> {

	private String name, artist, album, genre, size, time;
	
	/**
	 * Constructs a new <code>Song</code> object, and instantiates the instance fields (name, artist, album, genre, size, time)
	 * of the song. In order to extract each field from the text file, the split(String args) method is used to
	 * split the initial string at each tab. The individual field is then trimmed of extra spaces using the trim()
	 * method.
	 * @param s A line from the text file passed from iTunes, that includes all the information about the song.
	 */
	public Song(String s) {
		String[] fields = s.split("\t");
		name = fields[0].trim();
		artist = fields[1].trim();
		album = fields[2].trim();
		genre = fields[3].trim();
		size = fields[4].trim();
		time = fields[5].trim();
	}
	
	/**
	 * Returns the name of the song to the user.
	 * @return The name of the song.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the name of the artist to the user.
	 * @return The song's artist.
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * Returns the album the song is found on.
	 * @return The album the song is found on.
	 */
	public String getAlbum() {
		return album;
	}
	
	/**
	 * Returns the genre of the song.
	 * @return The genre of the song.
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * Returns the size of the song (in kB).
	 * @return The size of the song, in kilobytes.
	 */
	public String getSize() {
		return size;
	}
	
	/**
	 * Returns the time length of the song (in s).
	 * @return The time length of the song, in seconds.
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * Returns all the song's instance fields in one string, neatly separated by tabs.
	 * @return The song's information (name, artist, album, genre, size, time).
	 */
	public String toString() {
		String str = "["+getClass().getName()+"]:";
		str += String.format("\t%-30s\t%-30s\t%-30s\t%-15s\t%-10s\t%-10s", name, artist, album, genre, size, time);
		return str;
	}
	
	/**
	 * Compares the instance field of one song to the instance field of another, and returns an integer based
	 * on the difference in the ASCII values of the characters at the same position. For example,
	 * "Aerosmith".compareTo("AC/DC") would return a value of 34, because the value of C is 67, while the value
	 * of e is 101. It can therefore determine which String comes first in dictionary order.
	 * @return The difference in the ASCII values of the String's characters at the same index position.
	 */
	public int compareTo(Song other) {
		return this.name.compareTo(other.name);
	}
}
