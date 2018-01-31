import java.util.*;

/**
 * Weight Node to use with Prim's algorithm, weight is included into
 * the node to eliminated the need of Edge.Dest & Source node can be 
 * found from adjacency list
 **/
class WeightedNode {
  int v;
  int weight;
  
  public WeightedNode(int v, int weight){
    this.v = v;
    this.weight = weight;
  }
  
  public boolean equals(Object other){
		if(other == null) return false;
		if(other == this) return true;
		if(this.getClass() != other.getClass()) return false;
		
		WeightedNode otherNode = (WeightedNode) other;
		return otherNode.v == this.v;
	}
  
  public int hashCode(){
    return this.v;
  }
}

class MSTNode implements Comparable<MSTNode> {
  int v;
  int key;
  
  public MSTNode(int v, int key){
    this.v = v;
    this.key = key;
  }
  
  public MSTNode(int v){
    this.v = v;
    this.key = Integer.MAX_VALUE;
  }
  
  public int compareTo(MSTNode node){
    return Integer.compare(this.key, node.key);
  }
  
  public boolean equals(Object other){
		if(other == null) return false;
		if(other == this) return true;
		if(this.getClass() != other.getClass()) return false;
		MSTNode otherNode = (MSTNode) other;
		return otherNode.v == this.v;
	}
}

public class WeightedGraph{
  int V; 
  int E;
  ArrayList[] adj;
  
  public WeightedGraph(int V){
    this.V = V;
    this.E = 0;
    adj = new ArrayList[V];
    for(int i = 0; i < V; i++){
      adj[i] = new ArrayList<WeightedNode>();
    }
  }
  
  public void addEdge(int a, int b, int weight){
    adj[a].add(new WeightedNode(b, weight));
    adj[b].add(new WeightedNode(a, weight));
  }
  
  public void removeEdge(int a, int b, int weight){
    adj[a].remove(new WeightedNode(b, weight));
    adj[b].remove(new WeightedNode(a, weight));
  }
  
  public void printGraph(){
    for(int i = 0; i < adj.length; i++){
      System.out.print(i+" :");
      Iterator<WeightedNode> iter = adj[i].iterator();
      while(iter.hasNext()){
        WeightedNode node = iter.next();
        System.out.print(node.v + "("+node.weight+") ");
      }
      System.out.println();
    }
  }
  
  public void primMST(){//O(ElogV)
    int[] parent = new int[V];
    boolean[] mst = new boolean[V];
    int[] key = new int[V];
    
    PriorityQueue<MSTNode> queue = new PriorityQueue<>();
    
    queue.add(new MSTNode(0));
    parent[0] = -1;
    
    for(int i = 1; i < V; i++){
      queue.add(new MSTNode(i));
      key[i] = Integer.MAX_VALUE;
      parent[i] = -1;
    }
    
    while(queue.size()!=0){
      MSTNode node = queue.poll();
      int v = node.v;
      mst[v] = true;
      Iterator<WeightedNode> iter = adj[v].iterator();
      
      while(iter.hasNext()){
        WeightedNode neighbor = iter.next();
        int w = neighbor.v;
        if(!mst[w] && neighbor.weight < key[w]){
          parent[w] = v;
          key[w] = neighbor.weight;
          updateKey(queue, w, key[w]);
        }
      }
    }
    
    printMST(parent);
  }
  
  private void updateKey(PriorityQueue<MSTNode> q, int w, int key){
    MSTNode newNode = new MSTNode(w, key);
    q.remove(newNode);
    q.add(newNode);
  }
  
  private void printMST(int[] parent){
    for(int i = 1; i < parent.length; i++){
      System.out.println(parent[i]+" - " + i);
    }
  }
  
}
