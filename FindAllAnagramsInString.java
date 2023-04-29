/*
 @link https://leetcode.com/problems/find-all-anagrams-in-a-string/
 @categories (two-pointers/sliding-window)

 Given two strings s and p, return an array of all the start indices of p's anagrams in s. 
You may return the answer in any order. An Anagram is a word or phrase formed by rearranging 
the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:
    Input: s = "cbaebabacd", p = "abc"
    Output: [0,6]
    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
    Input: s = "abab", p = "ab"
    Output: [0,1,2]
    Explanation:
    The substring with start index = 0 is "ab", which is an anagram of "ab".
    The substring with start index = 1 is "ba", which is an anagram of "ab".
    The substring with start index = 2 is "ab", which is an anagram of "ab".

Constraints:
    1 <= s.length, p.length <= 3 * 10^4
    s and p consist of lowercase English letters.
*/ 
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return Collections.emptyList(); 
        }
        List<Integer> answer = new ArrayList<>(s.length()/p.length()); 
        int[] map1 = new int[26]; 
        int[] map2 = new int[26]; 

        for (int i=0; i<p.length(); i++) { 
            map1[p.charAt(i)-'a'] += 1; 
        }

        int n = p.length(); 
        int m = s.length(); 
        int i=0, j=0; 

        while (i<n) {
            map2[s.charAt(i++)-'a'] += 1; 
        }

        if (Arrays.equals(map1, map2)) {
            answer.add(j);
        }
        
        while (i<m) {
            map2[s.charAt(j++)-'a'] -= 1; 
            map2[s.charAt(i++)-'a'] += 1; 

            if (Arrays.equals(map1, map2)) { 
                answer.add(j);
            }
        }

        return answer; 
    }
}
