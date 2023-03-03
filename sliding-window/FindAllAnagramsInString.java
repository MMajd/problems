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
        List<Integer> res = new ArrayList<>();

        if(s.length() < p.length()){
            return res;
        }
        int[] ct1 = new int[26];
        int[] ct2 = new int[26];

        int k = p.length();

        for(char it : p.toCharArray()){
            ct1[it-'a']++;
        }

        int i = 0;

        while(i < k){
            ct2[s.charAt(i++)-'a']++;
        }

        k = s.length();
        int j = 0;

        if(Arrays.equals(ct1, ct2)){
            res.add(j);
        }

        while(i < k) {
            ct2[s.charAt(j++)-'a']--;
            ct2[s.charAt(i++)-'a']++;

            if(Arrays.equals(ct1, ct2)){
                res.add(j);
            }
        }
        return res;
    }
}
