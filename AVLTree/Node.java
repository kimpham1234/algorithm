public class Node {
  int val;
  Node left;
  Node right;
  int height;
  
  public Node(int val){
    this.val = val;
    height = 1;
    right = null;
    left = null;
  }
}