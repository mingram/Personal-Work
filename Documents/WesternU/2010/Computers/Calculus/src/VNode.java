
public class VNode extends ENode {
	
	private char type = 'V';

	public VNode(char value) {
		super(value);
		super.setType(type);
	}

	public VNode(char value, ENode left, ENode right) {
		super(value, left, right);
		super.setType(type);
	}


}