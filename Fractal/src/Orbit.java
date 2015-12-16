import java.util.ArrayList;

/**
 * <b>Purpose:</b> Develop the <code>Orbit</code> class that creates an array of Orbits from a given seed. 
 * <b>Date:</b> 2009 05 12
 * @author M. Ingram
 *
 */
public class Orbit {


	private final int ITERATIONS = 5;
	private ArrayList<ComplexNumber>orbits = new ArrayList<ComplexNumber>();
	private ComplexNumber c;
	
	
    public Orbit(ComplexNumber c) {
	    this.c = c;
	    orbits = new java.util.ArrayList<ComplexNumber>();
	    createOrbits();
	  }
	
/**
 * Creates new orbits by taking the seed as the first number. For the second orbit, it is the first <code>orbit</code><sup>2</sup> + the <code>seed</code>.
 */
	
    private void createOrbits() {
       
    orbits.add(c);
    
    for (int i = 0; i < ITERATIONS-1; i++) {
       ComplexNumber z = orbits.get(i);
         orbits.add(z.square().add(c));
        }
      }
	
/**
 * Goes through the <code>ArrayList</code>, and returns each <code>ComplexNumber</code>.
 */
	
    public String toString() {
        String s = "";
        for (ComplexNumber z:orbits) {
          s += z.toString() + '\n';
        }
        return s;
      }

	}
	
	

