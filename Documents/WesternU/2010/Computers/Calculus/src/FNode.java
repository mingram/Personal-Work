
public class FNode extends ENode {
	
	private char type = 'F';

	public FNode(int value) {
		super(value);
		super.setType(type);
	}

	public FNode(int value, ENode left, ENode right) {
		super(value, left, right);
		super.setType(type);
	}


}