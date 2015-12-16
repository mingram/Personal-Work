public class ENode {

	private Object value;
	private ENode left, right;
	private char type;
	
	public ENode(Object value) {
		this.value = value;
	}

	public ENode(Object value, ENode left, ENode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public Object getValue() {
		return value;
	}

	public ENode getLeft() {
		return left;
	}

	public ENode getRight() {
		return right;
	}

	public void setType(char type) {
		this.type = type;
	}

	public char getType() {
		return type;
	}

}
