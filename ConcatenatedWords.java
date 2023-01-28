/*
 @link https://leetcode.com/problems/concatenated-words/description/
 @categories (dp/dfs/trie)

 Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.


Example 1:
    Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
    Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
    Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
    "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
    "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

Example 2:
    Input: words = ["cat","dog","catdog"]
    Output: ["catdog"]

Constraints:
    1 <= words.length <= 10^4
    1 <= words[i].length <= 30
    words[i] consists of only lowercase English letters.
    All the strings of words are unique.
    1 <= sum(words[i].length) <= 10^5
 */

class Solution {
    Trie root = null; // implemenation at the end  
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        root = new Trie('*');
        List<String> ans = new ArrayList<>(); 

        for (String w : words) { 
            insert(root, w.toCharArray()); 
        }

        for (String w : words) { 
            if (search(root, 0, 0, w.toCharArray())) {
                ans.add(w);
            }
        }

        return ans; 
    }

    private boolean search(Trie temp, int found, int idx, char[] word) {
        if (idx == word.length) {
            return found > 1 ? true : false; 
        }

        for (int i=idx; i<word.length; i++) {
            if (!temp.contains(word[i])) return false; 

            temp = temp.get(word[i]);
            if (temp.word) {
                if (search(root, found+1, i+1, word)) return true; 
            }
        }

        return false; 
    }

    private void insert(Trie root, char[] word) {
        Trie temp = root;
        for (int i=0; i<word.length; i++) {
            if (temp.contains(word[i])) {
                temp = temp.get(word[i]);
            } else { 
                temp = temp.insert(word[i]);
            }
        }
        temp.word = true; 
        temp.len = word.length; 
    }


    /** 
     * Trie Node
     */
    private static final class Trie {
        char val; 
        int len = 0; 
        Trie[] children = new Trie[26]; 
        boolean word = false;

        public Trie(char key) { this.val = key; } 
        public boolean contains(char key) { 
            if (children[key-'a'] != null) return true; 
            return false; 
        }
        public Trie insert(char key) {
            if (contains(key)) return children[key-'a']; 
            children[key-'a'] = new Trie(key);
            return children[key-'a']; 
        }
        public Trie get(char key) {
            return children[key-'a']; 
        }
    }
}

/**
 * DP Solution, 
 * We split words to prefix & suffix starting with prefix of null, and going on till prefix will be [0, word.length] 
 * State can be managed by boolean array, like this dp[i] = dp[j] && duplicates.contains(word.substring(j, i);
 *
 * i starts from 1 till equals word.length
 * j starts from zero if 1 is not word.length, 
 * and 1 if i is word.length to hava at least a 2 words one at (0, 1) and other (1, length)
 */ 
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        final Set<String> dictionary = new HashSet<>(Arrays.asList(words));
        final List<String> answer = new ArrayList<>();
        for (final String word : words) {
            final int length = word.length();
            final boolean[] dp = new boolean[length + 1];
            dp[0] = true;
            for (int i = 1; i <= length; ++i) {
                for (int j = (i == length ? 1 : 0); !dp[i] && j < i; ++j) {
                    dp[i] = dp[j] && dictionary.contains(word.substring(j, i)); 
                }
            }
            if (dp[length]) {
                answer.add(word);
            }
        }
        return answer;   
    }
}
