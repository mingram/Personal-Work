/**
 * Class that incorporates all properties of a <code>Citizen</code>.
 * @author M. Ingram
 *
 */
public class Citizen {
	
	private int custNum;
	private int arriveTime;
	
/**
 * <code>Citizen</code> constructor.	
 * @param custNum Number at which they arrive at the clinic.
 * @param arriveTime Time at which they arrive at the clinic.
 */
	
	public Citizen(int custNum,int arriveTime){
		this.custNum=custNum;
		this.arriveTime=arriveTime;
	}

/**
 * Returns the ID of the citizen.	
 * @return Citizen ID.
 */
	public int getID(){
		return custNum;
	}
	
/**
 * Returns the arrive time of the citizen.	
 * @return Arrive time.
 */
	public int getArriveTime(){
		return arriveTime;
	}

}
