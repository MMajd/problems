/*
 @link https://leetcode.com/problems/detect-capital/
 @categories (string/array)

 We define the usage of capitals in a word to be right when one of the following cases holds:
All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Given a string word, return true if the usage of capitals in it is right.

Example 1:
    Input: word = "USA"
    Output: true

Example 2:
    Input: word = "FlaG"
    Output: false

Constraints:
    1 <= word.length <= 100
    word consists of lowercase and uppercase English letters.

*/ 

class Solution {
    public boolean detectCapitalUse(String word) {
        char[] chars = word.toCharArray(); 
        int caps = 0; 
        boolean capitalized = Character.isUpperCase(chars[0]);

        for (int i=1; i<chars.length; i++) {
            if (Character.isUpperCase(chars[i])) caps += 1;
            if (caps > 0 && caps < i) return false; 
            else if (caps > 0 && !capitalized) return false; 
        }

        return true; 
    }
}

class Solution {
    public boolean detectCapitalUse(String word) {
        char[] chars = word.toCharArray(); 

        for (int i=1; i<chars.length; i++) {
            boolean prevLower = Character.isLowerCase(chars[i-1]); 
            boolean currUpper = Character.isUpperCase(chars[i]); 

            if (prevLower && currUpper) return false; 
            if (i>1 && !prevLower && !currUpper) return false; 
        }

        return true; 
    }
}
