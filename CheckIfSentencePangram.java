/*
 @link https://leetcode.com/problems/check-if-the-sentence-is-pangram/  
 @categories (string/map/bit-manipliation)

A pangram is a sentence where every letter of the English alphabet appears at least once.
Given a string sentence containing only lowercase English letters, 
return true if sentence is a pangram, or false otherwise.
 

Example 1:
    Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
    Output: true
    Explanation: sentence contains at least one of every letter of the English alphabet.

Example 2:
    Input: sentence = "leetcode"
    Output: false
     

Constraints:
    1 <= sentence.length <= 1000
    sentence consists of lowercase English letters.
*/ 


class Solution {
    public boolean checkIfPangram(String s) {
        if (s.length() < 26) return false; 
        
        long charset = 0; 
        
        for (int i=0; i<s.length(); i++) {
            charset |= 1 << (long)(s.charAt(i)-1); 
        }
        
        return charset == (1 << 26)-1; 
    }
}
