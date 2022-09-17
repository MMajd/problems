/*
 
 @link https://leetcode.com/problems/palindrome-pairs/
  
 Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.

Example 1:
    Input: words = ["abcd","dcba","lls","s","sssll"]
    Output: [[0,1],[1,0],[3,2],[2,4]]
    Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

Example 2:
    Input: words = ["bat","tab","cat"]
    Output: [[0,1],[1,0]]
    Explanation: The palindromes are ["battab","tabbat"]

Example 3:
    Input: words = ["a",""]
    Output: [[0,1],[1,0]]

Constraints:
    1 <= words.length <= 5000
    0 <= words[i].length <= 300
    words[i] consists of lower-case English letters.
  
 */


class Solution {
    class Node { 
        static final int SIZE = 26; 
        int idx; 
        Node[] children; 
        boolean isword; 
        List<Integer> palins; 
        
        public Node() { 
            idx = -1; 
            children = new Node[SIZE]; 
            palins = new ArrayList<>(); 
        }
        
        public boolean containsKey(char key) { 
            return children[key-'a'] != null; 
        }
        
        public Node get(char key) {
            if (containsKey(key)) return children[key-'a']; 
            return null; 
        }
        
        public void put(char key, Node value) {
            children[key-'a'] = value; 
        }
        
        public void setIdx(int i) {
            idx = i; 
        }
    }
    
    class Trie {
        private Node root; 
        
        public Trie() { 
            root = new Node(); 
        }
        
        public void insert(String word, int idx) { 
            Node curr = root; 
            
            for (int i=word.length()-1; i>=0; --i) { 
                char key = word.charAt(i);
                
                if (!curr.containsKey(key)) { 
                    curr.put(key, new Node()); 
                }
                
                if (isPalindrome(word, 0, i)) {
                    curr.palins.add(idx);
                }
                
                curr = curr.get(key); 
            }
            
            curr.setIdx(idx); 
            curr.palins.add(idx);
        }
        
        
        public List<List<Integer>> findPairs(String word, int idx) {
            int n = word.length();
            Node curr = root; 
            
            List<List<Integer>> pairs = new ArrayList<>();
            
            for (int i=0; i<n && curr != null; i++) { 
                if (curr.idx >= 0 && idx!=curr.idx && isPalindrome(word, i, n-1)) { 
                    pairs.add(Arrays.asList(new Integer[]{idx, curr.idx}));
                }
                
                char key = word.charAt(i);
                curr = curr.get(key); 
            }
            
            if (curr != null && curr.palins.size() > 0) { 
                for (int i : curr.palins) { 
                    if (idx != i) { 
                        pairs.add(Arrays.asList(new Integer[]{idx, i}));
                    }
                }
            }
            
            return pairs; 
        }
    }
    
    
    public static boolean isPalindrome(String word, int i, int j) { 
        while (i<j) {
            if (word.charAt(i++) != word.charAt(j--)) 
                return false; 
        }
        
        return true; 
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length; 
        List<List<Integer>> ans = new ArrayList<>(); 
        
        Trie trie = new Trie(); 
        
        for (int i=0; i<n; i++) {
            trie.insert(words[i], i);
        }
        
        for (int i = 0; i < words.length; i++) {
            ans.addAll(trie.findPairs(words[i], i));
        }

        return ans; 
    }
}
