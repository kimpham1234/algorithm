import java.util.*;

enum Color {
  WHITE, GRAY, BLACK;
}

public class DirectedGraph extends Graph{
  
  public DirectedGraph(int V){
    super(V);
  }
  
  public void addEdge(int src, int dest){
    adj[src].add(dest);
    E++;
  }
  
  public void removeEdge(int src, int dest){
    adj[src].remove((Integer) dest);
    E--;
  }
  
  /**
   * Detect a cycle using dfs
   **/
  public boolean isCyclic(){
    boolean[] visited = new boolean[this.V];
    boolean[] recurStack = new boolean[this.V];
    for(int i = 0; i < V; i++){
      if(visited[i] == false){
        if(isCyclic(i, visited, recurStack))
          return true;
      }
    }
    return false;
  }
  
  private boolean isCyclic(int v, boolean[] visited, boolean[] recurStack){
    
    //mark visited & recurStack true
    visited[v] = true;
    recurStack[v] = true;
    
    Iterator<Integer> iter = adj[v].iterator();
    
    //go through neighbors & do dfs
    while(iter.hasNext()){
      int u = iter.next();
      if(!visited[u] && isCyclic(u, visited, recurStack))//if not visited, do isCyclic & func returns true
        return true;
      else if(visited[u] && recurStack[u])//if is in recurStack
        return true;
    }
    
    recurStack[v] = false;
    return false;
  }
  
  /**
   * Detect a cycle using color
   **/
  public boolean isCyclicColor(){
    Color[] color = new Color[V];
    
    for(int i = 0; i < V; i++){
      color[i] = Color.WHITE;
    }
    
    for(int i = 0; i < V; i++){
      if(color[i] == Color.WHITE)
        if(isCyclicColor(i, color))
          return true;
    }
    return false;
  }
  
  private boolean isCyclicColor(int v, Color[] color){
    color[v] = Color.GRAY;
    Iterator<Integer> iter = adj[v].iterator();
    
    while(iter.hasNext()){
      int u = iter.next();
      if(color[u] == Color.WHITE){
        if(isCyclicColor(u, color))
          return true;
      }else if(color[u] == Color.GRAY){
        return true;
      }
    }
    color[v] = Color.BLACK;
    return false;
  }
  
  /**
   * Topological Sorting
   **/
  public void topologicalSort(){
    Stack<Integer> stack = new Stack<>();
    boolean[] visited = new boolean[V];
    
    for(int i = 0; i < V; i++){
      if(!visited[i])
        topologicalSort(i, stack, visited);
    }
    
    while(stack.empty() == false){
      System.out.print(stack.pop()+ " ");
    }
  }
  
  private void topologicalSort(int v, Stack<Integer> stack, boolean[] visited){
    
    visited[v] = true;
    
    Iterator<Integer> iter = adj[v].iterator();
    
    while(iter.hasNext()){
      int u = iter.next();
      if(visited[u]==false)
        topologicalSort(u, stack, visited);
    }
    
    stack.push(v);
  }
}

