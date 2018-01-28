import java.util.*;
import java.util.HashMap;
import java.util.Map.Entry;


/*Contains solutions about traversing and constructing a binary tree
  1.Print Postorder traversal from inorder and preorder traversal
  2.Traverse the tree diagonally & vertically
  3.Print Boundary of a tree anti-clockwise 
  4.Construct a tree from inorder and preorder traversal
  5.Construct a tree from inorder and postorder traversal
  6.Construct full binary tree from level order traversal
  7.Construct full binary tree from preorder and postorder
  8.Construct full binary tree from preorder and Leaf/NoLeaf Array
  
  9.Find Lowest Common Ancestor of Two Node
  10.Find distance between 2 Nodes of a binary tree
  11.Find height or depth of the tree
  12.Find number of element in a tree
  13.Find if tree has a root to leaf path sum
*/

public class TreeUtil {
  
  /**
   * Q1: Print Postorder traversal from inorder and preorder traversal
   **/
  static public void printPostOrder(int[] inorder, int[] preorder){
    List<Integer> pre = makeList(preorder);
    Iterator<Integer> iter = pre.iterator();
    print(inorder, preorder, iter, 0, inorder.length-1);
  }
  
  static private void print(int[] inorder, int[] preorder, Iterator<Integer> iter, int start, int end){
    if(start > end)
      return;
    if(iter.hasNext()){
      int r = iter.next();
      int rIndex = search(r, inorder);
      print(inorder, preorder, iter, start, rIndex-1);
      print(inorder, preorder, iter, rIndex+1, end);
      System.out.print(inorder[rIndex]+" ");
      
    }
  }
  
  static private List<Integer> makeList(int[] a){
    List<Integer> list = new ArrayList<>();
    for(int i = 0; i < a.length; i++){
      list.add(a[i]);
    }
    return list;
  }
  
  static private List<Character> makeList(char[] a){
    List<Character> list = new ArrayList<>();
    for(int i = 0; i < a.length; i++){
      list.add(a[i]);
    }
    return list;
  }
  
  static private int search(int root, int[] preorder){
    for(int i = 0; i < preorder.length; i++){
      if(root == preorder[i])
        return i;
    }
    return -1;
  }
  
  /**
   * Q2: Traverse the tree diagonally & vertically
   * https://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
   **/
   static public void printDiagonal(TreeNode root){
     HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
     getDiagonalsValue(root, 0, map);
     
     for(Entry<Integer, ArrayList<Integer>> entry : map.entrySet()){
        System.out.print(entry.getKey()+": ");
        System.out.println(entry.getValue());
     }
   }
   
   static private void getDiagonalsValue(TreeNode root, int d, HashMap<Integer, ArrayList<Integer>> map){
     if(root == null) return;
     
     ArrayList<Integer> list = map.get(d);
     if(list == null){
       list = new ArrayList<Integer>();
       list.add(root.val);
     }else{
       list.add(root.val);
     }
     map.put(d, list);
     
     getDiagonalsValue(root.left, d+1, map);
     getDiagonalsValue(root.right, d, map);
   }
   
   static public void printVertical(TreeNode root){
     HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
     getVerticalValue(root, 0, map);
     
     for(Entry<Integer, ArrayList<Integer>> entry : map.entrySet()){
        System.out.print(entry.getKey()+": ");
        System.out.println(entry.getValue());
     }
   }
   
   static private void getVerticalValue(TreeNode root, int d, HashMap<Integer, ArrayList<Integer>> map){
     if(root == null) return;
     
     ArrayList<Integer> list = map.get(d);
     if(list == null){
       list = new ArrayList<Integer>();
       list.add(root.val);
     }else{
       list.add(root.val);
     }
     map.put(d, list);
     
     getVerticalValue(root.left, d-1, map);
     getVerticalValue(root.right, d+1, map);
   }
   
   /**
    *Q3: Print Boundary of a tree anti-clockwise 
    **/
    static void printBoundary(TreeNode root){
      printLeftMost(root);
      printLeaves(root);
      printRightMost(root.right);
    }
    
