class Main {
  public static void main(String[] args) {
    
  /*             4
              /    \
             3      5
            /  \    / \
           6   7   2   1
  */
    
    MyBinaryTree tree = new MyBinaryTree(4);
    tree.root.right = new TreeNode(5);
    tree.root.left = new TreeNode(3);
    tree.root.left.left = new TreeNode(6);
    tree.root.left.right = new TreeNode(7);
    tree.root.right.right = new TreeNode(1);
    tree.root.right.left = new TreeNode(2);
   
    
    int[] in = {6,3,7,4,2,5,1};
    int[] post = {6,7,3,2,1,5,4};
    int[] bfs = {4,3,5,6,7,2,1};
    int[] pre = {4,3,6,7,5,2,1};
    tree.inorderIndented();
   
    
  }
}