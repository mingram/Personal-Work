public class LockTest {
	
	public static void main (String [] args) {
		
		int first = 21;
		int second = 32;
		int third = 34;
		Lock lock = new Lock(first, second, third);
		
		// first number...
		int step = 0;
		while (step<2*60+first) {
			System.out.print(lock.clockwise()+"-");
			step++;
		}
		System.out.println("\nFIRST Number Entered.\n");
		
		// second number...
		step = 0;
		while (step < 2*60-(second-first)+1) {
			System.out.print(lock.counterclockwise()+"-");
			step++;		
		}
		System.out.println("\nSECOND Number Entered.\n");
		
		// third number...
		step = 0;
		while (step < third-second+1) {
			System.out.print(lock.clockwise()+"-");
			step++;		
		}
		System.out.println("\nTHIRD Number Entered.\n");
	}
}