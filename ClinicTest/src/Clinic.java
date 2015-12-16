import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

/**
 * <b>Purpose:</b> Develop the <code>Clinic</code> class that simulates a day at an H1N1 clinic. <p>
 * <b>Date:</b> 2010 01 23<p>
 * @author M. Ingram
 *
 */

public class Clinic {

	Queue<Citizen> queue = new LinkedList();
	private int dayLength;
	private int nextArrival;
	private int completionTime;
	private int maxSize=0;
	private int maxWait=0;
	
	private Random waitTime = new Random();
	
	/**
	 * Constructor for the <code>Clinic</code> class.
	 * @param time Length of the day to be simulated.
	 * @param nurses Number of nurses working.
	 * @param arriveTime Upper limit of citizen arrival time.
	 * @param vaccTime Upper limit of citizen vaccination time.
	 */
	
	public Clinic(int time, int nurses, int arriveTime, int vaccTime){
		
		this.dayLength = time;
		this.nextArrival = arriveTime;
		this.completionTime = vaccTime;
		
	}
	
	/**
	 * Simulates the day at the H1N1 clinic, printing off the arrival of citizens, when they begin their vaccination,
	 * and when the vaccination is complete.
	 */
	
	public void run() {
		
		String str="";
		
		int custNum = 0;
		
		System.out.println("TIME\tH1N1 CLINIC SIMULATION");
		
		nextArrival = waitTime.nextInt(4)+1;
		completionTime = nextArrival+waitTime.nextInt(4)+1;	

		
		for(int t=1; t<=dayLength || !queue.isEmpty(); t++){
		
		str=t+":\t";
		
			if(t==nextArrival && t<dayLength){
				custNum++;
				queue.offer(new Citizen(custNum,t));
				str+="Citizen "+custNum+" arrives. ";
				nextArrival=t+waitTime.nextInt(4)+1;
			}
			
			if(queue.isEmpty())
				completionTime=nextArrival+waitTime.nextInt(4)+1;
		
		if(!queue.isEmpty()) {
			if(t==completionTime){
					if(t-queue.peek().getArriveTime()>maxWait)
						maxWait=t-queue.peek().getArriveTime();
					if(queue.size()>maxSize)
						maxSize=queue.size();
					
					str+="Service completed for Citizen "+queue.poll().getID();
					completionTime = t+waitTime.nextInt(4)+1;

			if(!queue.isEmpty())
				str+=". Service started for Citizen "+queue.peek().getID();
				
			}

			System.out.println(str);
		}
		}
			System.out.println("\n\tMaximum wait time was "+maxWait+" minutes. Maximum queue size was "+maxSize+" citizens.");
	}
}
