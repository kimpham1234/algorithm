public class BST extends MyBinaryTree {
  int size;
  
  public BST(){
    super();
    size = 0;
  }
  
  //insert
  public void insert(int val){
    TreeNode newNode = new TreeNode(val);
    if(root == null){
      root = newNode;
    }else{
      root = insert(root, val);
    }
    size++;
  }
  
  private TreeNode insert(TreeNode root, int val){
    if(root == null)
      return new TreeNode(val);
    if(val < root.val)
      root.left = insert(root.left, val);
    else root.right = insert(root.right, val);
    return root;
  }
  
  //iterative insert
  public void iterativeInsert(int val){
    TreeNode newNode = new TreeNode(val);
    if(root == null){
      root = newNode;
      size++;
      return;
    }
    
    TreeNode cur = root;
    TreeNode pre = root;
    while(cur!=null){
      pre = cur;
      if(val < cur.val)
        cur = cur.left;
      else
        cur = cur.right;
    }
    
    if(val < pre.val)
      pre.left = newNode;
    else pre.right = newNode;
  }
  
  //search
  public TreeNode search(int val){
    return search(root, val);
  }
  
  public TreeNode search(TreeNode root, int val){
    if(root == null) return null;
    if(root.val == val)
      return root;
    if(val < root.val) return search(root.left, val);
    else return search(root.right, val);
  }
  
  //iterative search
  public TreeNode iterativeSearch(int val){
    if(root == null) return null;
    
    TreeNode cur = root;
    
    while(cur!=null){
      if(cur.val == val)
        return cur;
      if(val < cur.val){
        cur = cur.left;
      }else cur = cur.right;
    }
    
    return null;
  }
  
  public void delete(int val){
    root = delete(root, val);
  }
  
  private TreeNode delete(TreeNode root, int val){
    if(root == null) return root;
    
    if(val < root.val)
      root.left = delete(root.left, val);
    else if(val > root.val)
      root.right = delete(root.right, val);
    else{
      if(root.left == null){
        return root.right;
      }else if(root.right == null) 
        return root.left;
      
      TreeNode successor = findSuccessor(root.right);
      root.val = successor.val;
      successor.val = val;
      
      root.right = delete(root.right, val);
      
    }
    return root;
  }
  
  public boolean isLeaf(TreeNode node){
    return node.left == null && node.right == null;
  }
  
  public TreeNode findSuccessor(TreeNode root){
    TreeNode ptr = root;
    while(ptr.left!=null){
      ptr = ptr.left;
    }
    return ptr;
  } 
}