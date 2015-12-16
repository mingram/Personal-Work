
public class Problem15 {
	
	public static void main(String [] args){
		
		System.out.println(routes(2,2));
	}
	
	public static int routes(int r, int c) {
		
		if(r==0 || c==0)
			return 1;
		
		if(r==c)
			return routes(r-1,c)*2;
		
		return routes(r-1,c)+routes(r,c-1);
		
	}
}
