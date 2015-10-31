public class ListNode {

	private Object value;
	private ListNode next;

	public ListNode(Object value, ListNode next) {
		this.value = value;
		this.next = next;
	}

	public Object getValue() {
		return value;
	}

	public ListNode getNext() {
		return next;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public String toString() {
		return new String("<ListNode> Value: " + value + " Next: " + next);
	}
}
