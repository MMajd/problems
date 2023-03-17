/* 
 @link https://leetcode.com/problems/implement-trie-prefix-tree/
 @categories (trie/design/n-tree)

 A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. 
There are various applications of this data structure, such as autocomplete and spellchecker.

* Implement the Trie class:
- Trie() Initializes the trie object.
- void insert(String word) Inserts the string word into the trie.
- boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
- boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

Example 1:
    Input
    ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
    [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
    Output
    [null, null, true, false, true, null, true]

    Explanation
    Trie trie = new Trie();
    trie.insert("apple");
    trie.search("apple");   // return True
    trie.search("app");     // return False
    trie.startsWith("app"); // return True
    trie.insert("app");
    trie.search("app");     // return True

Constraints:
    1 <= word.length, prefix.length <= 2000
    word and prefix consist only of lowercase English letters.
    At most 3 * 10^4 calls in total will be made to insert, search, and startsWith
*/

class Trie {
    private TreeNode root; 
    public Trie() {
        root = new TreeNode(); 
    }
    
    public void insert(String word) {
        TreeNode node = root; 
        for (int i=0; i<word.length(); i++) {
            char key = word.charAt(i);
            node = node.next(key);
        }
        node.word = true; 
    }
    
    public boolean search(String word) {
        TreeNode node = prefixSearch(word);
        return node != null && node.word;
    }
    
    public boolean startsWith(String prefix) {
        return prefixSearch(prefix) != null;
    }

    private TreeNode prefixSearch(String word) {
        TreeNode node = root; 
        for (int i=0; i<word.length(); i++) {
            char key = word.charAt(i);
            if (!node.contains(key)) {
                return null; 
            }
            node = node.next(key);
        }
        return node; 
    }

    private class TreeNode {
        TreeNode[] children = new TreeNode[26];
        boolean word = false; 

        public boolean contains(char key) {
            return children[key-'a'] != null; 
        }

        public TreeNode next(char key) {
            if (children[key-'a'] == null) {
                children[key-'a'] = new TreeNode();
            }
            return children[key-'a']; 
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
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
