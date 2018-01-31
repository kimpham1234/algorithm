class Main {
  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("bria");
    trie.insert("bright");
    trie.insert("brian");
    trie.insert("briam");
    
    trie.delete("bright");
    
    trie.testSearchPrefix("bri");
    
    
    
  }
}
