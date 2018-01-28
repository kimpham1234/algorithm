class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    BST tree = new BST();
    /*
              5
            /   \
           3     7
                  \
                   8
    */
    tree.iterativeInsert(5);
    tree.iterativeInsert(3);
    tree.iterativeInsert(7);
    tree.iterativeInsert(8);
    tree.inorder();
    System.out.println();
    tree.delete(5);
    tree.inorder();
    
    
  }
}