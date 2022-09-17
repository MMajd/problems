
/** 
 *
 * @link https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 */

class Node { 
    private Node[] children; 
    private final int MAP_SIZE = 26; // alphabet chars 
    private boolean isWord; 
    
    public Node() { 
        children = new Node[MAP_SIZE]; 
    }
    
    public boolean containsKey(char key) { 
        return children[key - 'a'] != null; 
    }
    
    public Node get(char key) { 
        if (containsKey(key))
            return children[key - 'a']; 
        
        return null;
    }
    
    public void put(char key, Node value) { 
        children[key-'a'] = value; 
    }
    
    public void setWordMark() { 
        isWord = true;
    }
    
    public boolean isWord() { 
        return isWord; 
    }
}

class Trie {
    private Node head; 

    public Trie() {
        head = new Node();
    }
    
    
    public void insert(final String word) {
        Node curr = head;  
        
        for(int i=0; i<word.length(); i++) { 
            char key = word.charAt(i);
            
            if (!curr.containsKey(key)) { 
                curr.put(key, new Node());
            }
            
            curr = curr.get(key);
        }
        
        curr.setWordMark();
    }
    
    /** 
     * We grouped the search & startsWith operations
     * under this method as they considered as one operation 
     * with a minor difference that can be handled in each one of them
     * if we return the node not a boolean 
     * */
    private Node searchNode(String word) { 
        Node curr = head; 
        
        for (int i=0; i<word.length(); i++) { 
            char key = word.charAt(i);
            
            if (!curr.containsKey(key)) 
                return null;
            
            curr = curr.get(key);
        }
        
        return curr;
    }
    
    public boolean search(String word) {
        Node n = searchNode(word);
        
        return n != null && n.isWord();
    }
    
    public boolean startsWith(String prefix) {
        Node n = searchNode(prefix);
        return n != null;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
