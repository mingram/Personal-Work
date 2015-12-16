
public class Sudoku {
	
	private int n = 3;
	private static int[][] board;
	private static Tableau t;
	
	public static void main(String [] args){
		
		new Sudoku();
		
		boolean ok = checkSudoku(t);

		if(ok)
			System.out.println("\nThe sudoku is OK.");
		
		else 
			System.out.println("\nThe sudoku is invalid.");
		
		//  test getValue() method.	
		//	System.out.println(t.getValue(0,1) + " " +t.getValue(1, 4) );
	}
	
	public Sudoku(){
	/*	
		board = new int[n*n][n*n];
		
		for(int i=0; i<n*n; i++){
			for(int j=0; j<n*n; j++){
				board[i][j]=0;
			}
		}
		
	*/
		
		t = new Tableau(new String[] {
				
				"534 678 912", 
				"672 195 348", 
				"198 342 567",
				
				"859 761 423",
				"426 853 791",
				"713 924 856",
		
				"961 537 284",
				"287 419 635",
				"345 286 179"
		});
		
		t.print();
	}
	
	public static boolean checkSudoku(Tableau t) {
		
		Slice s;
		
		for (int i=0; i<t.size(); i++){
			
			s=new RowSlice(t,i);
			
			if(!checkSlice(s))
				return false;
			
			s = new ColumnSlice(t,i);
			
			if(!checkSlice(s))
				return false;
			
			s = new SquareSlice(t,i);
			
			if(!checkSlice(s))
				return false;
			
			
		}
		
		return true;
	}
	
	public static boolean checkSlice(Slice s){
		
		for(int i = 1; i<s.size(); i++){
			//Check the value i is here
			boolean found = false;
			
			for(int j=0; j < s.size() && !found; j++) {
				if(s.getValue(j)==i)
					found = true;
			}
			
			//If not found, then the check fails
			if(!found)
				return false;
		}
		
		//All were found, checks out
		return true;

	}

}
