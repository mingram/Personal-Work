import java.util.Stack;

/**
 * <b>Purpose:</b> Develop the <code>BST</code> class that mimics a Binary Search Tree using a network of <Code>TreeNode</code>s.<p>
 * <b>Date: 2010 04 17</b>	<p>
 * @author M. Ingram
 *
 */
public class BST {

	private TreeNode root;
	private String code;
	private TreeNode newRoot;
	

	/**
	 * Constructs a BST object encapsulating a binary search tree into 'root'.
	 * Hard codes the Character data: 'J', 'A', 'V', 'A' as the node values in
	 * one statement (manually).
	 */
	
	public BST() {
		root = new TreeNode('J', new TreeNode('A', new TreeNode('A'), null),
				new TreeNode('V', null, null));
	}

	/**
	 * Constructs a BST object from the parameter containing the encoded Binary Search Tree.
	 * @param code the encoded binary search tree
	 */
	
	public BST(String code) {
		this.code = code;
		root = parse();
	}

	public BST(TreeNode root) {
		this.root = root;
	}
	
	public String getCode(){
		return getCode(root);
	}
	
	private String getCode(TreeNode node){
		if(node!=null && node.getValue()!=null)
			return node.getValue()+"["+getCode(node.getLeft())+","+getCode(node.getRight())+"]";
		
		else 
			return "/";
	}

	/**
	 * Decodes the given String of characters used to represent a <code>BST</code>.
	 * @return A <code>TreeNode</code>.
	 */
	private TreeNode parse() {
		Stack<TreeNode> stk = new Stack<TreeNode>();
		
		for(int i=0; i<code.length(); i++){
			
			switch (code.charAt(i)) {

			case '[' : 
				break;
			
			case ']' : 	
			
				TreeNode t1 = stk.pop();				
				TreeNode t2 = stk.pop();
				TreeNode t3 = stk.pop();
				stk.push(new TreeNode(t3.getValue(),t2,t1));
				
				break;
				
			case '/' :
				stk.push(new TreeNode(null));
				break;
				
			case ',' :			
				break;

			default :
				
				stk.push(new TreeNode(code.charAt(i),null,null));

			
			}
		}
		
		return stk.pop();

	}
/**
 * Method that calls helper <code>traverse</code> method.
 * @return a String of the traversal.
 */
	public String traverse() {
		return traverse(root);
	}
/**
 * Recursive method that returns the values of every node in a <code>BST</code>.
 * @param node The root to be traversed.
 * @return a <code>String</code> of characters that read left to right through the <code>BST</code>.
 */
	private String traverse(TreeNode node) {

		if (node != null && node.getValue()!=null)
			return traverse(node.getLeft()) + "" + node.getValue() + ""
					+ traverse(node.getRight());

		else
			return "";

	}


	private TreeNode add(TreeNode node, Object value) {
		if (node == null) {
			node = new TreeNode(value);
		}
		
		else {
			int diff = ((Character) value).compareTo(((Character) node.getValue()));

			if (diff < 0)
				node.setLeft(add(node.getLeft(), value));

			else
				node.setRight(add(node.getRight(), value));

		}

		return node;

	}

/**
 * Assigns the value of <code>newRoot</code> to <code>root</code> and calls the method <code>traverseAndAdd</code> and passes it
 * the explicit root.
 * A new BST is then formed from the value of <code>newRoot</code>.
 * @param other
 * @return
 */
	public BST merge(BST other){

		traverseAndAdd(this.root);
		traverseAndAdd(other.root);
		return new BST(newRoot);
		
	}

	/**
	 * Traverses a tree, calling <code>TreeNode add(TreeNode node, Object value)</code> method to add the value of the node to the new Tree
	 * @param root the root to be traversed.
	 */
	
	
	private void traverseAndAdd(TreeNode node) {
		
		if (node != null && node.getValue()!= null){
			traverseAndAdd(node.getLeft());
			newRoot = add(newRoot,node.getValue());
			traverseAndAdd(node.getRight());
		}
	}


	/**
	 * toString method that calls <code>traverse()</code>.
	 * 
	 * @return a call to <code>traverse()</code>
	 */
	public String toString() {
		return traverse();
	}
	
	
}