
public class MyList {
	
	ListNode head;
	
	public MyList(){
		head = new ListNode('A',new ListNode("A",new ListNode("V",new ListNode("A",null))));
	}
	
	public String traverse(){
		return traverse(head);
	}

	private String traverse(ListNode node) {
		
		if(node==null)
			return "";
			
		else 
			return node.getValue()+""+traverse(node.getNext());

	}

}
