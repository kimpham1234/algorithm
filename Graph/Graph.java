import java.util.*;

public class Graph {
  int V;
  int E;
  ArrayList[] adj;
  
  public Graph(int V){
    this.V = V;
    this.E = 0;
    adj = new ArrayList[V];
    for(int i = 0; i < V; i++){
      adj[i] = new ArrayList<Integer>();
    }
  }
  
  public void addEdge(int a, int b){
    adj[a].add(b);
    adj[b].add(a);
    E++;
  }
  
  public void removeEdge(int a, int b){
    adj[a].remove((Integer) b);
    adj[b].remove((Integer) a);
    E--;
  }
  
  public boolean isConnected(int a, int b){
    return adj[a].contains((Integer) b);
  }
  
  public int edges(){
    return E;
  }
  
  public int vertices(){
    return V;
  }
  
  /**
   *Breath First Traversal
   **/
  public void bfs(int s){
    boolean[] visited = new boolean[V];
    
    Queue<Integer> q = new LinkedList<>();
    
    q.offer(s);
    
    visited[s] = true;
    
    while(q.size()!=0){
      s = q.poll();
      
      System.out.print(s+" ");
     
      ArrayList<Integer> neighbor = adj[s];
      
      for(int i = 0; i < neighbor.size(); i++){
        if(!visited[neighbor.get(i)]){
          visited[neighbor.get(i)] = true;
          q.offer(neighbor.get(i));
        }
      }
    }
  }
  
  /**
   * Depth First Traversal
   **/
  public void dfs(){
    boolean[] visited = new boolean[V];
    
    for(int i = 0; i < V; i++){
      if(visited[i] == false)
        dfs(i, visited);
    }
  }
  
  private void dfs(int vertex, boolean[] visited){
    visited[vertex] = true;
    System.out.print(vertex + " ");
    
    ArrayList<Integer> neighbor = adj[vertex];
    
    for(int i = 0; i < neighbor.size(); i++){
      if(visited[i]==false)
        dfs(i, visited);
    }
  }
  
  public void dfsIterative(){
    boolean[] visited = new boolean[V];
    for(int i = 0; i < V; i++){
      if(visited[i]==false)
        dfsIterative(i, visited);
    }
  }
  
  private void dfsIterative(int v, boolean[] visited){
    Stack<Integer> s = new Stack<>();
    
    s.push(v);
    
    while(!s.empty()){
      v = s.pop();
      
      System.out.print(v + " ");
      visited[v] = true;
      
      ArrayList<Integer> neighbor = adj[v];
      for(int i = 0; i < neighbor.size(); i++){
        int u = neighbor.get(i);
        if(visited[u]==false)
          s.push(u);
      }
    }
  }
  
  public void printGraph(){
    for(int i = 0; i < V; i++){
      System.out.print(i+" :");
      
      ArrayList<Integer> l = adj[i];
      
      for(int j = 0; j < l.size(); j++){
        System.out.print(l.get(j) + " ");
      }
      
      System.out.println();
    }
  }
  
  /**
   * Detect a cycle
   **/
  private boolean isCyclic(int v, boolean[] visited, int parent){
    visited[v] = true;
   
    Iterator<Integer> iter = adj[v].iterator();
    
    while(iter.hasNext()){
      int u = iter.next();
      if(!visited[u]){
        if(isCyclic(u, visited, v))
          return true;
      }
      else if(u != parent){
        return true;
      }
    }
    return false;
  }
  
  public boolean isCyclic(){
    boolean[] visited = new boolean[V];
    
    for(int i = 0; i < V; i++){
      if(visited[i]==false && isCyclic(i, visited, -1))
        return true;
    }
    return false;
  } 
}