    static void printLeftMost(TreeNode root){
      while(root!=null){
        if(!isLeaf(root)){
          System.out.print(root.val+" ");
          root = root.left;
        }else{
          root = null;
        }
      }
    }
    
    static void printLeaves(TreeNode root){
      if(root == null) return;
      printLeaves(root.left);
      if(isLeaf(root))
        System.out.print(root.val+" ");
      printLeaves(root.right);
    }
    
    static void printRightMost(TreeNode root){
      Stack<TreeNode> s = new Stack<TreeNode>();
      while(root!=null && !isLeaf(root)){
        s.push(root);
        root = root.right;
      }
      
      while(!s.empty()){
        TreeNode node = s.pop();
        System.out.print(node.val+" ");
      }
    }
    
    static boolean isLeaf(TreeNode node){
      return node.right == null && node.left == null;
    }
 
  /**
   * Q4: BuildTree1: Construct a tree from inorder and preorder traversal
   **/
  static MyBinaryTree buildTree1(int[] in, int[] pre){
    MyBinaryTree tree = new MyBinaryTree();
    List<Integer> preorder = makeList(pre); //turn array into list
    tree.root = buildTree1(in, preorder, 0, in.length-1);
    return tree;
  }
  
  static TreeNode buildTree1(int[] in, List<Integer> pre, int start, int end){
    if(start > end)
      return null;
    
    TreeNode node = new TreeNode(pre.get(0));
    pre.remove(0);

    int rIndex = search(node.val, in);
    node.left = buildTree1(in, pre, start, rIndex-1);
    node.right = buildTree1(in, pre, rIndex+1, end);
    
    return node;
  }
 
 /**
   * Q5: BuildTree2: Construct a tree from inorder and postorder traversal
   **/
  static MyBinaryTree buildTree2(int[] in, int[] post){
    MyBinaryTree tree = new MyBinaryTree();
    List<Integer> postorder = makeList(post); //turn array into list
    Collections.reverse(postorder);
    tree.root = buildTree2(in, postorder, 0, in.length-1);
    return tree;
  }
  
  static TreeNode buildTree2(int[] in, List<Integer> post, int start, int end){
    if(start > end)
      return null;
    
    TreeNode node = new TreeNode(post.get(0));
    post.remove(0);

    int rIndex = search(node.val, in);
    
    node.right = buildTree2(in, post, rIndex+1, end);
    node.left = buildTree2(in, post, start, rIndex-1);
    
    return node;
  }
 
  /**
   * Q6: BuildTree3: Construct full binary tree from level order traversal
   **/
  static MyBinaryTree buildTree3(int[] list){
    MyBinaryTree tree = new MyBinaryTree(list[0]);
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.offer(tree.root);
    
    int i = 1;
    while(q.peek()!=null && i <= list.length-2){
      TreeNode left = new TreeNode(list[i]);
      TreeNode right = new TreeNode(list[i+1]);
      TreeNode root = q.poll();
      root.left = left;
      root.right = right;
      q.offer(left);
      q.offer(right);
      i+=2;
    }
    
    return tree;
  }
  
  /**
   * Q7: BuildTree4: Construct full binary tree from preorder and postorder traversal
   **/
   static MyBinaryTree buildTree4(int[] preorder, int[] postorder){
     List<Integer> pre = makeList(preorder);
     MyBinaryTree tree = new MyBinaryTree();
     tree.root = buildTree4(postorder, pre, 0, postorder.length-1);
     return tree;
   }
   
   static TreeNode buildTree4(int[] post, List<Integer> pre, int start, int end){
    if(start > end) 
      return null;
    
    if(start == end){
      TreeNode node = new TreeNode(pre.get(0));
      pre.remove(0);
      return node;
    }   
   
    TreeNode node = new TreeNode(pre.get(0));
    int leftNode = pre.get(1);
    int rootIndex = search(node.val, post);
    int leftIndex = search(leftNode, post);
    
    pre.remove(0);
    
    node.left = buildTree4(post, pre, start, leftIndex);
    node.right = buildTree4(post, pre, leftIndex+1, rootIndex-1);
    
    return node;
   }
 
