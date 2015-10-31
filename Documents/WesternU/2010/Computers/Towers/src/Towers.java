
public class Towers {

	public static void main(String [] args){
		
		showMoves(3,'A','B','C');
		
	}
	
	public static void showMoves(int n, char src, char dst, char temp){
		
		if(n==1)
			System.out.println(src+" -> "+dst);
		
		else {
		
		showMoves(n-1, src, temp, dst);
		
		System.out.println(src+" -> "+dst);
		
		showMoves(n-1, temp, dst, src);
		
		}
		
	}
	
}
