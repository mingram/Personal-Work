import java.util.Stack;

public class StackExample {

	static Stack<Integer> stack;
	
	public static void main(String [] args){
		
		stack = new Stack<Integer>();
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		System.out.println(stack.empty());
		
		while (!stack.empty()){
			System.out.println(stack.pop());
		}	
	}	
}
