/*
 @link https://leetcode.com/problems/verifying-an-alien-dictionary
 @categories (hash-table)

 In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.

Example 1:
    Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
    Output: true
    Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:
    Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
    Output: false
    Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

Example 3:
    Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
    Output: false
    Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).

Constraints:
    1 <= words.length <= 100
    1 <= words[i].length <= 20
    order.length == 26
    All characters in words[i] and order are English lowercase letters.
*/

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];  // lower case english letters

        for (int i=0; i<26; i++) {
            map[order.charAt(i)-'a'] = i; // map letter to its alien ordering
        }

        for (int i=0; i<words.length-1; i++) {
            for (int j=0; j<words[i].length(); j++) {
                // shorter words, must came first
                if (j >= words[i+1].length()) return false; 
                if (words[i].charAt(j) != words[i+1].charAt(j)) {
                    int o1 = words[i].charAt(j)-'a';
                    int o2 = words[i+1].charAt(j)-'a';

                    if (map[o2] > map[o1]) break; 
                    return false; 
                }
            }
        }

        return true; 
    }
}
