/*
 @link https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
 @categories (arrays/strings) 

 Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.

 A string is represented by an array if the array elements concatenated in order forms the string.

Example 1:
    Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
    Output: true
    Explanation:
    word1 represents string "ab" + "c" -> "abc"
    word2 represents string "a" + "bc" -> "abc"
    The strings are the same, so return true.

Example 2:
    Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
    Output: false

Example 3:
    Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
    Output: true

Constraints:
    1 <= word1.length, word2.length <= 103
    1 <= word1[i].length, word2[i].length <= 103
    1 <= sum(word1[i].length), sum(word2[i].length) <= 103
    word1[i] and word2[i] consist of lowercase letters.

*/

class Solution {
    public boolean arrayStringsAreEqual(String[] w1, String[] w2) {
        int i = 0, j = 0; 
        int x = 0, y = 0; 
        
        
        while (x < w1.length && y < w2.length) {
            if (w1[x].charAt(i++) != w2[y].charAt(j++)) return false;
            
            if (i == w1[x].length()) {
                x += 1; 
                i = 0; 
            }

            if (j == w2[y].length()) { 
                y += 1; 
                j = 0; 
            }
        }
        
        return x == w1.length && y == w2.length;
    }
}

class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder(); 
        for (String w : word1) sb1.append(w);
        
        
        StringBuilder sb2 = new StringBuilder(); 
        for (String w : word2) sb2.append(w);
        
        return sb1.toString().equals(sb2.toString());
    }
}

