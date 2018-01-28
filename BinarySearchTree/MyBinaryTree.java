import java.util.*;

public class MyBinaryTree {
  TreeNode root;
  
  public MyBinaryTree(int val){
    root = new TreeNode(val);
  }
  
  public MyBinaryTree(){
    root = null;
  }
  
  /**
   * DFS traversals recursion
   **/
  public void inorder(){
    inorder(root); 
  }
  
  private void inorder(TreeNode node){
    if(node == null)
      return;
    inorder(node.left);
    System.out.print(node.val + " ");
    inorder(node.right);
  }
  
  public void preorder(){
    preorder(root);
  }
  
  private void preorder(TreeNode node){
    if(node == null)
      return;
    System.out.print(node.val + " ");
    preorder(node.left);
    preorder(node.right);
  }
  
  public void postorder(){
    postorder(root);
  }
  
  private void postorder(TreeNode node){
    if(node == null)
      return;
    postorder(node.left);
    postorder(node.right);
    System.out.print(node.val + " ");
  }
  
  /**
   * DFS traversals stack
   **/
  public void sInorder(){
    if(root == null) return;
    
    Stack<TreeNode> s = new Stack<TreeNode>();
    
    TreeNode current = root;
    
    while(current!=null){
      s.push(current);
      current = current.left;
    }
    
    while(!s.empty()){
      if(current == null){
        current = s.pop();
        System.out.print(current.val + " ");
        current = current.right;
      }
      
      while(current!=null){
        s.push(current);
        current = current.left;
      }
    }
  }
  
  public void sPreorder(){
    if(root == null) return;
    
    Stack<TreeNode> s = new Stack<TreeNode>();
    
    s.push(root);
    
    while(!s.empty()){
      TreeNode node = s.pop();
      if(node!=null){
        System.out.print(node.val+" ");
        s.push(node.right);
        s.push(node.left);
      }
    }
  }
  
  public void sPostorder(){
    if(root == null) return;
    Stack<TreeNode> s = new Stack<TreeNode>();
    TreeNode current = root;
    
    while(current!=null){
      if(current.right!=null)
        s.push(current.right);
      s.push(current);
      current = current.left;
    }
    
    while(!s.empty()){
      current = s.pop();
      if(current.right!=null && !s.empty() && current.right == s.peek()){
        TreeNode temp = s.pop();
        s.push(current);
        current = temp;
      }else{
        System.out.print(current.val + " ");
        current = null;
      }
      
      while(current!=null){
        if(current.right!=null)
          s.push(current.right);
        s.push(current);
        current = current.left;
      }
    }
    
  }
  
  /**
   * BFS level order traversals
   * */
  public void bfsTree(){
    if(root == null) return;
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.offer(root);
    
    while(q.size()!=0){
      TreeNode node = q.poll();
      
      if(node!=null){
        System.out.print(node.val + " ");
        q.offer(node.left);
        q.offer(node.right);
      }
    }
  }
  
  /**
   * BFS level order traversals on each line
   * */
   public void printBfsTreeByLine(){
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.offer(root);
    
    int thisChild = 1;
    int nextChild = 0;
    
    while(q.size()!=0){
      TreeNode node = q.poll();
      
      System.out.print(node.val + " ");
      thisChild--;
      
      if(node.left!=null){
        nextChild++;
        q.offer(node.left);
      }
      if(node.right!=null){
        nextChild++;
        q.offer(node.right);
      }
      
      if(thisChild == 0){
        System.out.print("\n");
        thisChild = nextChild;
        nextChild = 0;
      }
      
    }
  }
  
  /**
   * BFS level order traversals on each line
   * */
   public void reverseBfs(){
    if(root == null) return;
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    Stack<TreeNode> s = new Stack<TreeNode>();
    
    q.offer(root);
    
    while(q.size()!=0){
      TreeNode node = q.poll();
      
      if(node!=null){
        s.push(node);
        q.offer(node.left);
        q.offer(node.right);
      }
    }
    
    while(!s.empty()){
      TreeNode node = s.pop();
      System.out.print(node.val + " ");
    }
   }
  
  
}