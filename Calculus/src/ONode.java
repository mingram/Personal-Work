
public class ONode extends ENode {
	
	private char type = 'O';

	public ONode(char value) {
		super(value);
		super.setType(type);
	}

	public ONode(char value, ENode left, ENode right) {
		super(value, left, right);
		super.setType(type);
	}

}
