/**
 * <b>Purpose:</b> Develop a project that recursively sorts an array.
 * <p>
 * <b>Date:</b> 2009 11 7
 * <p>
 * @author M. Ingram
 *
 */

public class R13_3 {

	/**
	 * Array containing different integer values.
	 */
	
	public static int[] nums = {4,6,8,2,14,10,12,26,3,11,39};

	/**
	 * Main method that prints out the populated <code>nums</code> array.
	 * The array is then sorted and the newly sorted <code>nums</code> is printed out.
	 * @param args
	 */
	
	public static void main(String [] args){
	
		
	//Unsorted	
		System.out.print("Unsorted:"+"\t");

		for(int i=0; i<nums.length; i++){
			System.out.print(nums[i]+"\t");
		}

		sort(0,nums.length);
		
	//Sorted	
		System.out.print("\n\n"+"  Sorted:"+"\t");
		
		for(int i=0; i<nums.length; i++){
			System.out.print(nums[i]+"\t");
		}
	}
	
	/**
	 * Sort method that sorts the array. A basis condition is set by if the array is one number. If it is, it returns.
	 * If not, the smallest integer in the array is set to the first position. 
	 * Then, a self call is made that inputs the array starting at the position directly after the smallest integer.
	 * @param start The starting position.
	 * @param end The last position to be sorted.
	 */
	
	public static void sort(int start, int end){

		if(start==end)
			return;
	
		swap(start,smallest(start,end));
		sort(start+1,end);	
	}
	
	/**
	 * Method that returns the smallest integer value in the array.
	 * @param start The starting position.
	 * @param end The last position to be examined.
	 * @return The smallest value in the array.
	 */
	
	private static int smallest(int start, int end){
		
		if(start==end)
			return nums.length-1;
	
		if(nums[end-1] <= nums[smallest(start,end-1)])
			return end-1;
	
		return smallest(start,end-1); 

	}
	
	/**
	 * Simple method that switches the values of two integers at different positions in the array.
	 * @param x The first value to be switched.
	 * @param y The second value to be switched.
	 */
	
	public static void swap(int x, int y){
			int temp = nums[x]; 
			nums[x] = nums[y]; 
			nums[y] = temp;
	}
}
