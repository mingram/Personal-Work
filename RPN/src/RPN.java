import java.util.*;

public class RPN {
	
	static String expression = "36*25/-";
	public static String str;
	static Stack<Character> stack;
	static String integers = "3625";
	static String operators = "*/-+";

	public static void main(String [] args){
			
		System.out.println(evaluate)
	
	}
	
	public static void evaluate(){
		
		
		for(int i = 0; i<expression.length(); i++){
			  
			  char ch = integers.charAt(i);
			  
			  if(expression.charAt(i)==integers.indexOf(ch,0)){
				  
				  stack.push(ch);
			  }
			  
			  else {
				  
				  stack.push(ch);
				  stack.pop();
				  
			  }
			  
			  
			  System.out.println(stack);
		  }
		
		
	}
	
	
	
}
