/*
 
 @link https://leetcode.com/problems/palindrome-pairs/
 @link http://www.allenlipeng47.com/blog/index.php/2016/03/15/palindrome-pairs/
  
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
    private static class Node {
        private int idx; 
        private Node[] children; 
        private List<Integer> p; 
        
        public Node() { 
            idx = -1; 
            children = new Node[26]; 
            p = new ArrayList<>(); 
        }
        
        public boolean containsKey(int key) {
            return children[key-'a'] != null;
        }
        
        public Node get(char key) {
           if (containsKey(key)) return children[key-'a'];
            return null; 
        }
        
        public void put(char key, Node value) { 
            children[key-'a'] = value; 
        }
        
        public boolean isWord() { return idx != -1; }
        
        public void setWordMark(int i) { idx = i; }
        public int getWordMark() { return idx; }
        
        public void addPalindrome(Integer i) { p.add(i); }
        public List<Integer> getPalindromes() { return p; }
    }
    
    private static class Trie {
        private Node root; 
        
        public Trie() { 
            root = new Node(); 
        }
        
        public void insert(String word, int idx) {
            int n = word.length();
            Node curr = root; 
            
            for (int i=n-1; i>=0; --i) { 
                char key = word.charAt(i);
                
                if (isPalindrome(word, 0, i)) { 
                    curr.addPalindrome(idx);
                }
                
                if (!curr.containsKey(key)) { 
                    curr.put(key, new Node());
                } 
                
                curr = curr.get(key);
            }
            
            curr.setWordMark(idx);
            curr.addPalindrome(idx);
        }
        
        public List<List<Integer>> getPalindromePairs(String word, int idx) {
            Node curr = root; 
            List<List<Integer>> pairs = new ArrayList<>(); 
            
            int n = word.length();

            for (int j=0; j<n && curr!=null; j++) { 
                
                if (curr.isWord() 
                    && curr.getWordMark() != idx 
                    && isPalindrome(word, j, n-1)) { 
                    pairs.add(Arrays.asList(new Integer[]{idx, curr.getWordMark()}));
                }
                
                curr = curr.get(word.charAt(j));
            }
            
            if (curr != null && curr.getPalindromes().size() > 0) { 
                for (int j : curr.getPalindromes()) { 
                    if (idx != j) pairs.add(Arrays.asList(new Integer[]{idx, j}));
                }
            }
            
            return pairs; 
        }
    }
    
    public static boolean isPalindrome(String word, int i, int j) {
        while (i < j) { 
            if (word.charAt(i++) != word.charAt(j--)) return false; 
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
        
        for (int i = 0; i<words.length; i++) {
            ans.addAll(trie.getPalindromePairs(words[i], i));
        }

        return ans; 
    }
}


/** Using Hasmap */
class Solution1 {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String,Integer> wordMap = new HashMap<>();
        Set<Integer> set = new TreeSet<>();
        int n = words.length;
        
        for(int i=0;i<n;i++){
            wordMap.put(words[i],i);
            set.add(words[i].length());
        }
        
        List<List<Integer>> ans = new ArrayList<>();
        
        for(int i=0;i<n;i++){
            int length = words[i].length();
            
            if(length ==1){
                if(wordMap.containsKey("")){
                    ans.add(Arrays.asList(i, wordMap.get("")));
                    ans.add(Arrays.asList(wordMap.get(""), i));
                }
                continue;
            }
            String reverse= new StringBuilder(words[i]).reverse().toString();
            if(wordMap.containsKey(reverse) && wordMap.get(reverse) != i)
                ans.add(Arrays.asList(i,wordMap.get(reverse)));
            
            for(Integer k:set){
                if(k==length)
                    break;
                if(isPalindrome(reverse,0,length-1-k)){
                    String s1 = reverse.substring(length-k);
                    if(wordMap.containsKey(s1))
                        ans.add(Arrays.asList(i,wordMap.get(s1)));
                }
                
                if(isPalindrome(reverse,k,length-1)){
                    String s2 = reverse.substring(0,k);
                    if(wordMap.containsKey(s2))
                        ans.add(Arrays.asList(wordMap.get(s2),i));
                }
            }
        }
        return ans;
    }
    
    private boolean isPalindrome(String s, int left, int right){
        while(left<right)
            if(s.charAt(left++)!=s.charAt(right--))
                return false;
        return true;
    }
}

