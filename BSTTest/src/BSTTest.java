public class BSTTest {

  public static void main(String[] args) {

    BST bst = new BST();
    System.out.println(bst.traverse());
    
    // From an Encoded String
    String code = "C[/,O[M[E[/,/],/],P[/,U[T[R[/,/],/],/]]]]";
    System.out.println(code);
    BST bst2 = new BST(code);
    System.out.println(bst2.getCode());
    System.out.println(bst2);
    
    // Merge and Confirm
    BST bst3 = bst.merge(bst2);
    
    System.out.printf("\nThe merged tree is: %s.\n", bst3);
    System.out.printf("The encoded tree is: %s.\n", bst3.getCode());
 
    //Confirm encoding
    BST bst4 = new BST(bst3.getCode());
    System.out.printf("Confirming the encoding: %s.\n",bst4);

  }

}

