
public class Primes {
	
	public static void main(String [] args){
		
		// Create array and initialize all entries
		boolean[] marks = new boolean[20];
		
		for(int i = 0; i<marks.length; i++)
			marks[i]=true;
		
		// For each number k >= 2
		for(int k = 2; k<marks.length; k++)
			//If it is prime, cross out its multiples
			if(marks[k])
				for(int j=2; j*k<marks.length; j++)
					marks[j*k] = false;
		
		// Display results
		for(int i = 0; i<marks.length; i++)
			if(marks[i])
				System.out.println("A prime is "+i);
		
		
	}

}
