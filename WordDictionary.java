/** 
 *
 * @link https://leetcode.com/problems/design-add-and-search-words-data-structure/
 *
 */


class Node { 
    private Map<Character, Node> children; 
    private boolean isWord; 
    
    public Node() { 
        children = new HashMap<>();
    }
    
    public boolean containsKey(char key) { 
        return children.containsKey(key); 
    }
    
    public void put(char key, Node value) { 
        children.put(key, value);
    }
    
    public Node get(char key) { 
        return children.get(key);
    }
    
    public void setWordMark() { 
        isWord = true;
    }
    
    public boolean isWord() { 
        return isWord; 
    }
    
    public Set<Map.Entry<Character,Node>> entrySet() { 
        return children.entrySet();
    }
}


class WordDictionary {
    private Node head; 

    public WordDictionary() {
        head = new Node();
    }
    
    public void addWord(String word) {
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
    
    private boolean search(int i, Node curr, String word) { 
        if (i >= word.length()) { 
            return curr!= null && curr.isWord();
        }
        
        char key = word.charAt(i);
        
        if (curr.containsKey(key)) { 
            return search(i+1, curr.get(key), word);
        }
        
        if (key == '.') { 
            for(Map.Entry<Character,Node> e : curr.entrySet()) { 
                if (search(i+1, e.getValue(), word)) { 
                    return true;
                }
            }
        }
        
       return false; 
    }
    
    public boolean search(String word) {
        Node curr = head; 
        
        return search(0, curr, word);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
