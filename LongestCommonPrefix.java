/**
 *
 * @link https://leetcode.com/problems/longest-common-prefix/
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

class Solution {
    private Node head; 
    
    public Solution() { 
        head = new Node();
    }
    
    private void insert(String word) { 
        Node curr = head; 
        
        for (int i=0; i<word.length(); i++) { 
            char key = word.charAt(i);
            if (!curr.containsKey(key)) { 
                curr.put(key, new Node());
            }
            
            curr = curr.get(key); 
        }
        
        curr.setWordMark();
    }
    
    public String longestCommonPrefix(String[] strs) {
        Node curr; 
        int ans = Integer.MAX_VALUE; 
        int counter = 0;
        
        if (strs.length == 1) return strs[0];
        
        insert(strs[0]);
        
        
        for (int i=1; i<strs.length; i++) {
            curr = head; 
            counter = 0; 
            String word = strs[i];
            
            for (int j=0; j<word.length(); j++) { 
                char key = word.charAt(j);
                if (!curr.containsKey(key)) break;
                counter += 1; 
                curr = curr.get(key); 
            }
            
            ans = Math.min(ans, counter);
        }
        
        return strs[0].substring(0, ans);
    }
}
