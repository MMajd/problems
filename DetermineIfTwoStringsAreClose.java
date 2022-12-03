/*
 @link https://leetcode.com/problems/determine-if-two-strings-are-close
 @categories (mapping/hash-table/sorting) 
   
Two strings are considered close if you can attain one from the other using the following operations:
Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.
Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.


Example 1:
    Input: word1 = "abc", word2 = "bca"
    Output: true
    Explanation: You can attain word2 from word1 in 2 operations.
    Apply Operation 1: "abc" -> "acb"
    Apply Operation 1: "acb" -> "bca"

Example 2:
    Input: word1 = "a", word2 = "aa"
    Output: false
    Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.

Example 3:
    Input: word1 = "cabbba", word2 = "abbccc"
    Output: true
    Explanation: You can attain word2 from word1 in 3 operations.
    Apply Operation 1: "cabbba" -> "caabbb"
    Apply Operation 2: "caabbb" -> "baaccc"
    Apply Operation 2: "baaccc" -> "abbccc"

Constraints:
    1 <= word1.length, word2.length <= 10^5
    word1 and word2 contain only lowercase English letters.

*/

class Solution {
    public boolean closeStrings(String s1, String s2) {
        if (s1.length() != s2.length()) return false; 

        int[] w1 = new int[26]; 
        int[] w2 = new int[26]; 

        for (int i=0; i<s1.length(); i++) { 
            w1[s1.charAt(i)-'a'] += 1; 
            w2[s2.charAt(i)-'a'] += 1; 
        }


        for (int i=0; i<26; i++) {
            if (w1[i] == 0 && w2[i] != 0 || w1[i] != 0 && w2[i] == 0) return false;
        }


        Arrays.sort(w1);
        Arrays.sort(w2);

        for (int i=0; i<26; i++) if (w1[i] != w2[i]) return false;

        return true; 
    }
}
