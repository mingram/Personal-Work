
public class Fibonacci {

	
	
	public static void main(String [] args){
		
		
		
		for(long n=1; n<92; n++){
			
			System.out.println(fib(n));
			
			System.out.println(compareTo(n));
			
		}
		
	}

	public static long fib(long n){
		if(n==1 || n==2)
			return 1;
		return fib(n-2)+fib(n-1);
	}
	
	public static long compareTo(long n){
		double fi = (1+Math.sqrt(5))/2;
		double num;
		
		num = Math.pow(fi,n)-Math.pow((1-fi),n)/Math.sqrt(5);
		
		
		if(fib(n)==num)
			System.out.println("same");
		
		
	
	
		
	}
	
}
