

public class KochSystems {
	
	private static String axiom = "F-F-F-F";
	private static String production = "FF-F+F-F-FF";
	private static String key = "F";

	public static void main(String [] args){
		
		System.out.println(generate(2));

	}
	
	public static String generate(int n) {
	
		if(n==0)
			return axiom;
		
		return generate(n-1).replace(key,production);
   }
}