 /**
  * Q8: BuildTree 5: Construct full binary tree from preorder and Leaf/NoLeaf Array
  * https://www.geeksforgeeks.org/binary-tree-data-structure/#Introduction
  * Construct and Conversion 5
  **/
  static MyBinaryTree buildTree5(int[] preorder, char[] preLN){
     List<Integer> pre = makeList(preorder);
     List<Character> ln = makeList(preLN);
     Iterator<Integer> preIter = pre.iterator();
     Iterator<Character> lnIter = ln.iterator();
     MyBinaryTree tree = new MyBinaryTree();
     tree.root = buildTree_5(preIter, lnIter);
     return tree;
   }
   
  static TreeNode buildTree_5(Iterator<Integer> pre, Iterator<Character> ln){
    if(!pre.hasNext())
      return null;
    TreeNode node = new TreeNode(pre.next());
   
    if(ln.next() == 'N')
      node.left = buildTree_5(pre, ln);
    else return node;
   
    node.right = buildTree_5(pre, ln);
   
    return node;
  }
  
  /**
   * Q9: Find Lowest Common Ancestor of Two Node
   **/
   static TreeNode lowestCommonAncestor(TreeNode node, int n1, int n2){
    if(node == null)
      return null;
    if(node.val == n1 || node.val == n2)
      return node;
    
    TreeNode leftNode = lowestCommonAncestor(node.left, n1, n2);
    TreeNode rightNode = lowestCommonAncestor(node.right, n1, n2);
    
    if(leftNode!=null && rightNode!=null)
      return node;
    
    if(leftNode!=null) 
      return leftNode;
    else return rightNode;
   }
   
  /**
   * Q10: Find distance between 2 Nodes of a binary tree
  **/
  static class Distance{
    int d1, d2, d_lca;
    public Distance(){
      d1 = d2 = d_lca = 0;
    }
  }
  
  static int distanceBetween2Node(TreeNode root, int n1, int n2){
    Distance distances = new Distance();
    findDistances(root, n1, n2, 0, distances);
    return distances.d1 + distances.d2 - 2*distances.d_lca;
  }
  
  static TreeNode findDistances(TreeNode node, int n1, int n2, int level, Distance d){
    if(node == null)
      return null;
    if(node.val == n1){
      d.d1 = level;
      return node;
    }
    if(node.val == n2){
      d.d2 = level;
      return node;
    }
    
    TreeNode leftNode = findDistances(node.left, n1, n2, level+1, d);
    TreeNode rightNode = findDistances(node.right, n1, n2, level+1, d);
    
    if(leftNode!=null && rightNode!=null){
      d.d_lca = level;
      return node;
    }
    
    if(leftNode!=null) 
      return leftNode;
    else return rightNode;
   }
   
   /**
    * Q11: Find height or depth of the tree
    **/
    static int findHeight(TreeNode root){
      if(root == null)
        return 0;
      int leftHeight = findHeight(root.left)+1;
      int rightHeight = findHeight(root.right)+1;
      return Math.max(leftHeight, rightHeight);
    }
    
    /**
     * Q12: Find number of element in a tree
     **/
    static int findSize(TreeNode root){
      if(root == null)
        return 0;
      return findSize(root.left) + findSize(root.right)+1;
    }
    //iterative
    static int findSize2(TreeNode root){
      if(root == null) return 0;
      Queue<TreeNode> q = new LinkedList<TreeNode>();
      q.offer(root);
      int size = 0;
      
      while(q.size()!=0){
        TreeNode node = q.poll();
    
        if(node!=null){
          size++;
          q.offer(node.left);
          q.offer(node.right);
        }
      }
      return size;
    }
    
    /**
     * Q13: Find if tree has a root to leaf path sum
     **/
    static boolean hasRootLeafSum(TreeNode root, int target){
      return hasRootLeafSum(root, 0, target);
    }
    
    static boolean hasRootLeafSum(TreeNode root, int sum, int target){
      if(root == null && sum!=target)
        return false;
      if(root == null && sum == target)
        return true;
      
      sum += root.val;
      if(sum > target) return false;
      return hasRootLeafSum(root.left, sum, target) || hasRootLeafSum(root.right, sum, target);
    }
    
}






