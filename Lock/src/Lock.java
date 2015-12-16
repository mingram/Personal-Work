import java.util.*;

/**
 * <b>Purpose:</b> Using a doubly <code>LinkedList</code>, simulate the opening of a combination lock.<p>
 * <b>Date:</b> 2010 02 06 <p>
 * @author M. Ingram
 * 
 */

public class Lock {
	
	private int first,second,third;
	private LinkedList<Integer> dial = new LinkedList<Integer>();
	private ListIterator<Integer> li;

/**
 * Constructor for <code>Lock</code> that takes three integers as parameters that serve as the numbers in the combination.	
 * @param f first number
 * @param s second number
 * @param t third number
 */
	public Lock(int f, int s, int t) {
		
		for(int i=1; i<=60; i++)
			dial.add(i);
		
		li = dial.listIterator();
	}

/**
 * Iterates through the <code>LinkedList</code> in a clockwise manner (forward). If there is no element in the next position, 
 * the iterator resets to the first position in the list.
 * @return the next element.
 */
	public Integer clockwise() {
		if(!li.hasNext())
			reset();

		return li.next();
	}

/**
 * Iterates through the <code>LinkedList</code> in a counter-clockwise manner (backward). If there is no element in the previous position,
 * the iterator resets to the last position in the list, the size in this case.
 * @return the previous element.
 */
	public Integer counterclockwise() {
		if(!li.hasPrevious())
			li = dial.listIterator(dial.size());
		
		return li.previous();
	}

/**
 * 	Resets the <code>ListIterator</code>'s position to the first element in the list.
 */
	public void reset(){
		li = dial.listIterator();
	}

}
