/**
 * Driver for the Clinic Simulation Project. Do not modify
 * @author C. D'Arcy. 2010 01 18 
 */

public class ClinicTest{

  public static void main(String[] args) { 
    // minutes, nurses, upper limit of citizen arrival time, upper limit of citizen vaccination time
    Clinic H1N1 = new Clinic(720, 1, 4, 4);

    H1N1.run();
  }

}