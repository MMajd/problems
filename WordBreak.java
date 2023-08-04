/*
 @link https://leetcode.com/problems/word-break/description/ 
 @category(trie/dp)

Given a string s and a dictionary of strings wordDict, 
return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
    Input: s = "leetcode", wordDict = ["leet","code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
    Input: s = "applepenapple", wordDict = ["apple","pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
    Note that you are allowed to reuse a dictionary word.

Example 3:
    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
    Output: false
 

Constraints:
    1 <= s.length <= 300
    1 <= wordDict.length <= 1000
    1 <= wordDict[i].length <= 20
    s and wordDict[i] consist of only lowercase English letters.
    All the strings of wordDict are unique.

*/ 


class Solution {

    private class Trie {
        final private Trie[] nodes = new Trie[26]; 
        boolean word = false;

        public boolean containsKey(char key) {
           return nodes[key-'a'] != null; 
        }

        public Trie get(char key) {
            return nodes[key-'a'];  
        }

        public boolean isWord() {
            return word == true; 
        }

        public void add(char key) {
            nodes[key-'a'] = new Trie(); 
        }

        public void markWord() {
            word = true; 
        }
    }

    public void addWord(Trie node, String word) {
        Trie curr = node; 

        for (char c : word.toCharArray()) {
            if (!curr.containsKey(c)) {
                curr.add(c);
            }
            curr = curr.get(c); 
        }
        curr.markWord(); 
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Trie root = new Trie(); 

        for (String word : wordDict) {
            addWord(root, word);
        }

        return bt(s, 0, root); 
    }

    public boolean bt(String s, int idx, Trie root) {
        Trie node = root;
        int n = s.length();

        boolean[] dp = new boolean[n]; 
        for(int i=0;i<n;i++) {
            if(i == 0 || dp[i-1]) {
                node = root; 
                for(int j=i;j<n;j++) {
                    char ch = s.charAt(j);
                    if(!node.containsKey(ch)) break;
                    node = node.get(ch);
                    if(node.isWord()){
                        dp[j] = true;
                    }
                }
            }
        }

        return dp[n-1];
    }
}

