public class AVLTree {
  Node root;

  public AVLTree(){
    root = null;
  }

  private int height(Node node){
    if(node == null) return 0;
    else return node.height;
  }
  
  private int getBalance(Node node){
    if(node == null) return 0;
    else return height(node.left) - height(node.right);
  }
  
  private Node rightRotate(Node y){
    Node x = y.left;
    Node t2 = x.right;
    
    y.left = t2;
    x.right = y;
    
    y.height = 1 + Math.max(height(y.left), height(y.right));
    x.height = 1 + Math.max(height(x.left), height(x.right));
    
    return x;
  }
  
  private Node leftRotate(Node x){
    Node y = x.right;
    Node t2 = y.left;
    
    x.right = t2;
    y.left = x;
    
    x.height = 1 + Math.max(height(x.left), height(x.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));
    
    return y;
  }
  
  public void insert(int val){
    root = insert(root, val);
  }
  
  private Node insert(Node node, int val){
    //insert like normal
    if(node == null)
      return new Node(val);
    else if(val < node.val)
      node.left = insert(node.left, val);
    else if(val > node.val)
      node.right = insert(node.right, val);
    
    //update height
    node.height = 1 + Math.max(height(node.left), height(node.right));
    int balance = getBalance(node);
    
    //left left
    if(balance > 1 && val < node.left.val){
      return rightRotate(node);
    }else if(balance > 1 && val > node.left.val){//left right
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }else if(balance < -1 && val > node.right.val){//right right
      return leftRotate(node);
    }else if(balance < -1 && val < node.right.val){//right left
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }
    
    return node;
  }
  
  public void delete(int val){
    root = delete(root, val);
  }
  
  public Node delete(Node node, int val){
    if(node == null) return null;
    
    if(val < node.val)
      node.left = delete(node.left, val);
    else if(val > node.val)
      node.right = delete(node.right, val);
    else{
      if(node.left == null)
        return node.right;
      else if(node.right == null)
        return node.left;
      else{
        Node min = findMin(node.right);
        
        //swap
        node.val = min.val;
       
        node.right = delete(node.right, node.val);
      }
    }
    
    node.height = 1 + Math.max(height(node.left), height(node.right));
    
    int balance = getBalance(node);
    
    //left left
    if(balance > 1 && getBalance(node.left) > 0){
      return rightRotate(node);
    }else if(balance > 1 && getBalance(node.left) < 0){//left right
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }else if(balance < -1 && getBalance(node.right) < 0){//right right
      return leftRotate(node);
    }else if(balance < -1 && getBalance(node.right) > 0){//right left
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }
    return node;
  }
  
  public Node findMin(Node node){
    if(node.left!=null){
      node = node.left;
    }
    return node;
  }
  
  
  public void inorderIndented(){
    inorderIndented(root, "");
  }
  
  private void inorderIndented(Node root, String tab){
    if(root==null) return;
    
    inorderIndented(root.right, tab+"  ");
    System.out.println(tab+ root.val);
    inorderIndented(root.left, tab+"  ");
  }
  
  public void preorder(){
    preorder(root);
  }
  
  private void preorder(Node root){
    if(root==null) return;
    
    System.out.print(root.val+" ");
    preorder(root.left);
    preorder(root.right);
  }  
}

