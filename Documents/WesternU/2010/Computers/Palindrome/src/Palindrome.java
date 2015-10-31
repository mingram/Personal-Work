/**
 * Demonstrates tail-recursion to reverse the order of the characters of a String. 
 * (not recommended-use iteration) 
 * @author C. D'Arcy
 */

public class Palindrome {
  private static String word = "Radar";
  private static String reverse = "";	
  
  public static void main(String args[]) {
    reverse = Reverse(word);
    System.out.printf("The string %s and %s are ",word,reverse);
    if (!word.equalsIgnoreCase(reverse)) {
      System.out.print("not ");
    }
    System.out.println("palindromes.");
  }
	
  /** 
   * Develop the simplest recursion algorithm for reversing the characters of the String parameter
   * @param str the String to be reversed
   * @param i the current index within the string
   */
  
  private static String Reverse(String str) {	  
	
	  char c = str.charAt(str.length()-1);

      if(str.length() == 1) 
    	  return Character.toString(c);  

      return c + Reverse(str.substring(0,str.length()-1));  
      
  }
  
}