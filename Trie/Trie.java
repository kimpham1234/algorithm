import java.util.*;
class TrieNode{
  static int ALPHABET_SIZE = 26;
  TrieNode[] children;
  boolean isEndOfWord;
  
  public TrieNode(){
    children = new TrieNode[ALPHABET_SIZE];
    for(int i = 0; i< children.length; i++){
      children[i] = null;
    }
    isEndOfWord = false;
  }
}

public class Trie {
  TrieNode root;
  
  public Trie(){
    root = new TrieNode();
  }
  
  public void insert(String s){
    char[] c = s.toCharArray();
    
    TrieNode ptr = root;
    
    for(int i = 0; i < c.length; i++){
      int childIndex = Character.toLowerCase(c[i]) - 'a';
      if(ptr.children[childIndex] == null){
        ptr.children[childIndex] = new TrieNode();
      }
      ptr = ptr.children[childIndex];
    }
    
    ptr.isEndOfWord = true;
  }
  
  public void testSearch(String s){
    if(searchFullWord(s))
      System.out.println("Found");
    else System.out.println("Not Found");
  }
  
  public void testSearchPrefix(String s){
    if(searchPrefixOnly(s))
      System.out.println("Found");
    else System.out.println("Not Found");
  }
  
  public boolean searchFullWord(String s){
    char[] c = s.toCharArray();
    
    TrieNode ptr = root;
    
    for(int i = 0; i < c.length; i++){
      int childIndex = Character.toLowerCase(c[i]) - 'a';
      if(ptr.children[childIndex] == null)
        return false;
      else ptr = ptr.children[childIndex];
    }
    
    if(ptr.isEndOfWord == false)
      return false;
    else return true;
  }
  
  public boolean searchPrefixOnly(String s){
    char[] c = s.toCharArray();
    
    TrieNode ptr = root;
    
    for(int i = 0; i < c.length; i++){
      int childIndex = Character.toLowerCase(c[i]) - 'a';
      if(ptr.children[childIndex] == null)
        return false;
      else ptr = ptr.children[childIndex];
    }
    
    return true;
  }
  
  private boolean hasChild(TrieNode node){
    for(int i = 0; i < node.children.length; i++){
      if(node.children[i]!=null)
        return true;
    }
    return false;
  }
  
  public void delete(String s){
    delete(root, s, 0);
  }
  
  private boolean delete(TrieNode current, String s, int level){
    if(level == s.length()){
      if(!current.isEndOfWord)
        return false;
      current.isEndOfWord = true;
      
      return hasChild(current) == false;
    }else{
      int i = s.charAt(level)-'a';
      TrieNode child = current.children[i];
      if(child == null){
        return false;
      }
      boolean shouldDeleteCurrent = delete(child, s, level+1);
      
      if(shouldDeleteCurrent){
        current.children[i] = null;
        return hasChild(current) == false;
      }
      return false;
    }
  }
}


